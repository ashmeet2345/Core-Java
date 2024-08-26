package DSA;

import Fundamentals.Arr;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import org.apache.commons.lang3.tuple.Pair;

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

    public boolean dfsCycleDetection(ArrayList<ArrayList<Integer>> graph,int src,int parent,int[] visited){
        visited[src]=1;
        for(Integer i:graph.get(src)){
            if(visited[i]!=1){
                if(dfsCycleDetection(graph,i,src,visited))
                    return true;
            } else if(i!=parent) return true;
        }
        return false;
    }
    public void detectCycleUsingDfs(ArrayList<ArrayList<Integer>> graph,int src){
        int[] visited=new int[graph.size()];
        if(dfsCycleDetection(graph,src,-1,visited)) System.out.println("Cycle Detected");
        else System.out.println("Cycle Not detected");
    }

    public void distanceOfNearestCellHaving1and0(int[][] graph){
        int[][] visited=new int[graph.length][graph[0].length];
        int[][] distance=new int[graph.length][graph[0].length];

        Queue<ImmutablePair<ImmutablePair<Integer,Integer>,Integer> > queue=new ArrayDeque<>();
        int n=graph.length;
        int m=graph[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(graph[i][j]==1){
                    queue.add(new ImmutablePair<>(new ImmutablePair<>(i,j),0));
                    visited[i][j]=1;
                } else {
                    visited[i][j]=0;
                }
            }
        }

        int[] row=new int[]{-1,0,1,0};
        int[] col=new int[]{0,1,0,-1};

        while(queue.size()>0){
            ImmutablePair<ImmutablePair<Integer, Integer>, Integer> pair=queue.poll();
            int i=pair.left.left;
            int j=pair.left.right;
            int dist=pair.right;
            distance[i][j]=dist;
            for(int x=0;x<4;x++){
                int nrow=i+row[x];
                int ncol=j+col[x];

                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && visited[nrow][ncol]==0){
                    visited[nrow][ncol]=1;
                    queue.add(new ImmutablePair<>(new ImmutablePair<>(nrow,ncol),dist+1));
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(distance[i][j]+" ");
            }
            System.out.println();
        }
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

        System.out.print("Cycle detection using Dfs: ");
        g.detectCycleUsingDfs(graph,1);

        System.out.println("Distance of nearest cell having 1s and 0s: ");
        int[][] t1={{0,0,0},{0,1,0},{1,0,1}};
        g.distanceOfNearestCellHaving1and0(t1);
    }
}
