package Inheritence;

public class Parent {
    double length;
    double breadth;
    double height;

    Parent(){
        this.length=1;
        this.breadth=1;
        this.height=1;
    }

    Parent(Double num){
        this.length=num;
        this.breadth=num;
        this.height=num;
    }

    Parent(Double length, Double breadth, Double height){
        this.length=length;
        this.breadth=breadth;
        this.height=height;
    }
}
