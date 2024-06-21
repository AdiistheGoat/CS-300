//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task Queue Tester
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

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A suite of tester methods to check the correctness of various methods for the Prioritized Task
 * Manager assignment.
 */
public class TaskQueueTester {


  // Is the general logic to build test cases is to keep 1 factor constant and other factors
  // variable???

  /**
   * Tests the correctness of a Task compareTo() method implementation when the criteria parameter
   * is TIME.
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testCompareToTime() {

    Task task1 = new Task("Task1", "Task1", 1, PriorityLevel.LOW);
    Task task2 = new Task("Task2", "Task1", 2, PriorityLevel.LOW);
    Task task3 = new Task("Task1", "Task2", 3, PriorityLevel.LOW);
    Task task4 = new Task("Task1", "Task1", 4, PriorityLevel.HIGH);

    if (task1.compareTo(task2, CompareCriteria.TIME) >= 0) {
      return false;
    }

    if (task1.compareTo(task3, CompareCriteria.TIME) >= 0) {
      return false;
    }

    if (task1.compareTo(task4, CompareCriteria.TIME) >= 0) {
      return false;
    }

    if (task1.compareTo(task1, CompareCriteria.TIME) != 0) {
      return false;
    }

    return true;

  }

  /**
   * Tests the correctness of a Task compareTo() method implementation when the criteria parameter
   * is TITLE.
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testCompareToTitle() {

    Task task1 = new Task("ask1", "Task1", 1, PriorityLevel.LOW);
    Task task2 = new Task("Ask2", "Task1", 1, PriorityLevel.LOW);
    Task task3 = new Task("ask3", "Task2", 3, PriorityLevel.LOW);
    Task task4 = new Task("Task4", "Task1", 1, PriorityLevel.HIGH);
    Task task5 = new Task("xhinj", "iono", 0, PriorityLevel.URGENT);


    if (task1.compareTo(task2, CompareCriteria.TITLE) >= 0) {
      return false;
    }

    if (task1.compareTo(task3, CompareCriteria.TITLE) <= 0) {
      return false;
    }

    if (task1.compareTo(task4, CompareCriteria.TITLE) >= 0) {
      return false;
    }

    if (task1.compareTo(task5, CompareCriteria.TITLE) <= 0) {
      return false;
    }

    if (task1.compareTo(task1, CompareCriteria.TITLE) != 0) {
      return false;
    }

    return true;

  }

  /**
   * Tests the correctness of a Task compareTo() method implementation when the criteria parameter
   * is LEVEL.
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testCompareToLevel() {
    Task task1 = new Task("ask1", "Task1", 2, PriorityLevel.MEDIUM);

    Task task2 = new Task("ask1", "Task1", 45, PriorityLevel.LOW);

    Task task3 = new Task("ask1", "LJFEL", 2, PriorityLevel.HIGH);

    Task task4 = new Task("fj4ef", "Task1", 2, PriorityLevel.LOW);

    Task task5 = new Task("heeo;9f", "nd;eio2", 32, PriorityLevel.URGENT);


    if (task1.compareTo(task2, CompareCriteria.LEVEL) <= 0) {
      return false;
    }

    if (task1.compareTo(task3, CompareCriteria.LEVEL) >= 0) {
      return false;
    }

    if (task1.compareTo(task4, CompareCriteria.LEVEL) <= 0) {
      return false;
    }

    if (task1.compareTo(task5, CompareCriteria.LEVEL) >= 0) {
      return false;
    }

    if (task1.compareTo(task1, CompareCriteria.LEVEL) != 0) {
      return false;
    }

    return true;
  }

  // size and elements

  // does it throw exceptions appropriately
  // does it change the size (testing .size() ) (.IsEmpty() as well)
  // is the old array equal to the new array (should not be) (testing getHeapData() as
  // well(partially))
  // if .peekBest returns the appropriate value. (tests .peekBest )(no need since testing it later)

  // testing each element is correct....(tests get HeapData completely) (need this step later as
  // well cause elements are one of main criteria)..



  // getHeapData used as testing in some methods and in some methods to replace checking element by
  // element (since there should ideally be no change after implementing that method).
  // once its been like twice tested, no need to test it again.

  // check out Nimit's advice cause may lessen the no of lines of code.

  /**
   * Tests the correctness of a TaskQueue enqueue() method implementation including exceptions and
   * edge cases (if applicable).
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testEnqueue() {

    TaskQueue taskQueue = new TaskQueue(6, CompareCriteria.TIME);

    Task Task1 = new Task("Task1", "enqueSetup1", 3, PriorityLevel.LOW);
    Task Task2 = new Task("Task2", "enqueSetup2", 2, PriorityLevel.HIGH);
    Task Task3 = new Task("Task3", "enqueSetup3", 7, PriorityLevel.MEDIUM);
    Task Task4 = new Task("Task4", "enqueSetup4", 10, PriorityLevel.MEDIUM);
    Task Task5 = new Task("Task5", "enqueSetup4", 5, PriorityLevel.URGENT);
    Task Task6 = new Task("Task6", "enqueSetup4", 7, PriorityLevel.LOW);

    // adding the first (non-compelted) task to the priority queue.
    {
      Task[] oldArray = taskQueue.getHeapData();

      taskQueue.enqueue(Task1);

      if (taskQueue.size() != 1) {
        return false;
      }

      if (taskQueue.isEmpty()) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();

      if (Arrays.deepEquals(oldArray, newArray)) {
        return false;
      }

      if (newArray[0] != Task1) {
        return false;
      }
    }


    // testing if it adds a completed Task in the priority queue.
    {
      Task[] oldArray = taskQueue.getHeapData();

      Task completedTask = new Task("Task1", "completedTask", 5, PriorityLevel.LOW);
      completedTask.markCompleted();

      try {
        taskQueue.enqueue(completedTask);
        return false;
      }

      catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }

      if (taskQueue.size() != 1) {
        return false;
      }

      if (taskQueue.isEmpty()) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();

      if (!(Arrays.deepEquals(oldArray, newArray))) {
        return false;
      }
    }


    // adding the second (non-compelted) task to the priority queue.
    {

      taskQueue.enqueue(Task2);

      if (taskQueue.size() != 2) {
        return false;
      }

      if (taskQueue.isEmpty()) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();

      if (newArray[0] != Task1) {
        return false;
      }

      if (newArray[1] != Task2) {
        return false;
      }
    }



    // adding the third (non-compelted) task to the priority queue.
    {
      taskQueue.enqueue(Task3);

      if (taskQueue.size() != 3) {
        return false;
      }

      if (taskQueue.isEmpty()) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();

      if (newArray[0] != Task3) {
        return false;
      }

      if (newArray[1] != Task2) {
        return false;
      }

      if (newArray[2] != Task1) {
        return false;
      }
    }


    // adding the fourth (non-compelted) task to the priority queue.
    {
      taskQueue.enqueue(Task4);

      if (taskQueue.size() != 4) {
        return false;
      }

      if (taskQueue.isEmpty()) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();

      if (newArray[0] != Task4) {
        return false;
      }

      if (newArray[1] != Task3) {
        return false;
      }

      if (newArray[2] != Task1) {
        return false;
      }

      if (newArray[3] != Task2) {
        return false;
      }
    }


    // adding the fifth (non-compelted) task to the priority queue.
    {
      Task[] oldArray = taskQueue.getHeapData();

      taskQueue.enqueue(Task5);

      if (taskQueue.size() != 5) {
        return false;
      }

      if (taskQueue.isEmpty()) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();

      if (Arrays.deepEquals(oldArray, newArray)) {
        return false;
      }

      if (newArray[0] != Task4) {
        return false;
      }

      if (newArray[1] != Task3) {
        return false;
      }

      if (newArray[2] != Task1) {
        return false;
      }

      if (newArray[3] != Task2) {
        return false;
      }

      if (newArray[4] != Task5) {
        return false;
      }
    }



    // adding the sixth (non-compelted) task to the priority queue.
    {
      Task[] oldArray = taskQueue.getHeapData();

      taskQueue.enqueue(Task6);

      if (taskQueue.size() != 6) {
        return false;
      }

      if (taskQueue.isEmpty()) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();

      if (Arrays.deepEquals(oldArray, newArray)) {
        return false;
      }

      if (newArray[0] != Task4) {
        return false;
      }

      if (newArray[1] != Task3) {
        return false;
      }

      if (newArray[2] != Task6) {
        return false;
      }

      if (newArray[3] != Task2) {
        return false;
      }

      if (newArray[4] != Task5) {
        return false;
      }

      if (newArray[5] != Task1) {
        return false;
      }
    }



    // trying to add a task when the priority queue is full
    {
      Task[] oldArray = taskQueue.getHeapData();

      Task enqueTask = new Task("Task2", "enqueTask ", 7, PriorityLevel.LOW);

      try {
        taskQueue.enqueue(enqueTask);
        return false;
      }

      catch (IllegalStateException e) {
        System.out.println(e.getMessage());
      }

      if (taskQueue.size() != 6) {
        return false;
      }

      if (taskQueue.isEmpty()) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();

      if (!(Arrays.deepEquals(oldArray, newArray))) {
        return false;
      }
      // no need to check for element to element since there will be no change, and we can easily
      // figure that out using Arrays.deepEquals()
    }

    return true;
  }

  // Enqueued already checked.
  // .IsEmpty already checked.
  // .getHeapData already checked.
  // .peekBest already checked. .peek is being tested everytime dequeue is called.

  // does it throw exceptions appropriately
  // does it change the size (check .size() )
  // is the old array equal to the new array (we can put in some methods(which do not cause any
  // change) to avoid checking for every element)
  // testing each element is correct...

  // Dequeue from array with one element
  // Dequeue from array with lot of elements

  /**
   * Tests the correctness of a TaskQueue dequeue() method implementation including exceptions and
   * edge cases (if applicable).
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testDequeue() {

    TaskQueue taskQueue = new TaskQueue(6, CompareCriteria.TIME);

    Task Task1 = new Task("Task1", "dequeSetup1", 3, PriorityLevel.LOW);
    Task Task2 = new Task("Task2", "dequeSetup2", 2, PriorityLevel.HIGH);
    Task Task3 = new Task("Task3", "dequeSetup3", 7, PriorityLevel.MEDIUM);
    Task Task4 = new Task("Task4", "dequeSetup4", 10, PriorityLevel.MEDIUM);
    Task Task5 = new Task("Task5", "dequeSetup5", 5, PriorityLevel.URGENT);
    Task Task6 = new Task("Task6", "dequeSetup6", 8, PriorityLevel.LOW);

    // dequeing from an empty priority queue.
    {
      Task[] oldArray = taskQueue.getHeapData();
      try {
        taskQueue.dequeue();
        return false;
      }

      catch (NoSuchElementException e) {
        System.out.println(e.getMessage());
      }


      Task[] newArray = taskQueue.getHeapData();

      if (taskQueue.size() != 0) {
        return false;
      }

      if (!(Arrays.deepEquals(oldArray, newArray))) {
        return false;
      }
    }


    // dequeing from a priority queue with one element
    {
      Task[] oldArray = taskQueue.getHeapData();

      taskQueue.enqueue(Task6);

      Task actual = taskQueue.dequeue();
      Task expected = Task6;

      if (expected != actual) {
        return false;
      }

      if (taskQueue.size() != 0) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();

      if (!(Arrays.deepEquals(oldArray, newArray))) {
        return false;
      }

    }


    // dequeing element by element from a priority queue representing a 3 level binary tree.
    {
      taskQueue.enqueue(Task1);
      taskQueue.enqueue(Task2);
      taskQueue.enqueue(Task3);
      taskQueue.enqueue(Task4);
      taskQueue.enqueue(Task5);
      taskQueue.enqueue(Task6);

      // dequeing from a priority queue with 6 elements.
      {

        Task expected = Task4;
        Task actual = taskQueue.dequeue();

        if (expected != actual) {
          return false;
        }

        if (taskQueue.size() != 5) {
          return false;
        }

        Task[] newArray = taskQueue.getHeapData();

        if (newArray[0] != Task6) {
          return false;
        }

        if (newArray[1] != Task3) {
          return false;
        }

        if (newArray[2] != Task1) {
          return false;
        }

        if (newArray[3] != Task2) {
          return false;
        }

        if (newArray[4] != Task5) {
          return false;
        }

      }

      // dequeing from a priority queue with 5 elements.
      {
        Task actual = taskQueue.dequeue();
        Task expected = Task6;

        if (expected != actual) {
          return false;
        }

        if (taskQueue.size() != 4) {
          return false;
        }

        Task[] newArray = taskQueue.getHeapData();


        if (newArray[0] != Task3) {
          return false;
        }

        // lol
        if (newArray[1] != Task5) {
          return false;
        }

        if (newArray[2] != Task1) {
          return false;
        }

        if (newArray[3] != Task2) {
          return false;
        }

      }

      // dequeing from a priority queue with 4 elements.
      {
        Task actual = taskQueue.dequeue();
        Task expected = Task3;

        if (expected != actual) {
          return false;
        }

        if (taskQueue.size() != 3) {
          return false;
        }

        Task[] newArray = taskQueue.getHeapData();


        if (newArray[0] != Task5) {
          return false;
        }

        if (newArray[1] != Task2) {
          return false;
        }

        if (newArray[2] != Task1) {
          return false;
        }

      }

      // dequeing from a priority queue with 3 elements.
      {
        Task actual = taskQueue.dequeue();
        Task expected = Task5;

        if (expected != actual) {
          return false;
        }

        if (taskQueue.size() != 2) {
          return false;
        }

        Task[] newArray = taskQueue.getHeapData();


        if (newArray[0] != Task1) {
          return false;
        }

        if (newArray[1] != Task2) {
          return false;
        }

      }


      // dequeing from a priority queue with 2 elements.
      {
        Task actual = taskQueue.dequeue();
        Task expected = Task1;

        if (expected != actual) {
          return false;
        }

        if (taskQueue.size() != 1) {
          return false;
        }

        Task[] newArray = taskQueue.getHeapData();


        if (newArray[0] != Task2) {
          return false;
        }
      }
    }
    return true;
  }


  // peek doesn't change size,the elements themselves(can use.deepEquals), and returns correct
  // element
  // .Enqueue and .Dequeue confirm works
  /**
   * Tests the correctness of a TaskQueue peek() method implementation including exceptions and edge
   * cases (if applicable).
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */
  public static boolean testPeek() {
    // 7,2,3

    TaskQueue taskQueue = new TaskQueue(3, CompareCriteria.TIME);

    Task Task1 = new Task("Task1", "dequeSetup1", 3, PriorityLevel.LOW);
    Task Task2 = new Task("Task2", "dequeSetup2", 2, PriorityLevel.HIGH);
    Task Task3 = new Task("Task3", "dequeSetup3", 7, PriorityLevel.MEDIUM);

    taskQueue.enqueue(Task1);
    taskQueue.enqueue(Task2);
    taskQueue.enqueue(Task3);

    // calling .peekBest from a priority queue with 3 elements
    {
      Task[] oldArray = taskQueue.getHeapData();

      Task actual = taskQueue.peekBest();
      Task expected = Task3;

      if (expected != actual) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();


      if (!(Arrays.deepEquals(oldArray, newArray))) {
        return false;
      }

      if (taskQueue.size() != 3) {
        return false;
      }
    }

    taskQueue.dequeue();

    // calling .peekBest from a priority queue with 2 elements
    {
      Task[] oldArray = taskQueue.getHeapData();

      Task actual = taskQueue.peekBest();
      Task expected = Task1;

      if (expected != actual) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();


      if (!(Arrays.deepEquals(oldArray, newArray))) {
        return false;
      }

      if (taskQueue.size() != 2) {
        return false;
      }
    }


    taskQueue.dequeue();

    // calling .peekBest from a priority queue with 2 elements
    {
      Task[] oldArray = taskQueue.getHeapData();

      Task actual = taskQueue.peekBest();
      Task expected = Task2;

      if (expected != actual) {
        return false;
      }

      Task[] newArray = taskQueue.getHeapData();


      if (!(Arrays.deepEquals(oldArray, newArray))) {
        return false;
      }

      if (taskQueue.size() != 1) {
        return false;
      }
    }


    taskQueue.dequeue();


    // calling .peekBest from an empty priority queue
    {

      Task[] oldArray = taskQueue.getHeapData();

      try {
        Task actual = taskQueue.peekBest();
        return false;
      }

      catch (NoSuchElementException e) {
        System.out.println(e.getMessage());
      }

      Task[] newArray = taskQueue.getHeapData();


      if (!(Arrays.deepEquals(oldArray, newArray))) {
        return false;
      }

      if (taskQueue.size() != 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tests the correctness of a TaskQueue testReprioritize() method implementation including
   * exceptions and edge cases (if applicable).
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   * 
   */

  // Factors to check:
  // the elements are as expected.
  // the size is as expected

  // .compareTo works
  // .enqueue works

  public static boolean testReprioritize() {

    TaskQueue taskQueue = new TaskQueue(6, CompareCriteria.TIME);

    Task Task1 = new Task("Task1", "dequeSetup1", 3, PriorityLevel.LOW);
    Task Task2 = new Task("Task2", "dequeSetup2", 2, PriorityLevel.HIGH);
    Task Task3 = new Task("Task3", "dequeSetup3", 7, PriorityLevel.MEDIUM);
    Task Task4 = new Task("Task4", "dequeSetup4", 10, PriorityLevel.URGENT);

    taskQueue.enqueue(Task1);
    taskQueue.enqueue(Task2);
    taskQueue.enqueue(Task3);
    taskQueue.enqueue(Task4);

    // 4,3,1,2
    // 4(U),2(High),1(L),3(M)


    // the task was already enqued with time criteria. if we enque those again, the values could be
    // different. That is why we compare one by one.(pls confirm). We have to build test cases in a
    // somewhat general manner and not example specific.

    // A very important thing to take note of is that you have to first build tester cases for
    // methods which are called in other methods. You have to confirm those work so that it is
    // easier for us to see where the problem is when building complex methods.(pls confirm)


    // testing if it reprioritizes correctly for the same ComparingCriteria.
    {
      Task[] oldArr = taskQueue.getHeapData();
      taskQueue.reprioritize(CompareCriteria.TIME);
      Task[] newArr = taskQueue.getHeapData();

      if (newArr[0] != Task4) {
        return false;
      }

      if (newArr[1] != Task3) {
        return false;
      }

      if (newArr[2] != Task1) {
        return false;
      }

      if (newArr[3] != Task2) {
        return false;
      }


      if (taskQueue.size() != 4) {
        return false;
      }
    }


    // testing if it reprioritizes correctly for a different
    // ComparingCriteria(ComparingCriteria.LEVEL).
    {
      taskQueue.reprioritize(CompareCriteria.LEVEL);

      Task[] newArr = taskQueue.getHeapData();

      if (taskQueue.size() != 4) {
        return false;
      }


      if (newArr[0] != Task4) {
        return false;
      }

      if (newArr[1] != Task2) {
        return false;
      }

      if (newArr[2] != Task1) {
        return false;
      }

      if (newArr[3] != Task3) {
        return false;
      }
    }
    return true;
  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out
        .println("testCompareToTime()          Expected: True    Actual: " + testCompareToTime());
    System.out
        .println("testCompareToTitle()         Expected: True    Actual: " + testCompareToTitle());
    System.out
        .println("testCompareToLevel()         Expected: True    Actual: " + testCompareToLevel());

    System.out.println("testEnqueue()                Expected: True    Actual: " + testEnqueue());

    System.out.println("testDequeue()                Expected: True    Actual: " + testDequeue());

    System.out.println("testPeek()                   Expected: True    Actual: " + testPeek());

    System.out
        .println("testReprioritize()           Expected: True    Actual: " + testReprioritize());


  }


}
