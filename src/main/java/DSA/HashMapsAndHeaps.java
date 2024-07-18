package DSA;

import java.util.*;

public class HashMapsAndHeaps {

    public static void highestFrequencyCharacter(String str){
        HashMap<Character,Integer> hm=new HashMap<>();
        for(int i=0;i<str.length();i++){
            if(!hm.containsKey(str.charAt(i)))
                hm.put(str.charAt(i),1);
            else{
                int of=hm.get(str.charAt(i));
                of+=1;
                hm.put(str.charAt(i),of);
            }
        }

        int mxf=hm.get(str.charAt(0));
        char c=str.charAt(0);
        for(Character ch:hm.keySet()){
            if(hm.get(ch) > mxf){
                mxf=hm.get(ch);
                c=ch;
            }
        }
        System.out.println(c+"-"+mxf);
    }

    public static void getCommonElement1(int[] arr1,int[] arr2){
        Set<Integer> s1=new LinkedHashSet<>();
        for(int i:arr1){
            s1.add(i);
        }

        for(int i:arr2){
            if(s1.contains(i)){
                System.out.print(i+" ");
                s1.remove(i);
            }
        }
        System.out.println();
    }

    public static void getCommonElement2(int[] arr1,int[] arr2){
        HashMap<Integer,Integer> hm=new HashMap<>();

        for(int i:arr1){
            if(!hm.containsKey(i))
                hm.put(i,1);
            else{
                int of=hm.get(i);
                of+=1;
                hm.put(i,of);
            }
        }

        for(int i:arr2){
            if(hm.containsKey(i) && hm.get(i) > 0){
                System.out.print(i+" ");
                int of=hm.get(i);
                of-=1;
                hm.put(i,of);
            }
        }
        System.out.println();
    }

    public static void longestConsecutiveSubsequence(int[] arr){
        HashMap<Integer,Boolean> hm=new HashMap<>();
        for(int i:arr){
            hm.put(i,true);
        }

        for(int i: hm.keySet()){
            if(hm.containsKey(i-1)){
                hm.put(i,false);
            }
        }

        int ml=0;
        int msp=0;
        for(int i: arr){
            if(hm.get(i) == true){
                int tl=1;
                int tsp=i;
                while(hm.containsKey(tsp+tl))
                    tl++;

                if(tl > ml){
                    ml=tl;
                    msp=tsp;
                }
            }
        }
        System.out.println(msp+"-"+ml);
    }

    public static void kLargestElements(int[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<k;i++){
            pq.add(arr[i]);
        }

        for(int i=k;i<arr.length;i++){
            if(pq.peek() < arr[i]){
                pq.remove();
                pq.add(arr[i]);
            }
        }

        System.out.println(pq);
    }

    public static void kSortedArray(int[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<=k;i++){
            pq.add(arr[i]);
        }

        for(int i=k+1;i<arr.length;i++){
            System.out.print(pq.remove()+" ");
            pq.add(arr[i]);
        }

        while(pq.size() > 0){
            System.out.print(pq.remove()+" ");
        }

        System.out.println();
    }

    public static ArrayList<Integer> mergeKSortedList(ArrayList<ArrayList<Integer> > list){
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        ArrayList<Integer> res=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Pair p=new Pair(i,0,list.get(i).get(0));
            pq.add(p);
        }

        while(pq.size() > 0){
            Pair p=pq.remove();
            res.add(p.val);
            p.lli++;

            if(p.lli < list.get(p.li).size()){
                p.val=list.get(p.li).get(p.lli);
                pq.add(p);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str="aabccdfffaabbbbc";
        highestFrequencyCharacter(str);
        getCommonElement1(new int[]{1,1,2,4,4,5,5,8},new int[]{1,4,4,5,5,8});
        getCommonElement2(new int[]{1,1,2,4,4,5,5,8},new int[]{1,4,4,5,5,8});
        longestConsecutiveSubsequence(new int[]{10,5,9,1,11,8,6,15,3,12,2});
        kLargestElements(new int[]{2,10,5,17,18,6,4},3);
        kSortedArray(new int[]{2,3,1,4,6,7,5,8,9},2);


        MedianPriorityQueue mpq=new MedianPriorityQueue();
        mpq.add(10); mpq.add(20); mpq.add(30);
        System.out.println(mpq.peek());
    }

    public static class Pair implements Comparable<Pair>{
        int li;
        int lli;
        int val;

        public Pair(int li, int lli, int val) {
            this.li = li;
            this.lli = lli;
            this.val = val;
        }

        public int compareTo(Pair o) {
            return this.val-o.val;
        }
    }

    public static class MedianPriorityQueue{
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianPriorityQueue(){
            left=new PriorityQueue<>(Collections.reverseOrder());
            right=new PriorityQueue<>();
        }

        public void add(int val){
            if(right.size() > 0 && val > right.peek()){
                right.add(val);
            } else {
                left.add(val);
            }

            if(left.size()-right.size() == 2){
                right.add(left.remove());
            } else if(right.size() - left.size() == 2){
                left.add(right.remove());
            }
        }

        public int remove(){
            if(this.size() == 0){
                System.out.println("Queue underflow");
                return -1;
            } else if(left.size() >= right.size())
                return left.remove();
            else
                return right.remove();
        }

        public int peek(){
            if(this.size() == 0){
                System.out.println("Queue underflow");
                return -1;
            } else if(left.size() >= right.size())
                return left.peek();
            else
                return right.peek();
        }

        public int size(){
            return left.size()+right.size();
        }
    }
}
