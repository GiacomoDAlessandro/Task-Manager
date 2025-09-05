import java.util.Arrays;

/**
 * Implements a priority queue of tasks, uses a min-heap structure maintained in an array
 */
public class PriorityTaskManager {

  /**
   * Array that contains the task the user enters
   */
  private Task[] heapData;

  /**
   * Number of items in heapData
   */
  private int size;


  /**
   * Constructor that initializes the size
   *
   * @param size number of tasks the array has to fit
   */
  public PriorityTaskManager(int size) {
    this.size = 0;
    heapData = new Task[size];
  }

  /**
   * Class to add all tasks to the taskManager, which also puts them in min-heap form
   *
   * @param tasks array of Task[] to be added
   */
  public void addAllTasks(Task[] tasks) {
    for (int i = 0; i < tasks.length; i++) {
      heapData[i] = tasks[i];
      size++;
      //If the current node is not the root, it gets percolated up
      if (i > 0) {
        percolateUp(i);
      }
    }
  }

  /**
   * Adds a task to heapData, percolatesUp to maintain min-heap properties
   *
   * @param t task to be added
   */
  public void addTask(Task t) {
    //If heapData is full, an exception is thrown
    if (heapData.length == size) {
      throw new IllegalArgumentException("Queue is full");
    }

    heapData[size] = t;

    //PercolateUp is called if the current node is not the root
    if (size > 0) {
      percolateUp(size);
    }

    size++;
  }

  /**
   * Percolates the node called up only if the parent is bigger than the child
   *
   * @param i node to be percolated up
   */
  public void percolateUp(int i ) {
    //If the node called is the root, return
    if (i < 0) {
      return;
    }

    //Get parent index
    int parentIndex = (i - 1) / 2;
    Task temp;

    //If child is smaller than parent, swap
    if (heapData[i].compareTo(heapData[parentIndex]) < 0) {
      temp = heapData[parentIndex];
      heapData[parentIndex] = heapData[i];
      heapData[i] = temp;
      percolateUp(parentIndex);
    } else if (heapData[i].compareTo(heapData[parentIndex]) >= 0) {
      return;
    }
  }

  /**
   * Percolates down the node called if it is bigger than either of its children nodes
   *
   * @param i node to be percolatedDown
   */
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

  /**
   * Removes the task that is happening the earliest and calls percolateDown to maintain min-heap
   */
  public void removeMostRecent() {
    heapData[0] = heapData[size - 1];
    heapData[size - 1] = null;
    size--;

    //If heapData is not empty percolateDown is called
    if (size > 0) {
      percolateDown(0);
    }
  }

  /**
   * Gets the task that is happening earliest and returns it
   *
   * @return earliest task
   */
  public Task getMostRecent() {
    Task[] copy = getHeapData();
    return copy[0];
  }

  /**
   * Accessor method for heapData
   *
   * @return deep-copy of heapData
   */
  public Task[] getHeapData() {return Arrays.copyOf(heapData, heapData.length);}




}
