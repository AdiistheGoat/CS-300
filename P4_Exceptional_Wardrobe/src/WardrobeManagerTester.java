import java.text.ParseException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.io.File;
import java.util.Arrays;

// TODO: ADD FILE HEADER HERE


/**
 * A tester class for the Wardrobe Manager project. It contains various tests to check the
 * correctness of the Wardrobe and Clothing classes.
 */
public class WardrobeManagerTester {

  /**
   * Tests both of the Clothing constructors and all getters for correctness. This test accounts for
   * the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * @author Michelle
   */
  public static boolean testClothingConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the 2-argument constructor
      Clothing c = new Clothing("black t-shirt", "Gildan");

      // check that the four instance data fields have been initialized correctly
      // using the getters to do this we are also checking their correctness
      // in a bad implementation either 1) the constructor didn't intialize a data field correctly
      // OR 2) the getter doesn't return the correct value
      if (!c.getDescription().equals("black t-shirt"))
        return false;
      if (!c.getBrand().equals("Gildan"))
        return false;
      if (c.getNumOfTimesWorn() != 0)
        return false;
      if (c.getLastWornDate() != null)
        return false;

      // test the 4 argument constructor
      // same idea as the previous test case
      LocalDate date = LocalDate.of(2024, 2, 14); // create a date object for last worn
      c = new Clothing("jeans", "Levi", 3, date);
      if (!c.getDescription().equals("jeans"))
        return false;
      if (!c.getBrand().equals("Levi"))
        return false;
      if (c.getNumOfTimesWorn() != 3)
        return false;
      if (!c.getLastWornDate().equals(date))
        return false;

    } catch (
        Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests that both of the Clothing constructors throw the correct type of exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   * @author Michelle and Hobbes
   */
  public static boolean testClothingConstructorExceptions() {
    // Here we call constructors with input that should lead to an IllegalArgumentException
    // on a correct (good) implementation. ALL of these cases SHOULD throw exceptions,
    // so we test them in separate try-catch statements to verify that each individual
    // case throws an exception.

    try {
      // test the 2 argument constructor with a blank description
      new Clothing(" ", "Gildan");

      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }

    try {
      // and make sure a blank brand will also throw an exception
      new Clothing("black t-shirt", "  ");

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // test the 4 argument constructor with a blank description
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing(" ", "Gildan", 4, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // and verifying that a blank brand will also throw an exception
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing("black t-shirt", "  ", 6, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message,
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    // passed all the tests!
    return true;
  }

  /**
   * Tests for the correctness of the Clothing classes' wearClothing() method. This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testClothingWear() {
    Clothing clothing = new Clothing("Boots", "Gucci");

    // testing correct implementation of wearClothing()
    clothing.wearClothing(2015, 12, 26);
    if (clothing.getNumOfTimesWorn() != 1) {
      return false;
    }

    String dateToCompare = "2015-12-26";
    if (!clothing.getLastWornDate().toString().equals(dateToCompare)) {
      return false;
    }

    // year less than 1 should throw exception
    try {
      clothing.wearClothing(-3, 12, 21);
      return false; // this line should not execute if exception is thrown
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    // month not in [1,12] should throw exception
    try {
      clothing.wearClothing(2019, 19, 21);
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe constructor and all getters for correctness. This test accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testWardrobeConstructorAndGetters() {

    // check if any unexpected exception is thrown
    try {
      // test the constructor
      Wardrobe wardrobe = new Wardrobe(5);

      // check if the capacity has been initalized correct to 5
      if (wardrobe.capacity() != 5) {
        return false;
      }

      // check if the wardrobeSize has been correctly initialized to 0
      if (wardrobe.size() != 0) {
        return false;
      }
      // check if the Clothing Array was correctly initialized to [null, null, null, null, null]
      Clothing[] expectedWardrobe = new Clothing[5];
      if (!Arrays.equals(expectedWardrobe, wardrobe.getArray())) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests that the Wardrobe constructor throws the correct type of exception(s) with a message in
   * situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testWardrobeConstructorExceptions() {
    try {
      Wardrobe wardrobe = new Wardrobe(-1);
      return false; // this line should not execute if exception is thrown
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests that the Wardrobe's addClothing() method throws the correct type of exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothingExceptions() {
    Wardrobe wardrobe = new Wardrobe(5);
    Clothing clothing = new Clothing("Pants", "Gucci");
    Clothing sameClothing = new Clothing("pants", "gucci");

    wardrobe.addClothing(clothing);
    try {
      wardrobe.addClothing(sameClothing);
    } catch (IllegalArgumentException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe's addClothing() method for correctness. This test accounts for the fact a
   * bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothing() {
    try {
      Wardrobe wardrobe = new Wardrobe(5);
      Clothing clothing = new Clothing("Pants", "Gucci");

      wardrobe.addClothing(clothing);
      Clothing[] expectedArray = {clothing, null, null, null, null};
      if (!Arrays.equals(expectedArray, wardrobe.getArray())) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }


  /**
   * Tests the Wardrobe's getClothing() method for correctness. This test accounts for the fact a
   * bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothing() {
    Wardrobe wardrobe = new Wardrobe(5);
    Clothing clothing1 = new Clothing("Pants", "Gucci");
    Clothing clothing2 = new Clothing("Shirt", "Nike");
    wardrobe.addClothing(clothing1);
    wardrobe.addClothing(clothing2);
    try {
      Clothing actual = wardrobe.getClothing("Pants", "Gucci");
      Clothing expected = clothing1;
      if (!actual.equals(expected)) {
        return false;
      }
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true;
  }


  /**
   * Tests that the Wardrobe's getClothing() method throws the correct type of exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothingExceptions() {
    Wardrobe wardrobe = new Wardrobe(5);
    Clothing clothing1 = new Clothing("Pants", "Gucci");
    Clothing clothing2 = new Clothing("Shirt", "Nike");
    wardrobe.addClothing(clothing1);
    wardrobe.addClothing(clothing2);
    try {
      Clothing actual = wardrobe.getClothing("Jeans", "Mango");
    } catch (NoSuchElementException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests that the Wardrobe's removeClothing() method throws the correct type of exception(s) with
   * a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothingExceptions() {
    Wardrobe wardrobe = new Wardrobe(5);
    // testing for empty wardrobe
    try {
      wardrobe.removeClothing("Pants", "Gucci");
    } catch (IllegalStateException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other exception is also not good
      e.printStackTrace();
      return false;
    }

    // testing for clothing not present in wardrobe
    Clothing clothing = new Clothing("Pants", "Gucci");
    wardrobe.addClothing(clothing);
    try {
      wardrobe.removeClothing("Jeans", "Mango");
    } catch (NoSuchElementException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other exception is also not good
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe's removeClothings() method for correctness. This test accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothing() {
    Wardrobe wardrobe = new Wardrobe(5);
    Clothing clothing1 = new Clothing("Pants", "Gucci");
    Clothing clothing2 = new Clothing("Jeans", "Mango");

    wardrobe.addClothing(clothing1);
    wardrobe.addClothing(clothing2);

    try {
      wardrobe.removeClothing("Pants", "Gucci");
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    Clothing[] expectedArray = {clothing2, null, null, null, null};
    if (!Arrays.equals(expectedArray, wardrobe.getArray())) {
      return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe's removeAllClothingWornBefore() method for correctness. This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornBefore() {
    Wardrobe wardrobe = new Wardrobe(5);
    Clothing clothing1 = new Clothing("Pants", "Gucci", 2, LocalDate.of(2015, 5, 12));
    Clothing clothing2 = new Clothing("Jeans", "Mango", 2, LocalDate.of(2016, 4, 12));
    Clothing clothing3 = new Clothing("Hoodie", "Nike", 2, LocalDate.of(2016, 5, 10));
    Clothing clothing4 = new Clothing("Coat", "Zara", 2, LocalDate.of(2017, 10, 21));
    Clothing clothing5 = new Clothing("Sneakers", "Adidas", 2, LocalDate.of(2016, 5, 12));

    wardrobe.addClothing(clothing1);
    wardrobe.addClothing(clothing2);
    wardrobe.addClothing(clothing3);
    wardrobe.addClothing(clothing4);
    wardrobe.addClothing(clothing5);


    try {
      wardrobe.removeAllClothingWornBefore(2016, 5, 12);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    Clothing[] expectedArray = {clothing4, clothing5, null, null, null};
    if (!Arrays.equals(expectedArray, wardrobe.getArray())) {
      return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe's removeAllClothingWornNumTimes() method for correctness. This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornNumTimes() {
    Wardrobe wardrobe = new Wardrobe(5);
    Clothing clothing1 = new Clothing("Pants", "Gucci", 5, LocalDate.of(2015, 5, 12));
    Clothing clothing2 = new Clothing("Jeans", "Mango", 3, LocalDate.of(2016, 4, 12));
    Clothing clothing3 = new Clothing("Hoodie", "Nike", 1, LocalDate.of(2016, 5, 10));
    Clothing clothing4 = new Clothing("Coat", "Zara", 0, LocalDate.of(2017, 10, 21));
    Clothing clothing5 = new Clothing("Sneakers", "Adidas", 6, LocalDate.of(2016, 5, 12));

    wardrobe.addClothing(clothing1);
    wardrobe.addClothing(clothing2);
    wardrobe.addClothing(clothing3);
    wardrobe.addClothing(clothing4);
    wardrobe.addClothing(clothing5);

    try {
      wardrobe.removeAllClothingWornNumTimes(5);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    Clothing[] expectedArray = {clothing1, clothing5, null, null, null};
    if (!Arrays.equals(expectedArray, wardrobe.getArray())) {
      return false;
    }

    return true;
  }

  /**
   * Tests that the Wardrobe's parseClothing() method throws the correct type of exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothingExceptions() {

    // testing for incorrect number of items in string
    String incorrectItems = "Gucci,Pants,5";
    try {
      Wardrobe.parseClothing(incorrectItems);
    } catch (ParseException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other exception is also not good
      e.printStackTrace();
      return false;
    }

    // testing for incorrect timesWorn format
    incorrectItems = "Gucci,Pants,hello,12/12/2015";
    try {
      Wardrobe.parseClothing(incorrectItems);
    } catch (ParseException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other exception is also not good
      e.printStackTrace();
      return false;
    }

    // testing for incorrect date format
    incorrectItems = "Gucci,Pants,hello,12.12.2015";
    try {
      Wardrobe.parseClothing(incorrectItems);
    } catch (ParseException e) {
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other exception is also not good
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the Wardrobe's parseClothing method for correctness. This test accounts for the fact a
   * bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothing() {
    String clothingObjectString = "Pants,Gucci,10/31/0215,5";
    Clothing actual = null;
    try {
      actual = Wardrobe.parseClothing(clothingObjectString);
    } catch (ParseException e) {
        if (e.getMessage() == null || e.getMessage().isBlank())
          return false;
      } catch (Exception e) { // any other exception is also not good
        e.printStackTrace();
        return false;
      }

    Clothing expected = new Clothing("Pants", "Gucci", 5, LocalDate.of(215, 10, 31));

    // checking if description and brand are correct
    if (!expected.equals(actual)) {
      return false;
    }

    // checking if last worn date is correct
    if (!expected.getLastWornDate().equals(actual.getLastWornDate())) {
      return false;
    }

    // checking if number of times worn is correct
    if (expected.getNumOfTimesWorn() != actual.getNumOfTimesWorn()) {
      return false;
    }

    return true;
  }

  /**
   * Runs all testing methods and prints out their results.
   *
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllTests() {
    boolean test1 = testClothingConstructorExceptions();
    System.out.println("testClothingConstructorExceptions(): " + (test1 ? "pass" : "FAIL"));

    boolean test2 = testClothingConstructorAndGetters();
    System.out.println("testClothingConstructorAndGetters(): " + (test2 ? "pass" : "FAIL"));

    boolean test3 = testClothingWear();
    System.out.println("testClothingWear(): " + (test3 ? "pass" : "FAIL"));

    boolean test4 = testWardrobeConstructorAndGetters();
    System.out.println("testWardrobeConstructorAndGetters(): " + (test4 ? "pass" : "FAIL"));

    boolean test5 = testWardrobeConstructorExceptions();
    System.out.println("testWardrobeConstructorExceptions(): " + (test5 ? "pass" : "FAIL"));

    boolean test6 = testAddClothingExceptions();
    System.out.println("testAddClothingExceptions(): " + (test6 ? "pass" : "FAIL"));

    boolean test7 = testAddClothing();
    System.out.println("testAddClothing(): " + (test7 ? "pass" : "FAIL"));

    boolean test8 = testGetClothing();
    System.out.println("testGetClothing(): " + (test8 ? "pass" : "FAIL"));

    boolean test9 = testGetClothingExceptions();
    System.out.println("testGetClothingExceptions(): " + (test9 ? "pass" : "FAIL"));

    boolean test10 = testRemoveClothing();
    System.out.println("testRemoveClothing(): " + (test10 ? "pass" : "FAIL"));

    boolean test11 = testRemoveClothingExceptions();
    System.out.println("testRemoveClothingExceptions(): " + (test11 ? "pass" : "FAIL"));

    boolean test12 = testRemoveAllClothingWornBefore();
    System.out.println("testRemoveAllClothingWornBefore(): " + (test12 ? "pass" : "FAIL"));

    boolean test13 = testRemoveAllClothingWornNumTimes();
    System.out.println("testRemoveAllClothingWornNumTimes(): " + (test13 ? "pass" : "FAIL"));

    boolean test14 = testParseClothingExceptions();
    System.out.println("testParseClothingExceptions(): " + (test14 ? "pass" : "FAIL"));

    boolean test15 = testParseClothing();
    System.out.println("testParseClothing(): " + (test15 ? "pass" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10 && test11 && test12 && test13 && test14 && test15;
  }

  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());
  }
}
