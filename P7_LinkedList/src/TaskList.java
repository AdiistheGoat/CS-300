//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TaskList
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
 * This class models a list of Tasks. It implements the ListADT as a doubly linked list that stores
 * only elements of type Task.
 */
public class TaskList extends Object implements ListADT<Task> {

  private LinkedNode<Task> head = null; // Reference to the first node in this list (with respect to
  // the forward direction)

  private LinkedNode<Task> tail = null; // Total number of Task objects stored in this list

  private int size = 0; // Reference to the last node in this list (with respect to the forward
  // direction)



  /**
   * Adds newElement at the given position index within this list
   * 
   * @param index      - index at which the specified element is to be inserted
   * @param newElement - element to be added to this list
   * 
   * @throws NullPointerException      - if newElement is null
   * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
   */

  @Override
  public void add(int index, Task newElement) {
    if (newElement == null) {
      throw new NullPointerException();
    }

    if ((index > size) || (index < 0)) {
      throw new IndexOutOfBoundsException();
    }

    // index=0;
    if ((head == null) || (index == 0)) {
      addFirst(newElement);
    }


    // 0<index<size;
    else if (index < size) {

      int k = 0;
      LinkedNode<Task> currNode = head;

      while (k < index) {
        currNode = currNode.getNext();
        k++;
      }

      LinkedNode<Task> prevNode = currNode.getPrev();

      LinkedNode<Task> newNode = new LinkedNode<Task>(newElement, prevNode, currNode);

      prevNode.setNext(newNode);

      currNode.setPrev(prevNode);

      size++;

    }

    // index=size;
    else {
      add(newElement);
    }



  }


  /**
   * Returns the element at the specified position in this list.
   * 
   * @param index - - index of the element to return
   * 
   * @return the element at the specified position in this list
   * 
   * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index ≥ size())
   */
  @Override
  public Task get(int index) {

    if ((index < 0) || (index >= size)) {
      throw new IndexOutOfBoundsException();
    }

    int k = 0;
    LinkedNode<Task> currNode = head;

    while (k < index) {
      currNode = currNode.getNext();
      k++;
    }

    return currNode.getData();
  }



  /**
   * Returns true if this list contains the specified element toFind. More formally, returns true if
   * and only if this list contains at least one element e such that toFind.equals(e) == true.
   * 
   * @param toFind - element to find
   * 
   * @return true if this list contains at least one match with toFind
   * 
   */
  @Override
  public boolean contains(Task toFind) {
    for (int i = 0; i < size; i++) {
      if (this.get(i).equals(toFind)) {
        return true;
      }
    }

    return false;
  }


  /**
   * Removes the element at the specified position in this list.
   * 
   * @param index - the index of the element to be removed
   * 
   * @return the element that was removed from the list
   *
   * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index ≥ size())
   */
  @Override
  public Task remove(int index) {

    if ((index < 0) || (index >= size)) {
      throw new IndexOutOfBoundsException();
    }


    if (index == 0) {
      LinkedNode<Task> currNode = head;
      head = head.getNext();
      if (head != null) {
        head.setPrev(null);
      }
      size--;
      return currNode.getData();
    }

    else if (index == size - 1) {
      LinkedNode<Task> currNode = tail;
      tail = tail.getPrev();
      tail.setNext(null);
      size--;
      return currNode.getData();
    }

    else {

      int k = 0;
      LinkedNode<Task> currNode = head;

      while (k < index) {
        currNode = currNode.getNext();
        k++;
      }


      currNode.getNext().setPrev(currNode.getPrev());
      currNode.getPrev().setNext(currNode.getNext());

      size--;

      return currNode.getData();

    }

  }


  /**
   * Removes all of the elements from this list. The list will be empty after this call returns.
   */
  @Override
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }



  /**
   * Returns a String representation of the contents of this list traversed in the forward direction
   * separated by a newline.
   * 
   * @return a String representation of the connected nodes making this linked list traversed
   *         starting from the head of the list
   */
  protected String traverseForward() {

    String traversedForward = "";
    LinkedNode<Task> currNode = head;

    while (currNode != null) {
      traversedForward += currNode.getData().toString() + "\n";
      currNode = currNode.getNext();
    }

    return traversedForward;
  }


  /**
   * Returns a String representation of the contents of this list traversed in the backward
   * direction separated by a newline.
   * 
   * @return a String representation of the connected nodes making this linked list traversed
   *         starting from the tail of the list
   */
  protected String traverseBackward() {

    String traversedBackward = "";
    LinkedNode<Task> currNode = tail;

    while (currNode != null) {
      traversedBackward += currNode.getData().toString() + "\n";
      currNode = currNode.getPrev();
    }

    return traversedBackward;

  }


  /**
   * 
   * Returns a String representation of this task list traversed in the forward direction from the
   * head to the tail of the list if forward is true. Otherwise (i.e. forward is false), this method
   * returns the string representation of this task list traversed in the backward direction (from
   * tail to head).
   * 
   * @param forward - indicates the traversal direction of this task list: true if the traversal
   *                direction is forward, false otherwise.
   * @return a String representation of the tasks stored in this task list. The String
   *         representations of each Task are separated by a newline. If this list is empty, this
   *         method returns an empty string "".
   */
  public String traverse(boolean forward) {
    if (forward) {
      return traverseForward();
    }
    return traverseBackward();
  }


  /**
   * Checks if the list is empty
   * 
   * @return true if the list is empty or false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (size != 0) {
      return false;
    }

    if (head != null) {
      return false;
    }

    if (tail != null) {
      return false;
    }

    return true;

  }


  /**
   * Returns the size of this list
   * 
   * @return the number of items in the list
   */
  @Override
  public int size() {
    return this.size;
  }


  /**
   * Adds newElement to the end (tail) of this list
   * 
   * @param newElement - element to be added to this list
   * 
   * @throws NullPointerException - if newElement is null
   */
  @Override
  public void add(Task newElement) {

    if (newElement == null) {
      throw new NullPointerException();
    }

    if (head == null) {
      head = new LinkedNode<Task>(newElement, null, null);
      tail = head;
      size++;
    }

    else {
      LinkedNode<Task> newNode = new LinkedNode<Task>(newElement, tail, null);
      tail.setNext(newNode);
      tail = newNode;
      size++;
    }
  }


  /**
   * Adds new Element to the head of this list
   * 
   * @param newElement - element to be added to the head this list
   * 
   * @throws NullPointerException - if newElement is null
   */
  @Override
  public void addFirst(Task newElement) {

    if (newElement == null) {
      throw new NullPointerException();
    }

    if (head == null) {
      head = new LinkedNode<Task>(newElement, null, null);
      tail = head;
      size++;
    }

    else {
      LinkedNode<Task> newNode = new LinkedNode<Task>(newElement, null, head);
      head.setPrev(newNode);
      head = newNode;
      size++;
    }
  }

}
