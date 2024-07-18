package DSA;

import java.util.ArrayList;
import java.util.HashMap;

public class PriorityQueueImplementation {
    public static class PriorityQueue{
        ArrayList<Integer> data;
        public PriorityQueue(){
            data=new ArrayList<>();
        }

        public void add(int num){
            data.add(num);
            upHeapify(data.size()-1);
        }

        public void upHeapify(int i){
            if(i==0){
                return;
            }

            int pi=(i-1)/2;
            if(data.get(i) < data.get(pi)){
                swap(i,pi);
                upHeapify(pi);
            }
        }

        public void swap(int i,int j){
            int v1=data.get(i);
            int v2=data.get(j);
            data.add(i,v2);
            data.add(j,v1);
        }

        public int remove(){
            swap(0,data.size()-1);
            int val=data.remove(data.size()-1);
            downHeapify(0);
            return val;
        }

        public void downHeapify(int pi){
            int l=2*pi+1;
            int r=2*pi+2;
            int mini=pi;
            if(l < data.size() && data.get(l) < data.get(pi))
                mini=l;

            if(r < data.size() && data.get(r) < data.get(pi))
                mini = r;

            if(mini!=pi){
                swap(pi,mini);
                downHeapify(mini);
            }
        }

        public int peek(){
            if(this.size() == 0){
                System.out.println("Underflow");
                return -1;
            }

            return data.get(0);
        }

        public int size(){
            return data.size();
        }
    }

    public static void main(String[] args) {
        PriorityQueue pq=new PriorityQueue();
        pq.add(10);
        pq.add(20);
        System.out.println(pq.size());

    }
}
