package Cloning;

public class Human implements Cloneable{
    String name;
    int age;
    int[] arr;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.arr=new int[]{3,4,5,6,7,1};
    }

    public Human(Human other){
        this.name=other.name;
        this.age=other.age;
    }

    public Object clone() throws CloneNotSupportedException{
        //this is shallow copying
       Human twin=(Human)(super.clone());

       twin.arr=new int[twin.arr.length];
       System.arraycopy(this.arr, 0, twin.arr, 0, this.arr.length);
       return twin;
    }
}
