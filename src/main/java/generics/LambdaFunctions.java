package generics;

import java.util.ArrayList;

public class LambdaFunctions{
    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        final Integer[] val = {0};
        list.stream().forEach(i-> val[0]+= +i);
        System.out.println(val[0]);

        Operation sum=(a,b)->a+b;
        Operation subtract=(a,b)->a-b;
        Operation prod=(a,b)->a*b;
        LambdaFunctions lf=new LambdaFunctions();
        System.out.println(lf.operate(10,20,prod));
    }

    public int operate(int a,int b, Operation op){
        return op.operation(a,b);
    }

}

interface Operation{
    int operation(int a,int b);
}
