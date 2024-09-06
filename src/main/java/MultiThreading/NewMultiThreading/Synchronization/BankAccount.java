package MultiThreading.NewMultiThreading.Synchronization;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance=100;

    private final Lock lock=new ReentrantLock(); //this is explicit lock.

    public synchronized void withdrawWithSynchronized(int amount){
        System.out.println(Thread.currentThread().getName()+" attempting to withdraw "+amount);
        if(balance>=amount){
            System.out.println(Thread.currentThread().getName()+" Proceeding with withdrawal ");
            try {
                Thread.sleep(10000);
                //Here the point is, 2nd thread will have to wait 10 seconds for the completition of the
                //execution of first thread.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            balance-=amount;
            System.out.println(Thread.currentThread().getName()+" Completed Withdrawal. Remaining Balance: "+balance);
        } else {
            System.out.println(Thread.currentThread().getName()+" Insufficient balance ");
        }
    }

    public void withdrawWithLocks(int amount){
        System.out.println(Thread.currentThread().getName()+" attempting to withdraw "+amount);
        try {
            if(lock.tryLock(4000, TimeUnit.MILLISECONDS)){
                //here the tryLock will check if the lock is available within the mentioned time limit, then
                //it will not throw any exception else, if the wait time is over and still the lock is not free,
                //it will throw an exception
                if(balance>=amount){
                    try {
                        System.out.println(Thread.currentThread().getName()+" Proceeding with withdrawal ");
                        Thread.sleep(3000);
                        balance-=amount;
                        System.out.println(Thread.currentThread().getName()+" Completed Withdrawal. Remaining Balance: "+balance);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                    }

                } else {
                    System.out.println(Thread.currentThread().getName()+" Insufficient balance ");
                }
            } else {
                System.out.println(Thread.currentThread().getName()+" could not acquire the lock. Will try again later. ");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
