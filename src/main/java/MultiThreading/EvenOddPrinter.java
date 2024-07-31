package MultiThreading;

import lombok.SneakyThrows;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class EvenOddPrinter {

    private static Object object=new Object();
    private static IntPredicate even=e->e%2==0;
    private static IntPredicate odd=e->e%2!=0;

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.runAsync(()->printNumber(odd));
        CompletableFuture.runAsync(()->printNumber(even));
        Thread.sleep(1000);
    }


    public static void printNumber(IntPredicate condition){
        IntStream.rangeClosed(1,10).filter(condition).forEach(t-> {
            try {
                execute(t);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void execute(int num) throws InterruptedException {
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+":"+num);
            object.notify();
            object.wait();
        }
    }
}
