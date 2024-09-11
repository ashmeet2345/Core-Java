package DSA.Tree;

import DSA.BinaryTrees;
import MultiThreading.NewMultiThreading.B;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinaryTree {

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
    }
}
