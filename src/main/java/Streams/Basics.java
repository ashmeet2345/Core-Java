package Streams;

import java.util.*;
import java.util.stream.IntStream;

public class Basics {
    public static class Object{
        int data;
        int size;

        public Object(int data, int size) {
            this.data = data;
            this.size = size;
        }
    }

    public static void main(String[] args){
        ArrayList<Object> arr=new ArrayList<>();
        arr.add(new Object(10,40));
        arr.add(new Object(20,50));
        arr.add(new Object(30,60));
        arr.add(new Object(40,70));

        for(int i=0;i<5;i++){
            System.out.println(i*i);
            try{
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

       // System.out.println(arr.stream().filter(s->s.size > 40).mapToInt(s->s.data).sum());

        Integer a=2;
        Integer b=null;
        System.out.println(Math.min(a,b));
    }
}
