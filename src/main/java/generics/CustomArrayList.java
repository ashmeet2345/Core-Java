package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomArrayList<T>{
    private Object[] data;
    private static int DEFAULT_SIZE = 10;
    private int size = 0;

    public CustomArrayList(){
        data=new Object[DEFAULT_SIZE];
    }

    public void add(T num){
        if(size == data.length - 1)
            refill();

        data[size++]=num;
    }

    public void refill(){
        Object[] temp=new Object[size*2];
        for(int i=0;i<data.length;i++){
            temp[i]=data[i];
        }
        data=temp;
    }

    public int size(){
        return size;
    }

    public T remove(){
        T rem= (T) (data[--size]);
        return rem;
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

    public List<? extends Number> getList(List<? extends Number> list){
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        int[] data={50,60,70,80,90,100};
        Arrays.stream(data).forEach(s->list.add(s));
        System.out.println(list);

        CustomArrayList<Integer> l=new CustomArrayList<>();
       /* l.add(10);
        l.add(20);
        l.add(30);*/
        List<Float> list2=new ArrayList<>();
        List<Double> list3=new ArrayList<>();
        List<Long> list4=new ArrayList<>();
        list2.add(2.4f);
        list3.add(3.55d);
        list4.add(222213123123123123l);
        l.getList(list2);
        l.getList(list3);
        l.getList(list4);
    }
}
