package com.demo.javacore.thread.concurrency;

/**
 * Created by cao on 2018/11/11.
 */
public class MainThread {
    public static void main(String[] args) {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
        System.out.println("LiftOff Done...");
    }
}
