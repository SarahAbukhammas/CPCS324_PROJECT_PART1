package GraphFrameWork;

import PhoneNetworkApp.*;
import java.io.*;
import java.util.*;

public class Graph {
    
    //Attributes: 
    int veticesNo;
    int edgeNo;
    int CurrentEdge=0;
    static boolean isDigraph = false;   
    //list for vertices
    public ArrayList<Vertex> vertices;
    //list for the edges
    public LinkedList<Edge> EdgeadjList;
    
    //constructers:
    public Graph() {
        vertices = new ArrayList<>();
        EdgeadjList = new LinkedList<>();
    }
    public Graph(int veticesNo, int edgeNo, boolean isDigraph) {
        vertices = new ArrayList<>();
        EdgeadjList = new LinkedList<>();
        this.veticesNo = veticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph = isDigraph;
    }
    //getters and setters:
    public int getVeticesNo() {
        return veticesNo;
    }
    public int getEdgeNo() {
        return edgeNo;
    }
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }
    public LinkedList<Edge> getAdjList() {
        return EdgeadjList;
    }
    public void setVeticesNo(int veticesNo) {
        this.veticesNo = veticesNo;
    }
    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }
    public static void setIsDigraph(boolean isDigraph) {
        Graph.isDigraph = isDigraph;
    }
    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }
    public void setAdjList(LinkedList<Edge> adjList) {
        this.EdgeadjList = adjList;
    }
    //Methods:
    //A method used to check if a graph is directed or undirected:
    public static boolean isIsDigraph() {
        //Direct = true  ,  undirect=false
        return isDigraph;
    }
    /*
    CreateVertex method is used to create a Vertex object
    and adding it to the list of vertices
    and it returns the Vertex object to the caller
    */
    public Vertex CreateVertex(String Label) {
        Vertex vertex = new Vertex(Label);
        this.vertices.add(vertex);
        return vertex;
    }
    /*
    CreateEdge method is used to create an Edge object
    and it returns the Edge object to the caller
    */
    public Edge CreateEdge(Vertex source, Vertex destenation, int weight) {
        Edge edge = new Edge(source, destenation, weight);
        return edge;
    }
    /*
    readGraphFromFile Method is used to read the graph informatiom from a given
    file to create a graph for Requirement 1 
    */
    public void readGraphFromFile(String fileName) throws FileNotFoundException {
        //Create the file object
        File file = new File(fileName);
        //Use a scanner to read information from the file object
        Scanner read = new Scanner(file);
        int index=0;
        //skip line
        read.next();
        //Read if the graph is directed or not:
        int isDiagraph= read.nextInt();
        //Read number of vertices:
        int verticesNo = read.nextInt();
        //Read number of edges:
        int edgeNo = read.nextInt();
        //set the digraph information
        if (isDiagraph == 0) {
            //undirect=false
            setIsDigraph(false);
        }//End of if
        else {
            //Direct = true
            setIsDigraph(true);
        }//End of else
        // set the vertices number:
        setVeticesNo(veticesNo);
        // set the edges number:
        setEdgeNo(edgeNo);
        //read from the file object:
        while (read.hasNext()) {
            //read the label of the 1st vertex:
            String lable = read.next();
            //declare the vertex object 
            Vertex v1=null;
            for (int i = 0; i < vertices.size(); i++) {
                //check if vertex alreday exists and is in the vertices list
                if (vertices.get(i).getLable().equalsIgnoreCase(lable)) {
                    v1 = vertices.get(i);
                    break;
                }//end of if
            }//end of for loop
            //if vertex object is still null then it has not been created before
            if(v1==null){
            //Create the vertex object
            v1=CreateVertex(lable);
            v1.setIndex(index);
            index++;
            }//end of if 
            //read the label of the 2nd vertex:
            lable = read.next();
            //declare the vertex object 
            Vertex v2=null;
            for (int i = 0; i < vertices.size(); i++) {
                //check if vertex alreday exists and is in the vertices list
                if (vertices.get(i).getLable().equalsIgnoreCase(lable)) {
                    v2= vertices.get(i);
                    break;
                }//end of if 
            }//end of for loop 
            //if vertex object is still null then it has not been created before
            if(v2==null){
            //Create the vertex object
            v2=CreateVertex(lable);
            v2.setIndex(index);
            index++;
            }//end of if 
            //read the weight of the edge between the two vertices:
            int weight = read.nextInt();
            //create the edge object and add the edge to the list of edges of the graph
            addEdges(v1,v2, weight);
        }//end of while loop
        read.close();
    }//end of readGraphFromFile method 
    /*
    addEdges method is used to create an edge object between the two vertices
    sent to the parameters with the specific weigth specified in the parameters 
    and add it to the list of edges of the graph object
    */
    public void addEdges(Vertex source , Vertex des,int weight) { 
        //create the edge object
        Edge edge1= CreateEdge(source, des, weight);
        //add the edge to list of edges of the graph
        EdgeadjList.add(edge1); 
        //add the destination vertex to the adjacent list of the source vertex
        edge1.getSourceVer().getAdjList().add(edge1);
        // if it is undirected
        if (isIsDigraph()== false) {
            /*
            create another edge object with the source vertex as the destination
            and the destination vertex as the source
            */
            Edge edge2= CreateEdge(edge1.getDestVer(),edge1.getSourceVer(),edge1.weight);
            edge1.getDestVer().getAdjList().add(edge2);
            // increase the number of edges by two
            CurrentEdge += 2; 
        }//end of if
        // if it is directed
        else {
        // increase the number of edges by one
        CurrentEdge += 1;
        }//end of else
   }//end of addEdges method 
    /*
    make_graph is a method used by requirement two to make a randomized graph
    */
    public void make_graph(Graph graph) {
        
        Random randm = new Random();
        for (int i = 0; i < graph.veticesNo; i++) {
            //set the vertex label
            String vertexLabel = i + "";
            //create a vertex object with the random label 
            Vertex v1=CreateVertex(vertexLabel);
            v1.setIndex(i);
        }//end of for loop 
        
        int weight;
        for(int i=0;i<graph.veticesNo-1;i++){
            //randomize the weight of the edge object 
            weight = randm.nextInt(20) + 1;
            //create an edge object between two vertices with the random weight 
            addEdges(graph.vertices.get(i),graph.vertices.get(i+1),weight );
        }//end of for loop 
        
        //declare variables
        int v1;
        int v2;
        //see how many edges are left to create for the graph object 
        int RemainingEdge= graph.edgeNo - (graph.veticesNo-1);
        for (int i = 0; i < RemainingEdge; i++) {
            //randomize the weight of the edge object
            weight = randm.nextInt(20) + 1;
            //choose two random vertices 
            v1 = randm.nextInt(graph.veticesNo);
            v2 = randm.nextInt(graph.veticesNo);
            //to ensure the two vertices are not connected already before creating an edge between them
            if(v1 == v2 || isConnected(v1, v2,graph.EdgeadjList)) {
            //if the vertices are already connected by an edge, do not create a new edge between them
            i--;
                continue;
            }//end of if 
            /*
            if the vertices are not connected by an edge, 
            create edge object between the two randomly chosen vertices 
            with the random weight and add it to list of edges of the graph
            */
            graph.addEdges(graph.vertices.get(v1), graph.vertices.get(v2), weight);
        }//end of for loop 
        
    }//end of make_graph method 
    /*
    isConnected method is a method used to check wether or not an edge 
    already exists and connects the two specified vertices in the parameter of the method
    */
     public boolean isConnected(int Source, int Destination, LinkedList<Edge> allEdges) {
            for (int i=0;i<EdgeadjList.size();i++) {
                Edge edge = EdgeadjList.get(i);
                if ((edge.getSourceVer().getIndex() == Source && edge.getDestVer().getIndex() == Destination) || (edge.getSourceVer().getIndex() == Destination && edge.getDestVer().getIndex()== Source)) {
                    //return true if edge exists and the two vertices are connected 
                    return true;
                }//end of if
            }//end of for loop
        //return false if no edge exists and the two vertices are not connected 
        return false;
    }//end of isConnected method
}//End of class 
