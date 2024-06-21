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

/**
 * This class implements tester methods to ensure the correctness of the implementation of Furniture
 * and Toy classes in p03 Toy Saga I program.
 */
public class ToySagaTester {

  /**
   * This tester ensures the Furniture constructor which takes a String as input Furniture(String
   * name) correctly constructs a new Furniture object located at the center of the display window,
   * and assigned it a PImage and the name passed as input to the method call.
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testFurnitureConstructor1Getters() {

    Furniture furnitureTesterObject = new Furniture("bed");

    String expectedName = "bed";
    String actualName = furnitureTesterObject.name();

    int expectedXCoordinate = Utility.width() / 2;
    int actualXCoordinate = furnitureTesterObject.getX();

    int expectedYCooridnate = Utility.height() / 2;
    int actualYCoordinate = furnitureTesterObject.getY();


    if (!(expectedName.equals(actualName)) || (expectedXCoordinate != actualXCoordinate)
        || (expectedYCooridnate != actualYCoordinate) || (furnitureTesterObject.IMAGE == null)) {
      return false;
    }

    return true;
  }

  /**
   * This tester ensures the Furniture constructor which takes a String, and two integers as input
   * Furniture(String name, int x, int y) correctly constructs a new Furniture object located at the
   * (x,y) input position, assigned it the name passed as input to the method call, and an image.
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testFurnitureConstructor2Getters() {

    Furniture furnitureTesterObject = new Furniture("bed", 53, 99);

    String expectedName = "bed";
    String actualName = furnitureTesterObject.name();

    int expectedXCoordinate = 53;
    int actualXCoordinate = furnitureTesterObject.getX();

    int expectedYCooridnate = 99;
    int actualYCoordinate = furnitureTesterObject.getY();

    if (!(expectedName.equals(actualName)) || (expectedXCoordinate != actualXCoordinate)
        || (expectedYCooridnate != actualYCoordinate) || (furnitureTesterObject.IMAGE == null)) {
      return false;
    }

    return true;
  }

  /**
   * This tester ensures the Toy constructors, getters and setters of the x and y positions, and the
   * image field.
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyConstructorsGettersSetters() {

    Toy car = new Toy("car");
    Toy teddyBear = new Toy("teddyBear", 45, 99);



    {
      int expectedCarX = Utility.width() / 2;
      int actualCarX = car.getX();

      int expectedCarY = Utility.height() / 2;
      int actualCarY = car.getY();

      int expectedBearX = 45;
      int actualBearX = teddyBear.getX();

      int expectedBearY = 99;
      int actualBearY = teddyBear.getY();


      boolean expectedCarDrag = false;
      boolean actuaCarDrag = car.isDragging();


      boolean expectedBearDrag = false;
      boolean actualBearDrag = teddyBear.isDragging();

      if ((expectedCarX != actualCarX) || (expectedCarY != actualCarY)
          || (expectedBearX != actualBearX) || (expectedBearY != actualBearY)
          || (expectedCarDrag != actuaCarDrag) || (expectedBearDrag != actualBearDrag)) {
        return false;
      }

    }


    {
      car.setX(30);
      car.setY(40);

      int expectedCarX = 30;
      int actualCarX = car.getX();

      int expectedCarY = 40;
      int actualCarY = car.getY();

      teddyBear.setX(56);
      teddyBear.setY(90);

      int expectedBearX = 56;
      int actualBearX = teddyBear.getX();

      int expectedBearY = 90;
      int actualBearY = teddyBear.getY();


      if ((expectedCarX != actualCarX) || (expectedCarY != actualCarY)
          || (expectedBearX != actualBearX) || (expectedBearY != actualBearY)) {
        return false;
      }

    }

    if ((teddyBear.IMAGE == null) || (car.IMAGE == null)) {
      return false;
    }



    return true;
  }



  /**
   * This tester ensures the correctness of Toy.startDragging() and Toy.stopDragging instance
   * methods
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyStartStopDragging() {

    Toy car = new Toy("car");

    for (int i = 0; i < 3; i++) {

      car.startDragging();
      boolean expected = true;
      boolean actual = car.isDragging();

      car.stopDragging();
      boolean expected1 = false;
      boolean actual1 = car.isDragging();

      if ((expected != actual) || (expected1 != actual1)) {
        return false;
      }
    }


    return true;
  }

  /**
   * This tester ensures the correctness of Toy.move() method
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyMove() {


    Toy car = new Toy("car", 600, 250);

    // rotations = even

    {

      // normal


      car.move(200, -250);

      int expectedX1 = 800;
      int actualX1 = car.getX();

      int expectedY1 = 0;
      int actualY1 = car.getY();

      car.move(-30, 40);
      int expectedX2 = 770;
      int actualX2 = car.getX();

      int expectedY2 = 40;
      int actualY2 = car.getY();



      // special

      car.move(-800, 600);

      int expectedX3 = 0;
      int actualX3 = car.getX();

      int expectedY3 = 600;
      int actualY3 = car.getY();


      if ((expectedX3 != actualX3) || (expectedY3 != actualY3) || (expectedX2 != actualX2)
          || (expectedY2 != actualY2) || (expectedX1 != actualX1) || (expectedY1 != actualY1)) {
        return false;
      }
    }



    car.rotate();
    car.setX(600);
    car.setY(250);



    // rotations = odd


    // normal
    {
      car.move(200, -250);

      int expectedX1 = 800;
      int actualX1 = car.getX();


      int expectedY1 = 0;
      int actualY1 = car.getY();



      car.move(-30, 40);
      int expectedX2 = 770;
      int actualX2 = car.getX();


      int expectedY2 = 40;
      int actualY2 = car.getY();


      // special

      car.move(100, -100);

      int expectedX3 = 800;
      int actualX3 = car.getX();

      int expectedY3 = 0;
      int actualY3 = car.getY();

      if ((expectedX3 != actualX3) || (expectedY3 != actualY3) || (expectedX2 != actualX2)
          || (expectedY2 != actualY2) || (expectedX1 != actualX1) || (expectedY1 != actualY1)) {
        return false;
      }

    }

    return true;
  }



  /**
   * This tester ensures the correctness of Toy.rotate() method.
   * 
   * @author Mouna
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   * 
   */
  public static boolean testToyRotate() {

    Toy car1 = new Toy("car");
    Toy car2 = new Toy("car");

    if (car1.getRotationsCount() != 0) {
      System.out.println(
          "Toy.getRotationsCount() should return zero when called on a new created Toy object.");
      return false;
    }

    if (car2.getRotationsCount() != 0) {
      System.out.println(
          "Toy.getRotationsCount() should return zero when called on a new created Toy object.");
      return false;
    }

    for (int i = 0; i < 5; i++) {
      car1.rotate();
    }

    if (car1.getRotationsCount() != 5) {
      System.out.println(
          "Toy.getRotationsCount() did not return the expected output after calling the rotate() "
              + "method multiple times.");
      return false;
    }

    for (int i = 0; i < 3; i++) {
      car2.rotate();
    }

    if (car2.getRotationsCount() != 3) {
      System.out.println(
          "Toy.getRotationsCount() did not return the expected output after calling the rotate() "
              + "method multiple times.");
      return false;
    }
    return true;
  }

