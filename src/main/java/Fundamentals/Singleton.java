package Fundamentals;

public class Singleton {

    public static void main(String[] args) {
        UseSingleton obj1=UseSingleton.getInstance("Ashmeet");
        System.out.println(obj1.getName());
        UseSingleton obj2=UseSingleton.getInstance("Sameer");
        System.out.println(obj2.getName());
    }
}

class UseSingleton{
    private String name;
    private static volatile UseSingleton instance;

    private UseSingleton(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public static UseSingleton getInstance(String name){
        if(instance == null){
            synchronized (UseSingleton.class) {
                if(instance == null){
                    instance=new UseSingleton(name);
                }
            }
        }
        return instance;
    }
}
