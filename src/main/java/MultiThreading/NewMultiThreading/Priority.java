package MultiThreading.NewMultiThreading;

public class Priority extends Thread{
    public Priority(String name){
        super(name);
    }
    public Priority(){
    }

    public void run(){
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" "+Thread.currentThread().getPriority());
        }
    }
    public static void main(String[] args) {
        Priority low=new Priority("Low");
        Priority medium=new Priority("Medium");
        Priority high=new Priority("High");
        low.setPriority(Thread.MIN_PRIORITY);
        medium.setPriority(Thread.NORM_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);
        low.start();
        medium.start();
        high.start();

        //It doesn't mean that it will only give high priority to high thread. It could be in any sequence
    }
}
