//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MyQueue
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
 * A generic singly-linked queue implementation, which contains some additional methods to
 * facilitate the workings of the Markov Model.
 * 
 * @param <T> - the type of data contained in the queue
 */
public class MyQueue<T> implements QueueADT<T>

{

  private LinkedNode<T> back; // A reference to the LinkedNode currently at the back of the queue,
                              // which contains the most-recently added value in the queue.

  private LinkedNode<T> front; // A reference to the LinkedNode currently at the front of the queue,
                               // which contains the least-recently added value in the queue.

  private int size; // The number of values currently present in the queue


  /**
   * Enforces a maximum size for this queue. If the queue is already smaller than the requested
   * size, this method does nothing.
   * 
   * @param size - the maximum number of elements this queue should contain once the method has run
   */
  public void maintainSize(int size) {
    while (size < this.size()) {
      dequeue();
    }
  }

  /**
   * Creates a copy of the current contents of this queue in the order they are present here, in
   * ArrayList form. This method should traverse the queue without removing any elements, and add
   * the values (not the nodes!) to an ArrayList in the order they appear in the queue.
   * 
   * @return an ArrayList representation of the current state of this queue
   */
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<T>();
    LinkedNode<T> tempNode = front;
    for (int i = 0; i < this.size(); i++) {
      list.add(tempNode.getData());
      tempNode = tempNode.getNext();
    }
    return list;
  }

  /**
   * Concatenates the string representation of all values in this queue in order, from the front of
   * the queue to the back. Does not separate values (no whitespace, no commas).
   * 
   * @return the string representation of this queue
   */
  public String toString() {
    String representation = "";
    ArrayList<T> allNodes = this.getList();
    for (int i = 0; i < this.size(); i++) {
      representation += "" + allNodes.get(i);
    }
    return representation;
  }


  /**
   * Add a new element to the back of the queue, assumed to be non-null.
   * 
   * @param value - the value to add
   */
  public void enqueue(T value) {
    if (isEmpty()) {
      LinkedNode<T> newBack = new LinkedNode(value, null);
      front = newBack;
      back = newBack;
    }

    else {
      LinkedNode<T> newBack = new LinkedNode(value, null);
      back.setNext(newBack);
      back = newBack;
    }

    size++;
  }

  /**
   * Removes and returns the value added to this queue least recently
   * 
   * @return the least recently-added value, or null if the queue is empty
   */
  public T dequeue() {
    if (isEmpty()) {
      return null;
    }

    else {
      LinkedNode<T> tempFront = front;
      front = front.getNext();
      tempFront.setNext(null);
      size--;
      return tempFront.getData();
    }
  }

  /**
   * Accesses the value added to this queue least recently, without modifying the queue
   * 
   * @return the least recently-added value, or null if the queue is empty
   */
  public T peek() {
    if (isEmpty()) {
      return null;
    }

    else {
      return front.getData();
    }
  }


  /**
   * Returns true if this queue contains no elements.
   * 
   * @return true if the queue contains no elements, false otherwise
   */
  public boolean isEmpty() {
    if ((this.size() == 0) && (front == null)) {
      return true;
    }
    return false;
  }

  /**
   * Returns the number of elements in the queue.
   * 
   * @return the number of elements in the queue
   */
  public int size() {
    return this.size;
  }


}
