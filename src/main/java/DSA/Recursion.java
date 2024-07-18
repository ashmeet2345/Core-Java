package DSA;

import org.example.Arr;

import java.util.ArrayList;
import java.util.Arrays;

public class Recursion {

    public static void printNumbers(int n){
        if(n==0)
            return;
        System.out.println(n);
        printNumbers(n-1);
        System.out.println(n);
    }

    public static int factorial(int n){
        if(n==0)
            return 1;
        return n*factorial(n-1);
    }

    public static int power(int x,int n){
        if(n==0){
            return 1;
        }
        return x*power(x,n-1);
    }

    public static int powerLog(int x,int n){
       if(n==0)
           return 1;

       int y=powerLog(x,n/2);
       int xn=y*y;
       if(n%2==1)
           xn=xn*x;
       return xn;
    }

    public static void towerOfHanoi(char a,char b,char c,int n){
        if(n==0)
            return;

        towerOfHanoi(a,c,b,n-1);
        System.out.println(n+"-"+a+"->"+b);
        towerOfHanoi(c,b,a,n-1);
    }

    public static void displayArray(int[] a,int i){
        if(i==a.length)
            return;

        System.out.print(a[i]+" ");
        displayArray(a,i+1);
    }

    public static void displayArrayReverse(int[] a,int i){
        if(i==a.length)
            return;
        displayArrayReverse(a,i+1);
        System.out.print(a[i]+" ");
    }

    static int max=Integer.MIN_VALUE;
    public static int maxOfArray(int[] a,int i){
        if(i==a.length-1)
            return a[i];

        int val=maxOfArray(a,i+1);
        max=Math.max(max,val);
        return Math.max(max,a[i]);
    }

    public static ArrayList<String> subsequence(String str){
        if(str.length()==0){
            ArrayList<String> s=new ArrayList<>();
            s.add("");
            return s;
        }
        char c=str.charAt(0);
        ArrayList<String> rem=subsequence(str.substring(1));
        ArrayList<String> res=new ArrayList<>();
        for(String s:rem){
            res.add(s+"_");
            res.add(s+c);
        }
        return res;
    }

    static String[] keypad={"?!","abc","def","ghi","jkl","mnop","qrst","uv","wxyz",".,"};
    public static ArrayList<String> getKeypadComb(String str){
        if(str.length() == 0){
            ArrayList<String> s=new ArrayList<>();
            s.add("");
            return s;
        }

        int c=str.charAt(0)-'0';
        String sub=str.substring(1);
        ArrayList<String> rem=getKeypadComb(sub);
        ArrayList<String> res=new ArrayList<>();
        for(String s:rem){
            String ks=keypad[c];
            for(int i=0;i<ks.length();i++){
                res.add(s+ks.charAt(i));
            }
        }
        return res;
    }

    public static ArrayList<String> getStairsPath(int n){
        if(n==0){
            ArrayList<String> r=new ArrayList<>();
            r.add("");
            return r;
        } else if( n<0){
            ArrayList<String> r=new ArrayList<>();
            return r;
        }

        ArrayList<String> s1=getStairsPath(n-1);
        ArrayList<String> s2=getStairsPath(n-2);
        ArrayList<String> s3=getStairsPath(n-3);
        ArrayList<String> res=new ArrayList<>();
        for(String s: s1){
            res.add("1"+s);
        }
        for(String s: s2){
            res.add("2"+s);
        }
        for(String s: s3){
            res.add("3"+s);
        }
        return res;
    }

    public static ArrayList<String> getMazePaths(int n,int m,int dn,int dm){
        if(n==dn && m==dm){
            ArrayList<String> r=new ArrayList<>();
            r.add("");
            return r;
        }
        ArrayList<String> h=new ArrayList<>();
        ArrayList<String> v=new ArrayList<>();
        ArrayList<String> res=new ArrayList<>();
        if(n<dn)
            v=getMazePaths(n+1,m,dn,dm);
        if(m<dm)
            h=getMazePaths(n,m+1,dn,dm);
        for(String s:h){
            res.add("h"+s);
        }
        for(String s:v){
            res.add("v"+s);
        }

        return res;
    }

