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

    int[] srow=new int[]{-1,0,1,0};
    int[] scol=new int[]{0,1,0,-1};

    public void replaceOsWithXs(char[][] graph,int row,int col,boolean[][] visited){
        if(row<0 || row>=graph.length || col<0 || col>=graph[0].length || visited[row][col] || graph[row][col]!='O'){
            return;
        } else {
            visited[row][col]=true;
        }
        replaceOsWithXs(graph,row-1,col,visited);
        replaceOsWithXs(graph,row,col+1,visited);
        replaceOsWithXs(graph,row+1,col,visited);
        replaceOsWithXs(graph,row,col-1,visited);
    }

    public void surroundRegions(char[][] graph){
        int n=graph.length;
        int m=graph[0].length;
        boolean[][] visited=new boolean[n][m];

        for(int j=0;j<m;j++){
            if(!visited[0][j] && graph[0][j]=='O'){
                replaceOsWithXs(graph,0,j,visited);
            }
            if(!visited[n-1][j] && graph[n-1][j]=='O'){
                replaceOsWithXs(graph,n-1,j,visited);
            }
        }

        for(int i=0;i<n;i++){
            if(!visited[i][0] && graph[i][0]=='O'){
                replaceOsWithXs(graph,i,0,visited);
            }
            if(!visited[i][m-1] && graph[i][m-1]=='O'){
                replaceOsWithXs(graph,i,m-1,visited);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && graph[i][j]=='O'){
                    graph[i][j]='X';
                }
            }
        }


        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }

    }
    public void replace1s(int[][] graph,int row,int col,boolean[][] visited){
        if(row<0 || row>=graph.length || col<0 || col>=graph[0].length || visited[row][col] || graph[row][col]!=1){
            return;
        } else {
            visited[row][col]=true;
        }
        replace1s(graph,row-1,col,visited);
        replace1s(graph,row,col+1,visited);
        replace1s(graph,row+1,col,visited);
        replace1s(graph,row,col-1,visited);
    }
    public void numberOfEnclaves(int[][] graph){
        int n=graph.length;
        int m=graph[0].length;
        int count=0;
        boolean[][] visited=new boolean[n][m];

        for(int j=0;j<m;j++){
            if(!visited[0][j] && graph[0][j]==1){
                replace1s(graph,0,j,visited);
            }
            if(!visited[n-1][j] && graph[n-1][j]==1){
                replace1s(graph,n-1,j,visited);
            }
        }

        for(int i=0;i<n;i++){
            if(!visited[i][0] && graph[i][0]==1){
                replace1s(graph,i,0,visited);
            }
            if(!visited[i][m-1] && graph[i][m-1]==1){
                replace1s(graph,i,m-1,visited);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
               if(visited[i][j] && graph[i][j]==1){
                    graph[i][j]=-1;
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && graph[i][j]!=0){
                    count++;
                }
            }
        }

        System.out.println(count);

    }

    public void isGraphBipartiteBfs(ArrayList<ArrayList<Integer> > graph, int src){
        Queue<Integer> queue=new ArrayDeque<>();
        queue.add(src);
        int[] colour=new int[graph.size()];
        Arrays.fill(colour,0);
        colour[src]=1;
        while(!queue.isEmpty()){
            int node=queue.poll();
            for(int it:graph.get(node)){
                if(colour[it]==0){
                    colour[it]=colour[node]*-1;
                    queue.add(it);
                } else if(colour[it]==colour[node]){
                    System.out.println("Not Bipartite");
                    return;
                }
            }
        }
        System.out.println("Bipartite");
    }

    public boolean dfsForBipartite(ArrayList<ArrayList<Integer> > graph,int src, int[] colour,int col){
        colour[src]=col;
        for(int it: graph.get(src)){
            if(colour[it]==0){
                if(!dfsForBipartite(graph,it,colour,col*-1))
                    return false;
            } else if(colour[it]==colour[src]){
                return false;
            }
        }
        return true;
    }

    public void isBipartiteUsingDfs(ArrayList<ArrayList<Integer> > graph){
        int[] colour=new int[graph.size()];
        Arrays.fill(colour,0);
        for(int i=1;i<colour.length;i++){
            if(colour[i]==0){
               if(!dfsForBipartite(graph,i,colour,1)){
                   System.out.println("Not Bipartite");
                   return;
               }
            }
        }
        System.out.println("Bipartite");

    }


    public boolean dfsDirectedGraph(ArrayList<ArrayList<Integer> > graph,int src,int[] visited,int[] path){
        visited[src]=1;
        path[src]=1;
        for(int it: graph.get(src)){
            if(visited[it]!=1){
                if(dfsDirectedGraph(graph,it,visited,path)) return true;
            } else if(path[it]==1){
                return true;
            }
        }
        path[src]=0;
        return false;
    }

    public void cycleInDirectedGraph(ArrayList<ArrayList<Integer> > graph){
        int[] visited=new int[graph.size()];
        int[] path=new int[graph.size()];
        Arrays.fill(visited,0);
        Arrays.fill(path,0);

        for(int i=1;i<graph.size();i++){
            if(visited[i]!=1){
                if(dfsDirectedGraph(graph,i,visited,path)){
                    System.out.println("Cycle Detected");
                    return;
                }
            }
        }
        System.out.println("Cycle not Detected");
    }


    public static void main(String[] args) {

        ArrayList<ArrayList<Integer> > graph=new ArrayList<>();
        ArrayList<ArrayList<Integer> > directedGraph=new ArrayList<>();
        for(int i=0;i<=6;i++){
            graph.add(new ArrayList<>());
            directedGraph.add(new ArrayList<>());
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

        directedGraph.get(1).add(2);
        directedGraph.get(2).add(3);
        directedGraph.get(3).add(4);
        directedGraph.get(4).add(5);
        directedGraph.get(5).add(6);
        directedGraph.get(6).add(2);

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


        System.out.println("Surround Regions (Replace Os with Xs)");
        char[][] t2={{'X','X','X','X','X'},
                     {'X','O','O','X','O'},
                     {'X','X','O','X','O'},
                     {'X','O','X','O','X'},
                     {'O','O','X','X','X'}};
        g.surroundRegions(t2);

        System.out.print("Number of Enclaves: ");
        int[][] t3={{0,0,0,1},{0,1,1,0},{0,1,1,0},{0,0,0,1},{0,1,1,0}};
        g.numberOfEnclaves(t3);

        System.out.print("To check if graph is bipartite or not using bfs: ");
        g.isGraphBipartiteBfs(graph,1);

        System.out.print("To check if graph is bipartite or not using dfs: ");
        g.isBipartiteUsingDfs(graph);

        System.out.print("To check if Directed Graph is cyclic or not: ");
        g.cycleInDirectedGraph(directedGraph);
    }
}
