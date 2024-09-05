package MultiThreading.NewMultiThreading;

public class Yield extends Thread{

    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
            Thread.yield();
            //yield means to give a chance to other thread who wants to be executed as well.
            //It just give a hint, doesn't mean that jvm has to run the other thread wholly first.
        }
    }
    public static void main(String[] args) {
        Yield t1=new Yield();
        Yield t2=new Yield();
        t1.start();
        t2.start();
    }
}
