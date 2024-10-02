package DSA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Array {

    public void secondLargestElement(int[] arr){
        int first=arr[0];
        int second=arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>first){
                second=first;
                first=arr[i];
            } else if(arr[i]<first && arr[i]>second){
                second=arr[i];
            }
        }
        System.out.println(second);
    }

    public void removeDuplicatesInPlaceFromSortedArray(int[] arr){
        int i=0;
        int j=0;
        while(i<arr.length){
            if(arr[i]==arr[j]){
                i++;
            } else {
                j++;
                arr[j]=arr[i];
                i++;
            }
        }
        System.out.println(j+1);
    }

    public void reverse(int[] arr, int i,int j){
        int temp=-1;
        while(i<=j){
            temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
            j--;
        }
    }

    public void rotateArrayToLeftByKElements(int[] arr, int k){
        reverse(arr,0,k-1);
        reverse(arr,k,arr.length-1);
        reverse(arr,0,arr.length-1);

        Arrays.stream(arr).forEach(s-> System.out.print(s+" "));
        System.out.println();
    }

    public void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public void moveAllZeroesToEndOfArray(int[] arr){
        int j=-1;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==0 && j==-1){
                j=i;
                continue;
            }
            if(arr[i]!=0 && j>0){
                swap(arr,i,j);
                j++;
            }
        }

        Arrays.stream(arr).forEach(s-> System.out.print(s+" "));
        System.out.println();
    }

    public static void main(String[] args) {
        Array array=new Array();

        System.out.print("Second Largest Element: ");
        array.secondLargestElement(new int[]{5,1,7,7,4,3,2});

        System.out.print("Remove Duplicate Elements: ");
        array.removeDuplicatesInPlaceFromSortedArray(new int[]{1,1,2,2,2,3,3});

        System.out.print("Rotate an array to left by K elements: ");
        array.rotateArrayToLeftByKElements(new int[]{1,2,3,4,5,6,7},3);

        System.out.print("Move all zeroes to end: ");
        array.moveAllZeroesToEndOfArray(new int[]{1,0,2,3,2,0,0,4,5,1});
    }
}
