package MultiThreading;

import java.util.concurrent.*;

class CallableTask implements Callable<String> {

    private String name;
    public CallableTask(String name){
        this.name=name;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Hello "+name;
    }
}

public class CallableRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(1);
        Future<String> future=service.submit(new CallableTask("Ashmeet"));

        //future is a promise that there will be a result
        /*Difference between callable and runnable is that, runnable doesn't return a value
        * but callable does.
        * Runnable can be called using a thread class or executorservice
        * but callable can be called only using the executorservice*/
        System.out.println("new Callable executed");
        String s=future.get();
        System.out.println(s);
        System.out.println("Main completed");
    }
}
