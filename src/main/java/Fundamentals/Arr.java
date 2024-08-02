package Fundamentals;

public class Arr {
    public static void main(String[] args) {
       Arr obj=new Arr();
        obj.greeting();
        ABC();
    }

    static void ABC(){
        Arr main=new Arr();
        main.greeting();
    }

    void greeting(){
        System.out.println("Hey");
    }
}

class A{
    final int number= 1;
    String name=null;
    public A(String name){
        this.name=name;
    }
}
