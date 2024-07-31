package DSA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HashmapsAndHeapsL2 {

    public void reconstructItinerary(HashMap<String,String> map){
        Map<String,Boolean> hm=new HashMap<>();
        for(String src: map.keySet()){
            String dest=map.get(src);
            hm.put(dest,false);
            if(!hm.containsKey(src))
                hm.put(src,true);
            }

        String s="";
        for(String pot:hm.keySet()) {
            Boolean sr = hm.get(pot);
            if (sr == true) {
                s = pot;
                break;
            }
        }

        while(true){
            if(map.containsKey(s)){
                System.out.print(s+"->");
                s=map.get(s);
            } else {
                System.out.print(s+".");
                break;
            }
        }
    }

    public void checkIfArrayPairsAreDivisibleByK(int[] arr,int k){
        Map<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            int val=arr[i]%k;
            if(!mp.containsKey(val)){
                mp.put(val,1);
            } else {
                int freq=mp.get(val);
                mp.put(val,freq+1);
            }
        }

        for(int i:arr){
            int rem=i%k;
            if(rem==0){
                int fq=mp.get(rem);
                if(fq%2!=0){
                    System.out.println(false);
                    return;
                }
            }else if(2*rem==k){
                int fq=mp.get(rem);
                if(fq%2!=0){
                    System.out.println(false);
                    return;
                }
            } else {
                int fq=mp.get(rem);
                int ofq=mp.getOrDefault(k-rem,0);
                if(fq!=ofq){
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(true);
    }

    public ArrayList distinctElementsInWindowsOfSizeK(int[] arr,int k){
        Map<Integer,Integer> mp=new HashMap<>();
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<k-1;i++){
            mp.put(arr[i],mp.getOrDefault(arr[i],0)+1);
        }
        for(int j=0,i=k-1;i<arr.length;){
            mp.put(arr[i],mp.getOrDefault(arr[i],0)+1);

            list.add(mp.size());
            int freq=mp.get(arr[j]);
            if(freq==1){
                mp.remove(arr[j]);
            } else {
                mp.put(arr[j],freq-1);
            }
            i++;
            j++;
        }

        return list;
    }

    public void largestSubarrayWithSum0(int[] arr){
        Map<Integer,Integer> mp=new HashMap<>();
        mp.put(0,-1);
        int sum=0;
        int maxL=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(!mp.containsKey(sum)){
                mp.put(sum,i);
            } else {
                maxL=Math.max(maxL,i-mp.get(sum));
            }
        }
        System.out.println(maxL);
    }

    public void minimumWindowSubstring(String str1,String str2){
        Map<Character,Integer> mp1=new HashMap<>();
        Map<Character,Integer> mp2=new HashMap<>();

        for(int i=0;i<str2.length();i++){
            mp2.put(str2.charAt(i),mp2.getOrDefault(str2.charAt(i),0)+1);
        }

        String ans="";
        int i=-1;
        int j=-1;
        int size=0;
        int strSize=str2.length();
        while(true){
            boolean f1=false;
            boolean f2=false;
            while(i<str1.length()-1 && size<strSize){
                i++;
                char ch=str1.charAt(i);
                mp1.put(ch,mp1.getOrDefault(ch,0)+1);

                if(mp1.getOrDefault(ch,0) <= mp2.getOrDefault(ch,0)){
                    size++;
                }
                f1=true;
            }

            while(j<i && size==strSize){
                String pans=str1.substring(j+1,i+1);
                if(ans.length()==0 || pans.length() < ans.length()){
                    ans=pans;
                }
                j++;
                char ch=str1.charAt(j);
                if(mp1.get(ch)==1){
                    mp1.remove(ch);
                } else {
                     mp1.put(ch,mp1.get(ch)-1);
                }
                if(mp1.getOrDefault(ch,0) < mp2.getOrDefault(ch,0))
                    size--;
                f2=true;
            }
            if(f1==false && f2==false){
                break;
            }
        }
        System.out.print(ans);
    }


    public static void main(String[] args) {
        HashmapsAndHeapsL2 hash=new HashmapsAndHeapsL2();
        Map<String, String> hm=new HashMap<>();

        hm.put("Chennai","Bengaluru");
        hm.put("Bombay","Delhi");
        hm.put("Goa","Chennai");
        hm.put("Delhi","Goa");

        System.out.println("Reconstruct Itinerary: ");
        hash.reconstructItinerary((HashMap)hm);
        System.out.println();

        System.out.print("If Array pairs are divisible by K: ");
        hash.checkIfArrayPairsAreDivisibleByK(new int[]{77,22,56,11,45,34,78,29,23,55,65},10);

        System.out.print("Distinct elements in windows of size K: ");
        System.out.println(hash.
                distinctElementsInWindowsOfSizeK(new int[]{2,5,5,6,3,2,3,2,4,5,2,2,2,2,3,6},4)
                .toString());

        System.out.print("Largest Subarray with sum 0: ");
        hash.largestSubarrayWithSum0(new int[]{2,8,-3,-5,2,-4,6,1,2,1,-3,4});

        System.out.println("Minimum window substring I: ");
        hash.minimumWindowSubstring("dbaecbbabdcaafbddcabgba","abbcdc");

    }
}
