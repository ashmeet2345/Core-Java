package DSA;

public class Lists {

    public static class Node{
        int data;
        Node next;
        Node child;
        Node random;

        public Node(){}

        public Node(int data) {
            this.data=data;
            this.next=null;
            this.child=null;
            this.random=null;
        }
    }

    public static class LinkedList{
        Node head;
        Node tail;
        Node childTail;
        int size;

        void addLast(int num){
            if(size==0){
                Node temp=new Node();
                temp.data=num;
                temp.next=null;
                head=tail=temp;
                size++;
            } else {
                Node temp=new Node();
                temp.data=num;
                temp.next=null;
                tail.next=temp;
                tail=temp;
                size++;
            }
        }

        void display(){
            Node temp=head;
            while(temp!=null){
                System.out.print(temp.data+" ");
                temp=temp.next;
            }
            System.out.print("\nsize - "+size);
        }

        void removeFirst(){
            if(size==1){
                head=tail=null;
                size=0;
            } else {
                head=head.next;
                size--;
            }
            System.out.println();
        }

        int getValue(int index){
            Node temp=head;
            for(int i=1;i<index-1;i++){
                temp=temp.next;
            }
            return temp.next.data;
        }

        void addFirst(int num){
            if(size==0){
                Node temp=new Node();
                temp.data=num;
                temp.next=null;
                head=tail=temp;
                size++;
            } else {
                Node temp=new Node();
                temp.data=num;
                temp.next=head;
                head=temp;
                size++;
            }
        }

        void addAtIndex(int index,int num){
            if(size==0)
                addFirst(num);
            else{
                Node temp=new Node();
                Node temp2=head;
                temp.data=num;
                for(int i=1;i<index-1;i++){
                    temp2=temp2.next;
                }
                temp.next=temp2.next;
                temp2.next=temp;
                size++;
            }
        }

        void removeLast(){
            Node temp=head;
            while(temp.next.next!=null){
                temp=temp.next;
            }
            tail=temp;
            temp.next=null;
            size--;
        }

        void removeAtIndex(int index){
            Node temp=head;
            for(int i=1;i<index-1;i++){
                temp=temp.next;
            }
            temp.next=temp.next.next;
            size--;
        }

        void reverseList(){
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

        int kthFromLast(int k){
            Node temp=head;
            for(int i=1;i<=size-k;i++){
                temp=temp.next;
            }
            return temp.data;
        }

        int middleOfLL(){
            Node f=head;
            Node s=head;
            while(f.next!=null && f.next.next!=null){
                s=s.next;
                f=f.next.next;
            }
            return s.data;
        }

        int getFirst(){
            Node temp=head;
            return temp.data;
        }

        void kthReverse(int k){
           LinkedList prev=null;

           while(this.size>0){
               LinkedList curr=new LinkedList();
               if(this.size >= k){
                   for(int i=0;i<k;i++){
                       int val=this.getFirst();
                       this.removeFirst();
                       curr.addFirst(val);
                   }
               } else {
                   int os=this.size;
                   for(int i=0;i<os;i++){
                       int val=this.getFirst();
                       this.removeFirst();
                       curr.addLast(val);
                   }
               }

               if(prev == null){
                   prev=curr;
               } else {
                   prev.tail.next=curr.head;
                   prev.tail=curr.tail;
                   prev.size+=curr.size;
               }
           }
           this.head=prev.head;
           this.tail=prev.tail;
           this.size=prev.size;
        }

        void reverseHelper(Node node){
            if(node == null)
                return;
            reverseHelper(node.next);
            if( node == tail){
            } else {
                node.next.next=node;
            }
        }

        void recursiveReverse() {
            reverseHelper(head);
            head.next = null;
            Node temp = head;
            head = tail;
            tail = temp;
        }

        boolean palindromeHelper(Node right){
            if(right == null){
                return true;
            }
            boolean res=palindromeHelper(right.next);
            if(res==false)
                return false;
            else if (left.data!=right.data)
                return false;
            else{
                left=left.next;
                return true;
            }
        }

        Node left;
        boolean isPalindrome(Node head){
            left=head;
            return palindromeHelper(head);
        }

        void foldHelper(Node right,int floor){
            if(right == null){
                return;
            }
            foldHelper(right.next,floor+1);
            if(floor > size/2){
                Node temp=lleft.next;
                lleft.next=right;
                right.next=temp;
                lleft=temp;
            } else if (floor==size/2){
                tail=right;
                tail.next=null;
            }
        }

        Node lleft;
        void foldLL(Node head){
            lleft=head;
            foldHelper(head,0);
        }


        public static int addLLhelper(Node one,int s1,Node two,int s2,LinkedList res){
            if(one == null && two == null)
                return 0;

            int nd=0;
            if(s1>s2){
                int oc=addLLhelper(one.next,s1-1,two,s2,res);
                nd=one.data+oc;
            } else if(s1<s2){
                int oc=addLLhelper(one,s1,two.next,s2-1,res);
                nd=two.data+oc;
            } else {
                int oc=addLLhelper(one.next,s1-1,two.next,s2-1,res);
                nd=one.data+two.data+oc;
            }

            int d=nd%10;
            int c=nd/10;
            res.addFirst(d);
            return c;
        }

        LinkedList addLL(LinkedList list1,LinkedList list2){
            LinkedList res=new LinkedList();
            int oc = addLLhelper(list1.head,list1.size,list2.head,list2.size,res);
            if(oc > 0){
                res.addFirst(oc);
            }
            return res;
        }
    }

