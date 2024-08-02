package Fundamentals;

import ExceptionHandling.CustomException;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class CustomImmutable {

    private final String name;
    private final int age;
    private final Date dob;
    private final List<String> mobile;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getDob() {
        return (Date) dob.clone();
    }

    public List<String> getMobile() {
        return Collections.unmodifiableList(mobile);
    }

    @Override
    public String toString() {
        return "CustomImmutable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", mobile=" + mobile +
                '}';
    }

    public CustomImmutable(String name, int age, Date dob, List<String> mobile) {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.mobile = mobile;
    }

    public static void main(String[] args) {
        Date dob = new Date();
        CustomImmutable emp=new CustomImmutable("Ashmeet",24,dob, List.of("123","456"));
        System.out.println(emp);

    }
}
