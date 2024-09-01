package DSA;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class SlidingWindow {

    public void maxSumInWindowOfSizeK(int[] arr,int k){
        int mxsum=Integer.MIN_VALUE;
        int sum=0;

        for(int i=0;i<k  && i<arr.length;i++){
            sum+=arr[i];
        }
        int i=0;
        if(k<arr.length) i=k;
        else{
            System.out.println(Arrays.stream(arr).sum());
            return;
        }
        for(;i<arr.length;i++){
            int j=i-k+1;
            if(sum-arr[j-1]+arr[i]>mxsum){
                mxsum=sum-arr[j-1]+arr[i];
                sum=mxsum;
            }
        }
        System.out.print(mxsum);
    }

    public void longestSubarrayWithSumLessOrEqualToK(int[] arr, int k){
        int i=0;
        int j=0;
        int mxLen=Integer.MIN_VALUE;
        int sum=0;
        while(i<arr.length && j<arr.length){
            if(sum>k){
                sum-=arr[j];
                j++;
            } else {
                sum+=arr[i];
                if(sum <= k){
                    mxLen=Math.max(mxLen,i-j+1);
                }
                i++;
            }

        }

        System.out.print(mxLen);
    }

    public static void main(String[] args) {
        SlidingWindow window=new SlidingWindow();
        System.out.println("Maximum sum in windows of size K: ");
        window.maxSumInWindowOfSizeK(new int[]{2,1,8,4,5,9,9,1},3);

        System.out.println("\nLongest Subarray with Sum less than or equal to K: ");
        window.longestSubarrayWithSumLessOrEqualToK(new int[]{1,1,8,4,5,9,9,1},2);
    }
}
