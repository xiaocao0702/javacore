package com.demo.javacore.thread.concurrency;

/**
 * Created by cao on 2018/11/11.
 */
public class BasicThreads {
    public static void main(String[] args) {
        LiftOff liftOff = new LiftOff();
        Thread t = new Thread(liftOff);
        t.start();
        System.out.println("Waiting for Liftoff");
    }
}
