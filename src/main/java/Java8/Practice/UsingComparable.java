package Java8.Practice;

import java.util.Collections;
import java.util.List;

public class UsingComparable{

    public static void main(String[] args) {
        EmployeeDto dto=new EmployeeDto();
        List<Employee> list=dto.employees();
        //Collections.sort(list);
        Collections.sort(list,new NameComparator());
        System.out.println(list);

    }

}
