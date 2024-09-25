package Basics;

public interface Parent {
    default void m1(){
        System.out.println("I am default method of Parent interface");
    }
}
