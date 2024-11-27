package Java8.Practice;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Singleton obj1= Singleton.getInstance();
        System.out.println(obj1.hashCode());
        //Breaking into singleton class with help of
        //1st - Clonning method
        /*Singleton obj2= (Singleton) obj1.clone();
        System.out.println(obj2.hashCode());*/

        //2nd - Reflection method
        /*Singleton instance=null;
        Constructor[] constructors=Singleton.class.getDeclaredConstructors();
        for(Constructor constructor:constructors){
            constructor.setAccessible(true);
            instance=(Singleton) constructor.newInstance();
        }
        System.out.println(instance.hashCode());*/

    }
}
