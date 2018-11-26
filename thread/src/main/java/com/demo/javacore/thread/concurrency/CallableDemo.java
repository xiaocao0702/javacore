package com.demo.javacore.thread.concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by cao on 2018/11/11.
 */
class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    private int fib(int n) {
        if(n == 0 || n == 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public String call() throws Exception {
        return "result of TaskWithResult: id = " + id + ", fib(" + id + ") = "  + fib(id);
    }
}
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList();
        for (int i = 0; i < 10; i++)
            results.add(exec.submit(new TaskWithResult(i)));
//        results.forEach(fs -> {
//            try {
//                System.out.println(fs.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                System.out.println(e);
//                return;
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//                System.out.println(e);
//            } finally {
//                exec.shutdown();
//            }
//        });
    }
}
