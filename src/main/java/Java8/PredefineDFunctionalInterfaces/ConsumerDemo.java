package Java8.PredefineDFunctionalInterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo /*implements Consumer<Integer>*/ {
   /* @Override
    public void accept(Integer integer) {
        System.out.println("Printing: "+integer);
    }*/

    public static void main(String[] args) {
        //As Consumer is a functional interface, we will use the lamda expression
        //Consumer does the execution in the body, but it does not return anything. And its method accepts
        //one parameter of Generic type.
        Consumer<Integer> consumer=(i)->{
            System.out.println("Printing "+i);
        };

        consumer.accept(20);

        //consumer works in the forEach method of streams api
        //Example below

        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7);
        Consumer<Integer> listConsumer=(i)->{
            System.out.println("Printing "+i);
        };

        list.stream().forEach(listConsumer);
    }
}
