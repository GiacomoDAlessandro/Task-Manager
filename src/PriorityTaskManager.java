
public class PriorityTaskManager {

  /**
   * Array that contains the task the user enters
   */
  private Task[] heapData;

  /**
   * Size of heapData
   */
  private int size;



  public PriorityTaskManager(int size) {
    this.size = size;
  }

  public void addTask(Task t) {
    if (heapData.length == size) {
      throw new IllegalArgumentException("Queue is full");
    }

    heapData[size] = t;

    if (size > 0) {
      percolateUp(size);
    }

    size++;
  }

  public void percolateUp(int i ) {
    if (i < 0) {
      return;
    }

    int parentIndex = (i - 2) / 2;
    Task temp;

    if (heapData[i].compareTo(heapData[parentIndex]) < 0) {
      temp = heapData[parentIndex];
      heapData[parentIndex] = heapData[i];
      heapData[i] = temp;
      percolateUp(parentIndex);
    } else if (heapData[i].compareTo(heapData[parentIndex]) >= 0) {
      return;
    }
  }

  public void percolateDown(int i) {

    //Get children indexes
    int leftChildIndex = (2 * i) + 1;
    int rightChildIndex = (2 * i) + 2;

    //Method returns if i has no children
    if (leftChildIndex >= size && rightChildIndex >= size) {
      return;
    }


    //Get Task objects from each child indexes and parent index
    Task leftChild = heapData[leftChildIndex];
    Task rightChild = heapData[rightChildIndex];
    Task parent = heapData[i];

    //Get Smallest child
    int smallerIndex;

    //If only a left child no right child
    if (rightChildIndex >= size) {
      smallerIndex = leftChildIndex;
    } else {
      smallerIndex = (leftChild.compareTo(rightChild) <= 0) ? leftChildIndex : rightChildIndex;
    }

    Task smaller = heapData[smallerIndex];
    //Compare smallest child with parent, swap if child is bigger
    if (parent.compareTo(smaller) > 0) {
      heapData[i] = smaller;
      heapData[smallerIndex] = parent;
      percolateDown(smallerIndex);
    }
  }

  public void removeMostRecent() {

    heapData[0] = heapData[size - 1];
    heapData[size - 1] = null;
    size--;

    if (size > 0) {
      percolateDown(0);
    }
  }




}
