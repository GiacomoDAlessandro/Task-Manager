public class TESTER {

  public static boolean addAllTest() {

    //Checks that when tasks are in an array and not in min-heap order, when added using addAll()
    // they get chagned to minheap order
    {
      Task task1 = new Task("", 2025, 1, 01, "10:15", "12:15");
      Task task2 = new Task("", 2025, 3, 14, "10:15", "12:15");
      Task task3 = new Task("", 2025, 1, 10, "10:15", "12:15");
      Task task4 = new Task("", 2026, 1, 01, "10:15", "12:15");
      Task task5 = new Task("", 2025, 3, 14, "12:30", "16:00");

      Task[] incorrectOrder = {task1, task2, task3, task4, task5};

      Task[] correctOrder = {task1, task3, task2, task5, task4};

      PriorityTaskManager taskManager = new PriorityTaskManager(incorrectOrder.length);
      taskManager.addAllTasks(incorrectOrder);

      for (int i = 0; i < taskManager.getHeapData().length; i++) {
        if (correctOrder[i].compareTo(taskManager.getHeapData()[i]) != 0) {
          return false;
        }
      }

    }


    return true;
  }

  public static boolean percolateDownTester() {

    //Array already in min-heap form will stay the same when percolateDown is called

    {
      Task task1 = new Task("", 2025, 1, 01, "10:15", "12:15");
      Task task2 = new Task("", 2025, 3, 14, "10:15", "12:15");
      Task task3 = new Task("", 2025, 1, 10, "10:15", "12:15");
      Task task4 = new Task("", 2026, 1, 01, "10:15", "12:15");
      Task task5 = new Task("", 2025, 3, 14, "12:30", "16:00");

      Task[] testerDate = {task1, task3, task2, task5, task4};
      PriorityTaskManager taskManager = new PriorityTaskManager(5);


    }

    return false;
  }

  public static boolean percolateUpTester() {


    return false;
  }


  public static void main(String[] args) {
    boolean worked = addAllTest() && percolateDownTester() && percolateUpTester();

    System.out.println("All Tests Pass: " + worked);

    if (!worked) {
      System.out.println("addAllTest " + addAllTest());
      System.out.println("percolateDownTester " + percolateDownTester());
      System.out.println("percolateUpTester " + percolateUpTester());
    }
  }

}
