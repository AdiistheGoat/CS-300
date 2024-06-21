//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Graphic Object
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
import processing.core.PImage;

/**
 * This class models GraphicObject objects. This is an implementation of the Drawable interface
 */
public class GraphicObject extends Object implements Drawable {

  // Processing image of this GraphicObject
  protected PImage image;

  // x-position of this GraphicObject object in the display window
  protected int x;

  // y-position of this GraphicObject object in the display window
  protected int y;

  // Reference to the ToySaga graphic application where this object will be displayed.
  protected static ToySaga toySaga;

  /**
   * Constructs a new GraphicObject object positioned at the specific x and y position within the
   * display window. The image assigned to this GraphicObject object is defined by the provided
   * filename.
   * 
   * @param filename - filename of the image of this graphic object
   * @param x        - x-position (horizontal position) of this GraphicObject object
   * @param y        - y-position (vertical position) of this GraphicObject object
   */
  public GraphicObject(String filename, int x, int y) {
    image = toySaga.loadImage(filename);
    this.x = x;
    this.y = y;
  }

  /**
   * Constructs a new GraphicObject object positioned at the center of the display window. The image
   * assigned to this GraphicObject object is defined by the provided filename.
   * 
   * @param filename - filename of the image of this graphic object
   */
  public GraphicObject(String filename) {
    image = toySaga.loadImage(filename);
    this.x = toySaga.width / 2;
    this.y = toySaga.height / 2;
  }

  /**
   * Gets the x-position of this GraphicObject object
   * 
   * @return the x-position of this GraphicObject object
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y-position of this GraphicObject object
   * 
   * @return the y-position of this GraphicObject object
   */
  public int getY() {
    return y;
  }

  /**
   * Sets the ToySaga PApplet object where this graphic object will be drawn
   * 
   * @param toySaga - PApplet object that represents the display window of the ToySaga app
   */
  public static void setProcessing(ToySaga toySaga) {
    GraphicObject.toySaga = toySaga;
  }

  /**
   * Mutates the image of this GraphicObject by reloading it
   * 
   * @param filename - filename of the image to load
   */
  public void setImage(String filename) {
    this.image = toySaga.loadImage(filename);
  }

  /**
   * Draws the image of this GraphicObject to the display window at its current (x,y) position
   */
  @Override
  public void draw() {
    toySaga.image(image, x, y);
  }



}
