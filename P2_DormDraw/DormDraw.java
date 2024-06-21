//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Dorm Planner
// Course: CS 300 Spring 2024
//
// Author: Aditya Goyal
// Email: agoyal33@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources:
//////////////// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2024/spring/p2/doc/DormDraw.html#addSymbol(Symbol%5B%5D,Symbol)
// I used this online source for javaDoc comments.
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PImage;
import java.io.File;

/**
 * This class models an application to draw a floor plan for a dorm.
 */

public class DormDraw {

  private static PImage backgroundImage; // PImage object that represents the
  // background image

  private static Symbol[] symbols; // non-compact perfect size array storing
  // dorm symbols added to the display window


  /**
   * This method removes the first item from the Symbols array whose position matched the cursor
   * position when the user gave the command to remove the furniture item.
   */
  private static void backSpace() {
    for (int i = 0; i < symbols.length; i++) {

      if (symbols[i] != null) {
        if (isMouseOver(symbols[i])) {
          symbols[i] = null;
          break;
        }
      }
    }
  }

  /**
   * This method removes the first item from the Symbols array whose position matched the cursor
   * position when the user gave the command to rotate the furniture item.
   */
  private static void rotate() {

    for (int i = 0; i < symbols.length; i++) {

      if (symbols[i] != null) {

        if (isMouseOver(symbols[i])) {
          symbols[i].rotate();
          break;
        }
      }

    }

  }


  /**
   * This method calls another method that starts the application.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }


  /**
   * Checks if the mouse is over a given symbol.
   * 
   * @param symbol - reference to a given dorm symbol.
   * 
   * @return true if the mouse is over the given symbol object (i.e. over the frame of the image of
   *         the symbol), false otherwise.
   * 
   */
  public static boolean isMouseOver(Symbol symbol) {

    int xCoordinate = Utility.mouseX();
    int yCoordinate = Utility.mouseY();

    int x1 = symbol.x() - (symbol.width() / 2);
    int x2 = symbol.x() + (symbol.width() / 2);

    int y1 = symbol.y() - (symbol.height() / 2);
    int y2 = symbol.y() + (symbol.height() / 2);



    if (((xCoordinate >= x1) && (xCoordinate <= x2))
        && ((yCoordinate >= y1) && (yCoordinate <= y2))) {
      return true;
    }
    return false;
  }


  /**
   * Initializes the DormDraw data fields. This callback method is called once when the program
   * starts.
   */
  public static void setup() {

    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    symbols = new Symbol[12];
  }


  /**
   * This callback method continuously draws and updates the application display window. It is
   * automatically called directly after setup() and continuously executes until the program is
   * stopped.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 250, 220));
    System.out.println(Utility.width());
    System.out.println(Utility.height());
    
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    for (int i = 0; i < symbols.length; i++) {
      if (symbols[i] != null) {
        symbols[i].draw();
      }
    }
  }


  /**
   * Adds a new element (toAdd) to the perfect size array symbols
   * 
   * @param symbols - a non-compact perfect size array storing elements of type Symbol.
   * @param toAdd-  the symbol to add.
   */
  public static void addSymbol(Symbol[] symbols, Symbol toAdd) {

    for (int i = 0; i < symbols.length; i++) {

      if (symbols[i] == null) {
        symbols[i] = toAdd;
        break;
      }
    }
  }


  /**
   * Callback method called once every time a key is pressed. The key that was pressed is returned
   * by the Utility.key() utility method. The DormDraw.keyPressed() method performs specific actions
   * based on the pressed key:
   * 
   * - Pressing 'b' adds a new bed at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'c' adds a new chair at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'd' adds a new dresser at the mouse position if the array symbols is not full.
   * 
   * 
   * - Pressing 'k' adds a new desk at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'f' adds a new sofa at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'g' adds a new rug at the mouse position if the array symbols is not full.
   * 
   * - Pressing 'p' adds a new plant at the mouse position if the array symbols is not full.
   * 
   * - Pressing BACKSPACE key deletes a dorm symbol if the mouse is over it. The Backspace key char
   * is accessible through Utility.BACKSPACE. It is also the char with int value 8.
   * 
   * - Pressing 'r' rotates the image of a dorm symbol if the mouse is over it.
   * 
   * - Pressing 's' saves the current screen (dorm draw canvas) in a "dormDraw.png" file.
   */
  public static void keyPressed() {


    char key = Character.toLowerCase(Utility.key());
    System.out.print(key);
    int xCoordinate = Utility.mouseX();
    int yCoordinate = Utility.mouseY();


    switch (key) {

      case 'b':
        addSymbol(symbols, new Symbol("bed.png", xCoordinate, yCoordinate));
        break;

      case 'c':
        addSymbol(symbols, new Symbol("chair.png", xCoordinate, yCoordinate));
        break;

      case 'd':
        addSymbol(symbols, new Symbol("dresser.png", xCoordinate, yCoordinate));
        break;

      case 'k':
        addSymbol(symbols, new Symbol("desk.png", xCoordinate, yCoordinate));
        break;

      case 'f':
        addSymbol(symbols, new Symbol("sofa.png", xCoordinate, yCoordinate));
        break;

      case 'g':
        addSymbol(symbols, new Symbol("rug.png", xCoordinate, yCoordinate));
        break;


      case 'p':
        addSymbol(symbols, new Symbol("plant.png", xCoordinate, yCoordinate));
        break;

      case 'r':
        rotate();
        break;

      case Utility.BACKSPACE:
        backSpace();
        break;


      case 's':
        Utility.save("dormDraw.png");
        break;
    }
  }



  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {

    for (int i = 0; i < symbols.length; i++) {
      if (symbols[i] != null) {

        if (isMouseOver(symbols[i])) {
          symbols[i].startDragging();
          break;
        }
      }
    }
  }


  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {

    for (int i = 0; i < symbols.length; i++) {

      if (symbols[i] != null) {
        symbols[i].stopDragging();
      }

    }
  }



}
