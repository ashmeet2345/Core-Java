package Java8.Practice.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                    dp[i][j]=Math.min(1+dp[i-1][j-coins[i-1]],dp[i-1][j]);
                }
            }
        }

        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(dp[coins.length][amount]);
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

    public static void unboundedKnapsack(int[] weight, int[] val, int capacity){
        int[] dp=new int[capacity+1];
        Arrays.fill(dp,0);
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<val.length;j++){
                if(i>=weight[j]){
                    dp[i]=Math.max(dp[i],dp[i-weight[j]]+val[j]);
                }
            }
        }
        System.out.println(dp[capacity]);
    }

    public static void binaryStringsWithNoConsecutinve0s(int n){
        int oew0s=1;
        int oew1s=1;
        for(int i=2;i<=n;i++){
            int new0s=oew1s;
            int new1s=oew0s+oew1s;

            oew0s=new0s;
            oew1s=new1s;
        }

        System.out.println(oew1s+oew0s);
    }

    public static void maximumSumNonAdjacentElements(int[] arr){
        int inc=arr[0];
        int notInc=0;
        for(int i=1;i<arr.length;i++){
            int ninc=Math.max(notInc+arr[i],inc);
            int nNotInc=inc;

            inc=ninc;
            notInc=nNotInc;
        }
        System.out.println(Math.max(inc,notInc));
    }

    public static void decodeWays(String str){
        int[] dp=new int[str.length()];
        dp[0]=1;
        if(str.charAt(0)=='0') return;
        for(int i=1;i<dp.length;i++){
            if(str.charAt(i)=='0' && str.charAt(i-1)==0){
                dp[i]=0;
            }else if(str.charAt(i)=='0' && str.charAt(i-1)!=0){
                if(str.charAt(i-1)=='1' || str.charAt(i-1)=='2'){
                    dp[i]=dp[i>2?dp[i-2]:1];
                } else {
                    dp[i]=0;
                }
            }else if(str.charAt(i)!='0' && str.charAt(i-1)==0){
                dp[i]=dp[i-1];
            }else{
                if(Integer.parseInt(str.substring(i-1,i+1))<=26){
                    dp[i]=dp[i-1]+dp[i>2?dp[i-2]:1];
                } else {
                    dp[i]=dp[i-1];
                }
            }
        }

        System.out.println(dp[str.length()-1]);
    }

    public static void paintHouse(int[] red, int[] blue, int[] green, int n){
        int r=red[0];
        int b=blue[0];
        int g=green[0];
        for(int i=1;i<n;i++){
            int nr=Math.min(b,g)+red[i];
            int nb=Math.min(r,g)+blue[i];
            int ng=Math.min(r,b)+green[i];

            r=nr;
            b=nb;
            g=ng;
        }

        System.out.println(Math.min(g,Math.min(r,b)));
    }

    public static void paintFence(int n, int k){
        int s=k;
        int d=k*(k-1);
        int total=s+d;
        for(int i=3;i<=n;i++){
            s=d;
            d=total*(k-1);
            total=s+d;
        }
        System.out.println(total);
    }

    public static void buyAndSellStocksI(int[] price){
        int profit=0;
        int buy=price[0];
        for(int i=1;i<price.length;i++){
            if(price[i]<buy){
                buy=price[i];
            } else {
                profit=Math.max(price[i]-buy,profit);
            }
        }
        System.out.println(profit);
    }

    public static void buyAndSellStocksII(int[] price){
        int profit=0;
        int buy,sell;
        buy=sell=0;
        for(int i=1;i<price.length;i++){
            if(price[i]>price[i-1]){
                sell++;
            } else {
                profit+=price[sell]-price[buy];
                buy=sell=i;
            }
        }
        profit+=price[sell]-price[buy];
        System.out.println(profit);
    }

    public static void buyAndSellStocksWithFee(int[] price,int fee){
        int obp=-price[0];
        int osp=0;
        for(int i=1;i<price.length;i++){
            int nbp=0;
            int nsp=0;
            if(osp-price[i]>obp){
                nbp=osp-price[i];
            } else {
                nbp=obp;
            }
            if(obp+price[i]-fee>osp){
                nsp=obp+price[i]-fee;
            } else {
                nsp=osp;
            }
            osp=nsp;
            obp=nbp;
        }
        System.out.println(osp);
    }

    public static void buyAndSellStocksWithCoolDown(int[] arr){
        int bsp=-arr[0];
        int ssp=0;
        int csp=0;
        for(int i=1;i<arr.length;i++){
            if(csp-arr[i]>bsp){
                bsp=csp-arr[i];
            }
            if(bsp+arr[i]>ssp){
                ssp=bsp+arr[i];
            }
            if(ssp>csp){
                csp=ssp;
            }
        }

        System.out.println(ssp);
    }

    public static void largestSquareSubmatrixOfAll1s(int[][] matrix){
        int n=matrix.length;
        int m=matrix[0].length;

        int[][] dp=new int[n][m];
        int maxSquare=0;
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if((i==n-1 || j==m-1) || (i==n-1 && j==m-1)){
                    dp[i][j]=matrix[i][j];
                } else if(matrix[i][j]==0){
                    dp[i][j]=0;
                } else if(matrix[i][j]==1){
                    dp[i][j]=1+Math.min(dp[i+1][j],Math.min(dp[i+1][j+1],dp[i][j+1]));
                    if(dp[i][j]>maxSquare){
                        maxSquare=dp[i][j];
                    }
                }
            }
        }

        System.out.println(maxSquare);
    }

    public static boolean printPrime(int n){
        int[] arr=new int[n+1];
        Arrays.fill(arr,1);
        for(int i=2;i*i<=arr.length;i++){
            if(arr[i]==1){
                for(int j=i+i;j<arr.length;j+=i){
                    arr[j]=0;
                }
            }
        }

        for(int i=2;i<=n;i++){
            if(arr[i]==1) System.out.print(i+" ");
        }

        if(arr[n]==1){
            return true;
        } else {
            return false;
        }
    }

    public static int majorityElement(int[] nums){
        int count=1;
        int num=nums[0];
        for(int i=1;i<nums.length;i++){
            if(count==0){
                num=nums[i];
                count=1;
                continue;
            }
            if(nums[i]==num){
                count++;
            } else {
                count--;
            }
        }
        count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==num){
                count++;
            }
        }
        return count>nums.length/2 ? num:0;
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
        coinChange(11,new int[]{1,2,5});
        coinChangePermutation(7,new int[]{2,3,5});
        unboundedKnapsack(new int[]{2,5,1,3,4},new int[]{15,14,10,45,30},7);
        binaryStringsWithNoConsecutinve0s(6);
        maximumSumNonAdjacentElements(new int[]{5,10,10,100,5,6});
        buyAndSellStocksI(new int[]{1,2,3});
        buyAndSellStocksII(new int[]{1,2,3,4,5});
        buyAndSellStocksWithFee(new int[]{1,3,2,8,4,9},2);

        System.out.print("Largest Square submatrix: ");
        largestSquareSubmatrixOfAll1s(new int[][]{{1,1,1,1},{1,1,1,1},{1,1,0,1},{1,1,1,1}});

        System.out.println("Is Prime: "+printPrime(10));

        System.out.println("Majority Element: "+majorityElement(new int[]{2,2,1,1,1,2,2}));

    }
}
