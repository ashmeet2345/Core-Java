package DSA;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.security.Key;
import java.security.KeyPair;
import java.security.PublicKey;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
import java.util.Stack;

public class Stacks {
    public static void main(String[] args) {
        //duplicacy();
        //System.out.println(balanced());
        //nger();
        //stockspan();
        //largestHistogram(); to be checked later
       /* int[] ans=slidingWindow(6);
        for(int i:ans){
            System.out.print(i+" ");
        }*/
        //infixConversion();
        //infixConversions();
       // celebrity();
    }

    public static void duplicacy(){
        Stack<Character> stack=new Stack<>();
        String s="((a+b)+(c+d))";
        boolean ans=false;
        for(int i=0;i<s.length();i++){
            Character c=s.charAt(i);
            if(c==')'){
                if(stack.peek() =='('){
                    ans=true;
                    return;
                } else {
                    while(stack.peek() != '('){
                        stack.pop();
                    }
                }
                stack.pop();
            }
            else
                stack.push(c);
        }
        System.out.println(ans);
    }

    public static boolean balanced(){
        Stack<Character> st=new Stack<>();
        String s="{(a+b)+[c+d]}";
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            switch (c){
                case '}':
                    if(st.size()>0){
                        if(st.peek().equals('{'))
                            st.pop();
                        else return false;
                    } else return false;
                    break;
                case ')':
                    if(st.size()>0){
                        if(st.peek().equals('('))
                            st.pop();
                        else return false;
                    } else return false;
                    break;
                case ']':
                    if(st.size()>0){
                        if(st.peek().equals('['))
                            st.pop();
                        else return false;
                    } else return false;
                    break;
                default:
            }
            if(c=='{' || c=='(' || c=='[')
                st.push(c);
        }
        if(st.size()>0){
            return false;
        }
        return true;
    }

    public static void nger(){
        int[] a={2,5,9,3,1,12,6,8,7};
        int[] sol=new int[a.length];
        Stack<Integer> st=new Stack<>();
        st.push(a[a.length-1]);
        sol[a.length-1]=-1;
        for(int i=a.length-2;i>=0;i--){
            if(a[i] > st.peek()){
                while(st.size() > 0 && a[i]> st.peek())
                    st.pop();
                if(st.size()>0){
                    sol[i]=st.peek();
                } else {
                    sol[i]=-1;
                }
            } else
               sol[i]=st.peek();
            st.push(a[i]);
        }

        for(int i:sol){
            System.out.println(i);
        }
    }

    public static void stockspan(){
        int[] a={2,5,9,3,1,12,6,8,7};
        Stack<Integer> st=new Stack<>();
        int[] sol=new int[a.length];
        st.push(a[0]);
        sol[0]=1;

        for(int i=1;i<a.length;i++){
            int count=1;
            while(st.size() > 0 && a[i] > st.peek()){
                count++;
                st.pop();
            }
            if(st.size() == 0){
                sol[i]=i+1;
            }
            else {
                sol[i]=count;
            }
            st.push(a[i]);
        }

        for(int i:sol){
            System.out.print(i+" ");
        }
    }

    public static void largestHistogram(){
        int[] a={6,2,5,4,5,1,6};
        Stack<Integer> st=new Stack<>();
        int maxArea=0;
        int length=1;
        int breadth=a[0];
        st.push(a[0]);
        for(int i=1;i<a.length;i++){
            int area=length*breadth;
            if(area>maxArea)
                maxArea=area;
            if(a[i]==0){
                length=1;
                breadth=a[i];
            } else {
                if(a[i]>st.peek()){
                    length++;
                } else {
                    while(st.size()>0 && a[i]<st.peek()){
                        st.pop();
                    }
                    length++;
                    if(st.size()>0){
                        breadth=Math.min(a[i],st.peek());
                        st.pop();
                    } else
                        breadth=a[i];
                }
            }
            st.push(a[i]);

        }
        System.out.println(maxArea);
    }

    public static int[] slidingWindow(int k){
        int[] a= {9766,-5396,-7824,-3941,4600,-1485,-1486,-4530,-1636,-2088,-5295};
        int[] sol=new int[a.length-k+1];
        for(int i=0;i<a.length-k+1;i++){
            int max=Integer.MIN_VALUE;
            for(int j=i;j<=k+i-1;j++){
                max=Math.max(max,Math.max(a[i],a[j]));
            }
            sol[i]=max;
        }
        return sol;
    }

    public static void infixConversion(){
        String s="(2+3)*5";
        Stack<Integer> opnds=new Stack<>();
        Stack<Character> optor=new Stack<>();
        for(int i=0;i<s.length();i++){
            Character ch=s.charAt(i);
            if(ch.equals('('))
                optor.push(ch);
            else if(Character.isDigit(ch))
                opnds.push(ch-'0');
            else if(ch.equals(')')){
                while(optor.peek() != '('){
                    char op=optor.pop();
                    int v2=opnds.pop();
                    int v1=opnds.pop();
                    int num=operation(v1,v2,op);
                    opnds.push(num);
                }
                optor.pop();
            } else if(ch.equals('+') || ch.equals('-') || ch.equals('*') || ch.equals('/')){
                while(optor.size()>0
                        && optor.peek()!='('
                        && precedence(ch) <= precedence(optor.peek())){
                    char c=optor.pop();
                    int v2=opnds.pop();
                    int v1=opnds.pop();
                    int res=operation(v1,v2,c);
                    opnds.push(res);
                }
                optor.push(ch);
            }
        }
        while(optor.size() != 0){
            char op=optor.pop();
            int v2=opnds.pop();
            int v1=opnds.pop();
            int num=operation(v1,v2,op);
            opnds.push(num);
        }
        System.out.println(opnds.peek());
    }

    public static int precedence(char operator){
        if(operator == '+') return 1;
        else if(operator == '-') return 1;
        else if(operator == '*') return 2;
        else return 2;
    }

    public static int operation(int v1,int v2,char operator){
        if(operator == '+') return v1+v2;
        else if(operator == '-') return v1-v2;
        else if(operator == '*') return v1*v2;
        else return v1/v2;
    }

    public static void infixConversions(){

        String s="(a+b)*(c+d)";
        Stack<String> pre=new Stack<>();
        Stack<Character> in=new Stack<>();
        Stack<String> post=new Stack<>();

        for(int i=0;i<s.length();i++){
            Character ch=s.charAt(i);
            if(ch.equals('(')){
                in.push(ch);
            } else if(Character.isDigit(ch) || Character.isAlphabetic(ch)){
                pre.push(ch.toString());
                post.push(ch.toString());
            } else if(ch.equals(')')){
                while(!in.peek().equals('(')){
                    char op=in.pop();
                    String s2=pre.pop();
                    String s1=pre.pop();
                    String s22=post.pop();
                    String s21=post.pop();
                    String str1=operations(s1,s2,op,true);
                    String str2=operations(s21,s22,op,false);
                    pre.push(str1);
                    post.push(str2);
                }
                in.pop();
            } else if(ch.equals('+') || ch.equals('-') || ch.equals('*') || ch.equals('/')){
                while(in.size()>0
                        && in.peek()!='('
                        && precedence(ch) <= precedence(in.peek())){
                    char op=in.pop();
                    String s2=pre.pop();
                    String s1=pre.pop();
                    String s22=post.pop();
                    String s21=post.pop();
                    String str1=operations(s1,s2,op,true);
                    String str2=operations(s21,s22,op,false);
                    pre.push(str1);
                    post.push(str2);
                }
                in.push(ch);
            }
        }
        while(in.size() != 0){
            char op=in.pop();
            String s2=pre.pop();
            String s1=pre.pop();
            String s22=post.pop();
            String s21=post.pop();
            String str1=operations(s1,s2,op,true);
            String str2=operations(s21,s22,op,false);
            pre.push(str1);
            post.push(str2);
        }
        System.out.println(pre.peek());
        System.out.println(post.peek());
    }

    public static String operations(String v1, String v2, Character oper, Boolean prefix){
        if(prefix){
            String ch=oper+v1+v2;
            return ch;
        }
        else{
            String ch=v1+v2+oper;
            return ch;
        }
    }

    public static void celebrity(){
        int[][] arr={{0,1,1,1,1}
                    ,{1,0,0,1,0}
                    ,{1,0,0,1,0}
                    ,{0,0,0,0,0}
                    ,{0,1,0,1,0}};

        Stack<Integer> st=new Stack<>();
        for(int i=0;i<arr[0].length;i++){
            st.push(i);
        }

        while(st.size()>=2){
            int v1=st.pop();
            int v2=st.pop();

            if(arr[v1][v2] == 1){
                st.push(v2);
            } else st.push(v1);
        }

        int pot=st.pop();
        for(int i=0;i<arr.length;i++){
            if(i!=pot){
                if(arr[i][pot] == 0 || arr[pot][i] == 1){
                    System.out.println("None");
                    return;
                }
            }
        }
        System.out.println(pot);
    }

    public static void mergeOverlappingInterals(){
        int[][] arr=new int[5][2];

        Pair[] pairs=new Pair[5];
        for(int i=0;i<arr.length;i++){
            pairs[i]=new Pair(arr[i][0],arr[i][1]);
        }
        Arrays.sort(pairs);

        Stack<Pair> st=new Stack<>();
        st.push(pairs[0]);
        for(int i=1;i<pairs.length;i++){
            Pair top=st.peek();
            if(pairs[i].v1 >= top.v2)
                st.push(pairs[i]);
            else
                top.v2=Math.max(top.v2,pairs[i].v2);
        }

        Stack<Pair> rs=new Stack<>();
        while(st.size() > 0){
            Pair p=st.pop();
            rs.push(p);
        }
        while(rs.size() > 0){
            System.out.println(rs.pop());
        }
    }
}

class Pair implements Comparable<Pair>{
    int v1;
    int v2;
    Pair(int v1,int v2){
        this.v1=v1;
        this.v2=v2;
    }

    public int compareTo(Pair o) {
        if(this.v1 >=o.v1)
            return 1;
        else
            return -1;
    }

    public void minStack(){
        Stack<Integer> st=new Stack<>();
        int min=0;
        int[] arr={8,47,17,7,30,2};
        for(int i=0;i<arr.length;i++){
            int val=arr[i];
            if(st.size() == 0){
                st.push(arr[i]);
                min=val;
            } else {
                if(val>=st.peek())
                    st.push(val);
                else{
                    st.push(2*val-min);
                    min=val;
                }
            }
        }
    }
}
