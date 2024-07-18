package DSA;

public class DoublyLists {

    Node head;
    Node tail;
    public void insertFirst(int val){
        Node temp=new Node(val);
        if(head==null){
            temp.next=null;
            temp.prev=null;
            head=temp;
            tail=temp;
        } else {
            temp.next=head;
            temp.prev=null;
            head.prev=temp;
            head=temp;
        }
    }

    public void insertLast(int val){
        Node temp=new Node(val);
        if(head == null){
            temp.next=null;
            temp.prev=null;
            head=tail=temp;
        } else {
            temp.next=null;
            temp.prev=tail;
            tail.next=temp;
            tail=temp;
        }
    }

    public void insertAtK(int val,int k){
        Node temp=new Node(val);
        Node temp2=head;
        Node temp3;
        for(int i=1;i<k-1;i++){
            temp2=temp2.next;
        }
        temp.next=temp2.next;
        temp3=temp2.next;
        temp3.prev=temp;
        temp2.next=temp;
        temp.prev=temp2;
    }

    public void setTail(){
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        tail=temp;
    }

    public void setHead(){
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        tail=temp;
    }

    public static class Node{
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data=data;
        }

        public Node(int data,Node next,Node prev){
            this.data=data;
            this.next=next;
            this.prev=prev;
        }
    }

    public void display(){
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.println();
    }

    public void displayReverse(){
        Node temp=tail;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLists dll=new DoublyLists();
        dll.insertFirst(12);
        dll.insertFirst(15);
        dll.insertFirst(13);
        dll.insertFirst(21);
        dll.insertFirst(25);
        dll.insertLast(69);
        dll.insertAtK(45,3);
        dll.display();
        dll.displayReverse();
    }
}
