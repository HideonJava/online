package com.java.online;

import java.util.concurrent.CountDownLatch;

class Runner extends Thread{

    public int init = 3;
    public int count = 3;
    private  String name;
    private CountDownLatch latch;

    public Runner(String name,CountDownLatch latch){
        this.name = name;
        this.latch = latch;
    }

    @Override
    public void run() {

        while (count > 0){
            System.out.println(name + ":" + (init - count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }

        latch.countDown();
    }
}


public class App {

    public static void main(String[] args) {

        int threadCount = 10;

        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount ; i++) {
            new Runner("线程-"+(i+1),latch).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End");
    }

}
