//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task Queue
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
 * Instantiable class for TaskQueue objects. Its objects contain an array of Task objects and
 * various operations on the addition, removal and reprioritization of Task objects , present in an
 * array consisting of Task objects in that TaskQueue object.
 */
public class TaskQueue {

  private Task[] heapData; // oversized array that holds all of Tasks in the heap

  private CompareCriteria priorityCriteria; // the criteria used to determine how to prioritize
  // Tasks in the queue

  private int size; // the number of items in the TaskQueue

  /**
   * Creates an empty TaskQueue with the given capacity and priority criteria.
   * 
   * @param capacity         - the max number of Tasks this priority queue can hold
   * @param priorityCriteria - the criteria for the queue to use to determine a Task's priority
   * @throws IllegalArgumentException - with a descriptive message if the capacity is non-positive
   */
  public TaskQueue(int capacity, CompareCriteria priorityCriteria) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity is non-positive");
    }
    heapData = new Task[capacity];
    this.priorityCriteria = priorityCriteria;
  }


  /**
   * Gets the criteria use to prioritize tasks in this a TaskQueue.
   * 
   * @return the prioritization criteria of this TaskQueue.
   */
  public CompareCriteria getPriorityCriteria() {
    return this.priorityCriteria;
  }


  /**
   * Reports if a TaskQueue is empty.
   * 
   * @return true if this TaskQueue is empty, false otherwise
   */
  public boolean isEmpty() {
    if (this.size() == 0) {
      return true;
    }
    return false;
  }



  /**
   * Reports the size of a TaskQueue.
   * 
   * @return the number of Tasks in this TaskQueue
   */
  public int size() {
    return this.size;
  }



  /**
   * Gets the Task in a TaskQueue that has the highest priority WITHOUT removing it. The Task that
   * has the highest priority may differ based on the current priority criteria.
   * 
   * @return the Task in this queue with the highest priority
   * @throws NoSuchElementException - with descriptive message if this TaskQueue is empty
   */
  public Task peekBest() {
    if (isEmpty()) {
      throw new NoSuchElementException("The TaskQueue is empty");
    }
    return heapData[0];
  }


  /// are we allowed to throw null pointer exception??
  /**
   * Adds the newTask to this priority queue.
   * 
   * @param newTask - the task to add to the queue
   * @throws IllegalArgumentException - with a descriptive message if the Task is already completed
   * @throws IllegalStateException    - with a descriptive message if the priority queue is full
   */
  public void enqueue(Task newTask) {

    if (newTask.isCompleted()) {
      throw new IllegalArgumentException("The task is already completed.");
    }

    if (size() == heapData.length) {
      throw new IllegalStateException(
          "The priority queue is full. No new task can be added to the priority queue.");
    }

    heapData[size] = newTask;
    if (newTask != null) {
      size++; // if inputted task is null, size will change , that will be wrong. Therefore putting
              // if statement.
    }
    percolateUp(size - 1); // the input to percolate up will be the last non null input , and it
                           // will have the same output as before and have the same output if we put
                           // the peroclateUp(size-1) inside the if branch.
  }


  // are we allowed to see if valid index is put or not.(will only happen if any implementations are
  // wrong)..can catch in tester cases though...... that is if index will be out of bounds...

  // are we allowed to see if task at the index is null or not. if (index<size)
  /**
   * Fixes one heap violation by moving it up the heap.
   * 
   * @param index - the of the element where the violation may be
   */
  protected void percolateUp(int index) {

    int parentIndex = (index - 1) / 2;

    if ((parentIndex < 0) || (index < 1)) {
      return;
    }

    else {
      if (heapData[index].compareTo(heapData[parentIndex], priorityCriteria) > 0) {
        Task temp = heapData[index];
        heapData[index] = heapData[parentIndex];
        heapData[parentIndex] = temp;
        percolateUp(parentIndex);
      }
    }
  }



  // can we check if we are dequeing it without it being completed.
  /**
   * Gets and removes the Task that has the highest priority. The Task that has the highest priority
   * may differ based on the current priority criteria.
   * 
   * @return the Task in this queue with the highest priority
   * @throws NoSuchElementException - with descriptive message if this TaskQueue is empty
   */
  public Task dequeue() {

    // if its not an exception but peekBest returns null, a null pointer exception will occour,
    // which means the implementation of .isEmpty is wrong??--- or something which helps it enque is
    // wrong.

    Task highP = null;
    try {
      highP = peekBest(); // already checking if taskQueue is empty in peekBest() method.
    }

    catch (NoSuchElementException e) {
      throw e;
    }

    heapData[0] = heapData[size() - 1];
    heapData[size() - 1] = null;

    size--;
    percolateDown(0);

    return highP;

  }


  // are we allowed to see if task at the index is null or not. if (index<size).. if we are not
  // implementing it here , we have to ensure that in other methods that we are not allowing a null
  // argument to pass through
  /**
   * Fixes one heap violation by moving it down the heap.
   * 
   * @param index - the of the element where the violation may be
   */
  protected void percolateDown(int index) {

    int childrenIndex = 2 * index + 1;

    if ((index > size - 1) || (childrenIndex > size - 1) || heapData[childrenIndex] == null) {
      return;
    }

    else {
      if ((childrenIndex + 1 <= size - 1) && (heapData[childrenIndex + 1] != null)) {
        if (heapData[childrenIndex].compareTo(heapData[childrenIndex + 1], priorityCriteria) < 0) {
          childrenIndex = childrenIndex + 1;
        }
      }
      if (heapData[index].compareTo(heapData[childrenIndex], priorityCriteria) < 0) {
        Task temp = heapData[index];
        heapData[index] = heapData[childrenIndex];
        heapData[childrenIndex] = temp;
        percolateDown(childrenIndex);
      }
    }
  }

  /**
   * Changes the priority criteria of this priority queue and fixes it so that is a proper priority
   * queue based on the new criteria.
   * 
   * @param priorityCriteria - the (new) criteria that should be used to prioritize the Tasks in
   *                         this queue
   */
  public void reprioritize(CompareCriteria priorityCriteria) {

    this.priorityCriteria = priorityCriteria; // have to set new priority criteria

    Task[] oldHeapData = heapData;
    heapData = new Task[oldHeapData.length];

    size = 0; // have to reset size
    for (int i = 0; i < heapData.length; i++) {
      if (oldHeapData[i] != null) { // we are putting this size will increase unnecessarily if we
                                    // put
                                    // null Task as argument in enqueue
        enqueue(oldHeapData[i]);
      }
    }
  }

  // does .deepEquals call .equals on the objects in a array.

  /**
   * Creates and returns a deep copy of the heap's array of data.
   * 
   * @return the deep copy of the array holding the heap's data
   */
  public Task[] getHeapData() {

    Task[] copyHeapData = new Task[heapData.length]; // copying capacity, not size only.// creating
                                                     // the complete copy of the array.

    for (int i = 0; i < heapData.length; i++) {
      copyHeapData[i] = heapData[i];
    }

    return copyHeapData;
  }


}
