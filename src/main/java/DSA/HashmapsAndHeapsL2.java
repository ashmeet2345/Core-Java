package DSA;

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

    public static void main(String[] args) {
        HashmapsAndHeapsL2 hash=new HashmapsAndHeapsL2();
        Map<String, String> hm=new HashMap<>();

        hm.put("Chennai","Bengaluru");
        hm.put("Bombay","Delhi");
        hm.put("Goa","Chennai");
        hm.put("Delhi","Goa");

        hash.reconstructItinerary((HashMap)hm);

    }
}
