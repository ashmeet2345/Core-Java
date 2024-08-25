package DSA;

import Fundamentals.Arr;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class GraphSeries {

    public static class Pair{
        int i;
        int j;
        Pair(int i,int j){
            this.i=i;
            this.j=j;
        }
    }

    public void display(ArrayList<ArrayList<Integer> > graph){
       for(int i=1;i<=graph.size()-1;i++){
           System.out.print(i+"->");
           for(int j=0;j<graph.get(i).size();j++){
               System.out.print(graph.get(i).get(j)+" ");
           }
           System.out.println();
       }
    }
    public void connectedComponents(ArrayList<ArrayList<Integer> > graph, int[] visited,int src){
        visited[src]=1;
        for(Integer nbr:graph.get(src)){
            if(visited[nbr]!=1){
                connectedComponents(graph,visited,nbr);
            }
        }
    }

    public void connectedOrNot(ArrayList<ArrayList<Integer> > graph){
        int[] visited=new int[graph.size()];
        Arrays.fill(visited,0);

        boolean connected=true;
        for(int i=1;i<visited.length;i++){
            if(visited[i]!=1){
                connectedComponents(graph,visited,i);
            }
        }
        for(int i=1;i<visited.length;i++){
            if(visited[i]!=1){
                connected=false;
                break;
            }
        }
        if(connected) System.out.println("Connected"); else System.out.println("Not connected");
    }

    public void bfs(ArrayList<ArrayList<Integer> > graph,int src){
        Queue<Integer> queue=new ArrayDeque<>();
        int[] visited=new int[graph.size()];
        visited[src]=1;
        queue.add(src);
        while(queue.size()>0){
            Integer top=queue.poll();

            System.out.print(top+" ");
            for(Integer i:graph.get(top)){
                if(visited[i]!=1){
                    queue.add(i);
                    visited[i]=1;
                }
            }
        }
    }

    public void dfs(ArrayList<ArrayList<Integer> > graph,int src,int[] visited, boolean provinces){
        visited[src]=1;
        if(!provinces) System.out.print(src+" ");
        for(Integer i:graph.get(src)){
            if(visited[i]!=1){
                dfs(graph,i,visited,provinces);
            }
        }
    }

    public void depthFirstSearch(ArrayList<ArrayList<Integer> > graph,int src){
        int[] visited=new int[graph.size()];
        dfs(graph,src,visited,false);
    }


    public void numberOfProvinces(ArrayList<ArrayList<Integer> > graph){
        int[] visited=new int[graph.size()];
        int count=0;
        for(int i=1;i<visited.length;i++){
            if(visited[i]!=1){
                dfs(graph,i,visited,true);
                count++;
            }
        }
        System.out.println(count);
    }

    public void detectCycleUsingBfs(ArrayList<ArrayList<Integer>> graph,int src){
        int[] visited=new int[graph.size()];
        Queue<Pair> queue=new ArrayDeque<>();
        visited[src]=1;
        queue.add(new Pair(src,-1));
        while(queue.size()>0){
            Pair top=queue.poll();

            for(Integer p:graph.get(top.i)){
                if(visited[p]!=1){
                    visited[p]=1;
                    queue.add(new Pair(p,top.i));
                } else if(top.j!=p){
                    System.out.println("Cycle detected");
                    return;
                }
            }
        }
        System.out.println("Cycle not detected");
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer> > graph=new ArrayList<>();
        for(int i=0;i<=6;i++){
            graph.add(new ArrayList<>());
        }

        graph.get(1).add(2);

        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(2).add(4);

        graph.get(3).add(2);
        graph.get(3).add(4);

        graph.get(4).add(2);
        graph.get(4).add(3);
        graph.get(4).add(6);
        graph.get(4).add(5);

        graph.get(5).add(4);
        graph.get(5).add(6);

        graph.get(6).add(4);
        graph.get(6).add(5);

        GraphSeries g=new GraphSeries();
        System.out.println("Displaying Graph: ");
        g.display(graph);

        System.out.print("To check if graph is connected of not: ");
        g.connectedOrNot(graph);

        System.out.println("Breadth first search: ");
        g.bfs(graph,1);

        System.out.println("\nDepth first search: ");
        g.depthFirstSearch(graph,1);

        System.out.print("\nNumber of provinces: ");
        g.numberOfProvinces(graph);

        System.out.print("Cycle detection using bfs: ");
        g.detectCycleUsingBfs(graph,1);
    }
}
