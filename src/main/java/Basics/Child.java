package Basics;

public interface Child {
    default void m1(){
        System.out.println("I am default method of Child Interface");
    }
}
