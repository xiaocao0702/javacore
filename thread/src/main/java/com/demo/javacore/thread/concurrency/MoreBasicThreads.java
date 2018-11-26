package com.demo.javacore.thread.concurrency;

/**
 * Created by cao on 2018/11/11.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for Liftoff");
    }
}
