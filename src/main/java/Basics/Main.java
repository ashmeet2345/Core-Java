package Basics;

public class Main implements Parent,Child{
    public static void main(String[] args) {
        Main m=new Main();
        m.m1();
    }

    @Override
    public void m1() {
        Parent.super.m1();
    }

}
