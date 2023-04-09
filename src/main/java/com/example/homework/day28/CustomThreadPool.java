package com.example.homework.day28;


public class CustomThreadPool {
    private final CustomBlockingQueue<Runnable> queue;
    private final Thread[] workers;
    private boolean isShutDown;

    private class Worker extends Thread {
        private final String name;

        public Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (!isShutDown) {
                try {
                    System.out.println(name + " start...");
                    queue.take().run();
                    System.out.println(name + " finish!");
                } catch (InterruptedException e) {
                    System.out.println(name + " Interrupted");
                }
            }
        }
    }

    public CustomThreadPool(int numThreads) {
        this.queue = new CustomBlockingQueue<>(numThreads);
        this.workers = new Thread[numThreads];
        this.isShutDown = false;
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("Thread " + i);
            workers[i].start();
        }
    }

    public void addTask(Runnable task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutDown() {
        this.isShutDown = true;
        for (Thread thread: workers) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) {
        CustomThreadPool customThreadPool = new CustomThreadPool(3);
        customThreadPool.addTask(() -> System.out.println("First Sample Task"));
        customThreadPool.addTask(() -> System.out.println("Second Sample Task"));
        customThreadPool.addTask(() -> System.out.println("Third Sample Task"));
        customThreadPool.addTask(() -> System.out.println("Forth Sample Task"));
    }
}