  /**
   * This tester checks the correctness of Toy.isOver(int, int) method
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyIsOverPoint() {

    // rotations =0;


    int x = 600;
    int y = 250;
    // let x and y be any point on the GUI that u want the shape to be a center.

    Toy car = new Toy("car", x, y);


    {

      boolean expected1 = true;
      boolean actual1 = car.isOver(x + (car.IMAGE.width / 2), y + (car.IMAGE.height / 2));

      // not general
      boolean expected2 = false;
      boolean actual2 = car.isOver(630, 232);


      // general
      car.rotate();
      boolean expectedR = true;

      boolean actualR1 = car.isOver(x + (car.IMAGE.height / 2), y + (car.IMAGE.width / 2));
      boolean actualR2 = car.isOver(x + (car.IMAGE.height / 2), y - (car.IMAGE.width / 2));
      boolean actualR3 = car.isOver(x - (car.IMAGE.height / 2), y + (car.IMAGE.width / 2));
      boolean actualR4 = car.isOver(x - (car.IMAGE.height / 2), y - (car.IMAGE.width / 2));

      boolean actualR = actualR1 && actualR2 && actualR3 && actualR4;

      if ((expected1 != actual1) || (expected2 != actual2) || (expectedR != actualR)) {
        return false;
      }
    }


    // specific to the current x and y values
    boolean expected = false;
    boolean actual = car.isOver(617, 290);

    boolean expected1 = true;
    boolean actual1 = car.isOver(x, y);

    boolean expected2 = false;
    boolean actual2 = car.isOver(595, 225);

    // tests if the .isOver method returns false when coordinate is outside the GUI.


    boolean expected3 = false;
    boolean actual3 = car.isOver(900, 700);


    if ((expected != actual) && (expected1 != actual1) && (expected2 != actual2)
        && (expected3 != actual3)) {
      return false;
    }


    return true;
  }

  /**
   * This tester checks the correctness of Toy.isOver(Furniture) method
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyIsOverFurniture() {

    Furniture bed = new Furniture("bed");

    int xLeftF = bed.getX() - bed.IMAGE.width / 2;
    int xRightF = bed.getX() + bed.IMAGE.width / 2;
    int yTopF = bed.getY() + bed.IMAGE.height / 2;
    int yBottomF = bed.getY() - bed.IMAGE.height / 2;

    Toy car = new Toy("car"); // not intersecting
    Toy teddyBear = new Toy("teddyBear"); // intersecting
    Toy car2 = new Toy("car");


    // lets satisfy only three conditions out of 4.


    if (car.getRotationsCount() % 2 == 0) {


      {
        int xLeftT = 624; // wrong
        int xRightT = xLeftT + car.IMAGE.width;
        int yTopT = 399;
        int yBottomT = yTopT - car.IMAGE.height;

        car.setX((xRightT + xLeftT) / 2);
        car.setY((yBottomT + yTopT) / 2);

        boolean expected = false;
        boolean actual = car.isOver(bed);

        if (expected != actual) {
          return false;
        }

      }



      {
        int xLeftT = 242;
        int xRightT = xLeftT + car.IMAGE.width;
        int yTopT = 104;
        int yBottomT = yTopT - car.IMAGE.height;

        car.setX((xRightT + xLeftT) / 2);
        car.setY((yBottomT + yTopT) / 2);

        boolean expected = false;
        boolean actual = car.isOver(bed);

        if (expected != actual) {
          return false;
        }

      }


    }



    car.rotate();


    if (car.getRotationsCount() % 2 == 1) {

      {
        int xLeftT = 672;
        int xRightT = xLeftT + car.IMAGE.height;
        int yTopT = 361;
        int yBottomT = yTopT - car.IMAGE.width;

        car.setX((xRightT + xLeftT) / 2);
        car.setY((yBottomT + yTopT) / 2);

        boolean expected = false;
        boolean actual = car.isOver(bed);

        if (expected != actual) {
          return false;
        }

      }



      {
        int xLeftT = 291;
        int xRightT = xLeftT + car.IMAGE.height;
        int yTopT = 71;
        int yBottomT = yTopT - car.IMAGE.width;

        car.setX((xRightT + xLeftT) / 2);
        car.setY((yBottomT + yTopT) / 2);

        boolean expected = false;
        boolean actual = car.isOver(bed);

        if (expected != actual) {
          return false;
        }

      }


    }



    // lets satisfy all conditions


    if (car2.getRotationsCount() % 2 == 0) {

      int xLeftT = 509;
      int xRightT = xLeftT + car2.IMAGE.width;
      int yTopT = 194;
      int yBottomT = yTopT - car2.IMAGE.height;

      car.setX((xRightT + xLeftT) / 2);
      car.setY((yBottomT + yTopT) / 2);

      boolean expected = true;
      boolean actual = car2.isOver(bed);

      if (expected != actual) {
        return false;
      }
    }


    car2.rotate();



    if (car2.getRotationsCount() % 2 == 1) {

      int xLeftT = 213;
      int xRightT = xLeftT + car2.IMAGE.height;
      int yTopT = 407;
      int yBottomT = yTopT - car2.IMAGE.width;

      car.setX((xRightT + xLeftT) / 2);
      car.setY((yBottomT + yTopT) / 2);

      boolean expected = true;
      boolean actual = car2.isOver(bed);

      if (expected != actual) {
        return false;
      }


    }



    // lets create one enclosed by it.


    if (teddyBear.getRotationsCount() % 2 == 0) {

      int xLeftT = 308;
      int xRightT = xLeftT + teddyBear.IMAGE.width;
      int yTopT = 358;
      int yBottomT = yTopT - teddyBear.IMAGE.height;

      car.setX((xRightT + xLeftT) / 2);
      car.setY((yBottomT + yTopT) / 2);

      boolean expected = true;
      boolean actual = teddyBear.isOver(bed);

      if (expected != actual) {
        return false;
      }
    }


    teddyBear.rotate();



    if (teddyBear.getRotationsCount() % 2 == 1) {

      int xLeftT = 308;
      int xRightT = xLeftT + teddyBear.IMAGE.height;
      int yTopT = 405;
      int yBottomT = yTopT - teddyBear.IMAGE.width;

      car.setX((xRightT + xLeftT) / 2);
      car.setY((yBottomT + yTopT) / 2);

      boolean expected = true;
      boolean actual = teddyBear.isOver(bed);

      if (expected != actual) {
        return false;
      }

    }


    return true;
  }

  /**
   * Runs all the tester methods defined in this class
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean runAllTests() {
    System.out.println("Class Furniture Testers:");
    boolean test1Result = testFurnitureConstructor1Getters();
    System.out.println("testFurnitureConstructor1Getters: " + (test1Result ? "PASS" : "FAIL"));

    boolean test2Result = testFurnitureConstructor2Getters();
    System.out.println("testFurnitureConstructor2Getters: " + (test2Result ? "PASS" : "FAIL"));

    System.out.println();
    System.out.println("Class Toy Testers:");
    boolean test3Result = testToyConstructorsGettersSetters();
    System.out.println("testToyConstructorsGettersSetters: " + (test3Result ? "PASS" : "FAIL"));

    boolean test4Result = testToyStartStopDragging();
    System.out.println("testToyStartStopDragging: " + (test4Result ? "PASS" : "FAIL"));

    boolean testToyMove = testToyMove();
    System.out.println("testToyMove: " + (testToyMove ? "PASS" : "FAIL"));

    boolean testToyRotate = testToyRotate();
    System.out.println("testToyRotate: " + (testToyRotate ? "PASS" : "FAIL"));

    boolean testToyIsOverPoint = testToyIsOverPoint();
    System.out.println("testToyIsOverPoint: " + (testToyIsOverPoint ? "PASS" : "FAIL"));

    boolean testToyIsOverFurniture = testToyIsOverFurniture();
    System.out.println("testToyIsOverFurniture: " + (testToyIsOverFurniture ? "PASS" : "FAIL"));

    return test1Result && test2Result && test3Result && test4Result && testToyMove && testToyRotate
        && testToyIsOverPoint && testToyIsOverFurniture;
  }


  /**
   * Driver method to run all the tests defined in this class
   * 
   * @param args list of command-line input arguments if any.
   */
  public static void main(String[] args) {
    Utility.runTester();
  }

}
