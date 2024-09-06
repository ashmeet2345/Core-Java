package MultiThreading.NewMultiThreading.Synchronization;

import MultiThreading.NewMultiThreading.B;
import MultiThreading.Thread1;

public class MainLocks {
    public static void main(String[] args) {
        BankAccount account=new BankAccount();
        Runnable task=new Runnable() {
            @Override
            public void run() {
                account.withdrawWithLocks(50);
            }
        };
        Thread t1=new Thread(task,"Thread 1");
        Thread t2=new Thread(task,"Thread 2");
        t1.start();
        t2.start();
    }
}
