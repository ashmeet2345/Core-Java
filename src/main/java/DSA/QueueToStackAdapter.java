package DSA;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueToStackAdapter {

    public static class QueueToStack{
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;

        public QueueToStack(){
            mainQ=new ArrayDeque<>();
            helperQ=new ArrayDeque<>();
        }

        int size(){
            return mainQ.size();
        }

        int pop(){
            if(size()==0){
                System.out.println("Stack underflow");
                return -1;
            } else {
                return mainQ.remove();
            }
        }

        int peek(){
            if(size()==0){
                System.out.println("Stack underflow");
                return -1;
            } else {
                return mainQ.peek();
            }
        }

        void push(int num){
            while(mainQ.size()>0){
                helperQ.add(mainQ.remove());
            }
            mainQ.add(num);
            while(helperQ.size()>0){
                mainQ.add(helperQ.remove());
            }
        }
    }

    public static void main(String[] args) {
        QueueToStack stack=new QueueToStack();
        stack.push(20);
        stack.push(20);
        stack.push(20);
        stack.push(40);
        System.out.println(stack.peek());
    }
}
