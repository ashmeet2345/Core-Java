package org.example;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        Stack<ImmutablePair<Integer,Integer>> st=new Stack<>();
        st.push(new ImmutablePair<>(2,3));
        st.push(new ImmutablePair<>(4,3));
        st.push(new ImmutablePair<>(7,4));
        st.push(new ImmutablePair<>(9,2));

        int[][] arr=new int[st.size()][2];
        while(st.size()>0){
            ImmutablePair<Integer,Integer> res=st.peek();
            arr[st.size()-1][0]=res.left;
            arr[st.size()-1][1]=res.right;
            st.pop();
        }

        Arrays.stream(arr).flatMapToInt(Arrays :: stream).forEach(str -> System.out.print(str+","));
    }
}

class Pair implements Comparable<Pair> {
    int num1;
    int num2;

    Pair(int num1,int num2){
        this.num1=num1;
        this.num2=num2;
    }

    public int compareTo(Pair o) {
        if(this.num1 != o.num1)
            return  this.num1-o.num1;
        else
            return this.num2-o.num2;
    }
}

