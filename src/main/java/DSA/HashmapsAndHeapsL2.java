package DSA;

import java.util.*;
import java.util.stream.Collectors;

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
        int count1=0;
        int count2=0;
        for(int i=0;i<str2.length();i++){
            if(!mp2.containsKey(str2.charAt(i))){
                mp2.put(str2.charAt(i),1);
            } else {
                mp2.put(str2.charAt(i),mp2.get(str2.charAt(i))+1);
            }
            count2++;
        }

        int i=0;
        int j=0;
        int strLen=Integer.MAX_VALUE;
        String resStr="";

        while(true){
            boolean m1=false;
            boolean m2=false;
            while(count1!=count2 && i<str1.length()){
                if(!mp1.containsKey(str1.charAt(i))){
                    mp1.put(str1.charAt(i),1);
                } else {
                    mp1.put(str1.charAt(i),mp1.get(str1.charAt(i))+1);
                }
                if(mp1.get(str1.charAt(i))<=mp2.getOrDefault(str1.charAt(i),0)){
                    count1++;
                }
                i++;
                m1=true;
            }
            while(j<i && count1==count2){
                int v=mp1.getOrDefault(str1.charAt(j),0);
                int r=mp2.getOrDefault(str1.charAt(j),0);
                String res=str1.substring(j,i);
                if(v==1){
                    mp1.remove(str1.charAt(j));
                }else if(v>r){
                    mp1.put(str1.charAt(j),v-1);
                } else if(v==r){
                    mp1.put(str1.charAt(j),v-1);
                    count1--;
                }
                if(res.length()<strLen){
                    strLen=res.length();
                    resStr=res;
                }
                j++;
                m2=true;
            }
            if(!m1 && !m2){
                break;
            }
        }
        System.out.println(resStr);
    }

    public void longestSubstringWithoutRepeatingCharacters(String str){
        Map<Character, Integer> mp=new HashMap<>();

        int i=0;
        int j=0;
        int maxlen=Integer.MIN_VALUE;
        while(true){
            boolean m1=false;
            boolean m2=false;
            while(i<str.length()){
                m1=true;
                char ch=str.charAt(i);
                if(mp.getOrDefault(ch,0) == 2){
                    break;
                } else {
                    int len=i-j;
                    if(len>maxlen){
                        maxlen=len;
                    }
                }
            }
            while(j<i){
                m2=true;
                char ch=str.charAt(j);
                mp.put(ch,mp.get(ch)-1);
                if(mp.get(ch)==1){
                    break;
                }
            }

            if(!m1 && !m2) break;
        }

        System.out.println(maxlen);
    }

    public void countSubstringsWithoutRepeatingCharacters(String str){

        Map<Character,Integer> mp=new HashMap<>();
        int count=0;
        int i=-1;
        int j=-1;

        while(true){
            boolean m1=false;
            boolean m2=false;
            while(i<str.length()-1){
                m1=true;
                i++;
                char ch=str.charAt(i);
                if(!mp.containsKey(ch)){
                    mp.put(ch,1);
                    count+=(i-j);
                } else {
                    mp.put(ch,mp.getOrDefault(ch,0)+1);
                    break;
                }
            }

            while(j<=i && (mp.size() < (i-j))){
                m2=true;
                j++;
                char ch=str.charAt(j);
                int val=mp.get(ch);
                if(val==1){
                    mp.remove(ch);
                } else if(val>1){
                    mp.put(ch,val-1);
                }
                if(mp.get(str.charAt(i)) == 1){
                    count+=(i-j);
                    break;
                }

            }
            if(!m1 && !m2){
                break;
            }
        }
        System.out.println(count);
    }


    public void longestSubstringWithExactlyKDistinctCharacters(String str,int k){
        Map<Character,Integer> mp=new HashMap<>();
        int mxSize=Integer.MIN_VALUE;
        int i=-1;
        int j=-1;
        while(true){
            boolean m1=false;
            boolean m2=false;
            while(i<str.length()-1){
                m1=true;
                i++;
                char ch=str.charAt(i);
                mp.put(ch,mp.getOrDefault(ch,0)+1);
                if(mp.size()>k){
                    break;
                }
            }
            mxSize=Math.max(i-j-1,mxSize);
            while(j<=i && mp.size()>k){
                m2=true;
                j++;
                char ch=str.charAt(j);
                int val=mp.get(ch);
                if(val>1){
                    mp.put(ch,val-1);
                } else if(val==1){
                    mp.remove(ch);
                }
                if(mp.size()==k){
                    break;
                }
            }

            if(!m1 && !m2){
                break;
            }
        }
        System.out.println(mxSize);
    }


    public void countOfEquivalentSubarrays(int[] arr){
        Set<Integer> set=Arrays.stream(arr).boxed().collect(Collectors.toSet());
        int count=0;
        Map<Integer,Integer> mp=new HashMap<>();
        int i=-1;
        int j=-1;
        while(true){
            boolean m1=false;
            boolean m2=false;

            while(i<arr.length-1){
                m1=true;
                i++;
                mp.put(arr[i],mp.getOrDefault(arr[i],0)+1);
                if(mp.size()==set.size()){
                    count+=arr.length-i;
                    break;
                }
            }
            while(j<=i && i<arr.length-1){
                m2=true;
                j++;
                int val=mp.get(arr[j]);
                if(val>1){
                    mp.put(arr[j],val-1);
                } else if(val==1){
                    mp.remove(arr[j]);
                }
                if(mp.size()==set.size()){
                    count+=arr.length-i;
                } else if(mp.size()<set.size()){
                    break;
                }
            }

            if(!m1 && !m2){
                break;
            }
        }
        System.out.println(count);
    }


    public void maximumConsecutiveOnes1(int[] arr,int k){
        int countZeros=0;
        int mxLen=Integer.MIN_VALUE;
        int len=0;
        int i=-1;
        int j=-1;
        while(true){
            boolean m1=false;
            boolean m2=false;
            while(i<arr.length-1){
                m1=true;
                i++;
                if(arr[i]==1){
                    len++;
                } else if(arr[i]==0){
                    if(countZeros<k){
                        countZeros++;
                        len++;
                    } else {
                        i--;
                        break;
                    }
                }
            }
            mxLen=Math.max(mxLen,len);
            while(j<=i && i<arr.length-1){
                m2=true;
                j++;
                if(arr[j]==1){
                    len--;
                } else if(arr[j]==0){
                    if(countZeros>=k){
                        countZeros--;
                        len--;
                    } else {
                        break;
                    }
                }
                if(countZeros<k){
                    break;
                }
            }

            if(!m1 && !m2){
                break;
            }
        }
        System.out.println(mxLen);
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Character, Integer> map2 = new HashMap<>(), map1 = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            map2.put(p.charAt(i), map2.getOrDefault(p.charAt(i), 0) + 1);
        }
        int i = -1, j = -1;
        while (true) {
            boolean f1 = false, f2 = false;
            while (j < s.length() - 1) {
                f1 = true;
                j++;
                char ch = s.charAt(j);
                map1.put(ch, map1.getOrDefault(ch, 0) + 1);
                if (!map2.containsKey(ch) || map1.get(ch) > map2.get(ch)) break;
                if (j - i == p.length()) ans.add(i + 1);

            }
            while (i < j) {
                i++;
                f2 = true;
                char ch = s.charAt(i);
                map1.put(ch, map1.getOrDefault(ch, 0) - 1);
                if (map1.get(ch) <= 0) map1.remove(ch);
                if (!map2.containsKey(ch)) break;
                if (map1.containsKey(ch) && map1.get(ch).equals(map2.get(ch))) {
                    if (j - i == p.length()) ans.add(i + 1);
                    break;
                }
            }
            if (!f1 && !f2) break;
        }
        return ans;
    }

    public String perfectDifference(String s1){
        StringBuilder builder=new StringBuilder();
        if(s1.length()>1){
            for(int i=1;i<s1.length();i++){
                int val=(int)s1.charAt(i)-(int)s1.charAt(i-1);
                if(val<0){
                    val+=26;
                }
                builder.append(val).append("#");
            }
        } else {
            return builder.append("#").toString();
        }
        builder.deleteCharAt(s1.length());
        return builder.toString();
    }

    public void groupShiftedStrings(String[] arr){
        Map<String, List<String> > res=new HashMap<>();

        for(int i=0;i<arr.length;i++){
            String str=perfectDifference(arr[i]);
            if(!res.containsKey(str)){
                List<String> list=new ArrayList<>();
                list.add(arr[i]);
                res.put(str,list);
            } else {
                List<String> list=res.get(str);
                list.add(arr[i]);
                res.put(str,list);
            }
        }

        res.entrySet().stream().forEach(s-> System.out.print(s.getValue()+" "));
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

       /* System.out.println("Minimum window substring I: ");
        hash.minimumWindowSubstring("dbaecbbabdcaafbddcabgba","abbcdc");
        hash.longestSubstringWithoutRepeatingCharacters("dbaecbbabdcaafbddcabgba");*/

        System.out.println("Count Substrings without Repeating Characters: ");
        hash.countSubstringsWithoutRepeatingCharacters("abbd");

        System.out.println("Longest Substring With Exactly K Distinct Characters");
        hash.longestSubstringWithExactlyKDistinctCharacters("aabcbcdbca",2);

        System.out.println("Count of equivalent Subarrays");
        hash.countOfEquivalentSubarrays(new int[]{2,5,3,5,2,4,1,3,1,4});

        System.out.println("Maximum consecutive ones 1: ");
        hash.maximumConsecutiveOnes1(new int[]{1,1,0,1,0,0,1,1,0,1,0,1,1},3);

        System.out.println("Find Anagrams: ");
        hash.findAnagrams("abcabaccba","abac").stream().forEach(s-> System.out.print(s+" "));

        System.out.println("Group shifted Strings: ");
        hash.groupShiftedStrings(new String[]{"acd","dfg","wyz","yab","mop","bdfh","a","x","moqs"});
    }
}
