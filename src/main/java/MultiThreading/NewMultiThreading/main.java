package MultiThreading.NewMultiThreading;

public class main {
    public static void main(String[] args) {
        World_1 world=new World_1();
        Thread thread=new Thread(world);
        thread.start();
        for(int i=0;i<100000;i++){
            System.out.println("Hello");
        }

    }
}
