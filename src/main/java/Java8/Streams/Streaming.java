package Java8.Streams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
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

    static int cntWays(int arr[], int n)
    {
        // if length of array is 1
        // answer will be 0 as we have
        // to split it into two
        // non-empty halves
        if (n == 1)
        {
            return 0;
        }

        // variables to store total sum,
        // current sum and count
        int tot_sum = 0, sum = 0, ans = 0;

        // finding total sum
        for (int i = 0; i < n; i++)
        {
            tot_sum += arr[i];
        }

        // checking if sum equals total_sum/2
        for (int i = 0; i < n - 1; i++)
        {
            sum += arr[i];
            if (sum == tot_sum / 2)
            {
                ans++;
            }
        }

        return ans;
    }

    public void occurenceOfEachCharacter(String str){
        Map<String, Long> mp=Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(mp);
    }

    public void allDuplicateElementsFromAGivenString(String str){
        List<String> list=Arrays.stream(str.split(""))
                .collect(groupingBy(identity(), counting()))
                .entrySet().stream().filter(k->k.getValue()>1).map(Map.Entry::getKey).collect(Collectors.toList());

        System.out.println(list);
    }

    public void findFirstNonRepeatingElement(String str){
        String st=Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(s->s.getValue()==1)
                .findFirst().get().getKey();
        System.out.println(st);
    }

    public void find2ndHightValueInArray(int[] arr){
        Integer res=Arrays.stream(arr).boxed()
                .sorted(Collections.reverseOrder())
                .skip(1)
                .findFirst()
                .get();
        System.out.println(res);
    }

    public void longestStringInAGivenArray(String[] arr){
        String str=Arrays.stream(arr)
                .reduce((word1,word2)->word1.length()>word2.length()?word1:word2)
                .get();
        System.out.println(str);
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
                .collect(groupingBy(identity(),counting()));

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

        users.parallelStream().forEach(user -> System.out.println(user));

        Map<Integer,Integer> map=new HashMap<>();
        map.put(1,2);
        map.put(2,5);
        map.put(3,3);

        System.out.println("Maximum value of the map is: ");
        System.out.println(map.entrySet().stream().mapToInt(x->x.getValue()).max().getAsInt());

        Stream<Double> randoms = Stream.generate(Math::random).limit(5);
        randoms.forEach(consume);

        System.out.println();
        List<String> words = Arrays.asList("java", "streams", "are", "powerful");
        words.stream().map(t -> t.toUpperCase()).forEach(x -> System.out.print(x+" "));
        System.out.println();

        List<Integer> list=Arrays.asList(9,4,1,6,3,2,7,8,5);
        //if we want to print only limited elements from the list, then we use skip and limit

        list.stream().skip(1).limit(5).sorted().forEach(st-> System.out.print(st+" "));
        //above 1 mentioned in skip method means, skip the first element of the list,
        //and 5 mentioned in the limit method means, after skipping the 1st index, we count till 5th
        //element and print the elements.
        //If we want to skip first 2 elements, then we mention 2 in the parameter of the skip method.

        Streaming streaming=new Streaming();
        System.out.println();
        streaming.occurenceOfEachCharacter("ashmeetsingh");

        System.out.println();
        streaming.allDuplicateElementsFromAGivenString("ashmeetsingh");

        System.out.println();
        streaming.findFirstNonRepeatingElement("ashmeetsingh");

        System.out.println();
        streaming.find2ndHightValueInArray(new int[]{2,11,99,32,48,57,69});

        System.out.println();
        streaming.longestStringInAGivenArray(new String[]{"abc","asdasd","dasd","asasdfjjweieijfjn"});
    }


}
