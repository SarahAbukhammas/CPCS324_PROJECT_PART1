
package PhoneNetworkApp;

import GraphFrameWork.*;

/*Line class is a subclass or a child class for the Edge class
and it inherents all of its methods and attributes
and overrides some of its methods
*/
public class Line extends Edge{
    
    //Attributes: 
    public int lLenght;
    //constructer:
    public Line(Vertex sourceVer, Vertex destVer, int weight) {
        super(sourceVer, destVer, weight);
        //The lLength attribute represents the line length and it is 5 times the weight of the corresponding edge. 
        this.lLenght = 5 * weight;
    }
    //Methods:
    //A method to display information about the Line object: 
    @Override
    public void displayInfo() {
       System.out.println("Office No."+sourceVer.getLable()+" Office No. "+destVer.getLable()+" : line length: "+lLenght); 
    }
}//End of class 
