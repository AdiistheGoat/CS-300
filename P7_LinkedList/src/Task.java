//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task
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
 * This class models Task objects.
 */
public class Task implements Comparable<Task> {

  /**
   * High priority level
   */
  public static final int HIGH = 0;

  /**
   * Low priority level
   */
  public static final int LOW = 1;

  /**
   * Indicates the comparison criteria of the tasks (i.e. their sorting order).
   */
  private static SortingOrder sortingOrder;

  /**
   * Indicates the priority level of this Task. The priority level can be either HIGH or LOW
   */
  private int priorityLevel;

  /**
   * title of this task
   */
  private String title; // title of this task (unique case insensitive comparison)

  /**
   * A short description of this task
   */
  private String description; // short description

  /**
   * Indicates whether this task is completed or not
   */
  private boolean isCompleted; // indicates whether this task is completed


  /**
   * Constructs a new Task with a given priority level. A new constructed task is incomplete by
   * default.
   * 
   * @param title           title to be assigned to this task
   * @param description     description to be assigned to this task
   * @param hasHighPriority indicates whether this task has a high priority (true: this task has a
   *                        HIGH priority, LOW otherwise).
   * @throws IllegalArgumentException with a descriptive error message if title or description is
   *                                  null or blank.
   */
  public Task(String title, String description, boolean hasHighPriority) {
    if (title == null || title.isBlank() || description == null || description.isBlank())
      throw new IllegalArgumentException(
          "Invalid title or description: should not be null or blank");
    this.title = title;
    this.description = description;
    if (hasHighPriority)
      this.priorityLevel = HIGH;
    else
      this.priorityLevel = LOW;
  }

  /**
   * Constructs a new Task with a low priority level. A new constructed task is incomplete by
   * default.
   * 
   * @param title       title to be assigned to this task
   * @param description description to be assigned to this task
   * @throws IllegalArgumentException with a descriptive error message if title or description is
   *                                  null or blank.
   */
  public Task(String title, String description) {
    this(title, description, false);
  }

  /**
   * Gets the title of this task
   * 
   * @return the title of this task
   */
  public String getTitle() {
    return title;
  }

  /**
   * Gets the description of this task
   * 
   * @return the description of this task
   */
  public String getDescription() {
    return description;
  }

  /**
   * Edits the description of this task
   * 
   * @param description the description to set
   */
  public void editDescription(String description) {
    this.description = description;
  }

  /**
   * Checks whether this task is completed
   * 
   * @return true if this task is completed or false otherwise
   */
  public boolean isCompleted() {
    return isCompleted;
  }

  /**
   * Marks this task to be completed
   */
  public void markCompleted() {
    this.isCompleted = true;
  }

  /**
   * Sets the sortingOrder of tasks to be by Title
   */
  public static void setSortingOrderByTitle() {
    sortingOrder = SortingOrder.TITLE;
  }

  /**
   * Sets the sortingOrder of tasks to be by PRIORITY level
   */
  public static void setSortingOrderByPriorityLevel() {
    sortingOrder = SortingOrder.PRIORITY;
  }

  /**
   * Gets the sorting order of tasks
   * 
   * @return the sorting order of tasks
   */
  public static SortingOrder getSortingOrder() {
    return sortingOrder;
  }

  /**
   * Returns a String representation of this task in the format:
   * 
   * title: description
   * 
   * @return String representation of this task
   */
  @Override
  public String toString() {
    return title + ": " + description;
  }

  // TODO complete the implementation of equals and compareTo methods with accordance to the details
  // provided in their javadocs.

  /**
   * Compares this task to the specified object. The result is true if and only if the argument is
   * not null and is a Task object (instanceof Task) that has this same title as this task, case
   * INSENSITIVE comparison.
   * 
   * @param other other Object to compare this Task against.
   * @return true if the given object represents a Task equivalent to this task, false otherwise
   */
  @Override
  public boolean equals(Object other) {

    if ((other == null) || (!(other instanceof Task))) {
      return false;
    }

    Task othere = (Task) other; // have to downcast to implement the task methods.

    if (!(this.getTitle().equalsIgnoreCase((othere.getTitle())))) {
      return false;
    }

    return true; // default return statement
  }

  /**
   * Compares this task to anotherTask with respect to the sorting order of tasks. Note that the
   * sorting order of these tasks can be either by TITLE or by PRIORITY.
   * 
   * If the sortingOrder is by TITLE, tasks are compared with respect to lexicographical order of
   * their titles.
   * 
   * If the sortingOrder is by PRIORITY, tasks are compared with respect to their priority level.
   * 
   * @param anotherTask Task to be compared
   * @return the value 0 if the argument Task is equal to this task with respect to the sorting
   *         order; a value less than 0 if this task is less than the Task argument; and a value
   *         greater than 0 if this task is greater than the Task argument, with respect to the
   *         sorting order.
   */
  @Override
  public int compareTo(Task anotherTask) {

    if (this.getSortingOrder() == SortingOrder.TITLE) {
      return this.getTitle().compareTo(anotherTask.getTitle());
    }

    else {

      if (this.priorityLevel > anotherTask.priorityLevel) {
        return 1;
      }

      else if (this.priorityLevel < anotherTask.priorityLevel) {
        return -1;
      }

      else {
        return 0;
      }

    }

  }

}
