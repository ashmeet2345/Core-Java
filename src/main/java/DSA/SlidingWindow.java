package DSA;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class SlidingWindow {

    public static class Pair{
        int i;
        int j;
        Pair(int i,int j){
            this.i=i;
            this.j=j;
        }
    }

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

    public void maximumConsecutiveOnesIII(int[] arr, int k){
        int len=0;
        int mxLen=Integer.MIN_VALUE;
        int convert0To1=0;
        int i=0, j=0;
        while(i<arr.length && i>=j){
            if(arr[i]==1){
                len+=1;
                i++;
            } else {
                if(convert0To1<k){
                    convert0To1+=1;
                    len+=1;
                    i++;
                } else {
                    if(arr[j]==0){
                        convert0To1--;
                    }
                    len--;
                    j++;
                }
            }
            if(len>mxLen){
                mxLen=len;
            }
        }
        System.out.println(mxLen);
    }

    public void fruitIntoBaskets(int[] arr){

        Map<Integer,Integer> mp=new HashMap<>();

        int mxCount=Integer.MIN_VALUE;
        int cnt=0;
        int i=0;
        int j=0;
        while(i<arr.length && i>=j){
             if(mp.size()<=2){
                 mp.put(arr[i],mp.getOrDefault(arr[i],0)+1);
                 cnt++;
                 i++;
             }

             if(mp.size()>2){
                 int count=mp.get(arr[j]);
                 if(count>1){
                     mp.put(arr[j],count-1);
                 } else {
                     mp.remove(arr[j]);
                 }
                 cnt--;
                 j++;
             }
             if(cnt>mxCount){
                 mxCount=cnt;
             }
        }
        System.out.println(mxCount);
    }

    public void longestSubstringWithAtmostKDistinctCharacters(String s, int k){
        Map<Character,Integer> mp=new HashMap<>();
        int count=0;
        int mxLen=0;
        int i=0;
        int j=0;
        while(i>=j && i<s.length()){
            if(mp.size()<=k){
                mp.put(s.charAt(i),mp.getOrDefault(s.charAt(i),0)+1);
                count++;
                i++;
            }
            if(mp.size()>k){
                int cnt=mp.get(s.charAt(j));
                if(cnt>1){
                    mp.put(s.charAt(j),cnt-1);
                } else {
                    mp.remove(s.charAt(j));
                }
                count--;
                j++;
            }
            if(count>mxLen){
                mxLen=count;
            }
        }
        if(k>mp.size()){
            int cnt=0;
            for(int val: mp.values()){
                if(val==k){
                    cnt+=val;
                }
            }
            System.out.println(cnt);
            return;
        }
        System.out.println(mxLen);
    }

    public void numberOfSubstringsContainingAllThreeCharacters(String str){
        int[] lastSeen={-1,-1,-1};
        int count=0;
        for(int i=0;i<str.length();i++){
            lastSeen[str.charAt(i)-'a']=i;
            if(lastSeen[0]!=-1 && lastSeen[1]!=-1 && lastSeen[2]!=-1){
                count+=1+Math.min(Math.min(lastSeen[0],lastSeen[1]),lastSeen[2]);
            }
        }
        System.out.println(count);
    }

    public void countSubarraySumEqualsK(int[] arr, int k){
        int sumYet=0;
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(sumYet>=0){
                sumYet+=arr[i];
            } else {
                sumYet=0;
            }
            if(sumYet >= k){
                count++;
            }
        }
        System.out.println(count);
    }

    public int findAllSubarraysWithGivenSum(int arr[], int k) {
        int n = arr.length; // size of the given array.
        Map<Integer,Integer> mpp = new HashMap();
        int preSum = 0, cnt = 0;

        mpp.put(0, 1); // Setting 0 in the map.
        for (int i = 0; i < n; i++) {
            // add current element to prefix Sum:
            preSum += arr[i];

            // Calculate x-k:
            int remove = preSum - k;

            // Add the number of subarrays to be removed:
            cnt += mpp.getOrDefault(remove, 0);

            // Update the count of prefix sum
            // in the map.
            mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1);
        }
        return cnt;
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

        System.out.println("\nMaximum consecutive ones III: ");
        window.maximumConsecutiveOnesIII(new int[]{1,1,1,0,0,0,0,1,1,1,1,1},3);

        System.out.println("\nFruit Into Basket: ");
        window.fruitIntoBaskets(new int[]{3,3,3,1,1,1,1,3,3,4});

        System.out.println("\nLongest Substring with At most K distinct Characters: ");
        window.longestSubstringWithAtmostKDistinctCharacters("aaabb",3);

        System.out.println("\nNumber of substring containing all three characters: ");
        window.numberOfSubstringsContainingAllThreeCharacters("bbacba");

        System.out.println("\nCount Subarray sum Equals K: ");
        window.countSubarraySumEqualsK(new int[]{3,1,2,4},6);

        System.out.println("\nStrivers answer: ");
        System.out.println(window.findAllSubarraysWithGivenSum(new int[]{3,1,2,4},6));
    }
}
