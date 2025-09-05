/**
 * Tester Class
 */
public class TESTER {

  /**
   * Testing the addAll to make sure percolateUp works and that after this is called the array is
   * in min-heap form
   *
   * @return true if works false otherwise
   */
  public static boolean addAllTest() {

    //Checks that when tasks are in an array and not in min-heap order, when added using addAll()
    // they get changed to minheap order
    {
      Task task1 = new Task("", 2025, 1, 01, "10:15", "12:15");
      Task task2 = new Task("", 2025, 3, 14, "10:15", "12:15");
      Task task3 = new Task("", 2025, 1, 10, "10:15", "12:15");
      Task task4 = new Task("", 2026, 1, 01, "10:15", "12:15");
      Task task5 = new Task("", 2025, 3, 14, "12:30", "16:00");

      Task[] incorrectOrder = {task1, task2, task3, task4, task5};



      PriorityTaskManager taskManager = new PriorityTaskManager(incorrectOrder.length);
      taskManager.addAllTasks(incorrectOrder);
      Task[] percolatedOrder = taskManager.getHeapData();

      for (int i = 0; i < percolatedOrder.length; i++) {
        int leftChildIndex = (2 * i) + 1;
        int rightChildIndex = (2 * i) + 2;
        if (leftChildIndex >= percolatedOrder.length && rightChildIndex >= percolatedOrder.length) {
          break;
        }
        if (percolatedOrder[i].compareTo(
            percolatedOrder[leftChildIndex]) > 0 || percolatedOrder[i].compareTo(
            percolatedOrder[rightChildIndex]) > 0) {
          return false;
        }

      }
    }

    //If reached, all tests passed
    return true;
  }

  /**
   * Tests the most recent accessor method and method that removes the most recent task
   *
   * @return true if works false otherwise
   */
  public static boolean testMostRecent() {

    //Tests getting the most recent makes sure that it gets the correct one
    {
      Task task1 = new Task("", 2025, 1, 01, "10:15", "12:15");
      Task task2 = new Task("", 2025, 3, 14, "10:15", "12:15");
      Task task3 = new Task("", 2025, 1, 10, "10:15", "12:15");
      Task task4 = new Task("", 2026, 1, 01, "10:15", "12:15");
      Task task5 = new Task("", 2025, 3, 14, "12:30", "16:00");

      Task[] incorrectOrder = {task4, task2, task3, task1, task5};



      PriorityTaskManager taskManager = new PriorityTaskManager(incorrectOrder.length);
      taskManager.addAllTasks(incorrectOrder);

      if (taskManager.getMostRecent() != task1) {
        return false;
      }

    }


    //Tests removing the most recent then accessing it making sure it gets a different one
    {
      Task task1 = new Task("", 2025, 1, 01, "10:15", "12:15");
      Task task2 = new Task("", 2025, 3, 14, "10:15", "12:15");
      Task task3 = new Task("", 2025, 1, 10, "10:15", "12:15");
      Task task4 = new Task("", 2026, 1, 01, "10:15", "12:15");
      Task task5 = new Task("", 2025, 3, 14, "12:30", "16:00");

      Task[] incorrectOrder = {task4, task2, task3, task1, task5};



      PriorityTaskManager taskManager = new PriorityTaskManager(incorrectOrder.length);
      taskManager.addAllTasks(incorrectOrder);

      Task mostRecentFirst = taskManager.getMostRecent();

      taskManager.removeMostRecent();

      if (taskManager.getMostRecent().equals(mostRecentFirst)) {
        return false;
      }
    }

    //If reached, all tests passed
    return true;
  }



  /**
   * Main method to run test methods
   *
   * @param args not used
   */
  public static void main(String[] args) {
    boolean worked = addAllTest() && testMostRecent();

    System.out.println("All Tests Pass: " + worked);

    if (!worked) {
      System.out.println("addAllTest " + addAllTest() + "\n testMostRecent " + testMostRecent());
      ;

    }
  }

}
