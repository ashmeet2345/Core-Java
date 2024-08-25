package DSA;

import java.util.ArrayList;

public class GraphSeries {

    public void display(ArrayList<ArrayList<Integer> > graph){
       for(int i=1;i<=graph.size()-1;i++){
           System.out.print(i+"->");
           for(int j=0;j<graph.get(i).size();j++){
               System.out.print(graph.get(i).get(j)+" ");
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
        g.display(graph);
    }
}
