package SerializeDeserialize;

import java.io.Serializable;

public class Serializing implements Serializable {
    transient public String name; //if you dont want to serialize any variable, then use transient keyword
    public int age;
    public double salary;

    public Serializing(String name, double salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public Serializing(){
    }

}
