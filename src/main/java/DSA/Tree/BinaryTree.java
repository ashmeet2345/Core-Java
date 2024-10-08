package DSA.Tree;

import DSA.BinaryTrees;
import MultiThreading.NewMultiThreading.B;
import MultiThreading.NewMultiThreading.Priority;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinaryTree {
    public class Pair{
        int vertical;
        int level;

        public Pair(int vertical, int level){
            this.vertical=vertical;
            this.level=level;
        }
    }

    public class Tuple{
        Node node;
        int vertical;
        int level;

        public Tuple(Node node, int vertical, int level){
            this.node=node;
            this.vertical=vertical;
            this.level=level;
        }

    }

    public class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }

        public Node(int data, Node node, boolean left){
            this.data=data;
            if(left){
                this.left=node;
                this.right=null;
            }
            else {
                this.right = node;
                this.left = null;
            }
        }
    }

    static Node root=null;
    static Node temp=root;
    public void insert(Node temp, int data){
        if(temp == null){
            root=new Node(data);
            return;
        }
        Queue<Node> queue=new ArrayDeque<>();
        queue.add(root);
        while(queue.size()>0){
            Node top=queue.poll();
            if(top.left == null){
                top.left=new Node(data);
                break;
            } else {
                queue.add(top.left);
            } if (top.right == null){
                top.right=new Node(data);
                break;
            }
            else {
                queue.add(top.right);
            }
        }
    }

    public void inOrder(Node temp){
        if(temp == null){
            return;
        }
        inOrder(temp.left);
        System.out.print(temp.data+" ");
        inOrder(temp.right);
    }

    public void preOrder(Node temp){
        if(temp == null){
            return;
        }
        System.out.print(temp.data+" ");
        preOrder(temp.left);
        preOrder(temp.right);
    }

    public void postOrder(Node temp){
        if(temp == null){
            return;
        }
        postOrder(temp.left);
        postOrder(temp.right);
        System.out.print(temp.data+" ");
    }

    public void levelOrder(Node temp){
        Queue<Node> queue=new ArrayDeque<>();
        queue.add(temp);
        while(queue.size()>0){
            Node top=queue.poll();
            System.out.print(top.data+" ");
            if(top.left!=null){
                queue.add(top.left);
            }
            if(top.right!=null){
                queue.add(top.right);
            }
        }
    }

    public void IterativePreOrder(Node temp){
        Stack<Node> st=new Stack<>();
        st.add(temp);
        while(st.size()>0){
            Node top=st.pop();
            System.out.print(top.data+" ");
            if(top.right!=null){
                st.add(top.right);
            }
            if(top.left!=null){
                st.add(top.left);
            }
        }
    }

    public void IterativeInOrder(Node root){
        Stack<Node> st=new Stack<>();
        Node temp=root;
        while(true){
            if(temp!=null){
                st.push(temp);
                temp=temp.left;
            } else {
                if(st.size()==0)
                    break;
                temp=st.pop();
                System.out.print(temp.data+" ");
                temp=temp.right;
            }
        }
    }

    public void IterativePostOrder(Node root){
        Stack<Node> st=new Stack<>();
        ArrayList<Integer> ans=new ArrayList<>();
        st.push(root);
        while(st.size()>0){
            Node top=st.pop();
            if(top.left!=null){
                st.push(top.left);
            }
            if(top.right!=null){
                st.push(top.right);
            }
            ans.add(top.data);
        }

        List<Integer> reversed= IntStream.rangeClosed(1,ans.size())
                .mapToObj(i->ans.get(ans.size()-i))
                .collect(Collectors.toList());

        reversed.iterator().forEachRemaining(s-> System.out.print(s+" "));
    }

    static int diameter=Integer.MIN_VALUE;
    public int maximumDepthInBinaryTree(Node root){
        if(root==null){
            return 0;
        }
        int left=maximumDepthInBinaryTree(root.left);
        int right=maximumDepthInBinaryTree(root.right);
        diameter=Math.max(diameter,left+right);
        return Math.max(left,right)+1;
    }

    public boolean balancedBinaryTree(Node root){
        return Math.abs(maximumDepthInBinaryTree(root.left)-maximumDepthInBinaryTree(root.right)) <= 1 ? true : false;
    }

    public int diameterOfBinaryTree(Node root){
        maximumDepthInBinaryTree(root);
        return diameter+1;
    }

    static int pathSum=Integer.MIN_VALUE;
    public int maximumPathSum(Node root){
        if(root == null){
            return 0;
        }
        int left=maximumPathSum(root.left);
        int right=maximumPathSum(root.right);
        pathSum=Math.max(pathSum,left+right+root.data);
        return Math.max(left,right)+root.data;
    }

    public void zigZagPattern(Node root){
      Stack<Node> st1=new Stack<>();
      Stack<Node> st2=new Stack<>();
      st1.add(root);
      int level=1;
      while(st1.size()>0){
          if(level%2!=0){
              while(st1.size()>0){
                  Node top=st1.pop();
                  System.out.print(top.data+" ");
                  if(top.left!=null) st2.add(top.left);
                  if(top.right!=null) st2.add(top.right);
              }
              System.out.println();
              st1=st2;
              st2=new Stack<>();
              level++;
          } else {
              while(st1.size()>0){
                  Node top=st1.pop();
                  System.out.print(top.data+" ");
                  if(top.right!=null) st2.add(top.right);
                  if(top.left!=null) st2.add(top.left);
              }
              System.out.println();
              st1=st2;
              st2=new Stack<>();
              level++;
          }
      }
    }

    static ArrayList<Integer> left=new ArrayList<>();
    public void leftBorder(Node root){
        if(root.left == null && root.right == null){
            return;
        }
        left.add(root.data);
        leftBorder(root.left);
    }

    static ArrayList<Integer> right=new ArrayList<>();
    public void rightBorder(Node root){
        if(root.left == null && root.right == null){
            return;
        }
        rightBorder(root.right);
        right.add(root.data);
    }

    static ArrayList<Integer> leaf=new ArrayList<>();
    public void leafBorder(Node root){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            leaf.add(root.data);
            return;
        }
        if(root.left!=null) leafBorder(root.left);
        if(root.right!=null) leafBorder(root.right);
    }

    public void boundaryTraversal(Node root){
        leftBorder(root);
        rightBorder(root);
        leafBorder(root);

        left.stream().forEach(i-> System.out.print(i+" "));
        leaf.stream().forEach(j-> System.out.print(j+" "));
        right.stream().forEach(k-> System.out.print(k+" "));
    }

    public void verticalTraversal(Node root){
        Queue<Tuple> queue=new ArrayDeque<>();
        TreeMap<Integer, TreeMap<Integer,PriorityQueue<Integer> > > map=new TreeMap<>();
        queue.add(new Tuple(root,0,0));
        while(queue.size()>0){
            Tuple tuple=queue.poll();
            Node node=tuple.node;
            int v=tuple.vertical;
            int l=tuple.level;
            if(!map.containsKey(v)){
                map.put(v,new TreeMap<>());
            }
            if(!map.get(v).containsKey(l)){
                map.get(v).put(l, new PriorityQueue<>());
            }
            map.get(v).get(l).offer(node.data);
            if(node.left!=null){
                queue.add(new Tuple(node.left,v-1,l+1));
            }
            if(node.right!=null){
                queue.add(new Tuple(node.right,v+1,l+1));
            }
        }

        //Here we can traverse the map properly and print the result.
        //Main logic for the question is mentioned above, we have to consider node to be presented
        //on a graph, and consider them as coordinates
        //Treemap is used to store result in a sorted order, first sorted by vertical, then by level
        //Priority queue is used, if 2 nodes falls at same coordinate, then the smaller one is going
        //to be printed first.
    }

    public void topView(Node root){
        Queue<Tuple> queue=new ArrayDeque<>();
        TreeMap<Integer, Node> map=new TreeMap<>();
        queue.add(new Tuple(root,0,0));
        while(queue.size()>0){
            Tuple tuple=queue.poll();
            Node node=tuple.node;
            int v=tuple.vertical;
            int l=tuple.level;
            if(!map.containsKey(v)){
                map.put(v,node);
            }
            if(node.left!=null){
                queue.add(new Tuple(node.left,v-1,l+1));
            }
            if(node.right!=null){
                queue.add(new Tuple(node.right,v+1,l+1));
            }
        }

        for(Map.Entry<Integer,Node> entry:map.entrySet()){
            System.out.print(entry.getValue().data+" ");
        }
    }

    public boolean isSymmetric(Node left,Node right){
        if(left == null || right == null){
            return left==right;
        }
        if(left.data!=right.data){
            return false;
        }
        return isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
    }

    public boolean symmetricalBinaryTree(Node root){
        return root==null || isSymmetric(root.left,root.right);
    }

    static List<Node> list=new LinkedList<>();
    public boolean rootToNodePathHelper(Node root, int val){
        if(root == null){
            return false;
        }
        if(root.data == val){
            list.add(root);
            return true;
        }

        boolean left=rootToNodePathHelper(root.left,val);
        if(left){
            list.add(root);
            return true;
        }

        boolean right=rootToNodePathHelper(root.right,val);
        if(right){
            list.add(root);
            return true;
        }

        return false;
    }

    public Node lowestCommonAncestor(Node root, int p, int q){
        if(root == null || root.data == p || root.data == q){
            return root;
        }

        Node left=lowestCommonAncestor(root.left,p,q);
        Node right=lowestCommonAncestor(root.right,p,q);

        if(left==null){
            return right;
        }
        else if(right==null){
            return left;
        } else {
            return root;
        }
    }

    public void childrenSumPropertyInBinaryTree(Node root){
        if(root==null) return;
        int child=0;
        if(root.left!=null) child+=root.left.data;
        if(root.right!=null) child+=root.right.data;
        if(child>=root.data)
            root.data=child;
        else {
            if(root.left!=null) root.left.data=root.data;
            if(root.right!=null) root.right.data=root.data;
        }
        childrenSumPropertyInBinaryTree(root.left);
        childrenSumPropertyInBinaryTree(root.right);

        int sum=0;
        if(root.left!=null)
            sum+=root.left.data;
        if(root.right!=null)
            sum+=root.right.data;
        if(root.left!=null || root.right!=null){
            root.data=sum;
        }
    }

    public void printKlevelsDown(Node root, int k,Node blocker){
        if(root == null || k<0 || root == blocker){
            return;
        }
        if(k==0){
            System.out.print(root.data+" ");
        }
        printKlevelsDown(root.left,k-1,blocker);
        printKlevelsDown(root.right,k-1,blocker);
    }

    public void printKLevelsFar(Node root, int node, int k){
        rootToNodePathHelper(root,node);
        for(int i=0;i<list.size() && i<=k;i++){
            printKlevelsDown(list.get(i),k-i,i>0?list.get(i-1):null);
        }
    }

    public void printTillLeafNode(Node root,Node blocker,int count){
        if(root == null || root == blocker){
            return;
        }
        printTillLeafNode(root.left,blocker,count+1);
        printTillLeafNode(root.right,blocker,count+1);
    }

    public void minimumTimeTakenToBurnBinaryTree(Node root, int node){
        rootToNodePathHelper(root,node);
        int count;
        int mxVal=Integer.MIN_VALUE;
        for(int i=0;i<list.size();i++){
            count=i;
            printTillLeafNode(list.get(i),i>0?list.get(i-1):null,count);
            mxVal=Math.max(mxVal,count);
        }
        System.out.println(mxVal);
    }
    
    public static void main(String[] args) {
        BinaryTree tree=new BinaryTree();
        tree.insert(root, 10);
        tree.insert(root, 20);
        tree.insert(root, 30);
        tree.insert(root, 40);
        tree.insert(root, 50);
        tree.insert(root, 60);
        tree.insert(root, 70);
        tree.insert(root, 80);
        tree.insert(root, 90);
        tree.insert(root, 100);
        tree.insert(root, 110);


        System.out.print("\nRecursive Inorder: ");
        tree.inOrder(root);

        System.out.print("\nRecursive Preorder: ");
        tree.preOrder(root);

        System.out.print("\nRecursive Postorder: ");
        tree.postOrder(root);

        System.out.print("\nLevel order: ");
        tree.levelOrder(root);

        System.out.print("\nIterative Preorder: ");
        tree.IterativePreOrder(root);

        System.out.print("\nIterative Inorder: ");
        tree.IterativeInOrder(root);

        System.out.print("\nIterative Postorder: ");
        tree.IterativePostOrder(root);

        System.out.print("\nMaximum Depth of a Binary Tree: ");
        System.out.println(tree.maximumDepthInBinaryTree(root));

        System.out.print("\nIf a tree is a balanced binary tree: ");
        System.out.println(tree.balancedBinaryTree(root));

        System.out.print("\nDiameter of Binary Tree: ");
        System.out.println(tree.diameterOfBinaryTree(root));

        System.out.print("\nMaximum Path sum: ");
        tree.maximumPathSum(root);
        System.out.println(pathSum);

        System.out.println("\nZig-Zag traversal");
        tree.zigZagPattern(root);

        System.out.println("\nBoundary Traversal: ");
        tree.boundaryTraversal(root);

        System.out.println("\nTop View: ");
        tree.topView(root);

        System.out.println("\nIs Tree Symmetrical Binary Tree: ");
        System.out.println(tree.symmetricalBinaryTree(root));

        System.out.println("Print root to node path: ");
        tree.rootToNodePathHelper(root,10);
        list.stream().forEach(s-> System.out.print(s.data+" "));

        System.out.println("\nLowest Common Ancestor: ");
        System.out.println(tree.lowestCommonAncestor(root,80,50).data);

        /*System.out.println("Children Sum Property in Binary Tree");
        tree.childrenSumPropertyInBinaryTree(root);
        tree.levelOrder(root);*/

        System.out.println("\nPrint K levels down: ");
        tree.printKlevelsDown(root,3,null);

        System.out.println("\nPrint K levels far");
        tree.printKLevelsFar(root,20,2);

        System.out.println("\nMinimum time taken to burn the binary tree: ");
        tree.minimumTimeTakenToBurnBinaryTree(root,10);
    }
}
