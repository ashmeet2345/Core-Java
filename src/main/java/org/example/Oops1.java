package org.example;
import org.example.Arr.*;


public class Oops1 {
    public static void main(String[] args) {
      //  Student student1=new Student(50,"Sameer", 2.8f);
        Student student1=new Student();
       // System.out.println(student1.age + ", "+ student1.name + ", "+student1.score);
       /* Student random=new Student(student1);
        System.out.println(random.age + ", "+ random.name + ", "+random.score);*/
       // fibonacci(10);
        //occurence(1077827477);
        //reverse(23456);
        /*Scanner scn=new Scanner(System.in);
        String a=scn.next();
        checkingSwitch(a);*/
       // varAgs("Ashmeet",1,2,3,4,5,6);

    }

    public static void fibonacci(int num){
        int n1=0;
        int n2=1;
        for(int i=0;i<num;i++){
            if(i==0)
                System.out.print(n1+" ");
            System.out.print(n2+" ");
            int temp=n2;
            n2=n1+n2;
            n1=temp;
        }

    }

    public static void occurence(int num){
        int count=0;
        while(num>=1){
            int n=num%10;
            if(n==7)
                count++;
            num=num/10;
        }
        System.out.println(count);
    }

    public static void reverse(Integer num){
        int rev=0;
        while(num>=1){
            int n=num%10;
            rev=rev*10+n;
            num=num/10;
        }
        System.out.println(rev);
    }

    public static void checkingSwitch(String a){
        switch (a){
            case "Mango" -> System.out.println("They are very expensive");
            case "Orange" -> System.out.println("Not so expensive");
            default ->System.out.println("kuch to badhiya enter kar lala");
        }
    }

    public static void varAgs(String s, int ...v){
        System.out.println(s.charAt(4));
    }
}

class Student{
    int age;
    String name="";
    float score;
    Student(){
        this(25,"Sanchit",5.9f);
    }

    Student(Student other){
        this.age=other.age;
        this.name=other.name;
        this.score=other.score;
    }
    Student(int age,String name, float score){
        this.age=age;
        this.name=name;
        this.score=score;
    }
}


