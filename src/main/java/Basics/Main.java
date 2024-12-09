package Basics;

public class Main implements Parent,Child{

    public static void main(String[] args) {


    }

    @Override
    public void m1() {
        Parent.super.m1();
    }

}
