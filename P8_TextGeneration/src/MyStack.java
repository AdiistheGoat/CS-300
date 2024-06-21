//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MyStack
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
import java.util.Collections;

/**
 * A generic singly-linked stack implementation, which contains some additional methods to
 * facilitate the workings of the Markov Model. As such, this stack may NOT always strictly adhere
 * to the last-in-first-out protocols of standard stacks!
 * 
 * @param <T> - the type of data contained in the stack
 */
public class MyStack<T> implements StackADT<T> {


  private LinkedNode<T> top; // A reference to the LinkedNode currently at the top of the stack,
                             // which is null when the stack is empty.


  // cant we just initialize this to null. makes htings easier. actually
  // it would automaticually intiailie when clling th eocnstuctor. also
  // every task we are inputting in the methods is assumed to be
  // null...... aslo , if we like remvoe from an empty queue and od all
  // this stuff, the method has chosen not to return
  // excpetions but to just return null, which is the actual answer as
  // well sicne top will be null.


  /**
   * Creates a copy of the current contents of this stack in the order they are present here, in
   * ArrayList form. This method should traverse the stack without removing any elements, and add
   * the values (not the nodes!) to an ArrayList in the order they appear in the stack, with the top
   * of the stack at index 0.
   * 
   * @return an ArrayList representation of the current state of this stack
   */
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<T>();
    LinkedNode<T> tempNode = top;
    for (int i = 0; i < this.size(); i++) {
      list.add(tempNode.getData());
      tempNode = tempNode.getNext();
    }
    return list;
  } // checking for null is not needed is size method is correct


  /**
   * Randomly reorder the contents of this stack: Create an ArrayList representation of all of the
   * elements of this stack, in order Use Collections.shuffle() to create a new random ordering of
   * the contents REPLACE the current contents of the stack with the contents in their new order
   * from the ArrayList
   */
  public void shuffle() {
    ArrayList<T> list = this.getList();
    Collections.shuffle(list);

    while (!(this.isEmpty())) {
      this.pop();
    }

    for (int i = 0; i < list.size(); i++) {
      this.push(list.get(i));
    }

  }


  /**
   * Add a new element to the top of this stack, assumed to be non-null.
   * 
   * @param value - the value to add
   */
  public void push(T value) { // assuming value is null, so cant test for null cases in tester cases

    if (isEmpty()) {
      top = new LinkedNode(value, null);
    }

    else {
      LinkedNode newTop = new LinkedNode(value, top);
      top = newTop;
    }

  }


  /**
   * Removes and returns the value added to this stack most recently
   * 
   * @return the most recently-added value, or null if the stack is empty
   */
  public T pop() {

    if (isEmpty()) {
      return null;
    }

    else {
      LinkedNode<T> tempTop = top;
      top = top.getNext();
      tempTop.setNext(null);
      return tempTop.getData();
    }
  }

  /**
   * Accesses the value added to this stack most recently, without modifying the stack
   * 
   * @return the most recently-added value, or null if the stack is empty
   */
  public T peek() {
    if (isEmpty()) {
      return null;
    }

    else {
      return top.getData();
    }
  }


  /**
   * Returns true if this stack contains no elements.
   * 
   * @return true if the stack contains no elements, false otherwise
   */
  public boolean isEmpty() {
    return (top == null) && (this.size() == 0);
  }

  private int size() {
    int size = 0;
    LinkedNode<T> nextNode = top;
    while (nextNode != null) {
      size++;
      nextNode = nextNode.getNext();
    }
    return size;
  }



}
