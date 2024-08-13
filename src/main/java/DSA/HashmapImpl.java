package DSA;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashmapImpl {

    public static class HashMap<K,V>{

        private class HMNode{
            K key;
            V value;

            HMNode(K key, V value){
                this.key = key;
                this.value = value;
            }
        }

        private int size;
        private LinkedList<HMNode>[] buckets;

        public HashMap(){
            initBuckets(4);
            size=0;
        }

        private void initBuckets(int N) {
            buckets=new LinkedList[N];
            for(int i=0;i<N;i++){
                buckets[i]=new LinkedList<>();
            }
        }

        public void put(K key, V value){
            int bucketIndex=hashFunction(key);
            int dataIndex=findWithinBucket(key,bucketIndex);
            if(dataIndex != -1){
                HMNode node=buckets[bucketIndex].get(dataIndex);
                node.value=value;
            } else {
                buckets[bucketIndex].add(new HMNode(key,value));
                size++;
            }

            double lambda=size*1.0/buckets.length;
            if(lambda > 2){  //we can consider anything instead of 2
                rehash();
            }
        }

        private void rehash() {
           LinkedList<HMNode>[] oldBuckets=buckets;
           initBuckets(oldBuckets.length*2);
           size=0;

           for(int i=0;i<oldBuckets.length;i++){
               for(HMNode node: oldBuckets[i]){
                   put(node.key,node.value);
               }
           }
        }

        private int hashFunction(K key){
            int hc=key.hashCode();
            return Math.abs(hc)%buckets.length;
        }

        private int findWithinBucket(K key, int bucketIndex){
            LinkedList<HMNode> list=buckets[bucketIndex];
            for(int i=0;i<list.size();i++){
                if(list.get(i).key.equals(key)){
                    return i;
                }
            }
            return -1;
        }

        public V get(K key){
            int bucketIndex=hashFunction(key);
            int dataIndex=findWithinBucket(key,bucketIndex);
            if(dataIndex != -1){
                return buckets[bucketIndex].get(dataIndex).value;
            }
            return null;
        }

        public boolean containsKey(K key){
            int bucketIndex=hashFunction(key);
            int dataIndex=findWithinBucket(key,bucketIndex);
            if(dataIndex != -1){
                return true;
            }
            return false;
        }

        public V remove(K key){
            int bucketIndex=hashFunction(key);
            int dataIndex=findWithinBucket(key,bucketIndex);
            if(dataIndex != -1){
                HMNode node=buckets[bucketIndex].get(dataIndex);
                buckets[bucketIndex].remove(dataIndex);
                size--;
                return node.value;
            }
            return null;
        }

        public int size(){
            return size;
        }

        public ArrayList<K> keySet(){
            ArrayList<K> keys=new ArrayList<>();
            for(int i=0;i<buckets.length;i++){
                for(HMNode node: buckets[i]){
                    keys.add(node.key);
                }
            }
            return keys;
        }

    }

    public static void main(String[] args) {
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(1,12);
        map.put(2,23);
        map.put(3,34);
        map.put(4,45);
        map.put(5,56);
        System.out.println(map.get(1));
        System.out.println(map.containsKey(1));
        map.remove(1);
        System.out.println(map.containsKey(1));

        map.keySet().stream().forEach(s-> System.out.print(map.get(s)+" "));
    }
}
