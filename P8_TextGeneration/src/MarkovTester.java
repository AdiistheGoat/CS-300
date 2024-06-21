//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Markov Tester
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


import java.util.ArrayList;

/**
 * The following class tests the methods of MarkovModel
 */
public class MarkovTester {


  // doubt, can we like use .peek method here without testing it

  // we have to verify that some small methods( .isEmpty() , .getList() , and .peek() ) work during
  // checking if some majority method works. For queues, we have some repetition in .isEmpty() and
  // .size()

  /**
   * verifies that adding things to the stack correctly increases the stack’s size, and that the
   * ordering of all elements is correct.
   * 
   * @return
   */
  public static boolean testStackAdd() {
    // tests .push() method
    // tests .isEmpty() , .getList() , and .peek() in addition

    MyStack<String> stack = new MyStack<String>();

    stack.push("lol");

    if ((!stack.peek().equals("lol")) || stack.isEmpty() || stack.getList().size() != 1) {
      return false;
    }

    if (!(stack.getList().get(0).equals("lol"))) {
      return false;
    }


    stack.push("meow");

    if ((!stack.peek().equals("meow")) || stack.isEmpty() || stack.getList().size() != 2) {
      return false;
    }

    if (!(stack.getList().get(0).equals("meow"))) {
      return false;
    }

    if (!(stack.getList().get(1).equals("lol"))) {
      return false;
    }


    stack.push("heyy");

    if ((!stack.peek().equals("heyy")) || stack.isEmpty() || stack.getList().size() != 3) {
      return false;
    }

    if (!(stack.getList().get(0).equals("heyy"))) {
      return false;
    }

    if (!(stack.getList().get(1).equals("meow"))) {
      return false;
    }

    if (!(stack.getList().get(2).equals("lol"))) {
      return false;
    }

    return true;
  }

  /**
   * verifies that removing things from the stack (after adding them) correctly decreases the
   * stack’s size, and that the ordering of all remaining elements is correct. Additionally, verify
   * that a stack that has had elements added to it can become empty again later.
   * 
   * @return
   */
  public static boolean testStackRemove() {
    // tests .pop() method
    // tests .isEmpty() , .getList() , and .peek() in addition

    MyStack<String> stack = new MyStack<String>();
    stack.push("lol");
    stack.push("meow");
    stack.push("heyy");

    if ((!(stack.peek().equals("heyy"))) || stack.isEmpty() || stack.getList().size() != 3) {
      return false;
    }

    if (!(stack.getList().get(0).equals("heyy"))) {
      return false;
    }

    if (!(stack.getList().get(1).equals("meow"))) {
      return false;
    }

    if (!(stack.getList().get(2).equals("lol"))) {
      return false;
    }


    {
      String returnedValue = stack.pop();

      if ((!(returnedValue.equals("heyy"))) || stack.isEmpty() || stack.getList().size() != 2) {
        return false;
      }

      if (!(stack.getList().get(0).equals("meow"))) {
        return false;
      }

      if (!(stack.getList().get(1).equals("lol"))) {
        return false;
      }
    }


    {

      String returnedValue = stack.pop();

      if ((!(returnedValue.equals("meow"))) || stack.isEmpty() || stack.getList().size() != 1) {
        return false;
      }


      if (!(stack.getList().get(0).equals("lol"))) {
        return false;
      }


    }

    {

      String returnedValue = stack.pop();

      if ((!(returnedValue.equals("lol"))) || (!stack.isEmpty()) || stack.getList().size() != 0) {
        return false;
      }

    }

    return true;
  }


  /**
   * verifies that calling shuffle() on the stack results in a stack that still contains all of the
   * same elements, but in any order that is different from the original order.
   * 
   * @return
   */
  public static boolean testStackShuffle() {

    try {
      MyStack<String> stack = new MyStack<String>();
      stack.push("lol");
      stack.push("meow");
      stack.push("heyy");
      stack.push("goyle");
      stack.push("lauda");

      ArrayList<String> origList = stack.getList();
      stack.shuffle();
      ArrayList<String> shuffleList = stack.getList();

      if (origList.equals(shuffleList)) { // checks whether the contents are equal
        return false;
      }

      if (origList.size() != shuffleList.size()) { // if not, then checks if size is maintained
        return false;
      }
    }

    catch (Exception e) {
      return false;
    }
    return true;
  }


  /**
   * verifies that adding things to the queue correctly increases the queue’s size, and that the
   * ordering of all elements is correct.
   * 
   * @return
   */
  public static boolean testQueueAdd() {

    // tests .enqueue() method
    // tests .isEmpty() , .getList() , .peek() , and .size() in addition

    try {
      MyQueue<String> queue = new MyQueue<String>();

      queue.enqueue("lol");

      if ((!(queue.peek().equals("lol"))) || queue.isEmpty() || queue.size() != 1) {

        return false;
      }

      if (!(queue.getList().get(0).equals("lol"))) {
        return false;
      }

      queue.enqueue("meow");

      if ((!queue.peek().equals("lol")) || queue.isEmpty() || queue.size() != 2) {
        return false;
      }

      if (!(queue.getList().get(0).equals("lol"))) {
        return false;
      }

      if (!(queue.getList().get(1).equals("meow"))) {
        return false;
      }


      queue.enqueue("heyy");

      if ((!queue.peek().equals("lol")) || queue.isEmpty() || queue.getList().size() != 3) {
        return false;
      }

      if (!(queue.getList().get(0).equals("lol"))) {
        return false;
      }

      if (!(queue.getList().get(1).equals("meow"))) {
        return false;
      }

      if (!(queue.getList().get(2).equals("heyy"))) {
        return false;
      }
    }

    catch (Exception e) {
      return false;
    }
    return true;
  }


