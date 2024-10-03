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

    public void unionOfTwoSortedArrays(int[] arr1, int[] arr2){
        int i=0;
        int j=0;
        int[] union=new int[arr1.length+arr2.length];
        Arrays.fill(union,-1);
        int k=0;
        while(i<arr1.length && j<arr2.length){
            if(arr1[i]<=arr2[j]){
                if(union[0]==-1 || union[k]!=arr1[i]){
                    union[k++]=arr1[i];
                }
                i++;
            } else {
                if(union[0]==-1 || union[k]!=arr2[j]){
                    union[k++]=arr2[j];
                }
                j++;
            }
        }

        while(i<arr1.length){
            union[k++]=arr1[i];
            i++;
        }

        while(j<arr2.length){
            union[k++]=arr2[j];
            j++;
        }

        Arrays.stream(union).forEach(s-> System.out.print(s+" "));
    }

    public void intersectionOfTwoSortedArrays(int[] a, int[] b){
        int i=0;
        int j=0;
        int n1=a.length;
        int n2=b.length;
        int[] intersection=new int[n1+n2];
        int k=0;
        while(i<n1 && j<n2){
            if(a[i]==b[j]){
                intersection[k++]=a[i];
                i++;
                j++;
            } else if(a[i]<b[j]){
                i++;
            } else {
                j++;
            }
        }
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

        System.out.print("Union of 2 sorted arrays");
        array.unionOfTwoSortedArrays(new int[]{1,1,2,3,4,5},new int[]{2,3,4,4,5,6});

        System.out.print("Intersection of 2 sorted arrays: ");
        array.intersectionOfTwoSortedArrays(new int[]{1,2,2,3,3,4,5,6},new int[]{2,3,3,5,6,6,7});
    }
}
