package GraphFrameWork;

import PhoneNetworkApp.Line;
import PhoneNetworkApp.Office;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/*KruskalAlg class is a subclass or a child class for the MSTAlgorthim class
and it inherents all of its methods and attributes
and overrides some of its methods
*/
public class KruskalAlg extends MSTAlgorthim {
        
        //constructer:
        public KruskalAlg(LinkedList<Edge> EdgeadjList, ArrayList<Vertex> vertices) {
        //Start time to calculate the total running time 
        double startTime = System.currentTimeMillis();
        //declare the final cost of the algorithm
        int cost = 0;
        ArrayList<String>[] parent = new ArrayList[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = new ArrayList<String>();
        }//end of for loop 
        //call the makeset algorithm to make singlton sets for each vertex
        MakeSet(parent, vertices);
        //sort all the edges of the graph
        Collections.sort(EdgeadjList, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.getWeight()- e2.getWeight();
            }});
        //make a counter to go over all of the edges 
        int ecounter = 0;
        for (int i = 0; ecounter < vertices.size() - 1; i++) {
            //get an edge from the list of edges
            Edge edge = EdgeadjList.get(i);
            //find the parent of the two vertices of the edge
            int v1 = find(parent, edge.sourceVer.getLable());
            int v2 = find(parent, edge.destVer.getLable());
            //check if the Edge between v1 and v2 is acyclic (does not create a cycle):
            if (v1 != v2) {
                //add the edge to final set of edges of the minimum spanning tree 
                MSTResultList.add(edge);
                //add the weight to final cost of the algorithm
                cost = cost + edge.weight;
                ecounter++;
                //unite the sets of v1 and v2 into one set 
                union(parent, edge.sourceVer.getLable(), edge.destVer.getLable(), v1, v2);
            }//end of if 
        }//end of for loop 
        //end time to calculate the total running time  
        double ftime = System.currentTimeMillis();
        //call method to display the final cost 
        displayResultingMST();
        ////print the total time consumed by the algorithm
        System.out.println("Total runtime of Kruskal Algorithm: " + (ftime - startTime) + " ms.");  
    }//End of constructer
    //Methods:
    /*
    find method return the parent of the vertex sent to the parameter
    */
    public int find(ArrayList<String>[] parent, String Lable) {
        for (int i = 0; i < parent.length; i++) {
            for (int j = 0; j < parent[i].size(); j++) {
                if (parent[i].get(j).equalsIgnoreCase(Lable)) {
                    //return parent of vertex 
                    return i;
                }//end of if 
            }//end of inner for loop 
        }//end of outer for loop 
        return -1;
    }//end of find method 
    /*
    MakeSet method is a method used to make a set for every single vertex
    */
    public void MakeSet(ArrayList<String>[] parent, ArrayList<Vertex> vertices) {
        // Create set for every vertex
        for (int i = 0; i < vertices.size(); i++) {
            parent[i].add(vertices.get(i).getLable());
        }
    }//end of MakeSet method
    /*
    union method is used to unite two sets into one set and delete the two old sets 
    */
    public void union(ArrayList<String>[] parent, String source, String dest, int x, int y) {
        
        //add the smaller set to the end of the larger set
        if (parent[x].size() >= parent[y].size()) {
            for (int i = 0; i < parent[y].size(); i++) {
                parent[x].add(parent[y].get(i));
                parent[y].remove(i);
                if( i < parent[y].size())
                    i--;         
            }//end of for loop 
        }//end of if 
        else{
            for (int i = 0; i < parent[x].size(); i++) {
                parent[y].add(parent[x].get(i));
                parent[x].remove(i);
                if( i < parent[x].size())
                    i--;
            }//end of for loop 
        }//end of else 
    }//end of union method 
    /*
    displayResultingMST is a method that is overriden to display the output
    of the total running time and the cost of the kruskal algorithm
    */
    @Override
    public void displayResultingMST() {
        int cost = 0;
        for (int i = 0; i < MSTResultList.size(); i++) {
        //Print of output for requiremnt 1:
        //MSTResultList.get(i).displayInfo();
        cost = cost + MSTResultList.get(i).weight;
        }
        //Print of output for requiremnt 1:
        //System.out.println("The cost of designed phone network:  " + cost);
        //Print of output for requiremnt 2:
        System.out.println("The cost of Kruskal:  " + cost); 
    }
}//End of class 
