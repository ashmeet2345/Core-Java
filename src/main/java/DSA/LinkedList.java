package DSA;

public class LinkedList {
    public static class Node{
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    Node head=null;
    Node tail=null;
    int size=0;


    public void insertFirst(int data){
        Node temp=new Node(data);
        if(head==null){
            head=tail=temp;
        } else {
            temp.next=head;
            head=temp;
        }
        size++;
    }

    public void insertLast(int data){
        Node temp=new Node(data);
        if(head==null){
            head=tail=temp;
        } else {
            tail.next=temp;
            tail=temp;
        }
        size++;
    }

    public void insertAtK(int data,int k){
        Node temp=new Node(data);
        Node temp2=head;
        for(int i=1;i<=k-2;i++){
            temp2=temp2.next;
        }
        temp.next=temp2.next;
        temp2.next=temp;
    }

    public void display(){
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"->");
            temp=temp.next;
        }
    }

    public void reverseHelper(Node node){
        if(node.next==null){
            return;
        }
        reverseHelper(node.next);
        node.next.next=node;
    }

    public void reverseRecursive(Node head){
        reverseHelper(head);
        head.next=null;
        Node temp=head;
        head=tail;
        tail=temp;
        display();
    }

    public void reverse(){
        Node cur=head;
        Node prev=null;
        while(cur!=null){
            Node next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
        }
        head=prev;
    }

    public void foldHelper(Node right,int mid){
        if(right==null){
            return;
        }
        foldHelper(right.next,mid+1);
        if(mid > size/2){
            Node temp=left.next;
            left.next=right;
            right.next=temp;
            left=temp;
        } else if(mid == size/2){
            tail=right;
            tail.next=null;
        }
    }

    Node left;
    public void fold(){
        left=head;
        foldHelper(head,0);
    }

    public void unfold(){
        Node temp1=head;
        Node temp2=head.next;
        Node h1=head;
        Node h2=head.next;
        while(temp1.next.next!=null){
            temp1.next=temp1.next.next;
        }
        while(temp2.next.next!=null){
            temp2.next=temp2.next.next;
        }
    }

    public static void main(String[] args) {

        LinkedList l=new LinkedList();
        l.insertFirst(10);
        l.insertFirst(20);
        l.insertFirst(30);
        l.insertLast(40);
        l.insertLast(50);
        l.insertLast(60);
        l.insertAtK(15,3);
        l.display();
        l.reverse(); System.out.println(); l.display();
        l.fold(); System.out.println(); l.display();

    }
}