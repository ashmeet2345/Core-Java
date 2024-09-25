package MultiThreading;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       /* System.out.println("Main is starting");
      //  Thread thread1 = new Thread1("THREAD1");
        Thread thread2=new Thread(new Thread2(),"THREAD2");
       // thread1.setDaemon(true); //If the Main stops executing, then it might happen that this thread one
        //does not print all the values as daemon thread is executed on the mercy of the jvm machine
      //  thread1.start(); //Thread 1 is a child of the Main thread as thread 1 is spawned from the Main thread
        thread2.start();

        //other way to implement Thread2 class is
        Thread thread3=new Thread(() -> {
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        },"THREAD3");
        thread3.start();
        System.out.println("Main is exiting");
*/


 /*       Stacks stack=new Stacks();
        new Thread(()->{
            for(int i=1;i<=3;i++){
                stack.push(i*2);
                System.out.println("Pushed "+i*2);
            }
        },"Pushing").start();

        new Thread(()->{
            for(int i=1;i<=3;i++){
                try {
                    System.out.println("Popped "+stack.pop());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        },"Popping").start();*/

        System.out.println("Main starting");
        Thread thread=new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.print(i*2+" ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        thread.join(3000); //it helps to run threads sequentially, in an order,
        //If join is used with a thread, then that thread will first complete its work for
        //the duration mentioned in the parenthesis of the join method
        System.out.println("Main Exiting");
    }
}
