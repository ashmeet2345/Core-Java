package MultiThreading;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultipleCallableRunner {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service= Executors.newFixedThreadPool(3);

        List<CallableTask> list=List.of(new CallableTask("Sanchit"),new CallableTask("Sameer"));

        List<Future<String> > results=service.invokeAll(list);

        for(Future<String> result:results){
            System.out.println(result.get()+" ");
        }
        service.shutdown();
    }
}
