
package GraphFrameWork;
import PhoneNetworkApp.*;
import java.util.*;

/*MHPrimAlg class is a subclass or a child class for the MSTAlgorthim class
and it inherents all of its methods and attributes
and overrides some of its methods
*/
public class MHPrimAlg extends MSTAlgorthim{
    
    //Attributes: 
    boolean[] Heap;
    ResultSet[] resultSet;
    //keys[] used to check if min heap update is required
    int[] key;
    heapNode[] heapNodes;
    
    //constructer:
    public MHPrimAlg(LinkedList<Edge> EdgeadjList, ArrayList<Vertex> vertices) {
    primMinHeap(EdgeadjList,vertices);
    }
    //Methods:
    /*
     primMinHeap method is the method that will execute the prim algorithm 
    */
    public void primMinHeap(LinkedList<Edge> EdgeadjList, ArrayList<Vertex> vertices) {
        
        //Start time to calculate the total running time 
        double startTime = System.currentTimeMillis();
        //set the sizes of all the arrays 
        Heap = new boolean[vertices.size()];
        resultSet = new ResultSet[vertices.size()];
        key = new int[vertices.size()];
        MinHeap minHeap = new MinHeap(vertices.size());
        //create heapNode for all the vertices
        heapNodes = new heapNode[vertices.size()];
        //fill the arrays with the initial values: 
        for (int i = 0; i < vertices.size(); i++) {
            heapNodes[i] = new heapNode(vertices.get(i).getIndex(),Integer.MAX_VALUE);
            resultSet[i] = new ResultSet();
            resultSet[i].parent = -1;
            Heap[i] = true;
            //set originally with a large infinite number as key
            key[i] = Integer.MAX_VALUE;
        }//end of for loop 
        //decrease the key for the first index
        heapNodes[0].key = 0;
        //add all the vertices to the MinHeap
        for(int i=0;i<vertices.size();i++){
            minHeap.insert(heapNodes[i]);
        }//end of for loop 
        
        //declare a list for the chosen edges of the Minimum spanning tree 
        LinkedList<Edge> MSTlist=new LinkedList<>();
        do {
            //extract the minimum node
            heapNode extractedNode = minHeap.extractMin();
            //extracted vertex
            int extractedVertex = extractedNode.node;
            // visited
            Heap[extractedVertex] = false;
            
        //iterate through all the adjacent vertices
        LinkedList<Edge> list = vertices.get(extractedVertex).getAdjList();
        //decalre needed varaibles:
        Edge edge=null;
        int AllowEdge=vertices.size()-1;
        int CurrentEdge=0;
        
            for (int i = 0; i < list.size(); i++) {
                //get the edge from the list of edges 
                edge = list.get(i);
                //only if edge destination vertex is present in heap
                if (Heap[edge.getDestVer().getIndex()]) {
                    int destination = edge.getDestVer().getIndex();
                    //change the destination vertex key value 
                    int newKey = edge.getWeight();
                    /*
                    check if updated new key < existing key, 
                    if yes update key value to the new key value
                    if no keep the key value as it is
                    */
                    if ( newKey < key[destination] ) {
                        //add edge to the final set of edges of the Minimum spanning tree 
                        MSTResultList.add(edge);
                        //remove the node from the minheap
                        toDecreaseKey(minHeap, newKey, destination);
                        //update the parent node for destination vertex
                        resultSet[destination].parent = extractedVertex;
                        resultSet[destination].weight = newKey;
                        key[destination] = newKey;  
                    }//end of inner if 
                }//end of outer if
            }//end of for loop 
        } while (!minHeap.isEmpty());//end of do while loop 
        //end time to calculate the total running time  
        double ftime = System.currentTimeMillis();
        //print the final cost result 
        displayResultingMST();
        //print the total time consumed by the algorithm:
        System.out.println("Total runtime of min-heap based Prim Algorithm: " + (ftime - startTime) + " ms.");
    }//end of primMinHeap method 
    /*
    toDecreaseKey method is used to remove the node from the minheap 
    after its been added to the final result set 
    */
    private void toDecreaseKey(MinHeap minheap, int newKey, int vertex) {
        //get the index which key's needs a decrease
        int index = minheap.decreaseKey[vertex];
        //get the node and update its value
        heapNode node = minheap.minHeap[index];
        node.key = newKey;
        //update the minheap after the removal of the node 
        minheap.bubbleUp(index);
    }//end of toDecreaseKey method
    /*
    displayResultingMST is a method that is overriden to display the output
    of the total running time and the cost of the prim algorithm
    */
    @Override
    public void displayResultingMST(){
          
        for(int j=0;j<MSTResultList.size();j++){
            //Print of output for requiremnt 1:
            //MSTResultList.get(j).displayInfo();
        }//end of for loop 
        int totalWeight = 0, i = 0;
        while (i < resultSet.length) {
            totalWeight += resultSet[i].weight;
            i++;
        }//end of while loop 
        //Print of output for requiremnt 1:
        //System.out.println("The cost of designed phone network: " + totalWeight);
        //Print of output for requiremnt 2:
        System.out.println("The cost of Prim algorithm: " + totalWeight);
    }//end of displayResultingMST method     
}//End of class 
    
    
    
    
    
    
    
    
    
    
    
