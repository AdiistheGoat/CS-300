//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Toy
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
 * This class models Toy objects in the p05 Toy Saga II program
 */
public class Toy extends GraphicObject implements MouseListener, Movable {

  // indicates whether this Toy object is being dragged or not
  private boolean isDragging;

  /**
   * Constructs a new Toy object positioned at the center of the display window. The image assigned
   * to this Toy object is defined by the provided filename. A newly created Toy object is not being
   * dragged and have been rotated zero times.
   * 
   * @param filename - filename of the image of this Toy object.
   */
  public Toy(String filename) {
    super(filename);
    this.isDragging = false;
  }

  /**
   * Constructs a new Toy object positioned at the specific x and y position within the display
   * window. The image assigned to this Toy object is defined by the provided filename. A newly
   * created Toy object is not being dragged and have been rotated zero times.
   * 
   * @param filename - filename of the image of this Toy object
   * @param x        - x-position (horizontal position) of this Toy object
   * @param y        - y-position (vertical position) of this Toy object
   */
  public Toy(String filename, int x, int y) {
    super(filename, x, y);
    this.isDragging = false;
  }

  /**
   * Checks whether this Toy object is dragging
   * 
   * @return true if this toy object is dragging
   */
  public boolean isDragging() {
    return this.isDragging;
  }

  /**
   * Starts dragging this object by setting its instance field isDragging to true.
   */
  public void startDragging() {
    this.isDragging = true;
  }

  /**
   * Stops dragging this object by setting its instance field isDragging to false.
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * If this toy is dragging, this method moves it following the mouse moves. The current x-position
   * of the mouse is given by toySaga.mouseX The current y-position of the mouse is given by
   * toySaga.mouseY The old x-position of the mouse (in the frame previous to the current frame) is
   * given by toySaga.pmouseX The old y-position of the mouse (in the frame previous to the current
   * frame) is given by toySaga.pmouseY
   */
  @Override
  public void move() {
    if (isDragging()) {
      move(toySaga.mouseX - toySaga.pmouseX, toySaga.mouseY - toySaga.pmouseY);
    }
  }



  /**
   * Moves this toy object with the specific dx, and dy moves. This toy should not get out of the
   * boundaries of the display window of the toy saga.
   * 
   * @param dx - horizontal move
   * @param dy - vertical move
   */
  // overloading
  protected void move(int dx, int dy) {

    boolean valid = false;

    if ((this.x + dx > toySaga.width) || (this.x + dx < 0) || (this.y + dy > toySaga.height)
        || (this.y + dy < 0)) {

      if ((this.x + dx > toySaga.width) || (this.x + dx < 0)) {

        if (this.x + dx > toySaga.width) {
          this.x = toySaga.width;
        }

        else {
          this.x = 0;
        }
        this.isDragging = false;
      }

      else {
        this.x += dx;
      }

      if ((this.y + dy > toySaga.height) || (this.y + dy < 0)) {

        if (this.y + dy > toySaga.height) {
          this.y = toySaga.height;
        }

        else {
          this.y = 0;
        }

        this.isDragging = false;

      }

      else {
        this.y += dy;
      }
    }



    else {
      this.x += dx;
      this.y += dy;
    }

  }


  /**
   * If no toy is dragging within the toy saga, this method begins dragging this Toy if the mouse is
   * over it and no toy is dragging in the toy saga scene.
   */
  @Override
  public void onClick() {
    if (toySaga.noToyIsDragging()) {

      if (isMouseOver()) {
        startDragging();
      }

    }

  }

  /**
   * Stops dragging this Toy.
   */
  @Override
  public void onRelease() {

    if (isMouseOver()) {
      stopDragging();
    }
  }



  /**
   * Returns true the mouse is over the this Toy
   * 
   * @return true if the mouse is over this toy
   */
  @Override
  public boolean isMouseOver() {
    if ((toySaga.mouseX <= this.x + image.width / 2)
        && (toySaga.mouseX >= this.x - image.width / 2)) {
      if ((toySaga.mouseY <= this.y + image.height / 2)
          && (toySaga.mouseY >= this.y - image.height / 2)) {
        return true;
      }
    }

    return false;
  }


  /**
   * Checks whether this Toy object is over a given point: (x, y) position in the screen.
   * 
   * @param x- x-position within the display window
   * @param y- y-position within the display window
   * @return true if this toy is over the specific (x,y) coordinates.
   */

  public boolean isOver(int x, int y) {

    if ((x <= this.x + image.width / 2) && (x >= this.x - image.width / 2)) {
      if ((y <= this.y + image.height / 2) && (y >= this.y - image.height / 2)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Draws this Toy to the display window at its current (x,y) position taking into account of its
   * moves. This method first moves this toy by calling its move() method. Then, it draws it to the
   * screen as a GraphicObject by calling the draw() method defined in the super class.
   */
  @Override
  public void draw() {
    move();
    super.draw();
  }



}
