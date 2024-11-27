package Java8.Practice;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Practice {

    static EmployeeDto dto=new EmployeeDto();;

    public int sum(int a, int b){
        return a+b;
    }

    public int sum(String a, String b){
        return a.length()+b.length();
    }

    public static Map.Entry<Float,List<String>> dynamicNthSalary(int n, List<Employee> employees){
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getSalary, Collectors.mapping(Employee::getName,Collectors.toList())))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList())
                .get(n-1);
    }

    public static void main(String[] args) throws InterruptedException {
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

        List<Integer> list= Arrays.asList(1,5,3,12,23,331,512,75,912,421,4421,1233,3125,4554,2334);
        long result=list.stream().reduce(1,(a,b)->a+b);
        System.out.println(result);

        List<String> listOfString= Arrays.asList("Ashmeesadasd","sadasdasd","dasdasdasdasdasd");
        String ele=listOfString.stream().filter(s->s.length()>5).findAny().get();

        System.out.println(ele);
        String longest=listOfString.stream().reduce("",(a,b)->a.length()>b.length()?a:b);
        System.out.println(longest);

        list.stream().skip(3).limit(9).forEach(s-> System.out.print(s+" "));

        long start=System.currentTimeMillis();
        ExecutorService executor= Executors.newFixedThreadPool(9);
        for(int i=1;i<=5;i++){
            int finalI=i;
            executor.submit(()->{
                System.out.println(finalI*finalI);
            });
        }
        executor.shutdown();
        while(!executor.awaitTermination(1, TimeUnit.SECONDS)){
        }

        System.out.println("Time Taken: "+(System.currentTimeMillis()-start));


        String str="iloveashmtlleet";
        String[] strList=str.split("");
        List<String> listt=Arrays.stream(strList)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream().filter(s->s.getValue()>1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(listt);

        //First Unique Element of a string
       String firstUniqueElement = Arrays.stream(strList)
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(s->s.getValue()==1)
                .findFirst()
                .get()
                .getKey();

       int[] arr={5,11,23,51,91,44,31,01};
        List<String> listtt= Arrays.stream(arr)
                       .boxed()
                               .map(s->s+"")
                                       .filter(s->s.endsWith("1"))
                                               .collect(Collectors.toList());

        List<Employee> employees=dto.employees();
        System.out.println(dynamicNthSalary(3,employees));

        System.out.println(firstUniqueElement);
        System.out.println(listtt);


    }
}
