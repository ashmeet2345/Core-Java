package DSA.Tree;

import java.util.HashMap;
import java.util.Map;

public class heapPractice {

    public static void maximumSumSubarrayOfSizeK(int[] arr, int k){
        int j=0;
        int sum=0;
        int maxSum=0;
        int n=arr.length;
        for(;j<k;j++){
            sum+=arr[j];
        }
        maxSum=sum;
        int i=0;
        while(i<j && j<n){
            sum-=arr[i];
            sum+=arr[j];
            maxSum=Math.max(maxSum,sum);
            i++;
            j++;
        }
        System.out.println(maxSum);
    }

    public static void main(String[] args) {
        maximumSumSubarrayOfSizeK(new int[]{100, 200, 300, 400},2);
    }
}
