package DSA;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Stream;

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

    public void perfectSquaresSum(int num){
        int[] dp=new int[num+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        dp[3]=3;
        for(int i=4;i<=num;i++){
            int min=Integer.MAX_VALUE;
            for(int j=2;j*j<=i;j++){
                if(i%(j*j)!=0){
                    min=Math.min(dp[i-(j*j)]+1,min);
                    dp[i]=min;
                }
                if(i%(j*j)==0){
                    dp[i]=1;
                }
            }
        }

        System.out.println(dp[num]);
    }

    public void catalanNumbers(int num){
        int[] dp=new int[num+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=num;i++){
            for(int j=0;j<i;j++){
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }

        System.out.println(dp[num]);
    }

    public void rodCuttingProblem(int[] arr){
        int[] dp=new int[arr.length];
        dp[0]=0;
        dp[1]=arr[1];
        int maxx=Integer.MIN_VALUE;
        for(int i=2;i<arr.length;i++){
            for(int j=1;j<i;j++){
                maxx=Math.max(maxx,Math.max(arr[i],dp[j]+dp[i-j]));
            }
            dp[i]=maxx;
        }

        Arrays.stream(dp).forEach(i-> System.out.print(i+" "));
    }

    public void palindromePartitioningWithMinimumCuts(String str){
        boolean[][] dp=new boolean[str.length()][str.length()];

        for(int g=0;g<str.length();g++){
            for(int i=0,j=g;j<dp.length;i++,j++){
                if(g==0){
                    dp[i][j]=true;
                }else if(g==1){
                    dp[i][j]= str.charAt(i)==str.charAt(j);
                }else{
                    if(str.charAt(i)==str.charAt(j) && dp[i+1][j-1]==true){
                        dp[i][j]=true;
                    } else {
                        dp[i][j]=false;
                    }
                }
            }
        }

        int[] res=new int[str.length()];
        res[0]=0;
        for(int j=1;j<res.length;j++){
            if(dp[0][j]){
                res[j]=0;
                break;
            }
            int min=Integer.MAX_VALUE;
            for(int i=j;i>=1;i--){
                if(dp[i][j]){
                    if(res[i-1]<min){
                        min=res[i-1];
                    }
                }
            }
            res[j]=min+1;
        }
        System.out.println(res[str.length()-1]);
    }

    public void longestCommonSubsequence(String a, String b){
        int[][] dp=new int[a.length()+1][b.length()+1];

        for(int i=a.length()-2;i>=0;i--){
            for(int j=b.length()-2;j>=0;j--){
                if(a.charAt(i)==a.charAt(j)){
                    dp[i][j]=dp[i+1][j+1]+1;
                } else {
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }

        System.out.println(dp[0][0]);
    }

    public void longestPalindromicSubsequence(String s){
        int[][] dp=new int[s.length()][s.length()];
        for(int g=0;g<s.length();g++){
            for(int i=0,j=g;j<s.length();i++,j++){
                if(g==0){
                    dp[i][j]=1;
                }else if(g==1){
                    if(s.charAt(i)==s.charAt(j)){
                        dp[i][j]=2;
                    }else{
                        dp[i][j]=1;
                    }
                }else{
                    if(s.charAt(i)==s.charAt(j)){
                        dp[i][j]=2+dp[i+1][j-1];
                    } else {
                        dp[i][j]=Math.max(dp[i][j-1],dp[i+1][j]);
                    }
                }
            }
        }
        System.out.println(dp[0][s.length()-1]);
    }


    public void countPalindromicSubsequence(String s){
        int[][] dp=new int[s.length()][s.length()];
        for(int g=0;g<s.length();g++){
            for(int i=0,j=g;j<dp.length;i++,j++){
                if(g==0){
                    dp[i][j]=1;
                }else if(g==1){
                    dp[i][j]=s.charAt(i)==s.charAt(j)?3:2;
                }else{
                    if(s.charAt(i)==s.charAt(j)){
                        dp[i][j]=dp[i+1][j]+dp[i][j-1]+1;
                    } else {
                        dp[i][j]=dp[i+1][j]+dp[i][j-1]-dp[i+1][j-1];
                    }
                }
            }
        }
        System.out.println(dp[0][s.length()-1]);
    }

    public void countPalindromicSubstrings(String s){
        boolean[][] dp=new boolean[s.length()][s.length()];
        int count=0;
        for(int g=0;g<s.length();g++){
            for(int i=0,j=g;j<s.length();i++,j++){
                if(g==0){
                    dp[i][j]=true;
                }else if(g==1){
                    dp[i][j]=s.charAt(i)==s.charAt(j);
                }else{
                    if(s.charAt(i)==s.charAt(j)){
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                if(dp[i][j]){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public void longestPalindromicSubstring(String s){
        boolean[][] dp=new boolean[s.length()][s.length()];
        int length=Integer.MIN_VALUE;
        for(int g=0;g<s.length();g++){
            for(int i=0,j=g;j<s.length();i++,j++){
                if(g==0){
                    dp[i][j]=true;
                }else if(g==1){
                    dp[i][j]=s.charAt(i)==s.charAt(j);
                }else{
                    if(s.charAt(i)==s.charAt(j)){
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                if(dp[i][j]){
                    length=Math.max(length,g+1);
                }
            }
        }
        System.out.println(length);
    }

    public int kadanesAlgorithm(int[] arr){
        int curr=arr[0];
        int over=arr[0];
        for(int i=1;i<arr.length;i++){
            if(curr>0){
                curr+=arr[i];
            } else {
                curr=arr[i];
            }

            over=Math.max(over,curr);
        }
        return over;
    }

    public void wildcardMatching(String s, String p){
        boolean[][] dp=new boolean[p.length()+1][s.length()+1];

        dp[p.length()][s.length()]=true;
        for(int i=dp.length-2;i>=0;i--){
            for(int j=dp[0].length-2;j>=0;j--){
                if(p.charAt(i)=='?'){
                    dp[i][j]=dp[i+1][j+1];
                }else if(p.charAt(i)=='*'){
                    if(dp[i][j+1] || dp[i+1][j] || dp[i+1][j+1]){
                        dp[i][j]=true;
                    }
                }else{
                    if(p.charAt(i) == s.charAt(j)){
                        dp[i][j]=dp[i+1][j+1];
                    } else {
                        dp[i][j]=false;
                    }
                }
            }
        }
        System.out.println(dp[0][0]);
    }

    public int sum(int[] arr){
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        return sum;
    }

    public void kadanesFor2(int[] arr){
        int[] arr2=new int[arr.length*2];
        for(int i=0;i<arr.length;i++){
            arr2[i]=arr[i];
        }
        for(int i=0;i<arr.length;i++){
            arr2[i+arr.length-1]=arr[i];
        }
        arr=arr2;
    }

    public void kConcatenationMaximumSum(int[] arr, int k, int sum){
        if(k==1){
            System.out.println(kadanesAlgorithm(arr));
        }else if(k==2){
            kadanesFor2(arr);
            System.out.println(kadanesAlgorithm(arr));
        }else{
            System.out.println(kadanesAlgorithm(arr)+(k-2)*sum);
        }
    }

    public void maximumSumSubarrayWithAtleastSizeK(int[] arr, int k){
        int[] sum=new int[arr.length];
        sum[0]=arr[0];
        int csum=arr[0];
        for(int i=1; i<arr.length;i++){
            if(csum>0){
                csum+=arr[i];
            }else{
                csum=arr[i];
            }
            sum[i]=csum;
        }

        int kSum=0;
        for(int i=0;i<k;i++){
            kSum+=sum[i];
        }
        int mxSum=Integer.MIN_VALUE;

        if(kSum>mxSum){
            mxSum=kSum;
        }

        for(int i=k;i<sum.length;i++){
            kSum+=arr[i]-arr[i-k];
            if(kSum>mxSum){
                mxSum=kSum;
            }
            if(kSum+sum[i-k]>mxSum){
                mxSum=kSum+sum[i-k];
            }
        }
        System.out.println(mxSum);
    }

    public void eggDroppingProblem(int floors, int eggs){
        Integer[][] dp=new Integer[eggs+1][floors+1];
        /*
        * Base Cases
        * When floors==0, total trys to find the critical height =0
        * when eggs ==0, we might not be able to find ch, because we have no eggs
        * when eggs == 1, CH=total number of floors
        * when floors ==1, CH=1;*/

        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i==0 || j==0) {
                    dp[i][j]=0;
                }
                else if(i==1){
                    dp[i][j]=j;
                }else if(j==1){
                    dp[i][j]=1;
                }else{
                    int low=1;
                    int high=j;
                    int temp=0;
                    int min=Integer.MAX_VALUE;
                    while(low<=high){
                        int mid=(low+high)/2;
                        int left=dp[i-1][mid-1];
                        int right=dp[i][j-mid];
                        temp=1+Math.max(left,right);
                        if(left<right){
                            low=mid+1;
                        }else{
                            high=mid-1;
                        }
                        min=Math.min(min,temp);
                    }
                    dp[i][j]=min;
                }
            }
        }

        System.out.println(dp[dp.length-1][dp[0].length-1]);

    }

    public void optimalStrategyForGame(int[] arr){
        int[][] dp=new int[arr.length][arr.length];
        for(int g=0;g<dp.length;g++){
            for(int i=0,j=g;j<dp[0].length;i++,j++){
                if(g==0){
                    dp[i][j]=arr[i];
                }else if(g==1){
                    dp[i][j]=Math.max(arr[i],arr[j]);
                }else{
                    int firstTaken=arr[i]+Math.min(dp[i+2][j],dp[i+1][j-1]);
                    int lastTaken=arr[j]+Math.min(dp[i][j-2],dp[i+1][j-1]);
                    dp[i][j]=Math.max(firstTaken,lastTaken);
                }
            }
        }

        System.out.println(dp[0][arr.length-1]);
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

        System.out.print("Perfect sum of squares: ");
        dp.perfectSquaresSum(16);

        System.out.print("Catalan number: ");
        dp.catalanNumbers(5);

        System.out.println("Rod cutting problem");
        dp.rodCuttingProblem(new int[]{0,1,5,8,9,10,17,17,20});

        System.out.print("\nPalindrome Partitioning: ");
        dp.palindromePartitioningWithMinimumCuts("abccbc");

        System.out.print("\nLongest Common Subsequence: ");
        dp.longestCommonSubsequence("abcd","aebd");

        System.out.print("Longest Palindromic Subsequence: ");
        dp.longestPalindromicSubsequence("abkccbc");

        System.out.print("Count Palindromic Subsequence: ");
        dp.countPalindromicSubsequence("abccbc");

        System.out.print("Count Palindromic Substring: ");
        dp.countPalindromicSubstrings("abccbc");

        System.out.print("Longest Palindromic Substring: ");
        dp.longestPalindromicSubstring("abccbc");

        System.out.print("Kadane's Algorithm: ");
        System.out.println(dp.kadanesAlgorithm(new int[]{4,3,-2,6,-14,7,-1,4,5,7,-10,2,9,-10,-5,-9,6,1}));

        System.out.print("\nWildcard matching: ");
        dp.wildcardMatching("baaabab","pa*a?");

        System.out.println("K concatenation Maximum sum: ");
        int[] a={4,3,-2,6,-14,7,-1,4,5,7,-10,2,9,-10,-5,-9,6,1};
        dp.kConcatenationMaximumSum(a,2, dp.sum(a));

        System.out.println("Maximum Sum subarray with atleast K: ");
        dp.maximumSumSubarrayWithAtleastSizeK(a,2);

        System.out.print("Egg dropping problem: ");
        dp.eggDroppingProblem(7,3);

        System.out.print("Optimal Strategy for a game: ");
        dp.optimalStrategyForGame(new int[]{20,30,2,10});
    }
}
