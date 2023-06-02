
package PhoneNetworkApp;
import GraphFrameWork.*;


/*BluePrintsGraph class is a subclass or a child class for the Graph class
and it inherents all of its methods and attributes
and overrides some of its methods
*/
public class BluePrintsGraph extends Graph {
    //constructers:
    public BluePrintsGraph() {
    }
    public BluePrintsGraph(int veticesNo, int edgeNo, boolean isDigraph) {
        super(veticesNo, edgeNo, isDigraph);
    }
    //Methods:
    /*
    CreateVertex is an overriden method used to create Office object
    and adding it to the list of offices 
    and it returns the office object to the caller
    */
    @Override
     public Office CreateVertex(String label) {
         Office office= new Office(label);
        this.vertices.add(office);
        return office;
    }  
    /*
    CreateEdge is an overriden method used to create Line object
    and it returns the Line object to the caller
    */
     @Override
        public Line CreateEdge(Vertex source, Vertex destenation, int weight) {
        Line line = new Line(source, destenation, weight);
        return line;
    }
}//End of class 

