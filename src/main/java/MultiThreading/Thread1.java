package MultiThreading;

public class Thread1 extends Thread{

    private int number;
    public Thread1(String threadName){
        super(threadName);
    }
    public Thread1(int number){
        this.number=number;
    }
    //Extending Thread class adds some constraint to our design as java doesn't support multiple classes to be
    //inherited where we can inherit multiple interfaces, so it is better to implement Runnable interface
    //than to inherit Thread class.
    @Override
    public void run(){
        System.out.println("Thread1 " +number+" started");
        for(int i=number*100;i<number*100+99;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("Thread1 " +number+ " completed");
    }
}
