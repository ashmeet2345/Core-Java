package DSA;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class SlidingWindow {

    public void maxSumInWindowOfSizeK(int[] arr,int k){
        int mxsum=Integer.MIN_VALUE;
        int sum=0;

        if(k>arr.length || k== arr.length){
            for(int i=0;i<arr.length;i++){
                sum+=arr[i];
            }
            System.out.println(sum);
            return;
        }

        for(int i=0;i<k;i++){
            sum+=arr[i];
        }

        for(int i=k;i<arr.length;i++){
            int j=i-k+1;
            if(sum-arr[j-1]+arr[i]>mxsum){
                mxsum=sum-arr[j-1]+arr[i];
                sum=mxsum;
            }
        }

        System.out.println(mxsum);

    }


    public static void main(String[] args) {
        SlidingWindow window=new SlidingWindow();
        window.maxSumInWindowOfSizeK(new int[]{2,1,8,4,5,9,9,1},3);
    }
}
