//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Teddy Bear
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

import processing.core.PApplet;

/**
 * This class models a TeddyBear, which inherits from Toy. The properties of the bear include the
 * rotation, rotationDirection, callout, and ToySaga object
 */
public class TeddyBear extends Toy implements Drawable {

  // The callout object associated with a bear
  private Callout callout;

  // The direction the bear is rotating
  private boolean rotationDirection;

  // Current rotation amount of the bear
  private float rotation;


  /**
   * Constructs a new TeddyBear object positioned at the specific x and y position within the
   * display window. The image assigned to this TeddyBear object is defined by ToySaga.BEAR
   * filename.
   * 
   * @param x - x-position (horizontal position) of this TeddyBear
   * @param y - y-position (vertical position) of this TeddyBear
   */
  public TeddyBear(int x, int y) {
    super(ToySaga.BEAR, x, y);
    callout = new Callout(x, y);
    this.rotation = 0;
    this.rotationDirection = true;
  }

  /**
   * Draws this teddy bear by calling the helper method drawTeddyBearNightMode() if the toySaga mode
   * is night mode. Draws this teddy bear to the display window as an ordinary toy by calling the
   * draw() method defined in its super class if the toySaga mode is day mode.
   */
  @Override
  public void draw() {

    if (!(GraphicObject.toySaga.isNightMode())) {
      super.draw();
    }

    else {
      drawTeddyBearNightMode();
    }

  }

  /**
   * Provided method to draw this talking TeddyBear at night with respect to its moves
   */
  private void drawTeddyBearNightMode() {
    move();

    toySaga.pushMatrix();// Save the current transformation matrix

    toySaga.translate(x, y); // Translate to the teddy bear’s position

    toySaga.rotate(rotation * PApplet.PI / 2); // Apply rotation

    if (toySaga.getMode() == "NIGHT") {
      toySaga.image(callout.image, 20f, -90f);
    }

    toySaga.image(image, 0.0f, 0.0f); // Draw the image at the rotated position

    toySaga.popMatrix(); // Restore the previous transformation matrix
  }


  /**
   * If the toySaga mode is day mode, this method moves this teddy bear as an ordinary toy by
   * calling the move() method defined in its super class. If the toySaga mode is night mode, this
   * method moves this TeddyBear as follows: - Get the current rotation amount and increment it by 3
   * degrees using PApplet.radians(3) - Change the rotation direction to false if the
   * currentRotation is greater than or equal to 30 radians - Change the rotation direction to true
   * if the currentRotation is less than or equal to -30 radians - Set the bear's rotation amount
   * appropriately based on the rotation increment and the rotation direction
   */
  @Override
  public void move() {


    if (!(GraphicObject.toySaga.isNightMode())) {
      super.move();
    }

    else {

      // we earlier didn't use true or false. true and false were just indications . we would just
      // indicate but not change the values.
      if (this.getRotation() >= PApplet.radians(30)) { // 0.5235988
        this.setRotationDirection(false);
      }


      if (this.getRotation() <= PApplet.radians(-30)) { // -0.5235988
        this.setRotationDirection(true);
      }


      if (rotationDirection) {
        this.setRotation(this.getRotation() + PApplet.radians(3));
      }

      else {
        this.setRotation(this.getRotation() - PApplet.radians(3));
      }

    }

  }


  /**
   * Returns the rotation direction
   * 
   * @return boolean indicating the rotation direction
   */
  public boolean getRotationDirection() {
    return rotationDirection;
  }

  /**
   * Sets the rotation direction
   * 
   * @param direction - The rotation direction (boolean valued)
   */
  public void setRotationDirection(boolean direction) {
    this.rotationDirection = direction;
  }

  /**
   * Sets the rotation amount
   * 
   * @param rotation - The float-valued rotation amount
   */
  public void setRotation(float rotation) {
    this.rotation = rotation;
  }

  /**
   * Returns the rotation amount of the TeddyBear
   * 
   * @return float The rotation amount of the TeddyBear
   */
  public float getRotation() {
    return rotation;
  }


}
