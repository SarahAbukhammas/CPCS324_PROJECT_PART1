
package PhoneNetworkApp;
import GraphFrameWork.*;

/*Office class is a subclass or a child class for the Vertex class
and it inherents all of its methods and attributes
and overrides some of its methods
*/
public class Office extends Vertex {
    
    //constructer:
    public Office(String Lable) {
        super(Lable);
    }
    //getters and setters: 
    public void setLable(int i) {
        this.Lable = ("O"+i);
    }
    @Override
    public String getLable() {
        return Lable;
    }
    //Methods:
    //A method to display information about the Offices: 
    @Override
    public void displayInfo() {
    }
}//End of class 
