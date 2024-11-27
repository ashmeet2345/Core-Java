package Java8.MarkerInterface;

public class Dto {

    public void display(Child child){
        if(child instanceof Marker){
            System.out.println("Child class has implemented marker interface");
        } else {
            System.out.println("Child class did not implement marker interface");
        }
    }
}
