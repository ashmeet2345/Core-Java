package DSA;

import java.sql.SQLOutput;
import java.util.*;

public class Graphs {
    public static class Edges{
        int src;
        int nbr;
        int wt;

        public Edges(int src, int dst, int wt) {
            this.src = src;
            this.nbr = dst;
            this.wt = wt;
        }
    }

    public static class Pair implements Comparable<Pair>{
        int v;
        String path;
        int time;
        int weight;
        int av;

        public Pair(int v,String path){
            this.v=v;
            this.path=path;
        }

        public Pair(int v,int time){
            this.v=v;
            this.time=time;
        }

        public Pair(int v,int time,String path){
            this.v=v;
            this.time=time;
            this.path=path;
        }

        public Pair(int v, String path, int weight) {
            this.v = v;
            this.path = path;
            this.weight = weight;
        }

        public Pair(int v,int av,int weight){
            this.v=v;
            this.av=av;
            this.weight=weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight-o.weight;
        }
    }

    public boolean hasPath(ArrayList<Edges>[] graph,int src,int dst,int[] visited){
        if(src == dst){
            return true;
        }
        visited[src]=1;
        for( Edges edges: graph[src]){
            if(visited[edges.nbr]!=1){
                boolean path=hasPath(graph,edges.nbr,dst,visited);
                if(path == true){
                    return true;
                }
            }
        }
        return false;
    }

    public void printAllPaths(ArrayList<Edges>[] graph,int src,int dst,int[] visited,String path){
        if(src==dst){
            System.out.println(path+" ");
            return;
        }
        visited[src]=1;
        for(Edges edge: graph[src]){
            if(visited[edge.nbr]!=1){
                printAllPaths(graph,edge.nbr,dst,visited,path+" "+edge.nbr);
            }
        }
        visited[src]=0;
    }

    public void getConnectedComponents(ArrayList<Edges>[] graph,
                                                    int src,
                                                        ArrayList<Integer> comp,
                                                            int[] visited){
            visited[src]=1;
            comp.add(src);
            for(Edges edge:graph[src]){
                if(visited[edge.nbr]!=1){
                    getConnectedComponents(graph,edge.nbr,comp,visited);
                }
            }
    }

    public void connectedIslands(int[][] island,int i,int j,boolean[][] visited){
        if(i < 0 || j < 0 || i>=island.length || j>=island[0].length || island[i][j] == 1 || visited[i][j] == true){
            return;
        }

        visited[i][j]=true;
        connectedIslands(island,i-1,j,visited);
        connectedIslands(island,i,j+1,visited);
        connectedIslands(island,i+1,j,visited);
        connectedIslands(island,i,j-1,visited);
    }

    public int perfectFriends(ArrayList<ArrayList<Integer>> comps){
        int pair=1;
        for(int i=0;i<comps.size();i++){
            for(int j=i+1;j<comps.size();j++){
                pair+=comps.get(i).size()*comps.get(j).size();
            }
        }
        return pair;
    }

    public void hamiltonianGraph(ArrayList<Edges>[] graph,int src,HashSet<Integer> visited,String path,int osrc){
        if(visited.size() == graph.length-1){
            System.out.print(path);
            boolean hamiltonian=false;
            for(Edges edge:graph[src]){
                if(edge.nbr==osrc){
                    hamiltonian=true;
                }
            }

            if(hamiltonian)
                System.out.println(" *");
            else
                System.out.println(" .");
        }

        visited.add(src);
        for(Edges edge:graph[src]){
            if(!visited.contains(edge.nbr)){
                hamiltonianGraph(graph,edge.nbr,visited,path+" "+edge.nbr,osrc);
            }
        }
        visited.remove(src);
    }

    public void bfs(ArrayList<Edges>[] graph,int src){
        ArrayDeque<Pair> queue=new ArrayDeque<>();
        queue.add(new Pair(src,src+" "));
        boolean[] visited=new boolean[graph.length];
        while(!queue.isEmpty()){
            // r m* w a*
            Pair rem=queue.removeFirst();
            if(visited[rem.v]){
                continue;
            }
            visited[rem.v]=true;
            System.out.println(rem.v+"@"+rem.path);
            for(Edges edge:graph[rem.v]){
                if(!visited[edge.nbr]){
                    queue.add(new Pair(edge.nbr,rem.path+""+edge.nbr));
                }
            }
        }
    }

