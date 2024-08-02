package Fundamentals;

public class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode(){
        return id;
    }

    public boolean equals(Object obj) {
        Employee emp=(Employee)obj;
        return emp.id == this.id && this.name.equals(emp.name);
    }

    public static void main(String[] args) {
        Employee emp1=new Employee(20,"Ashmeet");
        Employee emp2=new Employee(20,"Sanchit");

        System.out.println("Hashcode equal: "+(emp1.hashCode()==emp2.hashCode()));
        System.out.println("is Equals() same: "+emp1.equals(emp2));
    }
}
