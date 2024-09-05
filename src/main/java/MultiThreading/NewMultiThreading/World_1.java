package MultiThreading.NewMultiThreading;

public class World_1 implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<100000;i++){
            System.out.println("World");
        }
    }
}
