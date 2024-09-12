package Java8.PredefineDFunctionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo /*implements Predicate<Integer>*/ {
    //Predicate is used for the conditional checks only. So its return type is boolean


    public static void main(String[] args) {
       /* Predicate<Integer> predicate=new PredicateDemo();
        System.out.println(predicate.test(50));     */

        Predicate<Integer> predicate=(number)->{
            if(number > 3) return true;
            else return false;
        };

        System.out.println(predicate.test(50));


        //Predicate is used in filter method of streams api.

        List<Integer> list= Arrays.asList(1,2,3,4,5,6);
        list.stream().filter(predicate).forEach(s -> System.out.print(s+" "));
    }
/*
    @Override
    public boolean test(Integer integer) {
        if(integer % 2 == 0)
            return true;
        else
            return false;
    }*/
}
