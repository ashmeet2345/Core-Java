package Java8.Practice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee implements Comparable<Employee> {

    private int id;
    private String name;
    private float salary;

    @Override
    public int compareTo(Employee o) {
        if(id==o.id)
            return 0;
        else if(id < o.id)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
