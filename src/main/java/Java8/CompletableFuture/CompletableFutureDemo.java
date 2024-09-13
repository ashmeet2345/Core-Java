package Java8.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureDemo {
    //it is used for Async programming in Java. When the task is completed, it notifies the main
    //thread (whether the task completed or failed)

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Demertis of using Futures (reason why using completealbeFuture is good)
        /*
        * It cannot be manually completed
        * Multiple futures cannot be chained together
        * We cannot combine multiple futures together
        * No proper Exception Handling Mechanish*/

        //Lets take an example of ExecutorService first

        ExecutorService service= Executors.newFixedThreadPool(10);
        Future<List<Integer>> future=service.submit(()->{
            System.out.println("Thread: "+Thread.currentThread().getName());
            Thread.sleep(10000);
            return Arrays.asList(1,2,3,4,5,6,7,8,9);
        });
        List<Integer> list=future.get();
        System.out.println(list);

        //Now if instead of returning a list in the submit method, we do some api call, and it delays
        //the response by 1 or 2 min (Lets say), the the main thread will have to wait for the
        //same delayed time. And we cannot complete the above future manually.

        //Now lets say we have multiple futures as future1, future2, future3 ... .If we want to combine
        //those futures, we won't be able to as there's no method to do so.


        CompletableFuture<String> completableFuture=new CompletableFuture<>();
        //If we use get method using completable future, it will still block the main thread.
        //But using complete method, we can stop the executon of completable future, if it is taking
        //longer time.


    }
}
