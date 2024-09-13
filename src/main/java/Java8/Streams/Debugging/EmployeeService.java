package Java8.Streams.Debugging;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {

    public static void main(String[] args) {
        EmployeeDAO data=new EmployeeDAO();
        List<Employee> employeeList=data.getEmployees();

        List<String> names=employeeList.stream()
                .filter(s -> s.getSalary() > 30000.00F)
                .map(Employee::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(names);

        //We can debugg streams as follows
            /*1- place debugging breakpoint at point from where stream api starts executing.
            (in above example, line 12)
            * 2 - When debugging starts, to the left of 'Threads & Variables' there are 3 points to see more preferences.
            * 3 - Check using the Trace current stream chain in the last of that list and voila.
            */
    }
}
