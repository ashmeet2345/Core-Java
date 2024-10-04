package DSA;

import java.util.*;

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
        System.out.println();

        System.out.print("Find missing number in the array: ");
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
    }
}
