package MultiThreading;

import java.util.ArrayDeque;
import java.util.Queue;

public class CustomQueue {
    private Queue<Integer> q;
    int capacity;

    public CustomQueue(int cap){
        q=new ArrayDeque<>();
        this.capacity=cap;
    }

    public void add(int num){
        synchronized (q){
            while(q.size() == capacity){
                try {
                    q.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            q.add(num);
            q.notify();
        }
    }

    public int poll(){
        synchronized (q){
            while(q.size() == 0){
                /*if size of the q is zero
            then we are going to wait for some thread to add element into the q so that we could poll it out.
            thats why we use the q.wait, so the thread to poll out has to wait for a thread to push into the queue

            While, the q.notify means, when an element has been pushed or polled out of the queue, the other threads
            which are blocked gets to know about it and they get to function normally.
            We used the while loop due to reason, there could be 2 or more threads waiting/ blocked.
            */
                try {
                    q.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int n=q.poll();
            q.notify();

            return n;
        }
    }
}