    public static class MergeLists{
        MergeLists(LinkedList l1,LinkedList l2){
            mergeSorted(l1,l2);
        }

        LinkedList mergeSorted(LinkedList l1,LinkedList l2){

            LinkedList res=new LinkedList();
            Node a=l1.head;
            Node b=l2.head;
            while(a!=null && b!=null){
                if(a.data < b.data){
                    res.addLast(a.data);
                    a=a.next;
                } else {
                    res.addLast(b.data);
                    b=b.next;
                }
            }

            while(a!=null){
                res.addLast(a.data);
                a=a.next;
            }

            while(b!=null){
                res.addLast(b.data);
                b=b.next;
            }

            return res;
        }

        LinkedList mergeSort(Node head, Node tail){
            if(head==tail){
                LinkedList l=new LinkedList();
                l.addLast(head.data);
                return l;
            }

            Node mid=midNode(head,tail);
            LinkedList fsl=mergeSort(head,mid);
            LinkedList ssl=mergeSort(mid.next,tail);
            LinkedList res=mergeSorted(fsl,ssl);
            return res;
        }

        Node midNode(Node head,Node tail){
            Node s=head;
            Node f=head;
            while(f!=tail && f.next!=tail){
                f=f.next.next;
                s=s.next;
            }
            return s;
        }

        void removeDuplicates(Node head){
            Node prev=head;
            Node curr=head.next;
            while(curr!=null){
                if(prev.data==curr.data){
                    curr=curr.next;
                    prev.next=curr;
                } else {
                    curr=curr.next;
                    prev=prev.next;
                }
            }
        }

        public Node flatteningLinkekedList(Node head){
            if(head==null || head.next==null)
                return head;
            Node mergedHead=flatteningLinkekedList(head.next);
            return mergeList(head,mergedHead);
        }

        public Node mergeList(Node head1, Node head2){
            Node dummy=new Node();
            Node res=dummy;
            Node temp1=head1;
            Node temp2=head2;
            while(temp1!=null&&temp2!=null){
                if(temp1.data < temp2.data){
                    res.child=temp1;
                    res=temp1;
                    temp1=temp1.next;
                } else {
                    res.child=temp2;
                    res=temp2;
                    temp2=temp2.next;
                }
                res.next=null;
            }

            if(temp1!=null){
                res.child=temp1;
            } else {
                res.child=temp2;
            }

            return dummy.child;
        }

        public void cloningLinkedListWithRandomPointers(Node head){
            Node temp=head;
            while(temp!=null){
                Node newNode=new Node(temp.data);
                newNode.next=temp.next;
                temp.next=newNode;
                temp=temp.next.next;
            }
            temp=head;
            while(temp!=null){
                Node copy=temp.next;
                if(temp.random==null){
                    copy.random=null;
                } else {
                    copy.random=temp.random.next;
                }
                temp=temp.next.next;
            }
            Node node=new Node(-1);
            Node res=node;
            temp=head;
            while(temp!=null){
                res.next=temp.next;
                temp.next=temp.next.next;
            }
        }
    }

    public static void main(String[] args) {
        LinkedList list=new LinkedList();
        LinkedList list2=new LinkedList();
        LinkedList list3=new LinkedList();
        MergeLists ans=new MergeLists(list,list2);
        list.addLast(2);
        list.addLast(2);
        list.addLast(2);
        list.addLast(18);
        list.addLast(18);
        list.addLast(41);
        list.addLast(69);
        list.reverseList();
        System.out.print("Before- ");
        list.display();
        ans.removeDuplicates(list.head);
        System.out.print("\nAfter removing duplicates- ");
        list.display();

        list2.addFirst(88);
        list2.addFirst(35);
        list2.addFirst(10);
        list2.addFirst(8);
        list2.addFirst(5);
        System.out.println("\n");
       // list2.display();
        //list.display();
       /* list.removeFirst();
        list.display();*/
       /* System.out.print("\nAfter- ");
        list.display();
        list.removeLast();
        System.out.print("\nAfter removing last- ");
        list.display();
        list.removeAtIndex(3);
        System.out.print("\nAfter removing 3rd- ");
        list.display();
        list.reverseList();
        System.out.print("\nAfter reversing- ");
        list.display();
        System.out.println("\n2nd element from last- "+list.kthFromLast(2));
        System.out.println("\nMiddle of the LL- "+list.middleOfLL());*/
        //System.out.print("\nValue at index 2- "+list.getValue(2));
        System.out.println("\n");

        list3.addLast(18);
        list3.addLast(2);
        list3.addLast(69);
        list3.addLast(4);
        list3.addLast(41);
        list3.addLast(33);
        list3.addLast(9);
        list3.kthReverse(3);
        System.out.print("\nAfter Kth reverse- ");
        list3.display();
        list3.recursiveReverse();
        System.out.print("\nAfter recursive reverse- ");
        list3.display();

        LinkedList list4=new LinkedList();
        list4.addLast(2);
        list4.addLast(8);
        list4.addLast(9);

        LinkedList list5=new LinkedList();
        list5.addLast(7);
        list5.addLast(8);


        System.out.print("\nIs list palindromic - "+list3.isPalindrome(list4.head));

        list3.foldLL(list3.head);
        System.out.print("\nFolded list is- ");
        list3.display();

        LinkedList res=list.addLL(list4,list5);
        System.out.print("\nList4+list5 = ");
        res.display();

        /*LinkedList l=ans.mergeSort(list3.head, list3.tail);
        l.display();*/
    }
}

