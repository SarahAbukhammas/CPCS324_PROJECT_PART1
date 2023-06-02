
package GraphFrameWork;


public class Edge {
    
    //Attributes: 
    public int weight;
    public Vertex sourceVer;
    public Vertex destVer;
    public Vertex parentVer;
    //constructer:
    public Edge( Vertex sourceVer, Vertex destVer,int weight) {
        this.weight = weight;
        this.sourceVer = sourceVer;
        this.destVer = destVer;
    }
    //getters and setters:
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setSourceVer(Vertex sourceVer) {
        this.sourceVer = sourceVer;
    }
    public void setDestVer(Vertex destVer) {
        this.destVer = destVer;
    }
    public void setParentVer(Vertex parentVer) {
        this.parentVer = parentVer;
    }
    public Vertex getSourceVer() {
        return sourceVer;
    }
    public Vertex getDestVer() {
        return destVer;
    }
    public Vertex getParentVer() {
        return parentVer;
    }   
    public int getWeight() {
        return weight;
    }
    //A method to display information about the edges: 
    public void displayInfo() {
       
        System.out.println("Source Vertex No."+sourceVer.getLable()+" Destination Vertex No. "+destVer.getLable()+" : Edge length: "+weight); 
    }   
}//End of class 
