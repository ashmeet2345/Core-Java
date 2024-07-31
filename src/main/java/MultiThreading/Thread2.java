package MultiThreading;

public class Thread2 implements Runnable{
    private int number;
    public Thread2(int number){
        this.number=number;
    }
    @Override
    public void run() {
        System.out.println("Thread2 "+number+" started");
        for(int i=number*100;i<number*100+99;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("Thread2 \"+number+\" started");
    }
}
