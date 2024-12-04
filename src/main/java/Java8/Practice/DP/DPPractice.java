package Java8.Practice.DP;

public class DPPractice {

    public static int fib(int n,int[] dp){
        if(n==0 || n==1){
            return n;
        }
        if(dp[n]!=0){
            return dp[n];
        }
        int a=fib(n-1,dp);
        int b=fib(n-2,dp);
        dp[n]=a+b;
        return dp[n];
    }

    public static void climbStairs(int n){
        int[] dp=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            if(i==1){
                dp[i]=dp[i-1];
            } else if(i==2) {
                dp[i]=dp[i-2]+dp[i-1];
            } else {
                dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
            }
        }

        System.out.println(dp[n]);
    }

    public static void climbStairsWithJumps(int[] jumps){
        int n=jumps.length;
        int[] dp=new int[n+1];
        dp[n]=1;
        for(int i=n-1;i>=0;i--){
            for(int j=1;j<=jumps[i] && i+j<dp.length;j++){
                dp[i]+=dp[i+j];
            }
        }
        System.out.println(dp[0]);
    }

    public static void climbStairsWithMinimumJumps(int[] jumps){
        int n=jumps.length;
        Integer[] dp=new Integer[n+1];
        dp[n]=0;
        for(int i=n-1;i>=0;i--){
            int min=Integer.MAX_VALUE;
            for(int j=1;j<jumps[i] && i+j<dp.length;j++){
                if(dp[i+j]!=null){
                    if(dp[i+j]<min){
                        min=dp[i+j];
                    }
                }
            }
            dp[i]=min+1;
        }
        System.out.println(dp[0]);
    }

    public static void minimumCostPath(int[][] path){
        int n=path.length;
        int m=path[0].length;
        int[][] dp=new int[n][m];
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(i==n-1 && j==m-1){
                    dp[i][j]=path[i][j];
                } else if(i==n-1) {
                    dp[i][j]=path[i][j]+dp[i][j+1];
                } else if(j==m-1) {
                    dp[i][j]=path[i][j]+dp[i+1][j];
                } else {
                    dp[i][j]=path[i][j]+Math.min(dp[i+1][j],dp[i][j+1]);
                }
            }
        }

        System.out.println(dp[0][0]);
    }

    public static void targetSumSubset(int[] arr, int k){
        boolean[][] dp=new boolean[arr.length+1][k+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=true;
        }
        for(int j=1;j<dp.length;j++){
            dp[0][j]=false;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(dp[i-1][j]==true){
                    dp[i][j]=true;
                } else {
                    int val=arr[i-1];
                    if(j>=val){
                        dp[i][j]=dp[i-1][j-val];
                    }
                }
            }
        }
        System.out.println(dp[dp.length-1][dp[0].length-1]);
    }

    public static void coinChangeCombination(int num, int[] arr){
        int[] dp=new int[num+1];
        dp[0]=0;
        for(int i=0;i<arr.length;i++){
            for(int j=arr[i];j<dp.length;j++){
                if(i==0){
                    dp[j]=1+dp[j-arr[i]];
                } else {
                    dp[j]=Math.min(1+dp[j-arr[i]],dp[j]);
                }
            }
        }
        System.out.println(dp[dp.length-1]);
    }

    public static void coinChange(int amount, int[] coins){
        int[][] dp=new int[coins.length+1][amount+1];
        for(int j=0;j<dp[0].length;j++){
            dp[0][j]=Integer.MAX_VALUE;
        }
        for(int i=0;i<dp.length;i++){
            dp[i][0]=0;
        }
        for(int i=1;i<=coins.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j-coins[i-1]>=0){
                    dp[i][j]=Math.min(1+dp[i][j-coins[i-1]],dp[i-1][j]);
                }
            }
        }

        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(dp[coins.length][amount]+1);
    }

    public static void coinChangePermutation(int num,int[] arr){
        int[] dp=new int[num+1];
        dp[0]=1;

        for(int i=1;i<=num;i++){
            for(int coin: arr){
                if(coin<=i){
                    dp[i]+=dp[i-coin];
                }
            }
        }
        System.out.println(dp[dp.length-1]);

    }

    public static void main(String[] args) {
        int[][] arr={{1,4,8},{5,4,8},{1,7,2}};
        System.out.println(fib(10,new int[11]));
        climbStairs(6);
        climbStairsWithJumps(new int[]{1,1,3,1,1});
        climbStairsWithMinimumJumps(new int[]{1,1,3,1,1});
        minimumCostPath(arr);
        targetSumSubset(new int[]{4,2,7,1,3},10);
        coinChangeCombination(7,new int[]{2,1});
        coinChange(7,new int[]{1,2,5});
        coinChangePermutation(7,new int[]{2,3,5});
    }
}
