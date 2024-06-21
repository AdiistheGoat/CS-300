//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TaskManager
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
 * This class models TaskManager objects. A TaskManager has a to do list of incomplete tasks, and a
 * list of completed tasks. This class allows the user to manage the tasks in the to do list.
 */
public class TaskManager {
  /**
   * to do list of incomplete tasks
   */
  protected TaskList toDoList; // list of incomplete tasks

  /**
   * TaskList of completed tasks
   */
  protected TaskList completedTasks; // list of completed tasks

  /**
   * Constructs a TaskManager with empty to-do and completed task lists.
   */
  public TaskManager() {
    toDoList = new TaskList();
    completedTasks = new TaskList();
  }


  /**
   * Appends a task to the end of the to-do list.
   *
   * @param task The task to be added to the to-do list.
   */
  public void appendTask(Task task) {
    toDoList.add(task);
  }

  /**
   * Moves a specified task to the top (head) of the to-do list.
   *
   * @param taskToMove The index of the task to move to the top (head) of the to-do list.
   */
  public void movetoTop(int indexTaskToMove) {

    if ((indexTaskToMove >= toDoList.size()) || (indexTaskToMove < 0)) {
      throw new IndexOutOfBoundsException();
    }

    Task tMove = toDoList.remove(indexTaskToMove);

    toDoList.addFirst(tMove);

  }

  /**
   * Moves a specified task to the bottom (tail) of the to-do list.
   *
   * @param indexTaskToMove The task to move to the bottom (tail) of the to-do list.
   */
  public void moveToBottom(int indexTaskToMove) {

    if ((indexTaskToMove >= toDoList.size()) || (indexTaskToMove < 0)) {
      throw new IndexOutOfBoundsException();
    }

    Task tMove = toDoList.remove(indexTaskToMove);

    toDoList.add(tMove);
  }

  /**
   * Moves a specified task to a position before another task in the to-do list.
   *
   * @param taskToMove The task to move.
   * @param other      The task before which the first task will be moved.
   * @return true if the task was successfully moved; false otherwise.
   */
  public boolean moveBeforeOtherTask(int indexTaskToMove, int indexOtherTask) {

    try {

      if ((indexTaskToMove < 0) || (indexTaskToMove >= toDoList.size())) {
        return false;
      }

      if ((indexOtherTask < 0) || (indexOtherTask >= toDoList.size())) {
        return false;
      }


      int beforeSize = toDoList.size();

      Task taskToMove = toDoList.get(indexTaskToMove);

      if (indexOtherTask > indexTaskToMove) {
        indexOtherTask--;
      }


      if (!(removeTask(indexTaskToMove))) {
        return false;
      }

      toDoList.add(indexOtherTask, taskToMove);


      int afterSize = toDoList.size();

      if (beforeSize != afterSize) {
        return false;
      }

    }

    catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Moves a specified task to a position after another task in the to-do list.
   *
   * @param taskToMove The task to move.
   * @param other      The task after which the first task will be moved.
   * @return true if the task was successfully moved; false otherwise.
   */
  public boolean moveAfterOtherTask(int indexTaskToMove, int indexOtherTask) {

    try {

      if ((indexTaskToMove < 0) || (indexTaskToMove >= toDoList.size())) {
        return false;
      }

      if ((indexOtherTask < 0) || (indexOtherTask >= toDoList.size())) {
        return false;
      }

      int beforeSize = toDoList.size();

      Task taskToMove = toDoList.get(indexTaskToMove);

      if (indexOtherTask > indexTaskToMove) {
        indexOtherTask--;
      }


      if (!(removeTask(indexTaskToMove))) {
        return false;
      }

      toDoList.add(indexOtherTask + 1, taskToMove);

      int afterSize = toDoList.size();

      if (beforeSize != afterSize) {
        return false;
      }

    }


    catch (Exception e) {
      return false;
    }

    return true;

  }

  /**
   * Removes a task from the to-do list based on its index.
   *
   * @param index The index of the task to remove.
   * @return true if the task was successfully removed; false if the index was invalid.
   */
  public boolean removeTask(int index) {

    try {
      Task remT = toDoList.get(index);
      int size = toDoList.size();

      Task remTExpected = toDoList.remove(index);
      int sizeNew = toDoList.size();

      if (sizeNew != size - 1) {
        return false;
      }

      if (remT != remTExpected) {
        return false;
      }
    }

    catch (IndexOutOfBoundsException e) {
      return false;
    }

    catch (NullPointerException e) {
      return false;
    }

    catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Marks a task in the to-do list as complete and moves it to the completed tasks list, based on
   * its index.
   *
   * @param index The index of the task to mark as complete.
   * @return true if the task was successfully marked as complete and moved; false if the index was
   *         invalid.
   */
  public boolean completeTask(int index) {


    try {

      int toDoSize = toDoList.size();
      int completeSize = completedTasks.size();

      Task completedT = toDoList.get(index);
      completedT.markCompleted();

      if (!(removeTask(index))) {
        return false;
      }

      completedTasks.add(completedT);

      int toDoSizeNew = toDoList.size();
      int completeSizeNew = completedTasks.size();


      if (toDoSizeNew != toDoSize - 1) {
        return false;
      }

      if (completeSizeNew != completeSize + 1) {
        return false;
      }


    }


    catch (IndexOutOfBoundsException e) {
      return false;
    }

    catch (NullPointerException e) {
      return false;
    }

    catch (Exception e) {
      return false;
    }

    return true;


  }

  /**
   * Returns a new TaskList that contains all tasks sorted in the increasing order
   * 
   * @return a new TaskList that contains all tasks sorted in the increasing order
   */
  public TaskList sortTasks() {

    SortedTaskList allSorted = new SortedTaskList();

    for (int i = 0; i < toDoList.size(); i++) {
      allSorted.add(toDoList.get(i));
    }

    for (int i = 0; i < completedTasks.size(); i++) {
      allSorted.add(completedTasks.get(i));

    }

    return allSorted; // allSorted instance of TaskList returns true

  }


}
