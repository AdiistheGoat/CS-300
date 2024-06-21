//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TaskManagerTester
// Course: CS 300 Spring 2024
//
// Author: Aditya Goyal
// Email: agoyal33@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class tests the TaskManager, TaskList, and SortedTaskList classes.
 */
public class TaskManagerTester {

  /**
   * Checks the correctness of the implementation of the method compareTo() defined in the Task
   * class. Consider different test scenarios including each of the SortingOrder values: TITLE and
   * PRIORITY
   * 
   * Test scenarios: <BR>
   * aTask and anotherTask references any Task objects you can create.<BR>
   * 1. aTask.compareTo(anotherTask) is expected to return 0 if they are equal with respect to the
   * comparison criteria. <BR>
   * 2. aTask.compareTo(aTask) is expected to return 0 <BR>
   * 3. aTask.compareTo(anotherTask) is expected to return a negative integer (less than zero) if
   * aTask is less than another Task with respect to the comparison criteria. <BR>
   * 4.aTask.compareTo(anotherTask) is expected to return a positive integer (greater than zero) if
   * aTask is greater than another Task with respect to the comparison criteria.
   * 
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testTaskCompareTo() {

    Task aTask = new Task("task1", "jngf;mn;43", true);
    Task anotherTask1 = new Task("task1", "jngf;mn;43", true);
    Task anotherTask2 = new Task("task2", "jngf;mn;43", false);
    Task anotherTask3 = new Task("task0", "jngf;mn;43", false);


    Task.setSortingOrderByTitle();

    if (aTask.compareTo(anotherTask3) < 0) {
      return false;
    }

    if (aTask.compareTo(anotherTask2) > 0) {
      return false;
    }

    if (aTask.compareTo(anotherTask1) != 0) {
      return false;
    }

    if (aTask.compareTo(aTask) != 0) {
      return false;
    }


    Task.setSortingOrderByPriorityLevel();

    if (aTask.compareTo(anotherTask2) > 0) {
      return false;
    }

    if (anotherTask2.compareTo(aTask) < 0) {
      return false;
    }

    if (aTask.compareTo(anotherTask1) != 0) {
      return false;
    }

    if (aTask.compareTo(aTask) != 0) {
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of the implementation of the equals() method defined in the Task class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testTaskEquals() {

    Task test1 = new Task("TestIng", "fponrnkvrv");
    Task test2 = new Task("tEsTinG", "jfo[4ifm'o");

    {
      boolean expected = false;
      boolean actual = test1.equals(null);

      if (expected != actual) {
        return false;
      }
    }


    {
      boolean expected = false;
      boolean actual = test1.equals("String");

      if (expected != actual) {
        return false;
      }

    }


    {
      boolean expected = true;
      boolean actual = test1.equals(test2);

      if (expected != actual) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests the add(), isEmpty(), and size() methods of the TaskList class.
   * 
   * Test scenarios: <BR>
   * 1. Create a new empty TaskList and verify that isEmpty() returns true.<BR>
   * 2. Add a few tasks to the end of the TaskList and verify that isEmpty() returns false.<BR>
   * 3. Verify that size() returns the expected size after adding each Task.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testAddIsEmptySize() {
    // shouldn't we do try and catch for any error while creating an Task object
    // we can use get // if it is correct and already tested to see if the add actually adds.
    TaskList testTaskList = new TaskList();

    Task task1 = new Task("task1", "jngf;mn;43", true);
    Task task2 = new Task("task2", "jngf;mn;43", true);
    Task task3 = new Task("task3", "jngf;mn;43", false);
    Task task4 = new Task("task4", "jngf;mn;43", false);

    {
      boolean expected = true;
      boolean actual = testTaskList.isEmpty();
      if (expected != actual) {
        return false;
      }
    }

    testTaskList.add(task1);
    testTaskList.add(task2);
    testTaskList.add(task3);
    testTaskList.add(task4);

    {
      boolean expected = false;
      boolean actual = testTaskList.isEmpty();
      if (expected != actual) {
        return false;
      }
    }


    {
      int expected = 4;
      int actual = testTaskList.size();
      if (expected != actual) {
        return false;
      }
    }

    {
      Task expected = testTaskList.get(0);
      if (expected != task1) {
        return false;
      }
    }

    {
      Task expected = testTaskList.get(1);
      if (expected != task2) {
        return false;
      }
    }


    {
      Task expected = testTaskList.get(2);
      if (expected != task3) {
        return false;
      }
    }


    {
      Task expected = testTaskList.get(3);
      if (expected != task4) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests the addFirst(), and add(index, element) methods of the TaskList class.
   * 
   * Test scenarios: <BR>
   * 1. Test adding elements to an empty TaskList <BR>
   * 2. Test adding elements to the beginning, middle, and end of a non-empty TaskList.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testAddToTaskList() {

    TaskList testTaskList = new TaskList();

    Task task1 = new Task("task1", "jngf;mn;43", true);
    Task task2 = new Task("task2", "jngf;mn;43", true);
    Task task3 = new Task("task3", "jngf;mn;43", false);
    Task task4 = new Task("task4", "jngf;mn;43", false);

    // tasks are not null. the arguments are not null.


    // testing addFirst
    testTaskList.addFirst(task1);


    if (testTaskList.size() != 1) {
      return false;
    }

    try {
      Task expected1 = testTaskList.get(0);
      if (expected1 != task1) {
        return false;
      }
    }

    catch (IndexOutOfBoundsException e) {
      return false;
    }

    testTaskList.addFirst(task2);

    if (testTaskList.size() != 2) {
      return false;
    }

    try {

      Task expected2 = testTaskList.get(0);
      if (expected2 != task2) {
        return false;
      }


      Task expected3 = testTaskList.get(1);
      if (expected3 != task1) {
        return false;
      }

    }

    catch (IndexOutOfBoundsException e) {
      return false;
    }



    testTaskList = new TaskList();



    // testing add(index, element)
    // regarding the indexes as argument, we could be correct but the listNode wouldn't have
    // developed to such an extent, or is that scenario not possible

    // the try catch statement proved very useful in cases where size was not updated and would help
    // a programmer to identify gaps in the method.
    // when size was not updated, the index , even though present, an index out of bounds exception
    // was till thrown.

    try {

      // adding at the start
      testTaskList.add(0, task1);

      Task expected1 = testTaskList.get(0);

      if (expected1 != task1) {
        return false;
      }

      if (testTaskList.size() != 1) {
        return false;
      }



      // adding at the last
      testTaskList.add(1, task2);

      Task expected2 = testTaskList.get(1);


      if (expected2 != task2) {
        return false;
      }

      if (testTaskList.size() != 2) {
        return false;
      }


      // adding in the middle
      testTaskList.add(1, task3);

      Task expected3 = testTaskList.get(1);
      Task expected3Next = testTaskList.get(2);


      if (expected3 != task3) {
        return false;
      }

      if (expected3Next != task2) {
        return false;
      }

      if (testTaskList.size() != 3) {
        return false;
      }

    }


    catch (IndexOutOfBoundsException e) {
      return false;
    }

    return true;
  }

  /**
   * Tests and remove(index) and clear() methods of the TaskList class.
   * 
   * Test scenarios: <BR>
   * 1. Test removing elements from various positions in the TaskList using remove(index). <BR>
   * 2. Test removing elements from an empty TaskList or at invalid indices. <BR>
   * 3. Test clear() method and verify that the TaskList is empty after calling it.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testRemoveClear() {


    TaskList testTaskList = new TaskList();

    Task task1 = new Task("task1", "jngf;mn;43", true);
    Task task2 = new Task("task2", "jngf;mn;43", true);
    Task task3 = new Task("task3", "jngf;mn;43", false);
    Task task4 = new Task("task4", "jngf;mn;43", false);
    Task task5 = new Task("task5", "jngf;mn;43", true);

    testTaskList.add(task1);
    testTaskList.add(task2);
    testTaskList.add(task3);
    testTaskList.add(task4);
    testTaskList.add(task5);


    // checking if the remove method works
    try {

      {
        int expectedSize = testTaskList.size() - 1;

        Task expectedNode = testTaskList.get(0);


        Task actualNode = testTaskList.remove(0);

        int actualSize = testTaskList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        if (expectedNode != actualNode) {
          return false;
        }

      }


      {
        int expectedSize = testTaskList.size() - 1;
        Task expectedNode = testTaskList.get(3);

        Task actualNode = testTaskList.remove(3);
        int actualSize = testTaskList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        if (expectedNode != actualNode) {
          return false;
        }

      }


      {
        int expectedSize = testTaskList.size() - 1;
        Task expectedNode = testTaskList.get(1);

        Task actualNode = testTaskList.remove(1);
        int actualSize = testTaskList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        if (expectedNode != actualNode) {
          return false;
        }

      }


    }

    catch (IndexOutOfBoundsException e) {
      return false;
    }



    testTaskList = new TaskList();


    try {
      testTaskList.remove(0);
      return false;
    }


    catch (IndexOutOfBoundsException e) {

    }

    catch (Exception e) {
      return false;
    }



    testTaskList = new TaskList();
    testTaskList.add(task5);


    try {
      testTaskList.remove(1);
      return false;
    }


    catch (IndexOutOfBoundsException e) {

    }

    catch (Exception e) {
      return false;
    }



    // checking the working of the .clear() method.
    testTaskList = new TaskList();

    testTaskList.add(task1);
    testTaskList.add(task2);
    testTaskList.add(task3);
    testTaskList.add(task4);
    testTaskList.add(task5);

    testTaskList.clear();

    int expectedSize = 0;
    int actualSize = testTaskList.size();

    if (expectedSize != actualSize) {
      return false;
    }

    try {
      Task testTask = testTaskList.get(0);
      return false;
    }

    catch (IndexOutOfBoundsException e) {

    }

    catch (Exception e) {
      return false;
    }



    return true;
  }

  /**
   * Tests the add() method of the SortedTaskList class.
   * 
   * Test scenarios: <BR>
   * 1. Test adding a task to an empty TaskList <BR>
   * 2. Test adding tasks to a non-mepty sorted task list such that the task should be added to the
   * beginning, middle, and end of a non-empty TaskList.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testAddToSortedTaskList() {

    Task.setSortingOrderByTitle();

    SortedTaskList testTaskList = new SortedTaskList();

    Task task1 = new Task("task1", "jngf;mn;43", true);
    Task task2 = new Task("task2", "jngf;mn;43", true);
    Task task3 = new Task("task3", "jngf;mn;43", false);
    Task task4 = new Task("task4", "jngf;mn;43", false);
    Task task5 = new Task("task5", "jngf;mn;43", true);


    try {

      // adding to emptylist
      testTaskList.add(task3);

      {
        int expectedSize = 1;
        int actualSize = testTaskList.size();

        if (expectedSize != actualSize) {
          return false;
        }


        Task expected0 = task3;
        Task actual0 = testTaskList.get(0);

        if (expected0 != actual0) {
          return false;
        }

      }

      // adding to start of non-empty tasklist
      testTaskList.add(task1);

      {
        int expectedSize = 2;
        int actualSize = testTaskList.size();

        if (expectedSize != actualSize) {

          return false;
        }

        Task expected0 = task1;
        Task actual0 = testTaskList.get(0);

        Task expected1 = task3;
        Task actual1 = testTaskList.get(1);


        if (expected0 != actual0) {
          return false;
        }


        if (expected1 != actual1) {
          return false;
        }

      }

      // adding to end of non empty tasklist
      testTaskList.add(task5);

      {
        int expectedSize = 3;
        int actualSize = testTaskList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        Task expected0 = task1;
        Task actual0 = testTaskList.get(0);

        Task expected1 = task3;
        Task actual1 = testTaskList.get(1);

        Task expected2 = task5;
        Task actual2 = testTaskList.get(2);

        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }

        if (expected2 != actual2) {
          return false;
        }

      }



      // adding to middle of non empty tasklist.

      testTaskList.add(task2);

      testTaskList.add(task4);


      {
        int expectedSize = 5;
        int actualSize = testTaskList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        Task expected0 = task1;
        Task actual0 = testTaskList.get(0);

        Task expected1 = task2;
        Task actual1 = testTaskList.get(1);

        Task expected2 = task3;
        Task actual2 = testTaskList.get(2);

        Task expected3 = task4;
        Task actual3 = testTaskList.get(3);

        Task expected4 = task5;
        Task actual4 = testTaskList.get(4);

        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }

        if (expected2 != actual2) {
          return false;
        }

        if (expected3 != actual3) {
          return false;
        }

        if (expected4 != actual4) {
          return false;
        }

      }

    }

    catch (Exception e) {
      System.out.print(e.getMessage());
      return false;
    }

    return true;
  }

  /**
   * Tests the appendTask() method of the TaskManager class.
   * 
   * Test scenarios: <BR>
   * 1. Test appending a task to an empty TaskManager. <BR>
   * 2. Test appending multiple tasks to the TaskManager.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testAppendTask() {


    TaskManager testManager = new TaskManager();

    Task task1 = new Task("task1", "jngf;mn;43", true);
    Task task2 = new Task("task2", "jngf;mn;43", true);
    Task task3 = new Task("task3", "jngf;mn;43", false);

    // Test appending a task to an empty TaskManager
    testManager.appendTask(task1);

    try {
      {
        int expectedSize = 1;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        Task expected0 = task1;
        Task actual0 = testManager.toDoList.get(0);

        if (expected0 != actual0) {
          return false;
        }

      }



      // Test appending multiple tasks to the TaskManager
      testManager.appendTask(task3);
      testManager.appendTask(task2);

      {
        int expectedSize = 3;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        Task expected0 = task1;
        Task actual0 = testManager.toDoList.get(0);

        Task expected1 = task3;
        Task actual1 = testManager.toDoList.get(1);

        Task expected2 = task2;
        Task actual2 = testManager.toDoList.get(2);

        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }

        if (expected2 != actual2) {
          return false;
        }

      }

    }

    catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    return true;
  }

  /**
   * Tests the moveToTop(), moveToBottom(), moveBeforeOtherTask(), and moveAfterOtherTask() methods
   * of the TaskManager class.
   *
   * Test scenarios: <BR>
   * 1. Test moving tasks to various positions in the toDoList and completedTasks lists. <BR>
   * 2. Test moving tasks relative to other tasks (before/after).
   * 
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testMoveTasks() {

    TaskManager testManager = new TaskManager();

    Task task1 = new Task("task1", "jngf;mn;43", true);
    Task task2 = new Task("task2", "jngf;mn;43", true);
    Task task3 = new Task("task3", "jngf;mn;43", false);

    testManager.appendTask(task1);
    testManager.appendTask(task2);

    try {
      // trying to move a invalid index
      {
        Task expected0 = testManager.toDoList.get(0);
        Task expected1 = testManager.toDoList.get(1);

        try {
          testManager.movetoTop(2);
          return false;
        }

        catch (IndexOutOfBoundsException e) {

        }

        catch (Exception e) {
          return false;
        }


        int expectedSize = 2;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        Task actual0 = testManager.toDoList.get(0);
        Task actual1 = testManager.toDoList.get(1);


        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }
      }



      // trying to move a valid index
      {

        Task expected0 = testManager.toDoList.get(1);
        Task expected1 = testManager.toDoList.get(0);

        testManager.movetoTop(1);

        int expectedSize = 2;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        if (expectedSize != actualSize) {
          return false;
        }

        Task actual0 = testManager.toDoList.get(0);
        Task actual1 = testManager.toDoList.get(1);


        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }

      }

      // trying to move a invalid index
      {
        Task expected0 = testManager.toDoList.get(0);
        Task expected1 = testManager.toDoList.get(1);

        try {
          testManager.moveToBottom(2);
          return false;
        }

        catch (IndexOutOfBoundsException e) {

        }

        catch (Exception e) {
          return false;
        }


        int expectedSize = 2;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        Task actual0 = testManager.toDoList.get(0);
        Task actual1 = testManager.toDoList.get(1);


        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }
      }



      testManager.appendTask(task3);

      // trying to move a valid index
      {
        Task expected0 = testManager.toDoList.get(1);
        Task expected1 = testManager.toDoList.get(2);
        Task expected2 = testManager.toDoList.get(0);

        testManager.moveToBottom(0);

        int expectedSize = 3;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        if (expectedSize != actualSize) {
          return false;
        }

        Task actual0 = testManager.toDoList.get(0);
        Task actual1 = testManager.toDoList.get(1);
        Task actual2 = testManager.toDoList.get(2);


        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }

        if (expected2 != actual2) {
          return false;
        }
      }


      {
        Task expected0 = testManager.toDoList.get(2);
        Task expected1 = testManager.toDoList.get(0);
        Task expected2 = testManager.toDoList.get(1);

        testManager.moveBeforeOtherTask(2, 0);

        int expectedSize = 3;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        Task actual0 = testManager.toDoList.get(0);
        Task actual1 = testManager.toDoList.get(1);
        Task actual2 = testManager.toDoList.get(2);


        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }

        if (expected2 != actual2) {
          return false;
        }
      }


      {
        Task expected0 = testManager.toDoList.get(0);
        Task expected1 = testManager.toDoList.get(2);
        Task expected2 = testManager.toDoList.get(1);

        testManager.moveAfterOtherTask(2, 0);

        int expectedSize = 3;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        Task actual0 = testManager.toDoList.get(0);
        Task actual1 = testManager.toDoList.get(1);
        Task actual2 = testManager.toDoList.get(2);


        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }

        if (expected2 != actual2) {
          return false;
        }
      }
    }

    catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Tests the removeTask() method of the TaskManager class.
   * 
   * Test scenarios: <BR>
   * 1. Test removing tasks from various positions in the toDoList and completedTasks lists. <BR>
   * 2 Test removing tasks at invalid indices return false.
   *
   * 
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testRemoveTask() {

    // size pehle methods handle karte hai lekin contents as a whole nahi dekhte hai

    TaskManager testManager = new TaskManager();

    Task task1 = new Task("task1", "jngf;mn;43", true);
    Task task2 = new Task("task2", "jngf;mn;43", true);
    Task task3 = new Task("task3", "jngf;mn;43", false);

    testManager.appendTask(task1);
    testManager.appendTask(task2);
    testManager.appendTask(task3);


    try {
      // removes an invalid index
      {

        Task expected0 = testManager.toDoList.get(0);
        Task expected1 = testManager.toDoList.get(1);
        Task expected2 = testManager.toDoList.get(2);

        boolean expected = false;
        boolean actual = testManager.removeTask(3);

        if (expected != actual) {
          return false;
        }

        Task actual0 = testManager.toDoList.get(0);
        Task actual1 = testManager.toDoList.get(1);
        Task actual2 = testManager.toDoList.get(2);

        int expectedSize = 3;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }

        if (expected2 != actual2) {
          return false;
        }


      }

      // removes a valid index
      {
        Task expected0 = testManager.toDoList.get(0);
        Task expected1 = testManager.toDoList.get(2);

        boolean expected = true;
        boolean actual = testManager.removeTask(1);

        if (expected != actual) {
          return false;
        }

        int expectedSize = 2;
        int actualSize = testManager.toDoList.size();

        if (expectedSize != actualSize) {
          return false;
        }

        Task actual0 = testManager.toDoList.get(0);
        Task actual1 = testManager.toDoList.get(1);

        if (expected0 != actual0) {
          return false;
        }

        if (expected1 != actual1) {
          return false;
        }

      }

    }

    catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Tests the completeTask() method of the TaskManager class.
   * 
   * Test scenarios: <BR>
   * 1. Test completing tasks from various positions in the toDoList. <BR>
   * 2. Test completing tasks at invalid indices.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testCompleteTask() {

    // lets complete task3 (index 2)
    TaskManager testManager = new TaskManager();

    Task task1 = new Task("task1", "jngf;mn;43", true);
    Task task2 = new Task("task2", "jngf;mn;43", true);
    Task task3 = new Task("task3", "jngf;mn;43", false);
    Task task4 = new Task("task4", "jngf;mn;43", false);

    testManager.appendTask(task1);
    testManager.appendTask(task2);
    testManager.appendTask(task3);
    testManager.appendTask(task4);

    // completing task from toDoList from invalid position .

    try {
      {
        int origToDoSize = testManager.toDoList.size();
        int origCompletedSize = testManager.completedTasks.size();

        boolean expected = false;
        boolean actual = testManager.completeTask(4);

        if (expected != actual) {
          return false;
        }


        // size should be updated.
        int newToDoSize = testManager.toDoList.size();
        int newCompletedSize = testManager.completedTasks.size();

        if (newToDoSize != origToDoSize) {
          return false;
        }

        if (origCompletedSize != newCompletedSize) {
          return false;
        }
      }


      // completing task from toDoList from middle position .
      {
        int origToDoSize = testManager.toDoList.size();
        int origCompletedSize = testManager.completedTasks.size();

        Task toBeRemoved = testManager.toDoList.get(2);
        boolean existOrNot = testManager.completedTasks.contains(toBeRemoved);

        boolean expected = true;
        boolean actual = testManager.completeTask(2);

        if (expected != actual) {
          return false;
        }

        // completed tasks should contain the task that is to be added.
        boolean containsOrNot = testManager.completedTasks.contains(toBeRemoved);

        if (!(existOrNot)) {
          if (!(containsOrNot)) {
            return false;
          }
        }

        // should be marked completed.
        if (!(toBeRemoved.isCompleted())) {
          return false;
        }

        // size should be updated.
        int newToDoSize = testManager.toDoList.size();
        int newCompletedSize = testManager.completedTasks.size();

        if (newToDoSize != origToDoSize - 1) {
          return false;
        }

        if (origCompletedSize != newCompletedSize - 1) {
          return false;
        }

        // do the completed tasks actually contain(first or last)
        if (!((testManager.completedTasks.get(0) == toBeRemoved) || (testManager.completedTasks
            .get(testManager.completedTasks.size() - 1) == toBeRemoved))) {
          return false;
        }
      }


      // completing task from toDoList from first position .
      {
        int origToDoSize = testManager.toDoList.size();
        int origCompletedSize = testManager.completedTasks.size();

        Task toBeRemoved = testManager.toDoList.get(0);
        boolean existOrNot = testManager.completedTasks.contains(toBeRemoved);

        boolean expected = true;
        boolean actual = testManager.completeTask(0);

        if (expected != actual) {
          return false;
        }

        // completed tasks should contain the task that is to be added. we can't say use the
        // contains
        // in toDoList, since there could be duplicate.
        boolean containsOrNot = testManager.completedTasks.contains(toBeRemoved);

        if (!(existOrNot)) {
          if (!(containsOrNot)) {
            return false;
          }
        }

        // should be marked completed.
        if (!(toBeRemoved.isCompleted())) {
          return false;
        }

        // size should be updated.
        int newToDoSize = testManager.toDoList.size();
        int newCompletedSize = testManager.completedTasks.size();

        if (newToDoSize != origToDoSize - 1) {
          return false;
        }

        if (origCompletedSize != newCompletedSize - 1) {
          return false;
        }

        // do the completed tasks actually contain(first or last)
        if (!((testManager.completedTasks.get(0) == toBeRemoved) || (testManager.completedTasks
            .get(testManager.completedTasks.size() - 1) == toBeRemoved))) {
          return false;
        }
      }


      // completing task from toDoList from last position .
      {
        int origToDoSize = testManager.toDoList.size();
        int origCompletedSize = testManager.completedTasks.size();

        Task toBeRemoved = testManager.toDoList.get(1);
        boolean existOrNot = testManager.completedTasks.contains(toBeRemoved);

        boolean expected = true;
        boolean actual = testManager.completeTask(1);

        if (expected != actual) {
          return false;
        }

        // completed tasks should contain the task that is to be added. we can't say use the
        // contains
        // in toDoList, since there could be duplicate.
        boolean containsOrNot = testManager.completedTasks.contains(toBeRemoved);

        if (!(existOrNot)) {
          if (!(containsOrNot)) {
            return false;
          }
        }

        // should be marked completed.
        if (!(toBeRemoved.isCompleted())) {
          return false;
        }

        // size should be updated.
        int newToDoSize = testManager.toDoList.size();
        int newCompletedSize = testManager.completedTasks.size();

        if (newToDoSize != origToDoSize - 1) {
          return false;
        }

        if (origCompletedSize != newCompletedSize - 1) {
          return false;
        }

        // do the completed tasks actually contain(first or last)
        if (!((testManager.completedTasks.get(0) == toBeRemoved) || (testManager.completedTasks
            .get(testManager.completedTasks.size() - 1) == toBeRemoved))) {
          return false;
        }
      }

    }

    catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testTaskCompareTo(): " + testTaskCompareTo());
    System.out.println("testTaskEquals(): " + testTaskEquals());
    System.out.println("testAddIsEmptySize(): " + testAddIsEmptySize());
    System.out.println("testAddToTaskList(): " + testAddToTaskList());
    System.out.println("testRemoveClear(): " + testRemoveClear());
    System.out.println("testAddToSortedTaskList(): " + testAddToSortedTaskList());
    System.out.println("testAppendTask(): " + testAppendTask());
    System.out.println("testMoveTasks(): " + testMoveTasks());
    System.out.println("testRemoveTask(): " + testRemoveTask());
    System.out.println("testCompleteTask(): " + testCompleteTask());
  }
}
