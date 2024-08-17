package Streams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Streaming {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
   public static class User{
        private String name;
        private String phone;
        List<String> email;
    }

    public static void main(String[] args) {
        List<User> users=new ArrayList<>();
        users.add(new User("user1","12345",List.of("abc@xyz","cde@fgh")));
        users.add(new User("user2","67890",List.of("kiadf@xyasz","dsacde@fsagh")));
        users.add(new User("user3","13579",List.of("abcasd@xyzasd","cde@fgadsh")));

        List<String> phones = users.stream().map(User::getPhone).collect(Collectors.toUnmodifiableList());
        System.out.println(phones);

        List<String> emails=users.stream().flatMap(u->u.getEmail().stream()).collect(Collectors.toUnmodifiableList());
        System.out.println(emails);

        String s="Ashmeet";

        Map<String,Long> mp= Arrays.stream(s.split(""))
                .collect(groupingBy(Function.identity(),counting()));

        System.out.println(mp);


        IntStream.rangeClosed(1,10).forEach(t-> System.out.println(Thread.currentThread().getName()+":"+t));
        System.out.println("----------------------------------------------------");
        IntStream.rangeClosed(1,10).parallel().forEach(t-> System.out.println(Thread.currentThread().getName()+":"+t));

        IntStream oldstream=IntStream.of(1,2,3,4,5,6);
        Stream<Integer> newStream=oldstream.boxed();

        //boxed helps in converting primitive data type into its respective Wrapper class.
        //oldstream has data with data type of int
        //newStream has data with data type of Integer

        Consumer consume=(t-> System.out.print(t+" "));
        newStream.forEach(consume);

        List<Integer> l= Arrays.asList(1,5,9,15,33,109);
        Integer sum=l.parallelStream().reduce(0,Integer::sum);
        System.out.println("\n"+sum);



    }
}
