
package GraphFrameWork;
import PhoneNetworkApp.Office;
import java.util.ArrayList;
import java.util.LinkedList;

public class Vertex {
    
    //Attributes: 
    public String Lable;
    boolean isVisited = true;
    int index;
    public LinkedList<Edge> adjList;
    //constructer:
    public Vertex(String Lable) {
        adjList = new LinkedList<>();
        this.Lable = Lable;
    }
    //getters and setters: 
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public void setLable(String Lable) {
        this.Lable = Lable;
    }
    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }
    public String getLable() {
        return Lable;
    }   
    public void setAdjList(LinkedList<Edge> adjList) {
        this.adjList = adjList;
    }
    public LinkedList<Edge> getAdjList() {
        return adjList;
    }
    //Methods: 
    //a method to create an adjacent list for the vertex object 
    public void CreateAdjList() {
        adjList = new LinkedList<>();
    }
    //a methdo to add the adjacent edges to the adjacent list of the vertex 
    public void addAdjacent(Edge vertex) {
        adjList.add(vertex);
    }
    /*
    a method used to check if a certain vertex is adjacent to the vertex object
    by checking if it is found in the adjacent list of the vertex
    */
    public boolean isAdjacent(Edge vertex) {
        return adjList.contains(vertex);
    }
    /*
    A method used to check if the vertex object has been visited or not
    */
    public boolean isIsVisited() {
        return isVisited;
    }
    //A method to display information about the vertices: 
     public void displayInfo() {
        System.out.print(" Vertex " + Lable + "  adjacent are: " + adjList.size());
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println(adjList.get(i).getDestVer()+ "  ");
        }
        System.out.println("");
    }
     
}//End of class 
