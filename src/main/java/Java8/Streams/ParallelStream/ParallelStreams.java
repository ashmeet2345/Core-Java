package Java8.Streams.ParallelStream;

import java.util.stream.IntStream;

public class ParallelStreams {

    public static void main(String[] args) {
        //It is meant for utilizing multiple core of the processor.
        //We can divide the code into multiple streams with help of parallelStreams that are executed
        //in parallel on seperate cores and the final result is the combination of the individual outcomes
        //The order of execution, however is not under our control.

        long start=System.currentTimeMillis();
        IntStream.rangeClosed(1,1000).forEach(s-> System.out.print(s+" "));
        long end=System.currentTimeMillis();

        System.out.println("\nTime taken by simple stream: "+(end-start)+" milliseconds");

         start=System.currentTimeMillis();
         IntStream.rangeClosed(1,1000).parallel().forEach(s-> System.out.print(s+" "));
         end=System.currentTimeMillis();

         System.out.println("\nTime taken by parallel stream: "+(end-start)+" milliseconds");
    }
}
