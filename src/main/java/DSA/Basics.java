package DSA;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Basics {
    public static void main(String[] args) {
        //fibonacci(10);
        //digitsOfNumber(23456);
        //inverse(21453);
        //rotation(23467,2);
       // gcdAndLcm(24,36);
       // primeFactorization(200);

       /* Pair[] pairs=new Pair[3];
        pairs[0]=new Pair(4,1);
        pairs[1]=new Pair(3,9);
        pairs[2]=new Pair(8,7);

        Arrays.sort(pairs);
        System.out.println("["+pairs[0].s1+","+pairs[0].s2+"]");
        System.out.println("["+pairs[1].s1+","+pairs[1].s2+"]");
        System.out.println("["+pairs[2].s1+","+pairs[2].s2+"]");*/
    }

    static void fibonacci(int num){
        int n1=0;
        int n2=1;
        for(int i=1;i<=num;i++){
            if(i==1){
                System.out.print(n1+" "+n2+" ");
            }
            else{
                System.out.print(n2+" ");
            }
            int temp=n2;
            n2=n1+n2;
            n1=temp;
        }
    }

    static void digitsOfNumber(int num){
        int n=0;
        int temp=num;
        while(temp !=0){
            temp=temp/10;
            n++;
        }
        int div=(int)Math.pow(10,n-1);
        int ans;
        while(div != 0){
            ans=num/div;
            System.out.print(ans+"-");
            num=num%div;
            div/=10;
        }
    }

    static void inverse(int num){
        int count=0;
        int digit=0;
        int ans=0;
        while(num > 0){
            digit=num%10;
            count++;
            ans+=count*Math.pow(10,digit-1);
            num=num/10;
        }
        System.out.println(ans);
    }

    static void rotation(int num, int r){
        int ans=num;
        int digit=0;
        int temp=num;
        int c=0;
        while(temp !=0){
            temp=temp/10;
            c++;
        }
        int div=(int)Math.pow(10,c-1);
        while(r>0){
            digit=ans%10;
            ans/=10;
            ans+=digit*div;
            r--;
        }
        System.out.println(ans);
    }

    static void gcdAndLcm(int num1,int num2){
        int n1=Math.max(num1,num2);
        int n2=Math.min(num1,num2);
        int r=0;
        while(n1%n2 != 0){
            r=n1%n2;
            n1=n2;
            n2=r;
        }
        System.out.println(r);
        int lcm=(num1*num2)/r;
        System.out.println(lcm);
    }

    static void primeFactorization(int num){
        for(int i=2;i*i<=num;i++){
            while(num%i==0){
                num=num/i;
                System.out.println(i);
            }
        }
        if(num !=1){
            System.out.println(num);
        }
    }
}

/*class Pair implements UsingComparable<Pair>{
    int s1;
    int s2;
    Pair(int s1,int s2){
        this.s1=s1;
        this.s2=s2;
    }

    public int compareTo(Pair o) {
        if(this.s2 > o.s2)
            return 1;
        else
            return -1;
    }
}*/
