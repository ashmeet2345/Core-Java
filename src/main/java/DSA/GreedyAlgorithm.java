package DSA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GreedyAlgorithm {

    public void assignCookies(int[] greed, int[] cookies){
        Arrays.sort(greed);
        Arrays.sort(cookies);
        int i=0;
        int j=0;
        while(j<cookies.length){
            if(greed[i] <= cookies[j]){
                i++;
            }
            j++;
        }

        System.out.println(i);
    }

    public void lemonadeChange(int[] bills){
        Map<Integer,Integer> mp=new HashMap<>();
        boolean change=true;
        for(int i=0;i<bills.length;i++){
            mp.put(bills[i],mp.getOrDefault(bills[i],0)+1);
            if(bills[i]==10){
                if(mp.getOrDefault(5,0) < 1){
                    change=false;
                } else {
                    mp.put(5,mp.getOrDefault(5,0)-1);
                }
            } else if(bills[i]==20){
                if((mp.getOrDefault(5,0) >= 1 && mp.getOrDefault(10,0) >= 1)){
                    mp.put(10,mp.getOrDefault(10,0)-1);
                    mp.put(5,mp.getOrDefault(5,0)-1);
                }
                else if(mp.getOrDefault(5,0) >= 3){
                    mp.put(5,mp.getOrDefault(5,0)-3);
                } else {
                    change=false;
                }
            }
        }
        System.out.println(change);
    }

    public static void main(String[] args) {
        GreedyAlgorithm greedy=new GreedyAlgorithm();

        System.out.println("Assign Cookies: ");
        greedy.assignCookies(new int[]{1,5,3,3,4},new int[]{4,2,1,2,1,3});

        System.out.println("Lemonade Change: ");
        greedy.lemonadeChange(new int[]{5});
    }
}
