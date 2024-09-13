package Basics;

public class A {

    public static void main(String[] args) {
        B obj=new C();

    }
}

class B{
    int a=10;
    public void m1(){
        System.out.println("Print F of m1 -- class B");
    }

    public static void m1Static(){
        System.out.println("Print F of Static m1 -- class B");
    }
}

class C extends B {
    int a=5;
    public void m1(){
        System.out.println("Print F of m1 -- class C");
    }

    public static void m1Static(){
        System.out.println("Print F of Static m1 -- class B");
    }
}
