package MultiThreading.NewMultiThreading;

public class B extends Thread{

    public void run(){
        System.out.println("Running");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws InterruptedException {

        B thread=new B();
        System.out.println(thread.getState()); //it shows new because of new operator above, as it is created only
        System.out.println(Thread.currentThread().getState()); //it shows runnable because it is the Main thread. which is already in a running state.
        thread.start();
        System.out.println(thread.getState()); //now it will print runnable because thread is in running state.
        Thread.sleep(200);
        System.out.println(thread.getState()); //now it will print timed waiting due to 2000ms waiting in the run method above.
        thread.join(); //Main method will wait for thread to get finished first
        System.out.println(thread.getState()); //now it will print terminated as Main method waited first
        //for thread method to get finished first.
    }
}
