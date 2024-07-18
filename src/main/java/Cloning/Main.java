package Cloning;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Human h1=new Human("Ashmeet",24);
        Human h2= (Human)h1.clone();
        System.out.println(Arrays.toString(h1.arr));
        System.out.println(Arrays.toString(h2.arr));
        h2.arr[0]=55;
        System.out.println(Arrays.toString(h1.arr));
        System.out.println(Arrays.toString(h2.arr)); //this is deep copy

    }
}
