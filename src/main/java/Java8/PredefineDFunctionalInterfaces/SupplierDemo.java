package Java8.PredefineDFunctionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SupplierDemo /*implements Supplier<String>*/ {
    /*@Override
    public String get() {
        return "Hello World!";
    }*/
    //This functional interface doesn't have any argument but expects one return type.

    public static void main(String[] args) {
        /*Supplier<String> supplier=new SupplierDemo();
        System.out.println(supplier.get());*/

        Supplier<String> supplier=()->{
            return "Hello world!";
        };
        System.out.println(supplier.get());
        //In Streams api, it is used in orElse method

        List<String> list= Arrays.asList();
        System.out.println(list.stream().findAny().orElseGet(supplier));



    }

}
