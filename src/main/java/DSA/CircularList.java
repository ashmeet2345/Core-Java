package DSA;

public class CircularList {

    Node head;
    Node tail;

    public class Node{
        int data;
        Node next;

        public Node(int data){
            this.data=data;
        }

    }

    public void insertFirst(int val){
        Node temp=new Node(val);
        if(head==null){
            temp.next=null;
            head=tail=temp;
        } else {
            temp.next=head;
            head=temp;
            tail.next=temp;
        }
    }

    public void display(){
        Node temp=head;
        while(temp!=tail){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.print(temp.data+" ");
    }

    public static void main(String[] args) {
        CircularList cl=new CircularList();
        cl.insertFirst(20);
        cl.insertFirst(30);
        cl.insertFirst(40);
        cl.insertFirst(50);
        cl.insertFirst(60);

        cl.display();
    }
}
