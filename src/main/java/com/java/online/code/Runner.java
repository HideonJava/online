package com.java.online.code;

import com.java.online.lib.Environment;
import com.java.online.lib.SpecialClassLoader;
import com.java.online.util.Constants;
import com.java.online.util.DomRender;
import com.java.online.util.ReflectUtil;

import java.io.Reader;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class Runner extends Thread {

    private Request request;
    private Response response;
    private SpecialClassLoader classLoader;
    private CountDownLatch countDownLatch;

    public Runner(Request request, Response response, SpecialClassLoader classLoader,
                  CountDownLatch countDownLatch) {
        this.request = request;
        this.response = response;
        this.classLoader = classLoader;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        RunInfo info = ReflectUtil.getRunInfo(request.getCodeId(), classLoader);
        long runtime = System.currentTimeMillis();
        Timer timer = new Timer();
        Counter counter = new Counter(this);
        timer.schedule(counter,0,1000);
        MemoryListener listener = new MemoryListener();
        Environment.on(request.getInput());
        listener.start();
        try {
            info.getMain().invoke(info.getTarget(),
                    (Object) request.getArgs().split("\\s+"));
            runtime = System.currentTimeMillis() - runtime;
            response.setResult(DomRender.result(listener.getMax(), runtime));
        } catch (Exception e) {
            response.setCode(Response.FAILURE);
            if (e.getCause() != null) {
                onThrowable(e.getCause(), response);
                e.printStackTrace();
            } else {
                response.setResult(DomRender.error(Constants.SYSTEM_ERROR_MESSAGE));
            }
        } finally {
            listener.setListener(false);
            Environment.off();
        }
        if(!counter.isCancel()){
            timer.cancel();
        }
        countDownLatch.countDown();
    }

    private void onThrowable(Throwable throwable, Response response) {
        String message = throwable.getMessage();
        String className = throwable.getClass().getSimpleName();
        String readerName = Reader.class.getName();
        String scannerName = Scanner.class.getName();
        String header = throwable.getStackTrace()[0].getClassName();
        if(throwable instanceof ThreadDeath){
            response.setResult(DomRender.error(Constants.TIMEOUT_ERROR_MESSAGE));
        }else{
            if (throwable instanceof SecurityException) {
                int line = throwable.getStackTrace()[3].getLineNumber();
                response.setResult(DomRender.error(line, message));
            } else if (header.equals(readerName) || header.equals(scannerName)) {
                response.setResult(DomRender.error(Constants.SCANNER_ERROR_MESSAGE));
            } else {
                int line = throwable.getStackTrace()[0].getLineNumber();
                response.setResult(DomRender.error(line, message, className));
            }
        }

    }

    public static class Counter extends TimerTask {

        private int count;
        private Thread task;

        public Counter(Thread task){
            this.task = task;
            this.count = Constants.TIMEOUT;
        }

        @Override
        public void run() {
            if(isCancel()) {
                task.stop();
                this.cancel();
            }
            count--;
        }

        public boolean isCancel(){
            return count == 0;
        }
    }

    public static class MemoryListener extends Thread {
        private long init;
        private long max;
        private boolean isListener;

        public MemoryListener() {
            this.max = useMemory();
            this.init = this.max;
            this.isListener = true;
        }

        private long useMemory() {
            return Runtime.getRuntime().totalMemory()
                    - Runtime.getRuntime().freeMemory();
        }

        public boolean isListener() {
            return isListener;
        }

        @Override
        public void run() {
            while (isListener) {
                long temp = useMemory();
                if (temp > max) {
                    max = temp;
                }
            }
        }

        public void setListener(boolean listener) {
            isListener = listener;
        }

        public long getMax() {
            return max - init;
        }

        public void setMax(long max) {
            this.max = max;
        }
    }
}
