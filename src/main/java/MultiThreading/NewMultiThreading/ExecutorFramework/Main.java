package MultiThreading.NewMultiThreading.ExecutorFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long milliSeconds=System.currentTimeMillis();
        ExecutorService executor=Executors.newFixedThreadPool(3);
        //Using executor service, we don't have to make threads by ourselves. Executor service will take care of it for us.
        //Executor service also reuses threads. So instead of creating plenty of threads, executor uses only some and
        //then reuse them again.
        for(int i=1;i<=10;i++){
            int finalI = i;
            executor.submit(()->{
                long result=factorial(finalI);
                System.out.println(result);
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //we can return something using executorService as a Future<?>

        System.out.println("Total time: "+(System.currentTimeMillis()-milliSeconds));

        ExecutorService service=Executors.newSingleThreadExecutor();
        Future<Integer> result=service.submit(()->42);
        //here callable is being used because we are returning 42.
        //If we don't want to return anything then we will use Runnable, else we use callable.
        System.out.println(result.get());
        service.shutdown();


        ExecutorService execute=Executors.newSingleThreadExecutor();
        Callable<?> callable=()-> "Hellow";
        Future<?> future=execute.submit(callable);
        System.out.println(future.get());
        execute.shutdown();

        ExecutorService execute2=Executors.newCachedThreadPool();
        //New cached thread pool is required when we don't know the size of a thread pool, and want our thread
        //to be efficiently ran. So it is upto the cached thread pool.
        Callable<Integer> a=()->1;
        Callable<Integer> b=()->2;
        Callable<Integer> c=()->3;
        List<Callable<Integer> > callables= Arrays.asList(a,b,c);
        List<Future<Integer> > futures=execute2.invokeAll(callables);
        futures.stream().forEach(s-> {
            try {
                System.out.print(s.get()+" ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        execute2.shutdown();
    }

    private static long factorial(int num){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long fact=1;
        for(int i=1; i<=num;i++){
            fact*=i;
        }
        return fact;
    }
}
