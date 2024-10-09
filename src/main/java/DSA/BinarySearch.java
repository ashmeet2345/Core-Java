package DSA;

public class BinarySearch {

    public void search(int[] arr,int ele){
        int low=0;
        int high=arr.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]==ele){
                System.out.println(mid);
                return;
            } else if (arr[mid] > ele){
                high=mid-1;
            } else {
                low=mid+1;
            }
        }
        System.out.println("Element not found");
        return;
    }

    public void lowerBound(int[] arr,int ele){
        int low=0;
        int high=arr.length-1;
        int ans=arr.length;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid] >= ele){
                ans=mid;
                high=mid-1;
            } else {
                low=mid+1;
            }
        }
        System.out.println(ans);
    }

    public void floorValue(int[] arr, int num){
        int ans=-1;
        int low=0;
        int high=arr.length-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(arr[mid] <= num){
                ans=arr[mid];
                low=low+1;
            } else {
                high=high-1;
            }
        }
        System.out.println(ans);
    }

    public void ceilValue(int[] arr, int num){
        int ans=-1;
        int low=0;
        int high=arr.length-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(arr[mid]>=num){
                ans=arr[mid];
                high=high-1;
            } else {
                low=low+1;
            }
        }
        System.out.println(ans);
    }

    public int firstOccurence(int[] arr, int n, int num){
        int ans=-1;
        int low=0;
        int high=n-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(arr[mid] == num){
                ans=mid;
                high=mid-1;
            } else if(arr[mid] < num){
                low=mid+1;
            } else {
                high=mid-1;
            }
        }
        return ans;
    }

    public int lastOccurence(int[] arr, int n, int num){
        int ans=-1;
        int low=0;
        int high=n-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(arr[mid] == num){
                ans=mid;
                low=mid+1;
            } else if(arr[mid] > num){
                high=mid-1;
            } else {
                low=mid+1;
            }
        }
        return ans;
    }

    public void findFirstAndLastOccurences(int[] arr, int num){
        int n=arr.length;
        int first=firstOccurence(arr,n,num);
        int last=lastOccurence(arr,n,num);
        System.out.println(first+","+last);
    }

    public void searchElementInRotatedSortedArray1(int[] arr, int ele){
        int low=0;
        int high=arr.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]==ele){
                System.out.println(mid);
                return;
            }
            if(arr[low] <= arr[mid]){
               if(arr[low] <= ele && ele <= arr[mid]){
                   high=mid-1;
               } else {
                   low=mid+1;
               }
            } else {
                if(arr[mid]<=ele && ele <= arr[high]){
                    low=mid+1;
                } else {
                    high=mid-1;
                }
            }
        }
        System.out.println("Element not found");
    }

    public void searchElementInRotatedSortedArray2(int[] arr, int ele){
        int low=0;
        int high=arr.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]==ele){
                System.out.println(mid);
                return;
            }
            if(arr[low]==arr[mid] && arr[mid]==arr[high]){
                low=low+1;
                high=high-1;
                continue;
            }
            if(arr[low] <= arr[mid]){
                if(arr[low] <= ele && ele <= arr[mid]){
                    high=mid-1;
                } else {
                    low=mid+1;
                }
            } else {
                if(arr[mid]<=ele && ele <= arr[high]){
                    low=mid+1;
                } else {
                    high=mid-1;
                }
            }
        }
        System.out.println("Element not found");
    }

    public void minimumInRotatedSortedArray(int[] arr){
        int low=0;
        int high=arr.length-1;
        int min=Integer.MAX_VALUE;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[low] <= arr[high]){
                min=Math.min(min,arr[low]);
                break;
            }

            if(arr[low]<=arr[mid]){
                min=Math.min(min,arr[low]);
                low=mid+1;
            } else {
                high=mid-1;
                min=Math.min(min,arr[mid]);
            }
        }
        System.out.println(min);
    }

    public int singleElementInSortedArray(int[] arr){
        int low=0;
        int high=arr.length-1;
        if(arr.length==1) return arr[0];
        if(arr[0]!=arr[1]) return arr[0];
        if(arr[arr.length-1]!=arr[arr.length-2]) return arr[arr.length-1];
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]!=arr[mid+1] && arr[mid]!=arr[mid-1]){
                return arr[mid];
            }
            if((mid%2!=0 && arr[mid-1]==arr[mid]) || ((mid&2)==0 && arr[mid]==arr[mid+1])){
                low=mid+1;
            } else {
                high=mid-1;
            }
        }
        return -1;
    }

    public int findPeakElement(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        if (arr[low] > arr[low + 1]) {
            return arr[low];
        }
        if (arr[high] > arr[high - 1]) {
            return arr[high];
        }
        low++;
        high--;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return arr[mid];
            } else if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public void sqrtOfANumber(int num){
        int low=1;
        int high=num;
        int ans=0;
        while(low<=high){
            int mid=(low+high)/2;
            if(mid*mid <= num){
                ans=mid;
                low=mid+1;
            } else {
                high=mid-1;
            }
        }
        System.out.println(ans);
    }


    public int exponential(int a, int n){
        if(n==1){
            return a;
        }
        if(n%2==0){
            return exponential(a,n/2)*exponential(a,n/2);
        } else {
            return exponential(a,n/2)*exponential(a,n/2)*a;
        }
    }

    public void nthRootOfAnyNumber(int num, int root){
        int low=1;
        int high=num;
        int ans=0;
        while(low<=high){
            int mid=(low+high)/2;
            if(exponential(mid,root) <= num){
                ans=mid;
                low=mid+1;
            } else {
                high=mid-1;
            }
        }
        System.out.println(ans);
    }

    public int totalHours(int[] arr,int hours){
        int total=0;
        for(int i=0;i<arr.length;i++){
            total+=Math.ceil(arr[i]/hours);
        }
        return total;
    }

    public void kokoEatingBananas(int[] arr, int hours){
        int low=1;
        int high=arr[arr.length-1];
        int ans=Integer.MAX_VALUE;
        while(low<=high){
            int mid=(low+high)/2;
            int total=totalHours(arr,mid);
            if(total<=hours){
                ans=mid;
                high=mid-1;
            } else {
                low=mid+1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        BinarySearch bs=new BinarySearch();
        System.out.print("Binary Search: ");
        bs.search(new int[]{1,5,6,9,12,15,25,26,50},12);

        System.out.print("Lower Bound: ");
        bs.lowerBound(new int[]{1,5,6,9,12,12,15,25,26,50},13);

        System.out.print("Search in rotated sorted array 1: ");
        bs.searchElementInRotatedSortedArray1(new int[]{25,26,50,1,5,6,9,12,15},1);

        System.out.print("Search in rotated sorted array 2: ");
        bs.searchElementInRotatedSortedArray2(new int[]{25,25,26,50,1,25,25,25},1);

        System.out.println("Minimum in rotated sorted array: ");
        bs.minimumInRotatedSortedArray(new int[]{25,26,50,1,5,6,9,12,15});

        System.out.println("Single element in a sorted array: "+bs.singleElementInSortedArray(new int[]{1,1,2,2,3,3,4,4,5,5,6}));

        System.out.println("Peak Element: "+bs.findPeakElement(new int[]{1,99,6,5,4,3}));

        System.out.print("Floor value: ");
        bs.floorValue(new int[]{10,20,30,40,50},25);

        System.out.print("Ceil value: ");
        bs.ceilValue(new int[]{10,20,30,40,50},25);

        System.out.print("First and last occurence: ");
        bs.findFirstAndLastOccurences(new int[]{2,8,8,8,8,8,11,13},10);

        System.out.print("Square root of a number (Floor value) : ");
        bs.sqrtOfANumber(27);

        System.out.print("Nth root of any number: ");
        bs.nthRootOfAnyNumber(125,3);

        System.out.print("Koko Eating Bananas: ");
        bs.kokoEatingBananas(new int[]{3,6,7,11},8);
    }
}
