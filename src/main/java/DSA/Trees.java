package DSA;

import com.sun.source.tree.Tree;

import java.util.*;

public class Trees {
    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left=null;
            this.right=null;
        }
    }

    static Node root;
    public void insert(Node node,int data){
        if(node == null){
            root=new Node(data);
            return;
        }

        Queue<Node> q=new ArrayDeque<>();
        q.add(node);
        while(q.size()>0){
            Node t=q.remove();
            if(t.left == null){
                t.left=new Node(data);
                break;
            } else {
                q.add(t.left);
            }

            if(t.right==null){
                t.right=new Node(data);
                break;
            } else {
                q.add(t.right);
            }
        }
    }

    public void inOrder(Node root){
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    public void levelOrder(Node node){
        System.out.print("Level Order: ");
        Queue<Node> q=new ArrayDeque<>();
        q.add(node);
        while(q.size()>0){
            Node temp=q.remove();
            System.out.print(temp.data+" ");
            if(temp.left!=null){
                q.add(temp.left);
            }
            if(temp.right!=null){
                q.add(temp.right);
            }
        }
    }

    public void iterativePreOrder(Node node){
        System.out.print("Iterative PreOrder: ");
        Stack<Node> st=new Stack<>();
        st.add(node);
        while(st.size()>0){
            System.out.print(st.peek().data+" ");
            Node temp=st.pop();
            if(temp.right!=null){
                st.push(temp.right);
            }
            if(temp.left!=null){
                st.push(temp.left);
            }
        }
    }

    public void iterativeInOrder(Node node){
        System.out.print("Iterative Inorder: ");
        Stack<Node> st=new Stack<>();

        while(true){
            if(node!=null){
                st.add(node);
                node=node.left;
            } else {
                if(st.size() == 0)
                    break;

                node=st.pop();
                System.out.print(node.data+" ");
                node=node.right;
            }
        }
    }

    public void iterativePostOrder2Stacks(Node node){
        System.out.print("Iterative PostOrder using 2 Stacks: ");
        Stack<Node> st1=new Stack<>();
        Stack<Node> st2=new Stack<>();
        st1.push(node);
        while(st1.size() > 0){
            Node temp=st1.pop();
            st2.push(temp);
            if(temp.left!=null){
                st1.push(temp.left);
            }
            if(temp.right!=null){
                st1.push(temp.right);
            }
        }

        while(st2.size()>0){
            System.out.print(st2.pop().data+" ");
        }
    }

    public int depth(Node node){
        if(node == null){
            return 0;
        }
        int hl=depth(node.left);
        int hr=depth(node.right);
        int h=Math.max(hl,hr);
        return h+1;
    }

    public int heightForBBT(Node node){
        if(node == null){
            return 0;
        }
        int lh=heightForBBT(node.left);
        int rh=heightForBBT(node.right);
        if(Math.abs(lh-rh)>1){
            return -1;
        } else return Math.max(lh,rh)+1;
    }

    public boolean checkBalancedBT(Node node){
        return heightForBBT(node)!=-1;
    }

    public int diameter(Node node){
        Queue<Node> q=new ArrayDeque<>();
        q.add(node);
        int lt=0;
        int rt=0;
        int max=Integer.MIN_VALUE;
        while(q.size()>0){
            Node temp=q.poll();
            if(temp.left!=null){
                q.add(temp.left);
               lt=depth(temp.left);
            }
            if(temp.right!=null){
                q.add(temp.right);
                rt=depth(temp.right);
            }
            if(lt+rt+1>max){
                max=lt+rt+1;
            }
        }
        return max;
    }

    public int diameterNoQueue(Node node,int max){
        if(node == null){
            return 0;
        }
        int lh=diameterNoQueue(node.left,max);
        int rh=diameterNoQueue(node.right,max);
        max=Math.max(max,lh+rh);
        return lh+rh+1;
    }

    public void insertTree(Node node,int data){
        if(root == null)
            root=new Node(data);
        root.left=new Node(data*2);
        root.right=new Node(data*3);
        root.left.left=new Node(data*4);
        root.left.right=new Node(data*5);
        root.left.left.left=new Node(data*6);
        root.left.right.right=new Node(data*7);
        root.left.left.left.left=new Node(data*8);
        root.left.right.right.right=new Node(data*9);
    }

    static int num=0;
    public int maxPathSum(Node node,int max){
        if(node == null)
            return 0;
        int lt=maxPathSum(node.left,max);
        int rt=maxPathSum(node.right,max);
        max=Math.max(lt+rt+node.data,max);
        num=max;
        return Math.max(lt,rt)+node.data;
    }

    public void leftTreeWithoutLeaf(Node node,ArrayList<Integer> list){
        if(node.left == null && node.right == null){
            return;
        }
        list.add(node.data);
        if(node.left!=null)
            leftTreeWithoutLeaf(node.left,list);
        else if(node.right!=null)
            leftTreeWithoutLeaf(node.right,list);
    }

    public void leaf(Node node,ArrayList<Integer> list){
        if(node.left == null && node.right == null){
            list.add(node.data);
            return;
        }
        leaf(node.left,list);
        leaf(node.right,list);
    }

    public void rightTreeWithoutLeaf(Node node,ArrayList<Integer> list){
        if(node.left == null && node.right == null){
            return;
        }
        if(node.left!=null)
            rightTreeWithoutLeaf(node.left,list);
        else if(node.right!=null)
            rightTreeWithoutLeaf(node.right,list);
        list.add(node.data);
    }

    public ArrayList<Integer> boundaryTraversal(Node node){
        ArrayList<Integer> list=new ArrayList<>();
        list.add(node.data);
        leftTreeWithoutLeaf(node.left,list);
        leaf(node,list);
        rightTreeWithoutLeaf(node.right,list);
        return list;
    }

    public void verticalOrderTraversal(Node node){
         Queue<Pair> q=new ArrayDeque<>();
         TreeMap<Integer,TreeMap<Integer,PriorityQueue>> treeMap=new TreeMap<>();
         Pair p=new Pair(node,0,0);
         q.add(p);
         while(q.size()>0){
             Pair temp=q.poll();
             int x=temp.x;
             int y=temp.y;
             Node tnode=temp.node;
             if(!treeMap.containsKey(x)){
                 treeMap.put(x,new TreeMap<>());
             }
             if(!treeMap.get(x).containsKey(y)){
                 treeMap.get(x).put(y,new PriorityQueue());
             }
             treeMap.get(x).get(y).offer(tnode.data);
             if(tnode.left!=null){
                 q.add(new Pair(tnode.left,x-1,y+1));
             }
             if(tnode.right!=null){
                 q.add(new Pair(tnode.right,x+1,y+1));
             }
         }

         List<List<Integer>> list=new ArrayList<>();
         for(TreeMap<Integer,PriorityQueue> tm:treeMap.values()){
            List<Integer> llist=new ArrayList<>();
            for(PriorityQueue<Integer> pq:tm.values()){
                while(!pq.isEmpty()){
                    llist.add(pq.poll());
                }
            }
            list.add(llist);
         }

         for(List<Integer> l:list){
             for(Integer i:l){
                 System.out.print(i+" ");
             }
             System.out.print(",");
         }
    }

    public void topView(Node node){
        Queue<Pair> q=new ArrayDeque<>();
        q.add(new Pair(0,node));
        TreeMap<Integer,Node> tm=new TreeMap<>();
        while(!q.isEmpty()){
            Pair p=q.poll();
            int x=p.x;
            Node temp=p.node;
            if(!tm.containsKey(x)){
                tm.put(x,temp);
            }
            if(temp.left!=null){
                q.add(new Pair(x-1,temp.left));
            }
            if(temp.right!=null){
                q.add(new Pair(x+1,temp.right));
            }
        }

        for(Map.Entry<Integer,Node> mp: tm.entrySet()){
            System.out.print(mp.getValue().data+" ");
        }
    }

    public void bottomView(Node node){
        Queue<Pair> q=new ArrayDeque<>();
        TreeMap<Integer,Node> tm=new TreeMap<>();
        q.add(new Pair(node,0,0));
        while(!q.isEmpty()){
            Pair p=q.poll();
            Node temp=p.node;
            int x=p.x;
            int y=p.y;
            tm.put(x,temp);
            if(temp.left!=null){
                q.add(new Pair(temp.left,x-1,y+1));
            }
            if(temp.right!=null){
                q.add(new Pair(temp.right,x+1,y+1));
            }
        }

        for(Map.Entry<Integer,Node> mp:tm.entrySet()){
            System.out.print(mp.getValue().data+" ");
        }
    }

    public void rightView(Node node){
        Queue<Pair> q=new ArrayDeque<>();
        TreeMap<Integer,Node> tm=new TreeMap<>();
        q.add(new Pair(node,1));
        while(!q.isEmpty()){
            Pair p=q.poll();
            int y=p.y;
            Node temp=p.node;
            tm.put(y,temp);
            if(temp.left!=null){
                q.add(new Pair(temp.left,y+1));
            }
            if(temp.right!=null){
                q.add(new Pair(temp.right,y+1));
            }
        }

        for(Map.Entry<Integer,Node> mp:tm.entrySet()){
            System.out.print(mp.getValue().data+" ");
        }
    }

    public void leftView(Node node){
        Queue<Pair> q=new ArrayDeque<>();
        TreeMap<Integer,Node> tm=new TreeMap<>();
        q.add(new Pair(node,1));
        while(!q.isEmpty()){
            Pair p=q.poll();
            int y=p.y;
            Node temp=p.node;
            tm.put(y,temp);
            if(temp.right!=null){
                q.add(new Pair(temp.right,y+1));
            }
            if(temp.left!=null){
                q.add(new Pair(temp.left,y+1));
            }
        }

        for(Map.Entry<Integer,Node> mp:tm.entrySet()){
            System.out.print(mp.getValue().data+" ");
        }
    }

    public boolean symmetryHelp(Node left,Node right){
        if(left==null || right==null)
            return left==right;

        if(left.data!=right.data) return false;
        return symmetryHelp(left.left,left.right) && symmetryHelp(right.right,right.left);
    }

    public boolean checkSymmetry(Node node){
        return node==null || symmetryHelp(node.left,node.right);
    }

    public void rootToNodePath(Node node,int val,String path){
        if(node == null){
            return;
        }

        if(node.data == val){
            System.out.println(path+node.data);
            return;
        }

        rootToNodePath(node.left,val,path+node.data+" ");
        rootToNodePath(node.right,val,path+node.data+" ");
    }

    public Node lowestCommonAncestor(Node node,int val1,int val2){
        if(node == null || node.data == val1 || node.data==val2){
            return node;
        }
        Node left=lowestCommonAncestor(node.left,val1,val2);
        Node right=lowestCommonAncestor(node.right,val1,val2);
        if(left == null)
            return right;
        else if(right == null)
            return left;
        else
            return node;
    }

    public Node childrenSumProperty(Node node,int prevNodeData){
        if(node.left == null || node.right == null || node == null){
            return node;
        }

        if(node.data < prevNodeData){
            node.data=prevNodeData;
        }

        Node left=childrenSumProperty(node.left,node.data);
        Node right=childrenSumProperty(node.right,node.data);
        node.data=left.data+right.data;
        return node;
    }

    static ArrayList<Node> path;
    public Boolean nodeToRootPath(Node node,int val){
        if(node == null){
            return false;
        }

        if(node.data==val){
            path.add(node);
            return true;
        }

        Boolean left=nodeToRootPath(node.left,val);
        if(left){
            path.add(node);
            return true;
        }

        Boolean right=nodeToRootPath(node.right,val);
        if(right){
            path.add(node);
            return true;
        }
        return false;
    }

    public void printKlevelsDown(Node node,int k,Node blocker){
        if(node == null || k<0 || node==blocker){
            return;
        }
        if(k == 0){
            System.out.print(node.data+" ");
            return;
        }

        printKlevelsDown(node.left,k-1,blocker);
        printKlevelsDown(node.right,k-1,blocker);
    }

    public void printNodesAtKDistance(Node node,int k,int target){
        path=new ArrayList<>();
        nodeToRootPath(node,target);

        for(int i=0;i<path.size();i++){
            printKlevelsDown(path.get(i),k-i,i==0?null:path.get(i-1));
        }

    }

    static HashMap<Node,Node> hmp;
    public void parent(Node node,Node parent){
       if(node == null){
           return;
       }
       hmp.put(node,parent);
       parent(node.left,node);
       parent(node.right,node);
    }

    public void minTimeTakenToBurnBinaryTree(Node node,int val){
        hmp=new HashMap<>();
        parent(node,null);
    }

    class Pair{
        Node node;
        int x;
        int y;
        public Pair(Node node,int row,int col){
            this.node=node;
            this.x=row;
            this.y=col;
        }

        public Pair(int row,Node node){
            this.x=row;
            this.node=node;
        }

        public Pair(Node node,int col){
            this.y=col;
            this.node=node;
        }

        public Pair(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    class Tuple{
        Node node;
        int x;
        int y;
    }
    public static void main(String[] args) {
        Trees tree=new Trees();
        tree.insert(root,10);
        tree.insert(root,20);
        tree.insert(root,30);
        tree.insert(root,40);
        tree.insert(root,50);
        tree.insert(root,60);
        tree.insert(root,70);
        //tree.insertTree(root,20);

        tree.inOrder(root);
        System.out.println();
        tree.levelOrder(root);
        System.out.println();
        tree.iterativePreOrder(root);
        System.out.println();
        tree.iterativeInOrder(root);
        System.out.println();
        tree.iterativePostOrder2Stacks(root);
        System.out.println();
        System.out.println("Depth: "+tree.depth(root));
        System.out.println("Check for balanced binary tree: "+tree.checkBalancedBT(root));
        System.out.println("Diameter of Binary tree: "+tree.diameter(root));
        tree.maxPathSum(root,Integer.MIN_VALUE);
        System.out.println("Maximum path sum of BT: "+num);
        System.out.println("Boundary order traversal: "+tree.boundaryTraversal(root).toString());
        System.out.print("Verticle Order Traversal: ");
        tree.verticalOrderTraversal(root);

        System.out.println();
        System.out.print("Top view: ");
        tree.topView(root);

        System.out.println();
        System.out.print("Bottom view: ");
        tree.bottomView(root);

        System.out.println();
        System.out.print("Right view: ");
        tree.rightView(root);

        System.out.println();
        System.out.print("Left view: ");
        tree.leftView(root);

        System.out.println();
        System.out.println("Check for symmetry: "+tree.checkSymmetry(root));
        tree.rootToNodePath(root,60,"");

        System.out.println("Lowest common ancestor: "+tree.lowestCommonAncestor(root,60,70).data);
        /*System.out.println("Children sum property: ");
        tree.childrenSumProperty(root,0);
        tree.levelOrder(root);*/

        System.out.print("Print K levels far from target: ");
        tree.printNodesAtKDistance(root,1,20);
        System.out.println();
        tree.minTimeTakenToBurnBinaryTree(root,0);
        for(Map.Entry<Node,Node> hm: hmp.entrySet()){
            System.out.println(hm.getKey().data+"->"+hm.getValue().data);
        }
    }
}
