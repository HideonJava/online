package com.java.online.code;

import com.java.online.lib.SpecialClassLoader;

import java.util.concurrent.CountDownLatch;


public class RunCode implements CodeChain {

    @Override
    public void nextChain(CodeChain codeChain) {}

    @Override
    public void doChain(Request request, Response response, SpecialClassLoader classLoader) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runner runner = new Runner(request, response, classLoader, countDownLatch);
        runner.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
