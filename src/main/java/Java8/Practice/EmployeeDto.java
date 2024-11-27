package Java8.Practice;

import java.util.LinkedList;
import java.util.List;

public class EmployeeDto {

    List<Employee> employeeList=new LinkedList<>();
    public List<Employee> employees(){
        employeeList.add(new Employee(1,"Ashmeet",20000L));
        employeeList.add(new Employee(2,"Dhruv",30000L));
        employeeList.add(new Employee(3,"Sameer",40000L));
        employeeList.add(new Employee(4,"Sachin",50000L));
        employeeList.add(new Employee(5,"Sanchit",60000L));
        employeeList.add(new Employee(2,"Dhruva",30000L));
        employeeList.add(new Employee(3,"Sameera",40000L));
        employeeList.add(new Employee(4,"Sakshi",50000L));
        employeeList.add(new Employee(5,"Sanvit",60000L));

        return employeeList;
    }
}
