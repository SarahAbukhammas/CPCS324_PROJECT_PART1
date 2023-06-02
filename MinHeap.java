
package GraphFrameWork;

class MinHeap {
    //Attributes: 
    int capacity;
    int Size; //The current size
    heapNode[] minHeap;
    int[] decreaseKey; //to decrease the key
    //Constructer:
    //---------------------------------MinHeap constructor----------------------
    public MinHeap(int capacity) {
        this.capacity = capacity;
        minHeap = new heapNode[capacity + 1];
        decreaseKey = new int[capacity];
        minHeap[0] = new heapNode(-1,Integer.MIN_VALUE);
        Size = 0;
    }
    //Methods:
    //-------------------Method to insert heapNode------------------------------
    public void insert(heapNode Node) {
        Size++;
        int idx = Size;
        minHeap[idx] = Node;
        decreaseKey[Node.node] = idx;
        bubbleUp(idx);
    }
    //-------------------------------To bubbleUp--------------------------------
    public void bubbleUp(int Position) {
        int parentIdx = Position / 2;
        int currentIdx = Position;
         while (currentIdx > 0 && minHeap[parentIdx].key > minHeap[currentIdx].key) {
            heapNode currentNode = minHeap[currentIdx];
            heapNode parentNode = minHeap[parentIdx];
            //to swap positions 
            decreaseKey[currentNode.node] = parentIdx;
            decreaseKey[parentNode.node] = currentIdx;
            swap(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx / 2;}      
    }
    //-------------------------------To exttract the min node-------------------
     public heapNode extractMin() {
        heapNode min = minHeap[1];
        heapNode lastNode = minHeap[Size];
        //to put the last node at the top and update the decreaseKey[]
        decreaseKey[lastNode.node] = 1;
        minHeap[1] = lastNode;
        minHeap[Size] = null;
        sinkDown(1);
        Size--;
        return min;
    }
    //-------------------------------To sink down the min heap------------------
    public void sinkDown(int T) {
        int theSmallest = T;
        int leftChild = 2 * T;
        int rightChild = 2 * T + 1;
        if (leftChild < heapSize() && minHeap[theSmallest].key
                > minHeap[leftChild].key) {
            theSmallest = leftChild;
        }
        if (rightChild < heapSize() && minHeap[theSmallest].key
                > minHeap[rightChild].key) {
            theSmallest = rightChild;
        }
        if (theSmallest != T) {

            heapNode smallestNode = minHeap[theSmallest];
            heapNode TNode = minHeap[T];
            //swap the positions
            decreaseKey[smallestNode.node] = T;
            decreaseKey[TNode.node] = theSmallest;
            swap(T, theSmallest);
            sinkDown(theSmallest);
        }
    }
    //-------------------------------To Check if heap is empty------------------
    public boolean isEmpty() {
        return Size == 0;
    }
    //-------------------------------To get the min Heap Size-------------------
    public int heapSize() {
        return Size;
    }
    //------------------------------- swap method to use it in sinkDown---------
    public void swap(int x, int y) {
        heapNode temp = minHeap[x];
        minHeap[x] = minHeap[y];
        minHeap[y] = temp;
    }
    //------------------------------- To display min heap node------------------
    public void display() {
        int i = 0;
        while (i <= Size) {
            System.out.println(" " + minHeap[i++].node
                    + "   key   " + minHeap[i].key);
        }
        System.out.println("--------------------------------");
    }
}//End of class 