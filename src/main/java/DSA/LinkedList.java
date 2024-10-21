package DSA;

public class LinkedList {

    public class Node{
        int data;
        Node next;

        public Node(int data){
            this.data=data;
        }

        public Node(int val, Node next){
            this.data=val;
            this.next=next;
        }
    }

    public static Node head=null;
    public static Node tail=null;
    public static int size=0;

    public void insertLast(int data){
        if(head==null){
            Node node=new Node(data);
            head=node;
            tail=node;
            return;
        } else {
            Node node=new Node(data);
            tail.next=node;
            tail=node;
        }
        size++;
    }

    public void insertFirst(int data){
        if(head==null){
            Node node=new Node(data);
            head=node;
            tail=node;
            return;
        } else {
            Node node=new Node(data);
            node.next=head;
            head=node;
        }
        size++;
    }

    public void insertAtKPosition(int data,int k){
        Node temp=head;
        Node node=new Node(data);
        for(int i=0;i<k-2;i++){
            temp=temp.next;
        }
        node.next=temp.next;
        temp.next=node;
        size++;
    }

    public void display(){
        Node temp=head;
        while(temp!=null){
            System.out.println(temp.data+" ");
            temp=temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list=new LinkedList();
        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);
        list.insertFirst(40);
        list.insertFirst(50);
        list.insertAtKPosition(80,3);
        list.display();

    }
}