package DSA;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Array {

    public static class Pair implements Comparable<Pair>{
        int first;
        int second;

        public Pair(int first, int second){
            this.first=first;
            this.second=second;
        }

        @Override
        public int compareTo(Pair o) {
            return this.first-o.first;
        }

        @Override
        public String toString() {
            return "["+first+","+second+"]";
        }
    }

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
        int[] intersection=new int[n1>n2?n2:n1];
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

        Arrays.stream(intersection).forEach(s-> System.out.print(s+" "));
    }

    public void findMissingNumber(int[] arr){
        int xor1=0;
        int xor2=0;
        for(int i=0;i<arr.length-1;i++){
            xor1^=(i+1);
            xor2^=arr[i];
        }
        xor1^=arr.length;
        System.out.println(xor1^xor2);
    }

    public void findElementThatAppearsOnce(int[] arr){
        int xor=0;
        for(int i=0;i<arr.length;i++){
            xor^=arr[i];
        }
        System.out.println(xor);
    }

    public void longestSubarrayWithSumK(int[] arr, int k){
        int i=-1;
        int j=-1;
        int mxLen=Integer.MIN_VALUE;
        int len=0;
        int sum=0;
        while(true){
            boolean t1=false,t2=false;
            while(i<arr.length-1){
                t1=true;
                i++;
                sum+=arr[i];
                len++;
                if(sum<k){
                    continue;
                } else if(sum==k){
                    mxLen=Math.max(mxLen,len);
                } else {
                    break;
                }
            }

            while(j<=i && i<arr.length-1){
                t2=true;
                j++;
                sum-=arr[j];
                len--;
                if(sum>k){
                    continue;
                } else if(sum==k){
                    mxLen=Math.max(mxLen,len);
                } else {
                    break;
                }
            }

            if(!t1 && !t2){
                break;
            }
        }

        System.out.println(mxLen);
    }

    public void twoSumProblem(int[] arr, int k){
        Arrays.sort(arr);
        int i=0;
        int j=arr.length-1;
        while(i<=j){
            if(arr[i]+arr[j]==k){
                System.out.println("Yes");
                break;
            } else if(arr[i]+arr[j]<k){
                i++;
            } else {
                j--;
            }
        }
    }

    public void sortAnArrayof0s1sAnd2s(int[] arr){
        int low=0;
        int mid=0;
        int high=arr.length-1;
        //we will be following dutch national flag algorithm
        //here index 0 - low-1 ---> 0s
        //low - mid-1  ----> 1s
        //high+1 - n-1 ----> 2s
        //so we have to keep checking for mid - high

        while(mid<=high){
            if(arr[mid]==0){
                swap(arr,low,mid);
                low++;
                mid++;
            }
            else if(arr[mid] == 1){
                mid++;
            }
            else {
                swap(arr,mid,high);
                high--;
            }
        }

        Arrays.stream(arr).forEach(a-> System.out.print(a+" "));
    }

    public void majorityElement1(int[] arr){
        int m=arr.length/2;
        int ele=0;
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(count==0){
                count=1;
                ele=arr[i];
            } else if(arr[i]==ele){
                count++;
            } else {
                count--;
            }
        }

        count=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==ele){
                count++;
            }
        }
        if(count>m){
            System.out.print(ele);
        }
    }

    public void kadanesAlgorithm(int[] arr){
        int omax=Integer.MIN_VALUE;
        int cmax=0;
        for(int i=0;i<arr.length;i++){
            cmax+=arr[i];

            if(cmax>omax){
                omax=cmax;
            }
            if(cmax < 0){
                cmax=0;
            }
        }
        System.out.println(omax);
    }

    public void rearrangeArrayElementsBySign(int[] arr){

        ArrayList<Integer> pos=new ArrayList<>();
        ArrayList<Integer> neg=new ArrayList<>();

        for(int i=0;i<arr.length;i++){
            if(arr[i]>=0) pos.add(arr[i]);
            else neg.add(arr[i]);
        }

        if(pos.size()==neg.size()){
            for(int i=0;i<pos.size();i++){
                arr[2*i]=pos.get(i);
                arr[(2*i)+1]=neg.get(i);
            }
        } else {
            if(pos.size()>neg.size()){
                int p=0;
                for(int i=0;i<neg.size();i++){
                    arr[2*i]=pos.get(i);
                    arr[(2*i)+1]=neg.get(i);
                    p+=2;
                }
                for(int i=neg.size();i<pos.size();i++){
                    arr[p++]=pos.get(i);
                }
            } else {
                int n=0;
                for(int i=0;i<pos.size();i++){
                    arr[2*i]=pos.get(i);
                    arr[(2*i)+1]=neg.get(i);
                    n+=2;
                }
                for(int i=pos.size();i<neg.size();i++){
                    arr[n++]=neg.get(i);
                }
            }
        }
       Arrays.stream(arr).forEach(s-> System.out.print(s+" "));
    }

    public void nextPermutation(List<Integer> arr){
        int ind=-1;
        for(int i=arr.size()-2;i>=0;i--){
            if(arr.get(i) < arr.get(i+1)){
                ind=i;
                break;
            }
        }
        if(ind == -1){
            Collections.reverse(arr);
            arr.stream().forEach(s-> System.out.print(s+" "));
            return;
        }

        for(int i=arr.size()-1;i>ind;i--){
            if(arr.get(i)>arr.get(ind)){
                Collections.swap(arr,ind,i);
                break;
            }
        }

        Collections.sort(arr.subList(ind+1,arr.size()));
        arr.stream().forEach(s-> System.out.print(s+" "));
    }

    public void leadersInAnArray(int[] arr){
        int n=arr.length;
        List<Integer> res=new ArrayList<>();
        int mx=Integer.MIN_VALUE;
        for(int i=n-1;i>=0;i--){
            if(arr[i]>mx){
                mx=arr[i];
                res.add(arr[i]);
            }
        }

        res.stream().forEach(s-> System.out.print(s+" "));
    }

    public void longestConsecutiveSequence(int[] arr){
        TreeSet<Integer> set=new TreeSet<>();
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        int len=1;
        int mxLen=Integer.MIN_VALUE;
        for(Integer it:set){
            if(set.contains(it+1)){
                len++;
            } else {
                len=1;
            }
            mxLen=Math.max(len,mxLen);
        }
        System.out.println(mxLen);
    }

    public void countSubarraySumEqualsK(int[] arr, int k){
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int sum=0;
        int count=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            int rsum=sum-k;
            if(!map.containsKey(rsum)){
                map.put(sum,1);
            } else {
                count+=map.get(rsum);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }

        System.out.println(count);

    }

    public void majorityElementII(int[] arr){
        int count1=0,count2=0,ele1=0,ele2=0;
        for(int i=0;i<arr.length;i++){
            if(count1 == 0 && arr[i]!=ele2){
                count1=1;
                ele1=arr[i];
            } else if(count2 == 0 && arr[i]!=ele1){
                count2=1;
                ele2=arr[i];
            }else if(ele1 == arr[i]){
                count1++;
            }else if(ele2 == arr[i]){
                count2++;
            }else{
                count1--;
                count2--;
            }
        }

        count1=0;
        count2=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==ele1){
                count1++;
            }else if(arr[i]==ele2){
                count2++;
            }
        }
        int mini=(int)(arr.length/3)+1;
        if(count1>=mini) System.out.print(ele1+" ");
        if(count2>=mini) System.out.print(ele2+" ");
    }


    public void threeSum(int[] arr){
        int i=0;
        int j=1;
        int k=arr.length-1;
        Arrays.sort(arr);
        Set<List<Integer>> set=new HashSet<>();
        while(i<arr.length-1){
            if(j<=k){
                if(arr[i]+arr[j]+arr[k]==0){
                    set.add(Arrays.asList(arr[i],arr[j],arr[k]));
                    j++;
                    k--;
                } else if(arr[i]+arr[j]+arr[k]<0){
                    j++;
                } else {
                    k--;
                }
            } else {
                i++;
                if(arr[i]==arr[i-1]){
                    continue;
                } else {
                    j=i+1;
                    k=arr.length-1;
                }
            }
        }
        for(List<Integer> list:set){
            System.out.println(list);
        }
    }

    public void fourSum(int[] arr,int target){
        int n=arr.length;
        int i=0;
        int j=i+1;
        int k=j+1;
        int l=n-1;
        Set<List<Integer>> set=new HashSet<>();
        while(i<arr.length-2){
            if(j<arr.length-1){
                if(k<=l){
                    if(arr[i]+arr[j]+arr[k]+arr[l]==target){
                        set.add(Arrays.asList(arr[i],arr[j],arr[k],arr[l]));
                        k++;
                        l--;
                        if(arr[k]==arr[k-1] || arr[l]==arr[l+1]){
                            continue;
                        }
                    } else if(arr[i]+arr[j]+arr[k]+arr[l] < target){
                        l--;
                    } else {
                        k++;
                    }
                } else {
                    j++;
                    if(arr[j]==arr[j-1]){
                        continue;
                    }
                    k=j+1;
                    l=n-1;
                }
            }else{
                i++;
                if(arr[i]==arr[i-1]){
                    continue;
                }
                j=i+1;
                k=j+1;
                l=n-1;
            }
        }

        for(List<Integer> list:set){
            System.out.println(list);
        }
    }

    public void mergeOverlappingIntervals(Pair[] arr){
        Arrays.sort(arr);
        Stack<Pair> st=new Stack<>();
        st.push(arr[0]);
        for(int i=1;i<arr.length;i++){
            if(st.peek().second >= arr[i].first){
                if(st.peek().second < arr[i].second){
                    Pair p=st.pop();
                    p.second=arr[i].second;
                    st.add(p);
                }
            } else {
                st.add(arr[i]);
            }
        }

        while(st.size()>0){
            System.out.println(st.pop()+" ");
        }
    }

    public void productOfArrayExceptItself(int[] arr){
        int[] left=new int[arr.length];
        int[] right=new int[arr.length];
        int[] res=new int[arr.length];

        left[0]=arr[0];
        right[arr.length-1]=arr[arr.length-1];
        for(int i=1;i<arr.length;i++){
            left[i]=left[i-1]*arr[i];
        }
        for(int i=arr.length-2;i>=0;i--){
            right[i]=right[i+1]*arr[i];
        }

        for(int i=0;i<arr.length;i++){
            if(i==0){
                res[i]=right[i+1];
            }else if(i==arr.length-1){
                res[i]=left[i-1];
            }else{
                res[i]=left[i-1]*right[i+1];
            }
        }

        Arrays.stream(res).forEach(s-> System.out.print(s+" "));
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
        System.out.println();

        System.out.print("Intersection of 2 sorted arrays: ");
        array.intersectionOfTwoSortedArrays(new int[]{1,2,2,3,3,4,5,6},new int[]{2,3,3,5,6,6,7});

        System.out.print("\nFind missing number in the array: ");
        array.findMissingNumber(new int[]{1,2,4,5});

        System.out.print("Find element that appears once: ");
        array.findElementThatAppearsOnce(new int[]{1,1,2,2,3,3,4,4,5});

        System.out.println("Longest Subarray with Sum K: ");
        array.longestSubarrayWithSumK(new int[]{1,2,3,1,1,1,1,4,2,3},9);

        System.out.println("Two sum problem: ");
        array.twoSumProblem(new int[]{2,6,5,8,11},14);

        System.out.println("Sort an array of 0s 1s & 2s");
        array.sortAnArrayof0s1sAnd2s(new int[]{0,1,1,0,1,2,1,2,0,0,0});

        System.out.println("\nMajority Element 1: ");
        array.majorityElement1(new int[]{1,2,3,1,1,1,1,1,2,3});

        System.out.print("\nKadane's Algorithm: ");
        array.kadanesAlgorithm(new int[]{-2,-3,4,-1,-2,1,5,-3});

        System.out.println("\nRearrange Array elements by sign: ");
        array.rearrangeArrayElementsBySign(new int[]{2,-1,5,6,-7,4,9,-2,-5,10,12});

        System.out.println("\nNext Permutation: ");
        array.nextPermutation(Arrays.asList(2,1,5,4,3,0,0));

        System.out.println("\nLeaders in an Array: ");
        array.leadersInAnArray(new int[]{10,22,12,3,0,6});

        System.out.println("\nLongest Consecutive Sequence: ");
        array.longestConsecutiveSequence(new int[]{102,4,100,1,101,3,2,1,103,104});

        System.out.print("\nCount subarray sum equals k: ");
        array.countSubarraySumEqualsK(new int[]{1,2,3,-3,1,1,1,4,2,-3},3);

        System.out.println("\nMajority Element II: ");
        array.majorityElementII(new int[]{2,1,1,1,1,3,2,2,2,2});

        System.out.println("\nThree sum: ");
        array.threeSum(new int[]{-2,-2,-2,-1,-1,-1,0,0,0,2,2,2,2});

        System.out.println("\nFour sum: ");
        array.fourSum(new int[]{1,1,1,2,2,2,3,3,3,4,4,4,5,5},8);

        System.out.println("\nMerge overlapping intervals: ");
        Pair[] arr=new Pair[8];
        Random rand=new Random();
        for(int i=0;i<8;i++){
            int a=rand.nextInt(10)+1;
            int b=rand.nextInt(15)+1;
            Pair p=new Pair(a,b);
            arr[i]=p;
        }
        array.mergeOverlappingIntervals(arr);

        System.out.println("Product of array except itself: ");
        array.productOfArrayExceptItself(new int[]{1,2,3,2,5,4});
    }
}
