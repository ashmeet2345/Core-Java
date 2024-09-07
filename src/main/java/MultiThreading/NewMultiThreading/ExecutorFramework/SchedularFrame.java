package MultiThreading.NewMultiThreading.ExecutorFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedularFrame {
    public static void main(String[] args) {
        ScheduledExecutorService schedular=Executors.newScheduledThreadPool(2);
        schedular.scheduleAtFixedRate(()-> System.out.println("Hello"),5,5, TimeUnit.SECONDS);
        schedular.schedule(()->{
            System.out.println("Initiating shutdown");
            schedular.shutdown();
        },20,TimeUnit.SECONDS);
    }


}