    public boolean isGraphACycleBfs(ArrayList<Edges>[] graph,int src,int[] visited){
        ArrayDeque<Pair> queue=new ArrayDeque<>();
        queue.add(new Pair(src,src+""));
        while(!queue.isEmpty()){
            Pair rem=queue.removeFirst();
            if(visited[rem.v]==1){
                return true;
            }
            visited[rem.v]=1;
            for(Edges edges: graph[rem.v]){
                if(visited[edges.nbr]!=1){
                    queue.add(new Pair(edges.nbr,rem.path+edges.nbr));
                }
            }
        }
        return false;
    }

    public Integer isGraphCycle(ArrayList<Edges>[] graph,int src,boolean[] visited){
        int count=0;
        ArrayDeque<Pair> queue=new ArrayDeque<>();
        queue.add(new Pair(src,src+" "));
        while(!queue.isEmpty()){
            Pair rem=queue.removeFirst();
            if(visited[rem.v]){
                return count;
            }
            visited[rem.v]=true;
            count++;
            for(Edges edge: graph[rem.v]){
                if(!visited[edge.nbr]){
                    queue.add(new Pair(edge.nbr,rem.path+edge.nbr));
                }
            }
        }
        return null;
    }

    public boolean isGraphBipartite(ArrayList<Edges>[] graph){
        boolean[] visited=new boolean[graph.length];
        boolean bipartite=false;
        for(int i=0;i<graph.length;i++){
            if(!visited[i]){
                Integer cycle=isGraphCycle(graph,i,visited);
                if(cycle == null)
                    return true;
                else {
                    if(cycle%2!=0){
                        return false;
                    } else {
                        bipartite=true;
                    }
                }
            }
        }
        return bipartite;
    }

    public void spreadInfection(ArrayList<Edges>[] graph,int src,int time){
        ArrayDeque<Pair> queue=new ArrayDeque<>();
        queue.add(new Pair(src,1,src+""));
        int count=0;
        int[] visited=new int[graph.length];
        while(!queue.isEmpty()){
            Pair rem=queue.removeFirst();
            if(visited[rem.v]>0){
                continue;
            }
            visited[rem.v]=rem.time;
            if(rem.time>time){
                System.out.println(rem.path+": "+count);
                continue;
            }
            count++;
            for(Edges edges: graph[rem.v]){
                if(visited[edges.nbr]==0){
                    queue.add(new Pair(edges.nbr,rem.time+1,rem.path+" "+edges.nbr));
                }
            }
        }
    }

    public void dijkstraAlgorithm(ArrayList<Edges>[] graph,int src){
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        pq.add(new Pair(src,src+"",0));
        boolean[] visited=new boolean[graph.length];
        while(!pq.isEmpty()){
            Pair rem=pq.poll();
            if(visited[rem.v])
                continue;
            visited[rem.v]=true;

            System.out.print(rem.v+"["+rem.weight+"] ");

            for(Edges edge: graph[rem.v]){
                if(!visited[edge.nbr]){
                    pq.add(new Pair(edge.nbr,rem.path+" "+edge.nbr,rem.weight+ edge.wt));
                }
            }
        }
        System.out.println();
    }

    public void PrimsAlgorithm(ArrayList<Edges>[] graph,int src){
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        pq.add(new Pair(src,-1,0));
        boolean[] visited=new boolean[graph.length];
        while(!pq.isEmpty()){
            Pair rem=pq.poll();
            if(visited[rem.v] == true){
                continue;
            }
            visited[rem.v]=true;
            if(rem.av!=-1){
                System.out.print("["+rem.v+"-"+rem.av+": "+rem.weight+"]");
            }

            for(Edges edge: graph[rem.v]){
                if(!visited[edge.nbr]){
                    pq.add(new Pair(edge.nbr,rem.v,edge.wt));
                }
            }
        }
        System.out.println();
    }

    public void topologicalSort(ArrayList<Edges>[] graph,int src,Stack st,boolean[] visited){

        visited[src]=true;
        for(Edges edge: graph[src]){
            if(!visited[edge.nbr]){
                topologicalSort(graph,edge.nbr,st,visited);
            }
        }

        st.push(src);
    }


