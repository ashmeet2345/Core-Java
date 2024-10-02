package DSA;

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

    public static void main(String[] args) {
        Array array=new Array();

        System.out.print("Second Largest Element: ");
        array.secondLargestElement(new int[]{5,1,7,7,4,3,2});

        System.out.print("Remove Duplicate Elements: ");
        array.removeDuplicatesInPlaceFromSortedArray(new int[]{1,1,2,2,2,3,3});
    }
}