  // Doubt
  // didn't need to test if added correctly since already tested that the enqueue method works and
  // addition in general works.- correct


  /**
   * verifies that removing things from the queue (after adding them) correctly decreases the
   * queue’s size, and that the ordering of all remaining elements is correct. This test should also
   * verify that the custom method maintainSize(int) works as described.
   * 
   * @return
   */
  public static boolean testQueueRemove() {

    // tests .dequeue() method
    // testing . enqueue method in the starting , but then not testing it later on. we don't need to
    // test it , right?? since we already have tested it.
    // tests .isEmpty() , .getList() , .peek() in addition

    try {

      MyQueue<String> queue = new MyQueue<String>();
      queue.enqueue("lol");
      queue.enqueue("meow");
      queue.enqueue("heyy");


      // testing whether it was added correctly
      if ((!(queue.peek().equals("lol"))) || queue.isEmpty() || queue.size() != 3
          || queue.getList().size() != queue.size()) {
        return false;
      }

      if (!(queue.getList().get(0).equals("lol"))) {
        return false;
      }

      if (!(queue.getList().get(1).equals("meow"))) {
        return false;
      }

      if (!(queue.getList().get(2).equals("heyy"))) {
        return false;
      }


      {
        String returnedValue = queue.dequeue();

        if ((!(returnedValue.equals("lol"))) || queue.isEmpty() || queue.size() != 2
            || queue.getList().size() != queue.size()) {
          return false;
        }

        if (!(queue.getList().get(0).equals("meow"))) {
          return false;
        }

        if (!(queue.getList().get(1).equals("heyy"))) {
          return false;
        }
      }

      {

        String returnedValue = queue.dequeue();

        if ((!(returnedValue.equals("meow"))) || queue.isEmpty() || queue.size() != 1
            || queue.getList().size() != queue.size()) {
          return false;
        }


        if (!(queue.getList().get(0).equals("heyy"))) {
          return false;
        }

      }

      {

        String returnedValue = queue.dequeue();

        if ((!(returnedValue.equals("heyy"))) || (!queue.isEmpty()) || queue.size() != 0
            || queue.getList().size() != queue.size()) {
          return false;
        }
      }
    }

    catch (Exception e) {
      return false;
    }



    // tests maintainSize
    // tests .isEmpty() , .getList() , .peek() , and .toString() in addition

    try {
      MyQueue<String> queue = new MyQueue<String>();
      queue.enqueue("lol");
      queue.enqueue("meow");
      queue.enqueue("heyy");
      queue.enqueue("badger");
      queue.enqueue("pride");

      queue.maintainSize(2);

      if (queue.getList().size() != queue.size() || queue.size() != 2 || queue.isEmpty()) {
        return false;
      }


      if ((!queue.peek().equals("badger")) || (!queue.getList().get(0).equals("badger"))
          || (!queue.getList().get(1).equals("pride"))) {
        return false;
      }

      if ((!queue.toString().equals("badgerpride"))) {
        return false;
      }
    }


    catch (Exception e) {
      return false;
    }

    return true;

  }


  /**
   * verifies that calling peek() on both a stack and a queue returns the correct element and does
   * not make any modifications to the data structure.
   * 
   * @return
   */
  public static boolean testPeek() {


    try {
      {
        MyStack<String> stack = new MyStack<String>();

        stack.push("lol");
        stack.push("meow");
        stack.push("heyy");

        String peekedValue = stack.peek();

        if (!(peekedValue.equals("heyy"))) {
          return false;
        }


        // we know .isEmpty works, so this is kind of repetitive, right??

        if (stack.isEmpty() || stack.getList().size() != 3) {
          return false;
        }


        if (!(stack.getList().get(0).equals("heyy"))) {
          return false;
        }

        if (!(stack.getList().get(1).equals("meow"))) {
          return false;
        }

        if (!(stack.getList().get(2).equals("lol"))) {
          return false;
        }
      }

      {
        MyQueue<String> queue = new MyQueue<String>();

        queue.enqueue("lol");
        queue.enqueue("meow");
        queue.enqueue("heyy");

        String peekedValue = queue.peek();

        if (!(peekedValue.equals("lol"))) {
          return false;
        }


        // we know .isEmpty works, so this is kind of repetitive, right??

        // if we had used just .toString and .size as ways to determine, that wouldn't be enough, we
        // have to see it for it indivisually as well to cover each case but if we see it
        // indivisually and size , thats enough. If.toString returns false while the other two
        // return true, and the other two have been proved right to work, the .istostring is for
        // sure faulty.

        if (queue.isEmpty() || queue.size() != 3 || queue.getList().size() != queue.size()) {
          return false;
        }


        if (!(queue.getList().get(0).equals("lol"))) {
          return false;
        }

        if (!(queue.getList().get(1).equals("meow"))) {
          return false;
        }

        if (!(queue.getList().get(2).equals("heyy"))) {
          return false;
        }
      }

    }

    catch (Exception e) {
      return false;
    }

    return true;
  }


  public static void main(String[] args) {
    System.out.println(" testStackAdd()       Expected: true         Actual: " + testStackAdd());
    System.out.println(" testStackRemove()    Expected: true         Actual: " + testStackRemove());
    System.out
        .println(" testStackShuffle()   Expected: true         Actual: " + testStackShuffle());
    System.out.println(" testQueueAdd()       Expected: true         Actual: " + testQueueAdd());
    System.out.println(" testQueueRemove()    Expected: true         Actual: " + testQueueRemove());
    System.out.println(" testPeek()           Expected: true         Actual: " + testPeek());
  }


}



// do private methods for checking no of elements

