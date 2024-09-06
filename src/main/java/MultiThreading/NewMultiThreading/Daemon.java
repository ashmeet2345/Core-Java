package MultiThreading.NewMultiThreading;

public class Daemon extends Thread{
    public void run(){
        while(true){
            System.out.println("Hello");
        }
    }

    public static void main(String[] args) {
        Daemon thread=new Daemon();
        thread.setDaemon(true);
        thread.start();
        System.out.println("Hello"+Thread.currentThread().getName());

        //Even though we have started the thread which consists of a forever loop, still the program terminates,
        //Because that thread is daemon thread, which means, it runs in the background only.
        //Jvm doesn't care if the thread has been executed or not
        //So Daemon thread is a background thread only

    }

}
