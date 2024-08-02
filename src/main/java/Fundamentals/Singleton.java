package Fundamentals;

public class Singleton {

    public static void main(String[] args) {
        UseSingleton obj1=UseSingleton.getInstance("Ashmeet");
        System.out.println(obj1.name);
        UseSingleton obj2=UseSingleton.getInstance("Sameer");
    }
}

class UseSingleton{
    String name="";
    private UseSingleton(String name){
        this.name=name;
    }
    private static UseSingleton instance;
    public static UseSingleton getInstance(String name){
        if(instance == null){
            instance=new UseSingleton(name);
        }
        return instance;
    }
}
