package MultiThreading.NewMultiThreading.Synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {
    private int count=0;

    private final ReadWriteLock lock=new ReentrantReadWriteLock();

    private final Lock readLock=lock.readLock();

    private final Lock writeLock=lock.writeLock();

    public void increment(){
        writeLock.lock();
        try{
            count++;
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount(){
        readLock.lock();
        try{
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter counter=new ReadWriteCounter();
        Runnable readCount=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+" count: "+counter.getCount());
                }
            }
        };

        Runnable writeCount=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    counter.increment();
                    System.out.println(Thread.currentThread().getName()+" incremented");
                }
            }
        };

        Thread writeThread=new Thread(writeCount,"WriteThread1");
        Thread readThread=new Thread(readCount,"ReadThread1");
        Thread readThread2=new Thread(readCount,"ReadThread2");

        writeThread.start();
        readThread.start();
        readThread2.start();

        writeThread.join();
        readThread.join();
        readThread2.join();

        System.out.println("Final count: "+counter.getCount());

    }

}
