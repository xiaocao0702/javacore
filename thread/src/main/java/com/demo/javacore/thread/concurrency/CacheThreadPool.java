package com.demo.javacore.thread.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cao on 2018/11/11.
 * 在程序执行过程中创建与所需数量相同的线程
 * 在它回收旧线程时停止创建新线程
 * 是合理的Executor首选
 */
public class CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
        System.out.println("Waiting For LiffOff.");
    }
}
