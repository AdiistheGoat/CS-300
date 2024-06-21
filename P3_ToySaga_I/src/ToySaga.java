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
// Online Sources:
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2024/spring/p2/doc/DormDraw.html#addSymbol(Symbol%5B%5D,Symbol)
// I used this online source for javaDoc comments.
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PImage;

import java.util.ArrayList;
import java.io.File;

/**
 * This class implements the main user interface of the p03 Toy Saga I program
 */
public class ToySaga {

  private static PImage backgroundImage;
  private static ArrayList<Furniture> furnitureList;
  private static ArrayList<Toy> toyList;

  private static final String BOX_NAME = "box";
  private static final int MAX_TOYS_COUNT = 8;


  /**
   * Initializes the ToySaga data fields. This callback method is called once when the program
   * starts.
   */
  public static void setup() {
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    furnitureList = new ArrayList<Furniture>();
    toyList = new ArrayList<Toy>();

    furnitureList.add(new Furniture("bed", 520, 270));
    furnitureList.add(new Furniture("rug", 220, 370));
    furnitureList.add(new Furniture("nightstand", 325, 240));
    furnitureList.add(new Furniture(BOX_NAME, 90, 230));
  }


  /**
   * This callback method continuously draws and updates the application display window. It is
   * automatically called directly after setup() and continuously executes until the program is
   * stopped. This method first draws the background image to the center of the screen. Updates the
   * contents of the toyList to remove any toy which is over the box furniture, and draws furniture
   * and toy objects.
   */
  public static void draw() {


    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    for (int i = 0; i < toyList.size(); i++) {
      toyList.get(i).draw();
    }

    for (int i = 0; i < furnitureList.size(); i++) {
      furnitureList.get(i).draw();
    }

    for (int i = toyList.size() - 1; i >= 0; i--) {

      if (toyList.get(i).isOver(furnitureList.get(3))) {
        toyList.remove(i);
      }
    }

  }


  /**
   * Returns the Furniture object with a name matching BOX_NAME (exact match: case sensitive
   * comparison) if any is found.
   * 
   * @return the reference to the box Furniture object, or null of no match found.
   */
  public static Furniture getToyBox() {

    for (int i = 0; i < furnitureList.size(); i++) {
      if (furnitureList.get(i).name().equals(BOX_NAME)) {
        return furnitureList.get(i);
      }
    }

    return null;
  }


  /**
   * Returns the toy which is currently dragging. We assume that there is at most one toy object
   * being dragged at a given time.
   * 
   * @return a reference to toy being dragged, or null if no toy is dragging.
   */
  public static Toy getDraggingToy() {

    for (int i = 0; i < toyList.size(); i++) {
      if (toyList.get(i).isDragging() == true) {
        return toyList.get(i);
      }
    }

    return null;
  }


  /**
   * Callback method called once after every time the mouse button is pressed. If no toy is
   * dragging, this method checks whether the mouse is over a toy and start dragging it. If the
   * mouse is over multiple toys, only the toy at the lowest index will start dragging.
   */
  public static void mousePressed() {

    if (getDraggingToy() == null) {

      for (int i = 0; i < toyList.size(); i++) {

        if (toyList.get(i).isOver(Utility.mouseX(), Utility.mouseY())) {
          toyList.get(i).startDragging();
          break;
        }

      }
    }
  }


  /**
   * Callback method called every time the mouse button is released. This method stops dragging any
   * toy stored in the toy list.
   */
  public static void mouseReleased() {

    while (getDraggingToy() != null) {
      getDraggingToy().stopDragging();
    }

  }


  /**
   * Callback method called once every time a key is pressed. The key that was pressed is returned
   * by the Utility.key() utility method. The ToySaga.keyPressed() method performs the below actions
   * based on the pressed key: - Pressing 'c' or 'C' adds a new toy car at the mouse position if the
   * MAX TOYS COUNT is not reached. - Pressing 't' or 'T'adds a teddy bear toy at the mouse position
   * if the MAX TOYS COUNT is not reached. - Pressing 'r' or 'R' rotates a toy if the mouse is over
   * it. Only one toy is rotated at once.
   */
  public static void keyPressed() {

    char key = Utility.key();

    switch (key) {

      case 'C':
      case 'c':
        if (toyList.size() != MAX_TOYS_COUNT) {
          toyList.add(new Toy("car", Utility.mouseX(), Utility.mouseY()));
        }

        break;


      case 't':
      case 'T':

        if (toyList.size() != MAX_TOYS_COUNT) {
          toyList.add(new Toy("teddyBear", Utility.mouseX(), Utility.mouseY()));
        }

        break;


      case 'r':
      case 'R':

        for (int i = 0; i < toyList.size(); i++) {

          if (toyList.get(i).isOver(Utility.mouseX(), Utility.mouseY())) {
            toyList.get(i).rotate();
            break;
          }

        }

        break;

    }

  }

  /**
   * Driver method that launches the application by calling Utility.runApplication()
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

}
