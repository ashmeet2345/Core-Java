package DSA;

import java.util.ArrayDeque;
import java.util.Arrays;

public class DynamicProgrammingLevel2 {

    public static class Pair implements Comparable<Pair>{
        int i;
        int s;
        int j;
        String path;

        public Pair(int i, int s, int j, String path) {
            this.i = i;
            this.s = s;
            this.j = j;
            this.path=path;
        }

        public Pair(int i,int j,String path){
            this.i=i;
            this.j=j;
            this.path=path;
        }

        public Pair(int i,int j){
            this.i=i;
            this.j=j;
        }

        @Override
        public int compareTo(Pair o) {
            return this.i-o.i;
        }
    }

    public void largestSquareSubmatrixOfAll1s(int[][] matrix){
        int[][] dp=new int[matrix.length][matrix[0].length];
        for(int j=dp[0].length-1;j>=0;j--){
            dp[dp.length-1][j]=matrix[matrix.length-1][j];
        }

        for(int i=dp.length-1;i>=0;i--){
            dp[i][dp[0].length-1]=matrix[i][matrix[0].length-1];
        }
        int max=0;
        for(int i=dp.length-2;i>=0;i--){
            for(int j=dp[0].length-2;j>=0;j--){
                if(matrix[i][j]==0){
                    dp[i][j]=0;
                } else {
                    dp[i][j]=Math.min(dp[i+1][j],Math.min(dp[i][j+1],dp[i+1][j+1]))+1;
                    if(dp[i][j]>max){
                        max=dp[i][j];
                    }
                }
            }
        }
        System.out.println(max);
    }

    public void printAllPathsWithMinimumJumps(int[] jumps){
        int n=jumps.length;
        Integer[] dp=new Integer[jumps.length];
        dp[n-1]=0;

        for(int i=n-2;i>=0;i--){
            int mn=Integer.MAX_VALUE;
            if(jumps[i]==0){
                dp[i]= Integer.MAX_VALUE;
            } else {
                for(int j=1;j<=jumps[i] && i+j<dp.length;j++){
                    mn=Math.min(dp[i+j],mn);
                }
                if(mn!=Integer.MAX_VALUE){
                    dp[i]=mn+1;
                }
            }
        }
    }

    public void printAllMinimumCostPath(int[][] grid){
        int[][] dp=new int[grid.length][grid[0].length];
        for(int i=dp.length-1;i>=0;i--){
            for(int j=dp[0].length-1;j>=0;j--){
                if(i==dp.length-1 && j==dp[0].length-1){
                    dp[i][j]=grid[i][j];
                }else if(i==dp.length-1){
                    dp[i][j]=dp[i][j+1]+grid[i][j];
                }else if(j==dp[0].length-1){
                    dp[i][j]=dp[i+1][j]+grid[i][j];
                }else{
                    dp[i][j]=Math.min(dp[i+1][j],dp[i][j+1])+grid[i][j];
                }
            }
        }

        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        ArrayDeque<Pair> queue=new ArrayDeque<>();
        queue.add(new Pair(0,0,""));
        while(!queue.isEmpty()){
            Pair rem=queue.removeFirst();

           if(rem.i==dp.length-1 && rem.j==dp[0].length-1){
               System.out.println(rem.path+"");
           }

            if(rem.i+1 < dp.length && rem.j+1<dp[0].length){
                if(dp[rem.i+1][rem.j] < dp[rem.i][rem.j+1]){
                    queue.add(new Pair(rem.i+1,rem.j,rem.path+"V"));
                } else if(dp[rem.i+1][rem.j] > dp[rem.i][rem.j+1]){
                    queue.add(new Pair(rem.i,rem.j+1,rem.path+"H"));
                } else {
                    queue.add(new Pair(rem.i+1,rem.j,rem.path+"V"));
                    queue.add(new Pair(rem.i,rem.j+1,rem.path+"H"));
                }
            }
        }
    }

