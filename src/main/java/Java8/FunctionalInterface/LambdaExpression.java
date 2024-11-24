package Java8.FunctionalInterface;

public class LambdaExpression{
    //Expression through which we can represent an anonymous function
    //It is applicable only for functonal interfaces
    //If the interface only contains one abstract method, then we don't need to use the implements keyword next to class name.

    public static void main(String[] args) {
        //to convert a function interface into a lambda expression (parameters)   ->  {body}

       /* FunctionaInterface face=() -> {
            System.out.println("Hello, I am the only abstract method in the interface.");
        };*/

        /*face.m1();*/

        FunctionaInterface face=(int a, int b) -> {
            System.out.println(a+b);
        };

       // face.sum(20,30);

        multiplication multiplicate=(int a, int b) -> {
            System.out.println(a*b);
        } ;

        multiplicate.multiply(20,30);
    }
}
