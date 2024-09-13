package Java8.Streams.Debugging;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> getEmployees(){
        List<Employee> list=new ArrayList<>();
        list.add(new Employee(1,"Ashmeet","ash@xyz",73500.00F));
        list.add(new Employee(2,"Sarthak","sar@gmail",50000.00F));
        list.add(new Employee(3,"Anirudh","ani@xyz",28900.00F));
        list.add(new Employee(4,"Yash","yash@yahoo",95000.00F));
        list.add(new Employee(5,"Param","par@hotmail",72000.00F));
        list.add(new Employee(6,"Sam","sam@reddit",88000.00F));
        list.add(new Employee(7,"Sameer","sameer@xyz",84100.00F));
        list.add(new Employee(7,"Sameer","sameer@xyz",89900.00F));

        return list;
    }
}
