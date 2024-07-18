package AbstractClasses;

public abstract class Parent {
    int age;

    public Parent(int age) {
        this.age=age;
    }

    abstract void career();
    abstract void partner();

    static void hello(){
        System.out.println("Hello static method");
    }

    void normal(){
        System.out.println("Hello normal method  ");
    }
    //cant create object of abstract classes.
    //Not even abstract constructors

}
