package Inheritence;

class Child extends Parent{
    double weight;

    Child(){
        this.weight=1;
    }

    Child(Double l,Double b,Double h,Double w){
        super(l,b,h);
        this.weight=w;
    }
}