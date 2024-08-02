package Fundamentals;

public class Child extends Parent{

   /* @Override
    public void m1() {
        System.out.println("Child m1");
    }*/



    public static void main(String[] args) {
        Parent p = new Child();
        /*The above method creates a reference of Parent and instance of child.
        * So the method will be called of child class as the instance was made of child.
        * Lets say there was no m1 method in child class, then the jvm first have looked in the child class,
        * then it would've looked in the parent class as it is the super class here.
        * We cannot override static and private methods
        * We can call a static method by the name of class itself only, no need to create an object*/
        p.m1();
    }
}
