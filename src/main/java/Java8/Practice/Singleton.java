package Java8.Practice;

public class Singleton extends MyClone {
    //Lazy Singleton
    private Singleton(){
        if(instance!=null){
            throw new IllegalStateException("Object can't be created using reflection method");
        }
    }

    private static Singleton instance = null;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public static Singleton getInstance(){

        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }
}
