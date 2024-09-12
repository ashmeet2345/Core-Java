package Java8.Streams.SortMapDemo;

import Java8.Compare.Book;

import java.util.ArrayList;
import java.util.List;

public class Books {

    public List<Book> books(){
        List<Book> list=new ArrayList<>();
        list.add(new Book(1,"Brief History of time",345.32F));
        list.add(new Book(2,"The parable of the pipeline",850.44F));
        list.add(new Book(3,"Death By Black Hole",3500.00F));
        list.add(new Book(4,"Answers to the big questions",590.93F));
        list.add(new Book(5,"Environment, Ecology and Biodiversity",480.77F));
        return list;
    }
}
