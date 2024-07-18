package DSA;

import java.util.ArrayList;
import java.util.Stack;

public class GenericTrees {
    private static class Node{
        int data;
        ArrayList<Node> children=new ArrayList<>();
    }

    public static void display(Node root){
        String str=root.data + "->";
        for(Node child: root.children){
            str+=child.data+",";
        }
        str+=".";
        System.out.println(str);
        for(Node child: root.children){
            display(child);
        }
    }

    public static int maximum(Node root){
        int max=Integer.MIN_VALUE;
        for(Node child:root.children){
            int mx=maximum(child);
            max=Math.max(mx,max);
        }

        max=Math.max(root.data,max);
        return max;
    }

    static int s=0;
    public static int size(Node root){
        for(Node child:root.children){
            s++;
            size(child);
        }
        return s+1;
    }

    public static int height(Node root){
        int h=-1;
        for(Node child:root.children){
            int hr=height(child);
            h=Math.max(h,hr);
        }
        return h+1;
    }

    public static void main(String[] args) {
        int[] a={10,30,60,-1,40,20,-1,70,50,-1,-1,-1,-1,35,60,-1,-1};
        Stack<Node> st=new Stack<>();

        Node root=null;
        for(int i=0;i<a.length;i++){
            if(a[i] < 0){
                st.pop();
            } else {
                Node temp=new Node();
                temp.data=a[i];
                if(st.size() > 0)
                    st.peek().children.add(temp);
                else
                    root=temp;
                st.push(temp);
            }
        }

        display(root);
        System.out.println("Size - "+size(root));
        System.out.println("Maximum node value - "+maximum(root));
        System.out.println("Height of generic tree - "+height(root));
    }
}
