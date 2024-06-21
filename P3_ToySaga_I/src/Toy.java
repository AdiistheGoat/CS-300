//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ToySagaTester
// Course: CS 300 Spring 2024
//
// Author: Aditya Goyal
// Email: agoyal33@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import processing.core.PImage;
import java.io.File;

/**
 * This class is used to define related data fields and methods for Toy objects which represent
 * on-screen items which the user can interact with.
 */
public class Toy {

  public final PImage IMAGE;
  private int x;
  private int y;
  private boolean isDragging;
  private int rotations;


  /**
   * This is a constructor which takes as argument a String name, an int x, and an int y, in that
   * order, and sets the corresponding instance variables.
   * 
   * @param name - name of the .png file which stores the image of the toy object
   * @param x    - x-coordinate of toy object
   * @param y    - y-coordinate of toy object
   */
  public Toy(String name, int x, int y) {
    this.x = x;
    this.y = y;
    this.isDragging = false;
    this.rotations = 0;
    IMAGE = Utility.loadImage("images" + File.separator + name + ".png");
  }

  /**
   * This is a constructor which takes as argument a String name , sets the corresponding instance
   * variable ,and initializes the position of the object to the center of the screen
   * 
   * @param name - name of the .png file which stores the image of the furniture object
   */
  public Toy(String name) {
    this.x = Utility.width() / 2;
    this.y = Utility.height() / 2;
    this.isDragging = false;
    this.rotations = 0;
    IMAGE = Utility.loadImage("images" + File.separator + name + ".png");
  }


  /**
   * This method returns the value in the private instance variable - x
   * 
   * @return the value of the object's x- coordinate
   */
  public int getX() {
    return this.x;
  }


  /**
   * This method returns the value in the private instance variable - y
   * 
   * @return the value of the object's y-coordinate
   */
  public int getY() {
    return this.y;
  }


  /**
   * This method sets the value in the private instance variable - x
   * 
   * @param x the value of the x-coordinate , given by the user, that we want to set the object to.
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * This method sets the value in the private instance variable - y
   * 
   * @param y the value of the y-coordinate , given by the user, that we want to set the object to.
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * This method returns the number of the rotations of the toy object
   * 
   * @return number of rotations of the toy object
   */
  public int getRotationsCount() {
    return this.rotations;
  }

  /**
   * This method checks whether the toy object is dragging or not , and returns the appropriate
   * indicator-true or false.
   * 
   * @return false- if not dragging ; true- if dragging
   */
  public boolean isDragging() {
    return this.isDragging;
  }


  /**
   * This method sets the value in the private instance variable isDragging to true to indicate that
   * the toy object is dragging.
   */
  public void startDragging() {
    this.isDragging = true;
  }

  /**
   * This method sets the value in the private instance variable isDragging to false to indicate
   * that the toy object is not dragging.
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * This method increments the value in the private instance variable (rotations) by 1 . The method
   * increases the no of rotations of a toy object when it is called.
   */
  public void rotate() {
    this.rotations++;
  }


  /**
   * This method is used to add the arguments dx and dy to the x and y fields respectively.This
   * method is basically used to move the toy object around the GUI.
   * 
   * @param dx user given value to be added to the x-coordinate of the center of the toy object.
   * @param dy user given value to be added to the y-coordinate of the center of the toy object.
   */
  public void move(int dx, int dy) {


    boolean valid = false;

    if ((this.x + dx > Utility.width()) || (this.x + dx < 0) || (this.y + dy > Utility.height())
        || (this.y + dy < 0)) {



      if ((this.x + dx > Utility.width()) || (this.x + dx < 0)) {

        if (this.x + dx > Utility.width()) {
          this.x = Utility.width();
        }

        else {
          this.x = 0;
        }
      }



      if ((this.y + dy > Utility.height()) || (this.y + dy < 0)) {

        if (this.y + dy > Utility.height()) {
          this.y = Utility.height();
        }

        else {
          this.y = 0;
        }

      }

    }



    else {
      valid = true;
    }


    if (valid) {

      this.x += dx;
      this.y += dy;
    }


  }



  /**
   * Helper method to draw an image accounting for any rotations to the screen. The implementation
   * of this method is fully provided in the write-up.
   */
  private void drawToyImage() {
    Utility.pushMatrix();
    Utility.translate(x, y);
    Utility.rotate(this.rotations * Utility.PI / 2);
    Utility.image(IMAGE, 0.0f, 0.0f);
    Utility.popMatrix();
  }


  /**
   * This method contains code/instructions which first updates the position of the Toy using move()
   * if it is dragging, and then draws the Toy at the updated position
   */
  public void draw() {

    if (this.isDragging()) {
      this.move(Utility.mouseX() - Utility.pmouseX(), Utility.mouseY() - Utility.pmouseY());
    }

    drawToyImage();
  }


  /**
   * This method is used to indicate whether the position (x,y) is within the rectangle of the
   * PImage representing this Toy, including the boundary.
   * 
   * @param x - xCoordinate given by the user
   * @param y - yCoordinate given by the user
   * 
   * @return true- if toy object is over the user given coordinate ; false - if toy object is not
   *         over the user given coordinate
   */
  public boolean isOver(int x, int y) {


    if ((x > Utility.width()) || (y > Utility.height())) {
      return false;
    }



    if (this.rotations % 2 == 0) {

      if ((y <= this.y + this.IMAGE.height / 2) && (y >= this.y - this.IMAGE.height / 2)
          && (x >= this.x - this.IMAGE.width / 2) && (x <= this.x + this.IMAGE.width / 2)) {
        return true;
      }
    }



    else {

      if ((y <= this.y + this.IMAGE.width / 2) && (y >= this.y - this.IMAGE.width / 2)
          && (x >= this.x - this.IMAGE.height / 2) && (x <= this.x + this.IMAGE.height / 2)) {
        return true;
      }


    }

    return false;

  }


  /**
   * This method is used to indicate whether the image of this Toy overlaps with the image of the
   * given Furniture object.
   * 
   * @param other the reference of the object of type Furniture provided by the user.
   * 
   * @return true- if toy object overlaps with the image of the given Furniture object ; false - if
   *         toy object does not overlap with the image of the given Furniture object
   * 
   */
  public boolean isOver(Furniture other) {

    int xLeftF = other.getX() - other.IMAGE.width / 2;
    int xRightF = other.getX() + other.IMAGE.width / 2;
    int yTopF = other.getY() + other.IMAGE.height / 2;
    int yBottomF = other.getY() - other.IMAGE.height / 2;

    if (this.rotations % 2 == 0) {

      int xLeftT = this.getX() - this.IMAGE.width / 2;
      int xRightT = this.getX() + this.IMAGE.width / 2;
      int yTopT = this.getY() + this.IMAGE.height / 2;
      int yBottomT = this.getY() - this.IMAGE.height / 2;

      if ((xRightT >= xLeftF) && (xLeftT <= xRightF) && (yTopF >= yBottomT)
          && (yBottomF <= yTopT)) {
        return true;
      }
    }


    else {
      int xLeftT = this.getX() - this.IMAGE.height / 2;
      int xRightT = this.getX() + this.IMAGE.height / 2;
      int yTopT = this.getY() + this.IMAGE.width / 2;
      int yBottomT = this.getY() - this.IMAGE.width / 2;


      if ((xRightT >= xLeftF) && (xLeftT <= xRightF) && (yTopF >= yBottomT)
          && (yBottomF <= yTopT)) {
        return true;
      }
    }
    return false;
  }


}
