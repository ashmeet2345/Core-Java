package Java8.Compare;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookService {

    public List<Book> getBooksInSortedOrder(){


        List<Book> list=new BookDao().books();
        //As Comparator<T> is a functional interface, we will use lambda expression
        Collections.sort(list, (o1,o2) -> (int) (o1.getPrice()-o2.getPrice()));

        return list;
    }

    public static void main(String[] args) {
      List<Book> books= new BookService().getBooksInSortedOrder();
      for(Book book:books){
          System.out.println(book.getId()+" "+book.getName()+" "+book.getPrice());
      }
    }
}
/*

class MyComparator implements Comparator<Book>{

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getPrice().compareTo() > o2.getPrice()?1:0;
    }
}*/
