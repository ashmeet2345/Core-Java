package DSA;

import java.util.*;

public class BinaryTrees {
    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int data,Node left,Node right){
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }

    public static class Pair{
        Node node;
        int state;
        Pair(Node node,int state){
            this.node=node;
            this.state=state;
        }
    }

    public static void insertion(Integer[] a,Node root){

        Stack<Pair> st=new Stack<>();
        Pair pair=new Pair(root,1);
        st.push(pair);

        int ind=0;
        while(st.size() > 0){
            Pair top=st.peek();
            if(top.state == 1){
                ind++;
                if(a[ind] != null){
                    top.node.left=new Node(a[ind],null,null);
                    Pair lp=new Pair(top.node.left,1);
                    st.push(lp);
                } else {
                    top.node.left=null;
                }
                top.state++;
            }else if(top.state == 2){
                ind++;
                if(a[ind] != null){
                    top.node.right=new Node(a[ind],null,null);
                    Pair rp=new Pair(top.node.right,1);
                    st.push(rp);
                } else {
                    top.node.right=null;
                }
                top.state++;
            } else{
                st.pop();
            }
        }
    }

    public static void display(Node node){
        if(node == null)
            return;

        String ans="";
        ans+=node.left == null? "." : node.left.data+"";
        ans+="<-" +node.data+ "->";
        ans+=node.right == null? "." : node.right.data+"";
        System.out.println(ans);
        display(node.left);
        display(node.right);
    }

    public static int size(Node node){
        if(node==null){
            return 0;
        }

        int sl=size(node.left);
        int sr=size(node.right);
        return sl+sr+1;
    }

    public static int sum(Node node){
        if(node==null){
            return 0;
        }

        int suml=sum(node.left);
        int sumr=sum(node.right);
        return suml+sumr+node.data;
    }

    public static int max(Node node){
        if(node==null){
            return 0;
        }

        int max=Integer.MIN_VALUE;
        int maxl=max(node.left);
        int maxr=max(node.right);
        max=Math.max(maxl,maxr);
        return Math.max(max,node.data );
    }

    public static int height(Node node){
        if(node == null){
            return 0;
        }
        int h=0; //if edges are not considered (h=-1, if considered edges only)
        int hl=height(node.left);
        int hr=height(node.right);
        return Math.max(hl,hr)+1;
    }

    public static void traversals(Node node,int i){
        if(node==null)
            return;

        if(i==1){
            System.out.print(node.data+" ");
            traversals(node.left,i);
            traversals(node.right,i);
        } else if(i==2){
            traversals(node.left,i);
            System.out.print(node.data+" ");
            traversals(node.right,i);;
        } else if(i==3){
            traversals(node.left,i);
            traversals(node.right,i);
            System.out.print(node.data+" ");
        }
    }

    public static void levelOrderTraversal(Node node){
        Queue<Node> nodeQueue=new ArrayDeque<>();
        nodeQueue.add(node);
        while(nodeQueue.size() > 0){
            Node top=nodeQueue.poll();
            System.out.print(top.data + " ");
            if(top.left!=null)
                nodeQueue.add(top.left);
            if(top.right!=null)
                nodeQueue.add(top.right);
        }
    }

    public static void iterativeTraversals(Node node){
        Stack<Pair> st=new Stack<>();
        Pair pair=new Pair(node,1);
        st.push(pair);
        String pre="";
        String in="";
        String post="";

        while(st.size() > 0){
            Pair top=st.peek();
            if(top.state == 1){
                pre+=top.node.data+" ";
                if(top.node.left!=null){
                    Pair p=new Pair(top.node.left,1);
                    st.push(p);
                }
                top.state++;
            } else if(top.state == 2){
                in+=top.node.data+" ";
                if(top.node.right!=null){
                    Pair p=new Pair(top.node.right,1);
                    st.push(p);
                }
                top.state++;
            } else {
                post+=top.node.data+" ";
                st.pop();
            }
        }
        System.out.println("\n"+pre+"\n"+in+"\n"+post);
    }

    static ArrayList<Node> path;

    public static boolean nodeToRootPath(Node node,int num){
        if(node == null){
            return false;
        }

        if(node.data == num){
            path.add(node);
            return true;
        }

        boolean lt=nodeToRootPath(node.left,num);
        if(lt){
            path.add(node);
            return true;
        }


        boolean rt=nodeToRootPath(node.right,num);
        if(rt){
            path.add(node);
            return true;
        }

        return false;
    }

    public static void printKlevelsDown(Node root,int k,Node blocker){
        if(root==null || k<0 || root==blocker)
            return;
        if(k==0){
            System.out.print(root.data+" ");
            return;
        }

        printKlevelsDown(root.left,k-1,blocker);
        printKlevelsDown(root.right,k-1,blocker);
    }

    public static void printNodeKLevelFar(Node node,int num,int  k){
        path=new ArrayList<>();
        nodeToRootPath(node,num);
        for(int i=0;i<path.size();i++){
            printKlevelsDown(path.get(i),k-i,i==0?null : path.get(i-1) );
        }
    }

    public static void pathToLeafFromRoot(Node node,int low,int high,int sum,String path){
        if(node==null){
            return;
        }
        if(node.left == null && node.right == null){
            sum+=node.data;
            if(sum>=low && sum<=high){
                System.out.print(path+node.data);
                return;
            }
        }
        pathToLeafFromRoot(node.left,low,high,sum+node.data,path+node.data+" ");
        pathToLeafFromRoot(node.right,low,high,sum+node.data,path+node.data+" ");
    }

    public static Node transformToLeftClonedTree(Node node){
        if(node==null)
            return null;

        Node lt=transformToLeftClonedTree(node.left);
        Node rt=transformToLeftClonedTree(node.right);
        Node temp=new Node(node.data,lt,null);
        node.left=temp;
        node.right=rt;
        return node;
    }

    static List<Integer> list=new ArrayList<>();
    public static List<Integer> inOrder(Node node){
        if(node==null){
            List<Integer> l=new ArrayList<>();
            return l;
        }
        List<Integer> lt=inOrder(node.left);
        list.add(node.data);
        List<Integer> rt=inOrder(node.right);
        return list;
    }
    public static void main(String[] args) {
        Integer[] arr={50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root=new Node(arr[0],null,null);
        insertion(arr,root);
        display(root);
        System.out.println("Size: "+size(root));
        System.out.println("Sum: "+sum(root));
        System.out.println("Max: "+max(root));
        System.out.println("Height: "+height(root));
        System.out.println();
        traversals(root,3);
        System.out.println();
        levelOrderTraversal(root);
        iterativeTraversals(root);

        path=new ArrayList<>();
        nodeToRootPath(root,70);
        path.stream().forEach(integer -> System.out.print(integer.data+" "));

        System.out.println();
        printKlevelsDown(root,2,null);

        System.out.println();
        printNodeKLevelFar(root,75,2);

        System.out.println();
        pathToLeafFromRoot(root,80,150,0,"");

        List<Integer> inO=inOrder(root);
        System.out.println();
        inO.stream().forEach(i-> System.out.print(i+" "));
    }
}
