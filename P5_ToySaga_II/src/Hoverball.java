//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Hoverball
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
 * This class models a Hoverball, which inherits from Toy. Properties of the car include the x and y
 * position of the ball
 */
public class Hoverball extends Toy implements Drawable {

  /**
   * Constructs a new Hoverball whose image filename is ToySaga.HOVERBALL_OFF located at the
   * specified (x, y) position within the display window
   * 
   * @param x - x-position of this Hoverball
   * @param y - y-position of this Hoverball
   */
  public Hoverball(int x, int y) {
    super(ToySaga.HOVERBALL_OFF, x, y);
  }

  /**
   * Sets the image of this Hoverball to ToySaga.HOVERBALL_ON if the toySaga mode is NIGHT_MODE and
   * to ToSaga.HOVERBALL_OFF, otherwise.
   */
  private void switchOnOff() {
    if (toySaga.isNightMode()) {
      image = toySaga.loadImage(ToySaga.HOVERBALL_ON);
    }

    else {
      image = toySaga.loadImage(ToySaga.HOVERBALL_OFF);
    }
  }


  /**
   * If the toySaga mode is night mode, this method bounces this Hoverball vertically by moving it
   * with dx equals to zero and dy equals to a factor of 6 * PApplet.sin(toySaga.frameCount * 0.1f)
   * use Math.round to cast the float to an int If the toySaga mode is day mode, this method moves
   * this Hoverball as an ordinary Toy by calling the move() method defined in its super class.
   */
  @Override
  public void move() {

    if (toySaga.isNightMode()) {
      int dY = Math.round(6 * PApplet.sin(toySaga.frameCount * 0.1f));
      move(0, dY);
    }

    else {
      super.move();
    }
  }

  /**
   * This method first sets the image of this Hoverball to ToySaga.HOVERBALL_ON if the toySaga mode
   * is NIGHT_MODE and to ToSaga.HOVERBALL_OFF, otherwise. Then, it draws this Hoverball by calling
   * the draw() method defined in its super class.
   */
  @Override
  public void draw() {
    this.switchOnOff();
    super.draw();
  }

}
