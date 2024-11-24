package Java8.Practice;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Practice {

    public static void main(String[] args) {
        Practice p=new Practice();
        Lambda l=(a,b) -> {
            return a+b;
        };
        System.out.println(l.sum(20,30));

        Supplier<String> supplier=()->{
            return "Not Found";
        };

        System.out.println(supplier.get());

        Consumer<Integer> consumer=(i)->{
            System.out.println(i*20);
        };
        consumer.accept(20);

        Map<Integer, String> mp=new HashMap<>();
        mp.put(1,"Ashmeet");
        mp.put(2,"Sanchitt");
        mp.put(3,"Sarthak");
        mp.put(4,"Param");

        mp.entrySet().stream().sorted(Map.Entry.comparingByKey((o1,o2)->{
            if(o1==o2)
                return 0;
            else if(o1 < o2)
                return 1;
            else
                return -1;
        })).forEach(s-> System.out.println(s.getKey()+","+s.getValue()));

        List<Integer> list= Arrays.asList(10,10,10,10,10,10);
        long result=list.stream().reduce(1,(a,b)->a*b);
        System.out.println(result);

        List<String> listOfString= Arrays.asList("Ashmeesadasd","sadasdasd","dasdasdasdasdasd");
        String longest=listOfString.stream().reduce("",(a,b)->a.length()>b.length()?a:b);
        System.out.println(longest);
    }
}
