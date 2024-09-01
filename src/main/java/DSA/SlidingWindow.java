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
        while(j<=i && i<arr.length){
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

    public void maxScore(int[] cardPoints, int k) {
        int lsum =0, rsum =0, max =0,sum =0;
        int n = cardPoints.length;
        for(int i=0;i<k;i++){
            lsum = lsum + cardPoints[i];
        }
        max = lsum;
        for (int i = 0; i < k; i++) {
            lsum -= cardPoints[k-1-i];
            rsum += cardPoints[cardPoints.length -1-i];
            sum = lsum+rsum;
            if(sum>max){
                max = sum;
            }
        }
        System.out.println(max);;
    }

    public void longestSubstringWithoutRepeatingCharacters(String str){
        int[] hash=new int[256];
        Arrays.fill(hash,-1);
        int n=str.length();
        int l=0, r=0, mxLen=0;
        int len=0;
        String res="";
        while(r < n){
            if(hash[str.charAt(r)]!=-1){
                if(hash[str.charAt(r)]>=l){
                    l=hash[str.charAt(r)]+1;
                }
            }
            len=r-l+1;
            if(len>mxLen){
                mxLen=len;
                res=str.substring(l,r+1);
            }
            hash[str.charAt(r)]=r;
            r++;
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        SlidingWindow window=new SlidingWindow();
        System.out.println("Maximum sum in windows of size K: ");
        window.maxSumInWindowOfSizeK(new int[]{2,1,8,4,5,9,9,1},3);

        System.out.println("\nLongest Subarray with Sum less than or equal to K: ");
        window.longestSubarrayWithSumLessOrEqualToK(new int[]{1,1,8,4,5,9,9,1},2);

        System.out.println("\nMaximum points you can obtain from cards: ");
        window.maxScore(new int[]{6,2,3,4,7,2,1,7,1},4);

        System.out.println("\nLongest Substring without repeating characters: ");
        window.longestSubstringWithoutRepeatingCharacters("caddbzabcd");
    }
}
