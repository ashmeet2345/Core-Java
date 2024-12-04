package DSA;

import com.sun.source.tree.ArrayAccessTree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dynamicprogramming {
    public int fibonacci(int n,int dp[]){
        if(n==0 || n==1){
            return n;
        }

        if(dp[n]!=0){
            return dp[n];
        }

        int a=fibonacci(n-1,dp);
        int b=fibonacci(n-2,dp);
        dp[n]=a+b;
        return dp[n];
    }

    public int climbStairsRecursiveDp(int n,int[] dp){
        if(n==0)
            return 1;
        if(n<0){
            return 0;
        }

        if(dp[n]>0){
            return dp[n];
        }

        int n1=climbStairsRecursiveDp(n-1,dp);
        int n2=climbStairsRecursiveDp(n-2,dp);
        int n3=climbStairsRecursiveDp(n-3,dp);
        int res=n1+n2+n3;
        dp[n] = res;
        return res;
    }

    public int climbStairsTabulation(int n){
        int[] dp=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            if(i==1){
                dp[i]=dp[i-1];
            } else if(i==2){
                dp[i]=dp[i-1]+dp[i-2];
            }else{
                dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
            }
        }

        return dp[n];
    }

    public int climbStairsHelper(int n,int[] dp,Boolean tabulationApproach){
        if(!tabulationApproach){
            return climbStairsRecursiveDp(n,dp);
        } else {
            return climbStairsTabulation(n);
        }
    }

    public int climbStairsWithJumps(int n,int[] arr){
        int[] dp = new int[n+1];
        dp[n]=1;
        for(int i=arr.length-1;i>=0;i--){
            for(int j=1;j<=arr[i] && i+j<dp.length;j++){
                dp[i]+=dp[i+j];
            }
        }

        return dp[0];
    }

    public int climbStairsWithMinimumSteps(int n,int[] arr){
        Integer[] dp=new Integer[n+1];
        dp[n]=0;

        for(int i=n-1;i>=0;i--){
            if(arr[i]>0){
                int min=Integer.MAX_VALUE;
                for(int j=1;j<=arr[i] && i+j<dp.length;j++){
                    if(dp[i+j]!=null)
                        if(dp[i+j]<min){
                            min=dp[i+j];
                        }
                }
                if(min!=Integer.MAX_VALUE){
                    dp[i]=min+1;
                } else {
                    dp[0]=null;
                }
            }
        }
        return dp[0];
    }

    public int minimumCostPath(int[][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int[][] dp=new int[n][m];

        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(i==n-1 && j==m-1){
                    dp[i][j]=arr[i][j];
                } else if(i==n-1){
                    dp[i][j]=arr[i][j]+dp[i][j+1];
                } else if(j==m-1) {
                    dp[i][j]=arr[i][j]+dp[i+1][j];
                } else {
                    dp[i][j]=arr[i][j]+Math.min(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }

    public int maximumGold(int[][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int[][] dp=new int[n][m];

        for(int j=m-1;j>=0;j--){
            for(int i=0;i<n;i++){
                if(j==m-1){
                    dp[i][j]=arr[i][j];
                } else if(i==0){
                    dp[i][j]=arr[i][j]+Math.max(dp[i][j+1],dp[i+1][j+1]);
                } else if(i==n-1) {
                    dp[i][j]=arr[i][j]+Math.max(dp[i][j+1],dp[i-1][j+1]);
                } else {
                    dp[i][j]=arr[i][j]+Math.max(Math.max(dp[i][j+1],dp[i+1][j+1]),dp[i-1][j+1]);
                }
            }
        }

        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            max=Math.max(dp[i][0],max);
        }
        return max;
    }

    public void targetSumSubsets(int[] arr,int target){
        boolean[][] dp=new boolean[arr.length+1][target+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=true;
        }
        for(int j=1;j<dp.length;j++){
            dp[0][j]=false;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(dp[i-1][j] == true){
                    dp[i][j]=true;
                } else {
                    int val=arr[i-1];
                    if(j>=val){
                        if(dp[i-1][j-val] == true)
                            dp[i][j]=true;
                    }
                }
            }
        }
        System.out.print(dp[arr.length][target]+"\n");
    }

    public void coinChangeCombination(int num,int[] arr){
        int[] dp=new int[num+1];
        dp[0]=1;
        for(int i=0;i<arr.length;i++){
            for(int j=arr[i];j<dp.length;j++){
                dp[j]+=dp[j-arr[i]];
            }
        }
        System.out.print(dp[num]+"\n");
    }

    public void coinChangePermutation(int amt,int[] coins){
        int[] dp=new int[amt+1];
        dp[0]=1;

        for(int i=1;i<=amt;i++){
            for(int coin:coins){
                if(coin<=i){
                    dp[i]+=dp[i-coin];
                }
            }
        }
        System.out.print(dp[amt]+"\n");
    }

    public void knapsack01(int[] val,int[] w, int cap){
        int[][] dp=new int[w.length+1][cap+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=0;
        }

        for(int i=0;i<dp[0].length;i++){
            dp[0][i]=1;
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j>=w[i-1]){
                    int amt=dp[i-1][j-w[i-1]]+val[i-1];
                    if(amt>dp[i-1][j]){
                        dp[i][j]=amt;
                    } else {
                        dp[i][j]=dp[i-1][j];
                    }
                } else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[w.length][cap]+"\n");
    }

    public void unboundedKnapsack(int cap, int[] val,int[] weight){
        int[] dp=new int[cap+1];
        Arrays.fill(dp,0);

        for(int i=1;i<dp.length;i++){
            for(int j=0;j<val.length;j++){
                if(i >= val[j]){
                    dp[i]=Math.max(dp[i],(dp[i-val[j]]+weight[j]));
                }
            }
        }

        System.out.println(dp[cap]);
    }

    public void binaryStringWithNoConsecutive0s(int n){
       /*int[] dp0=new int[n+1];
       int[] dp1=new int[n+1];

       dp0[1]=1;
       dp1[1]=1;
       for(int i=2;i<=n;i++){
           dp0[i]=dp1[i-1];
           dp1[i]=dp1[i-1]+dp0[i-1];

       //    It is giving right answer but, it takes a lot of space
       }*/

        int oc0s=1;
        int oc1s=1;
        for(int i=2;i<=n;i++){
            int nc0s=oc1s;
            int nc1s=oc0s+oc1s;

            oc0s=nc0s;
            oc1s=nc1s;
        }

        System.out.println(oc1s+oc0s);
    }

    public void totalWaysToDecodeAString(String str){
        int[] dp=new int[str.length()];
        dp[0]=1;

        for(int i=1;i<dp.length;i++){
            if(str.charAt(i) == '0' && str.charAt(i-1)=='0'){
                dp[i]=0;
            }else if(str.charAt(i) == '0' && str.charAt(i-1)!='0'){
                if(str.charAt(i-1)=='1' || str.charAt(i-1)=='2'){
                    dp[i]=(i>=2?dp[i-2]:1);
                } else {
                    dp[i]=0;
                }
            }else if(str.charAt(i) != '0' && str.charAt(i-1)=='0'){
                dp[i]=dp[i-1];
            }else {
                if(Integer.parseInt(str.substring(i-1,i+1))<=26){
                    dp[i]=dp[i-1]+(i>=2?dp[i-2]:1);
                } else {
                    dp[i]=dp[i-1];
                }
            }
        }
        System.out.println(dp[str.length()-1]+" ");
    }

    public void countSubsequencesOfFormAiBjCk(String str){
        int a=0;
        int ab=0;
        int abc=0;

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='a'){
                a=2*a+1;
            }else if(str.charAt(i)=='b'){
                ab=2*ab+a;
            }else if(str.charAt(i)=='c'){
                abc=2*abc+ab;
            }
        }
        System.out.println(abc);
    }

    public void maximumSumNonAdjacentElements(int[] arr){
        int inc=arr[0];
        int exc=0;
        for(int i=1;i<arr.length;i++){
            int ninc=arr[i]+exc;
            int nexc=Math.max(inc,exc);

            inc=ninc;
            exc=nexc;
        }

        System.out.println(Math.max(inc,exc)+" ");
    }

    public void paintHouse(int[] red,int[] blue,int[] green){
        int r=red[0];
        int b=blue[0];
        int g=green[0];

        for(int i=1;i<red.length;i++){
            int nr=Math.min(b,g)+red[i];
            int nb=Math.min(r,g)+blue[i];
            int ng=Math.min(b,r)+green[i];

            r=nr;
            b=nb;
            g=ng;
        }

        System.out.println(r+b+g);
    }

    public void paintFence(int n,int k){
        int same=k;
        int diff=k*(k-1);
        int total=same+diff;

        for(int i=3;i<=n;i++){
            same=diff*1;
            diff=total*(k-1);
            total=same+diff;
        }

        System.out.println(total);
    }

    public void tilingWithDominoes(int n){
        if(n==1){
            System.out.println(1);
            return;
        }else if(n==2){
            System.out.println(2);
            return;
        } else {
            int[] dp=new int[n+1];
            dp[1]=1;
            dp[2]=2;

            for(int i=3;i<=n;i++){
                dp[i]=dp[i-1]+dp[i-2];
            }

            System.out.println(dp[n]);
        }

    }

    public void friendPairing(int n){
        int[] dp=new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+(i-1)*dp[i-2];
        }

        System.out.println(dp[n]);
    }

    public void partitionIntoSubsets(int n,int k){
        int[][] dp=new int[k+1][n+1];
        for(int i=0;i<k;i++){
            dp[i][0]=0;
        }
        for(int j=0;j<n;j++){
            dp[0][j]=0;
        }

        for(int i=1;i<=k;i++){
            for(int j=1;j<=n;j++){
                if(j<i){
                    dp[i][j]=0;
                } else if(i==1){
                    dp[i][j]=1;
                } else if(i==j){
                    dp[i][j]=1;
                } else if(j>i){
                    dp[i][j]=dp[i][j-1]*i+dp[i-1][j-1];
                }
            }
        }
        System.out.println(dp[k][n]);
    }

    public void bestTimeToBuyAndSellStocks_OneTransactionAllowed(int[] prices){
        int lsf=Integer.MAX_VALUE;
        int profit=0;
        int pst=0;
        for(int i=0;i<prices.length;i++){
            if(lsf>prices[i]){
                lsf=prices[i];
            }
            pst=prices[i]-lsf;
            if(pst>profit){
                profit=pst;
            }
        }

        System.out.println(profit);
    }

    public void bestTimeToBuyAndSellStocks_InfiniteTransactions(int[] prices){
        int bd=0;
        int sd=0;
        int profit=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>=prices[i-1]){
                sd++;
            } else {
                profit+=prices[sd]-prices[bd];
                bd=sd=i;
            }
        }
        profit+=prices[sd]-prices[bd];
        System.out.println(profit);

    }

    public void bestTimeToBuyAndSellStocks_WithTransactionFeeAndInfiniteTransactions(int[] prices, int fee){
        int bsp=-prices[0];
        int ssp=0;
        for(int i=1;i< prices.length;i++){
            if(ssp-prices[i]>bsp){
                bsp=ssp-prices[i];
            }
            if(prices[i]+bsp-fee>ssp){
                ssp=prices[i]+bsp-fee;
            }
        }
        System.out.println(ssp);
    }

    public void bestTimeToBuyAndSellStocks_WithCooldownAndInfiniteTransactions(int[] prices){
        int bsp=-prices[0];
        int ssp=0;
        int csp=0;
        for(int i=1;i<prices.length;i++){
            if(csp-prices[i]>bsp){
                bsp=csp-prices[i];
            }
            if(prices[i]+bsp>ssp){
                ssp=prices[i]+bsp;
            }
            if(ssp>csp){
                csp=ssp;
            }
        }
        System.out.println(ssp);
    }

    public static void main(String[] args) {
        Dynamicprogramming dp=new Dynamicprogramming();
        int[][] arr={{1,4,8},{5,4,8},{1,7,2}};

        System.out.println(dp.fibonacci(10,new int[13]));
        System.out.println("Climb Stairs: "+dp.climbStairsHelper(6,new int[11],true));
        System.out.println("Climb stairs with jumps: "+dp.climbStairsWithJumps(5,new int[]{1,1,1,1,1}));
        System.out.println("Climb stairs with minimum steps: "+dp.climbStairsWithMinimumSteps(5,new int[]{1,1,3,1,1}));
        System.out.println("Minimum Cost path: "+dp.minimumCostPath(arr));
        System.out.println("Maximum gold: "+dp.maximumGold(arr));
        System.out.print("Target Sum subsets: ");
        dp.targetSumSubsets(new int[]{4,2,7,1,3},10);
        System.out.print("Coin change combination: ");
        dp.coinChangeCombination(7,new int[]{2,3,5});
        System.out.print("Coin change permutation: ");
        dp.coinChangePermutation(10,new int[]{2,3,5,6});

        System.out.print("Unbounded Knapsack: ");
        dp.unboundedKnapsack(7,new int[]{2,5,1,3,4},new int[]{15,14,10,45,30});

        System.out.print("Binary Strings with no consecutive 0s: ");
        dp.binaryStringWithNoConsecutive0s(6);

        System.out.print("Total ways to decode a string: ");
        dp.totalWaysToDecodeAString("21123");

        System.out.print("Count subsequences of form A^i+B^j+C^k: ");
        dp.countSubsequencesOfFormAiBjCk("abcabc");

        System.out.print("Maximum sum non adjacent elements: ");
        dp.maximumSumNonAdjacentElements(new int[]{5,10,10,100,5,6});

        System.out.print("Paint House: ");
        dp.paintHouse(new int[]{1,5,3,1},new int[]{5,8,2,2},new int[]{7,4,9,4});

        System.out.print("Paint fence: ");
        dp.paintFence(5,3);

        System.out.print("Tiling Dominoes: ");
        dp.tilingWithDominoes(5);

        System.out.print("Friends Pairing: ");
        dp.friendPairing(5);

        System.out.print("Partition into subsets: ");
        dp.partitionIntoSubsets(4,3);

        System.out.print("Best time to buy and sell stocks- One transaction allowed: ");
        dp.bestTimeToBuyAndSellStocks_OneTransactionAllowed(new int[]{1,2,3});

        System.out.print("Best time to buy and sell stocks- Infinite transaction allowed: ");
        dp.bestTimeToBuyAndSellStocks_InfiniteTransactions(new int[]{1,2,3,2,6});

        System.out.print("Best time to buy and sell stocks- With Transaction fee and Infinite transaction allowed: ");
        dp.bestTimeToBuyAndSellStocks_WithTransactionFeeAndInfiniteTransactions(new int[]{10,20,30},2);

        System.out.print("Best time to buy and sell stocks- With Cooldown and Infinite transaction allowed: ");
        dp.bestTimeToBuyAndSellStocks_WithCooldownAndInfiniteTransactions(new int[]{10,20,30});
    }
}
