package MultiThreading;

public class Stacks {
    int[] arr;
    int num;
    int top;
    Object lock;
    
    public Stacks(){
        arr=new int[10];
        top=-1;
        lock=new Object();
    }

    public synchronized void push(int num){
        //use of synchronize is to make sure that only one thread enters this block of code.
        //But to enter that such block, we need a lock to enter (like we need a lock to enter into a room)
        //By default, every object of java can be used as a lock to enter into such a block.
        //Primitive data types cannot be used as locks
        //But instance of Integer can be used
        /*we couldve used new Integer() or new String() or new Object() etc*)*/
        if(top == arr.length-1){
            int[] newArr=new int[arr.length*2];
            System.arraycopy(arr,0,newArr,0,newArr.length);
            arr=newArr;
        }
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            e.printStackTrace();
        }
        top++;
        arr[top]=num;
    }

    public synchronized int pop() throws Exception {
        if(top==-1){
            throw new Exception("Array out of bounds");
        }
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            e.printStackTrace();
        }
        int num=arr[top];
        arr[top]=Integer.MIN_VALUE;
        top--;
        return num;
    }

    /*As we can see that both the blocks are bounded by the same lock objects, so 1 thread can
    access only one block at a time, other threads will have to wait

    -If locks to both the synchronized blocks were different, then 2 blocks could concurrently access the
    blocks at the same time
    * */
    public int peek(){
        return arr[top];
    }

    public int size(){
        return arr.length;
    }
}