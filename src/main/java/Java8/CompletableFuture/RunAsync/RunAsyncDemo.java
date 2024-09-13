package Java8.CompletableFuture.RunAsync;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunAsyncDemo {

    public Void saveEmployees1(File jsonFile) throws ExecutionException, InterruptedException {

        ObjectMapper mapper=new ObjectMapper();
        CompletableFuture<Void> runAsyncFuture1=CompletableFuture.runAsync(new Runnable() {

            @Override
            public void run() {
                try {
                    List<Employee> list=mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                    });
                    System.out.println("Thread: "+Thread.currentThread().getName());
                    list.stream().forEach(System.out::println);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return runAsyncFuture1.get();

        //If we dont use executor, then the Thread will be used from the ForkJoinPool


    }

    public Void saveEmployees2(File jsonFile) throws ExecutionException, InterruptedException {

        ObjectMapper mapper=new ObjectMapper();
        Executor executor= Executors.newFixedThreadPool(5);
        CompletableFuture<Void> runAsyncFuture1=CompletableFuture.runAsync(new Runnable() {

            @Override
            public void run() {
                try {
                    List<Employee> list=mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                    });
                    System.out.println("Thread: "+Thread.currentThread().getName());
                    list.stream().forEach(System.out::println);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        },executor);

        return runAsyncFuture1.get();

        //If we dont use executor, then the Thread will be used from the ForkJoinPool


    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* If we want to run some background task asynchronously and do not want to return anything
        * from that task, then we use completableFuture.runAsync(). It takes Runnable object and
        * returns CompletableFuture<Void>*/

        RunAsyncDemo runAsync=new RunAsyncDemo();
        //Without using custom thread pool (Executor)
       // runAsync.saveEmployees1(new File("employees.json"));

        //With Help of custom thread pool
        runAsync.saveEmployees2(new File("employees.json"));

    }
}
