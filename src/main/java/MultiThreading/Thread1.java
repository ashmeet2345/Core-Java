package MultiThreading;

public class Thread1 extends Thread{
    public Thread1(String threadName){
        super(threadName);
    }
    //Extending Thread class adds some constraint to our design as java doesn't support multiple classes to be
    //inherited where we can inherit multiple interfaces, so it is better to implement Runnable interface
    //than to inherit Thread class.
    @Override
    public void run(){
        for(int i=0;i<5;i++){
            System.out.println("Inside "+currentThread().getName()+" "+i);
        }
    }
}