    public static ArrayList<String> mazePathWithJumps(int n,int m,int dn,int dm){

        if(n == dn && m == dm){
            ArrayList<String> l=new ArrayList<>();
            l.add("");
            return l;
        }

        ArrayList<String> path=new ArrayList<>();

        for(int i=1;i<=dm-m;i++){
            ArrayList<String> hpath=mazePathWithJumps(n,m+i,dn,dm);
            for(String s:hpath){
                path.add("h"+i+s);
            }
        }

        for(int i=1;i<=dn-n;i++){
            ArrayList<String> vpath=mazePathWithJumps(n+i,m,dn,dm);
            for(String s:vpath){
                path.add("v"+i+s);
            }
        }

        for(int i=1;i<=dm-m && i<=dn-n;i++){
            ArrayList<String> dpath=mazePathWithJumps(n+i,m+i,dn,dm);
            for(String s:dpath){
                path.add("d"+i+s);
            }
        }

        return path;
    }

    public static void printSubsequence(String str,String ans){
        if(str.length() == 0){
            System.out.print(ans+",");
            return;
        }
        char c=str.charAt(0);
        String rs=str.substring(1);
        printSubsequence(rs,ans+c);
        printSubsequence(rs,ans+"_");
    }

    public static void printKeypadCombinations(String str,String ans){
        if(str.length() == 0){
            System.out.print(ans+" ");
            return;
        }

        char ch=str.charAt(0);
        String code=keypad[ch-'0'];
        String rs=str.substring(1);
        for(int i=0;i<code.length();i++){
            char c=code.charAt(i);
            printKeypadCombinations(rs,ans+c);
        }
    }

    public static void permutations(String str,String ans){
        if(str.length() == 0){
            System.out.print(ans+" ");
            return;
        }

        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            String res=str.substring(0,i)+str.substring(i+1);
            permutations(res,ans+c);
        }
    }

    public static void floodFill(int r,int c,int[][] a,String ans,boolean[][] visited){

        if(r<0 || c<0 || r==a.length || c==a[0].length ||a[r][c] == 1 || visited[r][c] == true)
            return;

        if(r==a.length-1 && c==a[0].length-1){
            System.out.print(ans+" ");
            return;
        }

        visited[r][c]=true;
        floodFill(r-1,c,a,ans+"T",visited);
        floodFill(r,c+1,a,ans+"R",visited);
        floodFill(r+1,c,a,ans+"D",visited);
        floodFill(r,c-1,a,ans+"L",visited);
        visited[r][c]=false;
    }

    public static void targetSum(int[] a,int i,int sum,String ans,int target){
        if(i==a.length){
            if(sum == target){
                System.out.print(ans+" ");
            }
            return;
        }
        targetSum(a,i+1,sum+a[i],ans+a[i]+",",target);
        targetSum(a,i+1,sum,ans,target);
    }
    public static void main(String[] args) {
        printNumbers(5);
        System.out.println("Factorial-"+factorial(0));
        System.out.println("Power-"+power(3,3));
        System.out.println("Power (Log time)-"+powerLog(3,3));
        towerOfHanoi('a','b','c',3);
        int[] a={1,2,8,12,5,6};
        displayArray(a,0);
        System.out.println();
        displayArrayReverse(a,0);
        System.out.println("\nMax value: "+maxOfArray(a,0));
        ArrayList<String> res=subsequence("abc");
        for(String s: res){
            System.out.print(s+",");
        }
        System.out.println();
        ArrayList<String> res2=getKeypadComb("01");
        for(String s: res2){
            System.out.print(s+",");
        }
        System.out.println();
        ArrayList<String> res3=getStairsPath(3);
        for(String s: res3){
            System.out.print(s+",");
        }

        System.out.println();
        ArrayList<String> res4=getMazePaths(1,1,3,3);
        for(String s: res4){
            System.out.print(s+",");
        }

        System.out.println();
        ArrayList<String> res5=mazePathWithJumps(1,1,3,3);
        for(String s: res5){
            System.out.print(s+",");
        }

        System.out.println();
        printSubsequence("abc","");

        System.out.println();
        printKeypadCombinations("123","");

        System.out.println();
        permutations("abc","");

        int[][] arr={{0,1,0,0},
                     {0,1,0,1},
                     {0,0,0,0},
                     {1,0,1,0}};
        boolean[][] visited=new boolean[a.length][arr[0].length];

        System.out.println();
        floodFill(0,0,arr,"",visited);

        System.out.println();
        int[] abc={10,20,30,40,50};
        targetSum(abc,0,0,"",60);
    }
}
