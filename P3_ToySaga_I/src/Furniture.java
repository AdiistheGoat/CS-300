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

import java.io.File;
import processing.core.PImage;


/**
 * This class is used to define related data fields and methods for Furniture objects which
 * represent on-screen items which the user cannot interact with.
 */
public class Furniture {

  public final PImage IMAGE;
  private String name;
  private int x;
  private int y;

  /**
   * A constructor which takes as argument a String name, an int x, and an int y, in that order, and
   * sets the corresponding instance variables.
   * 
   * @param name name of the .png file which stores the image of the furniture object
   * @param x    x-coordinate of furniture object
   * @param y    y-coordinate of furniture object
   */
  public Furniture(String name, int x, int y) {
    this.name = name;
    IMAGE = Utility.loadImage("images" + File.separator + name + ".png");
    this.x = x;
    this.y = y;
  }


  /**
   * A constructor which takes as argument a String name , sets the corresponding instance variable
   * , and initializes the position of the object to the center of the screen
   * 
   * @param name - name of the .png file which stores the image of the furniture object
   */
  public Furniture(String name) {
    this.name = name;
    IMAGE = Utility.loadImage("images" + File.separator + name + ".png");
    this.x = Utility.width() / 2;
    this.y = Utility.height() / 2;
  }


  /**
   * returns the value in the private instance variable - x
   * 
   * @return the value of the object's x- coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * the value in the private instance variable - y
   * 
   * @return the value of the object's y-coordinate
   */
  public int getY() {
    return this.y;
  }

  /**
   * returns the value in the private instance variable - name
   * 
   * @return the object's name
   */
  public String name() {
    return this.name;
  }


  /**
   * The following method draws an image to the screen, one which is specified by its x and y
   * coordinate.
   */
  public void draw() {
    Utility.image(IMAGE, x, y);
  }



}
