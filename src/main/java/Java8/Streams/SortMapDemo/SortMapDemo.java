package Java8.Streams.SortMapDemo;

import Java8.Compare.Book;

import java.util.*;

public class SortMapDemo {

    public static void main(String[] args) {
        Map<String,Integer> mp=new HashMap<>();
        mp.put("one",1);
        mp.put("two",2);
        mp.put("nine",9);
        mp.put("six",6);
        mp.put("seven",7);

        //If we wanted our map to be sorted, we could easily have used TreeMap

        //Traditional way of sorting a map is as follows
        List<Map.Entry<String,Integer>> entries=new ArrayList<>(mp.entrySet());
        /*Collections.sort(entries, (o1,o2)->{
            return o1.getValue()-o2.getValue();
        });

        for(Map.Entry<String,Integer> entry:entries){
            System.out.println(entry.getKey()+","+entry.getValue());
        }*/

        //Modern way with the help of streams apis
       // mp.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(i-> System.out.println(i.getKey()+","+i.getValue()));


        mp.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(i -> System.out.println(i.getKey()+","+i.getValue()));
        //Now we will sort the books based on their price using maps.
        Books bookList=new Books();
        Map<Book,Integer> map=new HashMap<>();
        int i=20;
        for(Book book:bookList.books()){
            i+=1;
            map.put(book,i*2);
        }

        map.entrySet().stream().sorted(Map.Entry.comparingByKey(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int) (o1.getPrice()-o2.getPrice());
            }
        })).forEach(s-> System.out.println(s.getKey()+"----"+s.getValue()));
    }
}
