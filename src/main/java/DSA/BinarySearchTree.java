package DSA;

public class BinarySearchTree {
    public static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

    static Node root;

    public void insert(int data){
        root=insertData(root,data);
    }

    public Node insertData(Node root,int data){
        if(root==null){
            root=new Node(data);
            return root;
        }
        else if( data <= root.data) root.left=insertData(root.left,data);
        else root.right=insertData(root.right,data);
        return root;
    }

    public int max(Node root){
        if(root.right!=null)
           return max(root.right);
         else
            return root.data;
    }

    public int min(Node root){
        if(root.left!=null)
            return min(root.left);
        else
            return root.data;
    }

    public boolean find(Node root,int num){
        if(root==null){
            return false;
        }
        if(num == root.data)
            return true;
        else if ( num <=root.data)
            return find(root.left,num);
        else
            return find(root.right,num);
    }

    public Node delete(Node root,int num){
        if(root == null)
            return null;

        if(num < root.data){
            root.left=delete(root.left,num);
        }
        else if(num > root.data){
            root.right=delete(root.right,num);
        }
        else {
            if(root.left!=null && root.right!=null){
                int mx=max(root.left);
                root.data=mx;
                root.left=delete(root.left,mx);
                return root;

            } else if(root.left!=null){
                return root.left;
            } else if(root.right!=null){
                return root.right;
            } else {
                return null;
            }
        }
        return root;
    }
    static int sum=0;
    public void replaceSumOfLarger(Node root){
        if(root == null){
            return;
        }
        replaceSumOfLarger(root.right);
        int s=root.data;
        root.data=sum;
        sum+=s;
        replaceSumOfLarger(root.left);
    }

    public int lowestCommonAncestor(Node root,int n1,int n2){
        if( n1 > root.data && n2 > root.data)
            return lowestCommonAncestor(root.right,n1,n2);
        else if( n1 < root.data && n2 < root.data)
            return lowestCommonAncestor(root.left,n1,n2);
        else
            return root.data;
    }
    public static void main(String[] args) {
        BinarySearchTree tree=new BinarySearchTree();
        tree.insert(30);
        tree.insert(25);
        tree.insert(35);
        tree.insert(23);
        tree.insert(27);
        tree.insert(33);
        tree.insert(37);

       // Node node=tree.delete(root,37);
       // tree.replaceSumOfLarger(root);
       // System.out.println(root.left.left.data);

        System.out.println(tree.lowestCommonAncestor(root,33,37));
    }
}