    public static void main(String[] args) {
        Graphs graphs=new Graphs();

        ArrayList<Edges>[] graph=new ArrayList[7];
        for(int i=0;i<7;i++){
            graph[i]=new ArrayList<Edges>();
        }

        graph[0].add(new Edges(0,1,10));
        graph[0].add(new Edges(0,3,50));

        graph[1].add(new Edges(1,0,10));
        graph[1].add(new Edges(1,2,10));

        graph[2].add(new Edges(2,1,10));
        graph[2].add(new Edges(2,3,10));

        graph[3].add(new Edges(3,0,50));
        graph[3].add(new Edges(3,2,10));
        graph[3].add(new Edges(3,4,12));

        graph[4].add(new Edges(4,3,12));
        graph[4].add(new Edges(4,6,70));
        graph[4].add(new Edges(4,5,8));

        graph[5].add(new Edges(5,6,4));
        graph[5].add(new Edges(5,4,8));

        graph[6].add(new Edges(6,4,70));
        graph[6].add(new Edges(6,5,4));

        int[] visited=new int[7];
        Arrays.fill(visited,0);

        System.out.println(graphs.hasPath(graph,0,6,visited));
        Arrays.fill(visited,0);
        graphs.printAllPaths(graph,0,6,visited,0+"");
        Arrays.fill(visited,0);

        ArrayList<ArrayList<Integer>> comps=new ArrayList<>();
        for(int i=0;i<graph.length;i++){
            if(visited[i] == 0){
                ArrayList<Integer> comp=new ArrayList<>();
                graphs.getConnectedComponents(graph,i,comp,visited);
                comps.add(comp);
            }
        }
        if(comps.size()==1){
            System.out.println("Graph is connected");
        } else {
            System.out.println("Graph is not connected");
        }
        comps.stream().forEach(s -> System.out.println(s.toString()));


        int[][] island={{1,1,0,0},{0,1,1,0},{0,1,0,1},{1,1,1,0}};
        boolean[][] v=new boolean[island.length][island[0].length];
        for(int i=0;i<v.length;i++){
            Arrays.fill(v[i],false);
        }
        int count=0;
        for(int i=0;i<island.length;i++){
            for(int j=0;j<island[0].length;j++){
                if(island[i][j] == 0 && v[i][j] == false){
                    graphs.connectedIslands(island,i,j,v);
                    count++;
                }
            }
        }

        System.out.println("Total Islands: "+count);
        System.out.println(graphs.perfectFriends(comps));


        System.out.print("Hamiltonian graph and path: ");
        HashSet<Integer> vis=new HashSet<>();
        graphs.hamiltonianGraph(graph,0,vis,0+"",0);

        System.out.println("Breadth First Search: ");
        graphs.bfs(graph,0);

        System.out.println("Is Graph bipartite: "+graphs.isGraphBipartite(graph));

        System.out.print("Spread Infection: ");
        graphs.spreadInfection(graph,6,3);

        System.out.print("Dijkstra Algorithm: ");
        graphs.dijkstraAlgorithm(graph,0);

        System.out.print("Prims Algorithm: ");
        graphs.PrimsAlgorithm(graph,0);

        Arrays.fill(visited,0);
        for(int i=0;i<graph.length;i++){
            if(visited[i]!=1){
                boolean cycle= graphs.isGraphACycleBfs(graph,i,visited);
                if(cycle || !cycle){
                    System.out.println("Is Graph a cycle using bfs: "+cycle);
                    break;
                }
            }
        }

        ArrayList<Edges>[] graph2=new ArrayList[7];
        for(int i=0;i<7;i++){
            graph2[i]=new ArrayList<>();
        }

        graph2[0].add(new Edges(0,1,10));
        graph2[0].add(new Edges(0,3,10));

        graph2[1].add(new Edges(1,2,10));

        graph2[2].add(new Edges(2,3,10));

        graph2[4].add(new Edges(4,3,10));
        graph2[4].add(new Edges(4,5,10));
        graph2[4].add(new Edges(4,6,10));

        graph2[5].add(new Edges(5,6,10));



        boolean[] visit=new boolean[graph2.length];
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<graph2.length;i++){
            if(!visit[i]){
                graphs.topologicalSort(graph2,i,st,visit);
            }
        }
        System.out.print("Topological Sort: ");
        while(st.size()>0) System.out.print(st.pop()+" ");
        System.out.println();

    }
}
