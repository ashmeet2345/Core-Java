package DSA;

import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
       // sumOf2Arrays();
       // diffOf2Arrays();
        Array array=new Array();
        int[] arr={5,10,15,20,25,30,35,40};
        System.out.println(array.binarySearch(arr,40));
    }

    public boolean binarySearch(int[] a,int num){
        int i=0;
        int j=a.length-1;
        while(i<=j){
            int mid=(i+j)/2;
            if(num == a[mid]){
                return true;
            } else if(num < a[mid]){
                j=mid-1;
            } else if(num > a[mid]){
                i=mid+1;
            }
        }
        return false;
    }

    public void sumOf2Arrays(){
        int[] a1={9,2,9};
        int[] a2={8,0};
        int[] ans=new int[a1.length>a2.length ? a1.length : a2.length];

        int i=a1.length-1;
        int j=a2.length-1;
        int k=ans.length-1;
        int c=0;
        while(k>=0){
            int d=c;
            if(i>=0){
                d+=a1[i];
            }
            if(j>=0){
                d+=a2[j];
            }
            c=d/10;
            d=d%10;
            ans[k]=d;

            i--;
            j--;
            k--;
        }
        if(c>0){
            System.out.print(c);
        }
        for(int a:ans){
            System.out.print(a);
        }
    }
    public int[] diffOf2Arrays(){
        int[] a1={9,2,9};
        int[] a2={8,0};
        int[] diff=new int[a1.length];
        int i=a1.length-1;
        int j=a2.length-1;
        int k=diff.length-1;
        while (k >= 0) {
           int c=j<0?0:a2[j];
            if(a1[i]-c < 0){
                a1[i]+=10;
                diff[k]=a1[i]-c;
                a1[i-1]--;
            } else {
                diff[k]=a1[i]-c;
            }
            i--;
            j--;
            k--;
        }
        return diff;
    }
}