    public void printSubsetsWithTargetSum(int[] arr,int target){
        boolean[][] dp=new boolean[arr.length+1][target+1];

        for(int j=0;j<dp[0].length;j++){
            dp[0][j]=false;
        }

        for(int i=0;i<dp.length;i++){
            dp[i][0]=true;
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(dp[i-1][j])
                    dp[i][j]=true;
                else if(j>=arr[i-1]){
                    if(dp[i-1][j-arr[i-1]])
                        dp[i][j]=true;
                }
            }
        }
        System.out.println(dp[arr.length][target]);
        ArrayDeque<Pair> queue=new ArrayDeque<>();
        queue.add(new Pair(arr.length,target,""));
        while(!queue.isEmpty()){
            Pair rem=queue.removeFirst();

            if(rem.i==0 && rem.j==0){
                System.out.print(rem.path+" ");
            }else {
                if (rem.j >= arr[rem.i - 1]) {
                    boolean inc = dp[rem.i - 1][rem.j - arr[rem.i - 1]];
                    if (inc) {
                        queue.add(new Pair(rem.i - 1, rem.j - arr[rem.i - 1], (rem.i - 1) + " " + rem.path));
                    }
                }
                boolean exc=dp[rem.i-1][rem.j];
                if (exc) {
                    queue.add(new Pair(rem.i - 1, rem.j, rem.path));
                }
            }
        }
    }

    public void longestIncreasingSubsequence(int[] arr){
        int[] dp=new int[arr.length];
        dp[0]=1;
        int index=0;
        int maximum=Integer.MIN_VALUE;
        for(int i=1;i<dp.length;i++){
            int max=0;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]){
                    max=Math.max(dp[j],max);
                }
            }
            dp[i]=max+1;
            if(dp[i]>maximum){
                maximum=dp[i];
                index=i;
            }
        }
        System.out.println(maximum+" at "+index+"th index.");
    }

    public void maximumSumIncreasingSubsequence(int[] arr){
        int[] dp=new int[arr.length];
        dp[0]=arr[0];

        int maximum=Integer.MIN_VALUE;
        int index=0;
        for(int i=1;i<dp.length;i++){
            int max=0;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]){
                    max=Math.max(dp[j],max);
                }
            }
            dp[i]=max+arr[i];
            if(dp[i]>maximum){
                maximum=dp[i];
                index=i;
            }
        }
        System.out.println(maximum+" at "+index+"th index.");
    }

    public void longestBitonicSubsequence(int[] arr){
        int[] dp=new int[arr.length];
        dp[0]=1;
        int maximum=Integer.MIN_VALUE;
        for(int i=1;i<dp.length;i++){
            int max=0;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]){
                    max=Math.max(dp[j],max);
                }
            }
            dp[i]=max+1;
            if(dp[i]>maximum){
                maximum=dp[i];
            }
        }

        int maxim=Integer.MIN_VALUE;
        int[] dp2=new int[arr.length];
        dp2[arr.length-1]=1;
         for(int i=arr.length-2;i>=0;i--){
             int max=0;
             for(int j=arr.length-1;j>i;j--){
                 if(arr[i]>arr[j]){
                     max=Math.max(dp2[j],max);
                 }
             }
             dp2[i]=max+1;
             if(dp2[i]>maxim){
                maxim=dp2[i];
             }
         }

         int mx=0;
         for(int i=0;i<dp.length;i++){
             if(dp[i]+dp2[i]>mx){
                 mx=dp[i]+dp2[i];
             }
         }
        System.out.print(mx-1);
    }

    public void maximumNonOverlappingBridges(Pair[] arr){
        Arrays.sort(arr);

        int[] dp=new int[arr.length];
        int maximum=Integer.MIN_VALUE;
        dp[0]=1;
        for(int i=1;i<dp.length;i++){
            int max=0;
            for(int j=0;j<i;j++){
                if(arr[i].j>arr[j].j){
                    max=Math.max(max,dp[j]);
                }
            }
            dp[i]=max+1;
            if(dp[i]>maximum){
                maximum=dp[i];
            }
        }
        System.out.println(maximum);
    }

    public static void main(String[] args) {
        DynamicProgrammingLevel2 dp=new DynamicProgrammingLevel2();

        int[][] matrix={{0,1,0,1,0,1},
                {1,0,1,0,1,0},
                {0,1,1,1,1,0},
                {0,0,1,1,1,0},
                {1,1,1,1,1,1}};

        System.out.print("Largest Square submatrix of All 1s: ");
        dp.largestSquareSubmatrixOfAll1s(matrix);

        dp.printAllPathsWithMinimumJumps(new int[]{3,3,0,2,1,2,4,2,0,0});

        System.out.println("Print All minimum cost paths:- ");
        matrix=new int[][]{
                {2,6,1,1,3},
                {9,1,1,0,5},
                {0,7,5,2,0},
                {4,3,0,4,7},
                {2,0,1,4,1}};
        dp.printAllMinimumCostPath(matrix);

        System.out.print("Print Subset with Target Sum: ");
        dp.printSubsetsWithTargetSum(new int[]{4,2,7,1,3},10);
        System.out.println();

        System.out.print("Longest increasing subsequence: ");
        dp.longestIncreasingSubsequence(new int[]{10,22,9,33,21,50,41,60,80,3});

        System.out.print("Maximum sum increasing subsequence: ");
        dp.maximumSumIncreasingSubsequence(new int[]{10,22,9,33,21,50,41,60,80,3});

        System.out.print("Longest Bitonic subsequence: ");
        dp.longestBitonicSubsequence(new int[]{10,22,9,33,21,50,41,60,80,3});

        System.out.print("Maximum Non-Overlapping Bridges: ");
        Pair[] arr=new Pair[8];
        arr[0]=new Pair(0,2);
        arr[1]=new Pair(2,0);
        arr[2]=new Pair(1,1);
        arr[3]=new Pair(3,3);
        arr[7]=new Pair(4,5);
        arr[6]=new Pair(5,4);
        arr[5]=new Pair(6,6);
        arr[4]=new Pair(7,7);
        dp.maximumNonOverlappingBridges(arr);
    }
}
