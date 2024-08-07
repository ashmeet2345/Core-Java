package DSA;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    static Map<Integer, Node> map;
    static int capacity;

    public static class Node{
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key,int value){
            this.key=key;
            this.value=value;
            this.next=null;
            this.prev=null;
        }
    }

    static Node head=null;
    static Node tail=null;
    static int size=0;

    public LRUCache(int capacity){
        this.capacity=capacity;
        map=new LinkedHashMap<>(capacity);
    }

    public void deleteNode(Node node){
        Node temp=head;
        Node temp2;
        if(node == tail){
            tail=tail.prev;
            tail.next=null;
        } else if(node == head) {
            head=head.next;
            head.prev=null;
        } else {
            while(temp.next!=node){
                temp=temp.next;
            }
            temp2=temp.next.next;
            temp2.prev=temp;
            temp.next=temp2;
        }

    }

    public int put(int key,int value){
        if(map.containsKey(key)){
            Node t=map.get(key);
            t.value=value;
            map.put(key, t);
            deleteNode(t);
            insertNodeAtBeginning(t);
            return 1;
        } else {
            if(map.size()>=capacity){
                System.out.println("Size maxed out");
                return -1;
            }
            else {
                Node t=new Node(key,value);
                map.put(key, t);
                insertNodeAtBeginning(t);
                return 1;
            }
        }
    }

    public void insertNodeAtBeginning(Node node){
        if(head==null){
            head=node;
            tail=node;
            size++;
        } else {
            Node temp=head;
            head=node;
            node.next=temp;
            temp.prev=head;
            head.prev=null;
            size++;
        }
    }

    public void display(Node head){
        Node temp=head;
        while(temp!=null){
            System.out.print("("+temp.key+","+temp.value+") ");
            temp=temp.next;
        }
        System.out.println();
    }

    public int get(int key){
        if(!map.containsKey(key)){
            System.out.println("Key not present");
            return -1;
        } else{
            Node node=map.get(key);
            deleteNode(node);
            insertNodeAtBeginning(node);
            return node.value;
        }
    }

    public static void main(String[] args) {
        LRUCache cache=new LRUCache(4);
        cache.put(2,5);
        cache.put(3,4);
        cache.put(4,3);
        cache.put(5,2);
        cache.get(3);
        cache.put(2,5);
        cache.display(head);

    }
}
