//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: SortedTaskList
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

import java.util.NoSuchElementException;

/**
 * This class models a sorted task list data structure that extends the doubly linked list of tasks
 * TaskList.
 */
public class SortedTaskList extends TaskList {


  /**
   * Adds a specific task to this sorted list of tasks (in the increasing order). More explicitly,
   * this method finds the correct location to insert this task into the list, according to the
   * priority of the existing tasks in the list, and adds it there.
   * 
   * 
   * @param aTask - the task to be added to this sorted list
   * 
   * @throws NullPointerException     - if aTask is null
   * @throws IllegalArgumentException - with a descriptive error message if this list already
   *                                  contains a match with this task (with respect to the
   *                                  Object.equals() method). Duplicate items are not allowed in
   *                                  this list.
   */
  @Override
  public void add(Task aTask) throws NullPointerException {

    if (aTask == null) {
      throw new NullPointerException();
    }

    int k = this.size();
    while ((k > 0) && (aTask.compareTo(this.get(k - 1)) <= 0)) {

      if (aTask.compareTo(this.get(k - 1)) == 0) {
        throw new IllegalArgumentException("Error: Duplicate items are not allowed in the list");
      }

      else {
        k--;
      }
    }

    if (k == this.size()) {
      super.add(aTask);
    }

    else {
      super.add(k, aTask);
    }
  }


  /**
   * Adds a task to the front of the list IF AND ONLY IF it is less than any other task in the list
   * 
   * @param aTask - task to be added to the head of this sorted list
   * 
   * @throws NullPointerException  - if aTask is null
   * @throws IllegalStateException - with a descriptive error message if aTask cannot be added to
   *                               the head of this list, meaning that the list is not empty and
   *                               aTask is greater that the first task stored in this list.
   */
  @Override
  public void addFirst(Task aTask) throws NullPointerException {

    if (aTask == null) {
      throw new NullPointerException();
    }

    int k = this.size();
    while ((k > 0) && (aTask.compareTo(this.get(k - 1)) < 0)) {
      k--;
    }

    if (k == 0) {
      super.addFirst(aTask);
    }

    else {

      throw new IllegalStateException("Error: Task cannot be added to the head of this list");
    }
  }


  /**
   * Adds aTask to the given index position within this sorted list IF AND ONLY IF index the correct
   * position to aTask to be inserted in this sorted list.
   * 
   * @param - index at which the specified task is to be inserted
   * @param - aTask - task to be added to this list
   * 
   * @throws NullPointerException      - if aTask is null
   * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
   * @throws IllegalStateException     - with a descriptive error message if index is not a correct
   *                                   position at which aTask can be added to this sorted list
   *                                   (i.e. aTask is greater than the task at index+1 (if any), or
   *                                   aTask is less than the task at index-1 (if any).
   */
  @Override
  public void add(int index, Task aTask) throws NullPointerException, IndexOutOfBoundsException {

    if (aTask == null) {
      throw new NullPointerException();
    } // do we need to wrote this , since we are going to write subsequent methods that throw a
      // nullPointer of aTask is null

    if ((index > this.size()) || (index < 0)) {
      throw new IndexOutOfBoundsException();
    }

    int k = this.size();
    while ((k > 0) && (aTask.compareTo(this.get(k - 1)) < 0)) {
      k--;
    }

    if (k != index) {
      throw new IllegalStateException(
          "Error: The index provided in the argument is not a correct position at which aTask can be added to this sorted list");
    }


    else {
      super.add(k, aTask);
    }
  }

  /**
   * Returns the task at index zero in this sorted task list
   * 
   * @return the task at index zero in this sorted task list
   * @throws NoSuchElementException - with a descriptive error message if this list is empty
   */
  public Task peekBest() {
    if (this.size() > 0) {
      return this.get(0);
    }

    else {
      throw new NoSuchElementException(
          "Error: The list is empty. There is no task at index 0  to be returned in the sorted task list.");
    }
  }


  /**
   * Removes and returns the task at index zero in this sorted task list
   * 
   * @return the task that was at index zero within this sorted task list
   * @throws NoSuchElementException - with a descriptive error message if this list is empty
   * 
   */
  public Task removeBest() {
    if (this.size() > 0) {
      return remove(0);
    }

    else {
      throw new NoSuchElementException(
          "Error: The list is empty. There is no task at index 0 to be removed and returned in the sorted task list.");
    }
  }



}
