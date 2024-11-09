package Java8.Streams.MapVsFlatMap;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    public static void main(String[] args) {
        CustomerDAO customer=new CustomerDAO();
        List<Customer> customers=customer.getCustomers();

        customers.stream().map(c->c.getEmail()).forEach(s-> System.out.print(s+" "));
        System.out.println();

        customers.stream().flatMap(u->u.getPhone().stream()).forEach(s-> System.out.print(s+" "));

        String val=null;
        String value=Optional.ofNullable(val).orElse("Value not found");
        System.out.println(value);
    }
}
