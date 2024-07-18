package generics.comparable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Student Ashmeet=new Student(25,77,"Ashmeet");
        Student Ashish=new Student(24,85,"Ashish");
        Student Sam=new Student(77,72,"Sam");
        Student Yash=new Student(66,66,"Yash");
        Student Sarthak=new Student(51,97,"Sarthak");

        Student[] list={Ashmeet,Ashish,Sam,Yash,Sarthak};
        System.out.println(Arrays.toString(list));
        Arrays.sort(list);
        System.out.println(Arrays.toString(list));

    }
}
