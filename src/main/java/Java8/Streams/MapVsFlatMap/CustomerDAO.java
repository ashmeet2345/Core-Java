package Java8.Streams.MapVsFlatMap;

import DSA.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerDAO {

    public List<Customer> getCustomers(){
        List<Customer> list=new ArrayList<>();
        list.add(new Customer(1,"Ashmeet","abc@gmail.com", Arrays.asList("9884","42442")));
        list.add(new Customer(2,"Sameer","asdas@gmail.com", Arrays.asList("98312321","3123213")));
        list.add(new Customer(3,"Sachin","sachin@gmail.com", Arrays.asList("412332","312512","1231233")));
        list.add(new Customer(4,"Dhruv","dsadda@gmail.com", Arrays.asList("32131","31213")));

        return list;
    }
}
