package Java8.PredefineDFunctionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

        String s1=null;
        String s2="abc";

        //This is how me compare 2 strings if one of them could be null using equals operator only.
        if(Objects.equals(s1,s2)){
            System.out.println("True");
        } else {
            System.out.println("False");
        }


    }

}
