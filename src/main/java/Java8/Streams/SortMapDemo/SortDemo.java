package Java8.Streams.SortMapDemo;

import Java8.Compare.Book;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SortDemo {
    public static void main(String[] args) {

        List<Integer> list= Arrays.asList(20,54,38,95,33,34,72,89,203);

        Collections.sort(list);
        list.stream().forEach(s-> System.out.print(s+" "));
        System.out.println();

        Collections.reverse(list);
        list.stream().forEach(s-> System.out.print(s+" "));
        System.out.println();

        //using streams api - asc list
        list.stream().sorted().forEach(s-> System.out.print(s+" "));
        System.out.println();

        //using streams api - desc list
        list.stream().sorted(Collections.reverseOrder()).forEach(s-> System.out.print(s+" "));

        //using sorted from stream apis for sorting out Books object with respect to their prices.
        List<Book> booksList=new Books().books();
        System.out.println();
        booksList.stream().sorted((o1,o2)-> (int) (o1.getPrice()-o2.getPrice())).forEach(System.out::println);

        //If we want to sort wrt to their names, then
        System.out.println();
        booksList.stream().sorted((o1,o2)->o1.getName().compareTo(o2.getName())).forEach(System.out::println);


    }
}
