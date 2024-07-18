package DSA;

import java.util.Iterator;

public class TimeAndSpace {

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static boolean isSmall(int[] arr,int i,int j){
        if(arr[i]<arr[j])
            return true;
        else
            return false;
    }

    public static void display(int[] a){
        for(int v:a)
            System.out.print(v+" ");
    }
    public static void bubbleSort(int[] a){
        for(int i=1;i<a.length-1;i++){
            for(int j=0;j<a.length-i;j++){
                if(isSmall(a,j+1,j))
                    swap(a,j,j+1);
            }
        }
        display(a);
    }

    public static void selectionSort(int[] a){
        for(int i=0;i<a.length-1;i++){
            int min=i;
            for(int j=i+1;j<a.length;j++){
                if(isSmall(a,j,min)) {
                    min = j;
                }
            }
            swap(a,i,min);
        }
        display(a);
    }

    public static void insertionSort(int[] a){
        for(int i=a.length-1;i>=1;i--){
            for(int j=a.length-1;j>=1;j--){
                if(isSmall(a,j,j-1))
                    swap(a,j-1,j);
            }
        }
        display(a);
    }

    public static int[] mergeSortedArrays(int[] a,int[] b){
        int[] res=new int[a.length+b.length];
        int i=0;
        int j=0;
        int k=0;
        while(i < a.length && j < b.length){
            if(a[i] < a[j]){
                res[k++]=a[i];
                i++;
            }
            else{
                res[k++]=b[j];
                j++;
            }
        }

            while(i < a.length){
                res[k++]=a[i];
                i++;
            }

            while(j < b.length){
                res[k++]=b[j];
                j++;
            }

        return res;
    }

    public static int[] mergeSort(int[] a,int f,int l){
        if(f==l){
            int[] ans=new int[1];
            ans[0]=a[l];
            return ans;
        }

        int mid=(f+l)/2;
        int[] left=mergeSort(a,f,mid);
        int[] right=mergeSort(a,mid+1,l);
        int[] ans=mergeSortedArrays(left,right);
        return ans;
    }

    public static void partitioningArray(int[] a,int pivot){
        int i=0;
        int j=0;
        while(i<a.length){
            if(a[i] > pivot)
                i++;
            else {
                swap(a,j,i);
                i++;
                j++;
            }
        }
        display(a);
    }

    public static void main(String[] args) {
        int[] a={2,7,1,5,9,6,4};
        int[] b={9,3,2,6,8,4,7};
        bubbleSort(a);
        System.out.println();
        selectionSort(a);
        System.out.println();
        insertionSort(a);

        System.out.println();
        int[] res=mergeSort(b,0,b.length-1);
        for(int v:res)
            System.out.print(v+" ");

        System.out.println();
        partitioningArray(b,5);
    }
}
