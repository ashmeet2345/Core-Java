package DSA.Tree;

import DSA.BinaryTrees;
import MultiThreading.NewMultiThreading.B;

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

    public static void main(String[] args) {
        BinaryTree tree=new BinaryTree();
        tree.insert(root, 10);
        tree.insert(root, 20);
        tree.insert(root, 30);
        tree.insert(root, 40);
        tree.insert(root, 50);
        tree.insert(root, 60);
        tree.insert(root, 70);

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
    }
}
