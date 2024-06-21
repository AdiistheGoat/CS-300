//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: WardRobe Manager
// Course: CS 300 Spring 2024
//
// Author: Aditya Goyal
// Email: agoyal33@wisc.edu
// Lecturer: Hobbes LeGault
//
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
//
// Online Sources:
// https://www.geeksforgeeks.org/java-util-arrays-deepequals-java/ -- helped me gain a deeper
//////////////// understanding of the method.
// https://www.geeksforgeeks.org/arrays-copyof-in-java-with-examples/ -- helped me gain a deeper
//////////////// understanding of the method.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class contains several testing methods that test the methods in the WardrobeManger java
 * file.
 */

public class WardrobeManagerTester {



  //// CONTAINS

  /**
   * example test method to test if the method correctly identifies whether a particular clothing
   * item is present in an empty wardrobe.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testContainsEmpty() {

    String[][] wardrobe = {{null}, {null}, {null}};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);

    int size = 0;

    boolean expected = false;
    boolean actual = WardrobeManager.containsClothing("Black t-shirt", "Hanes", wardrobeCopy, size);


    if (expected != actual) {
      return false;
    }

    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) {
      return false;
    }

    return true;

  }



  /**
   * example test method to test if the method correctly identifies whether a particular clothing
   * item is present in the wardrobe when it actually is.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */

  public static boolean testContainsTrue() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"Black t-shirt", "Hanes", "never"},
        {"Dark blue jeans", "Levi", "never"}, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = true;
    boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) another test method call, this time with case difference (that should still match!)
    actual = WardrobeManager.containsClothing("dark blue jeans", "LEVI", wardrobe, size);
    if (expected != actual)
      return false;

    // (4) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }


  /**
   * 
   * example test method to test if the method correctly identifies whether a particular clothing
   * item is present in the wardrobe when it actually isn't.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testContainsFalse() {


    String[][] wardrobe =
        {{"White Pant", "Free Authority", "2023-08-05"}, {"Batman t-shirt", "Jack&Jones", "never"},
            {"black jeans", "LEVIS", "2019-03-29"}, null, null, null, null};

    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);

    int size = 3;

    boolean expected = false;
    boolean actual =
        WardrobeManager.containsClothing(" Black Shorts  ", " Levis ", wardrobeCopy, size);

    if (expected != actual) {
      return false;
    }

    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) {
      return false;
    }


    return true;
  }



  //// ADD

  /**
   * tests the method for adding a new clothing item to an EMPTY oversize array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testAddToEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] empty = new String[10][];
    int size = 0;
    int expected = 1;
    int actual = WardrobeManager.addClothing("green crop top", "H&M", empty, size);

    // (2) verify the expected return value
    if (expected != actual)
      return false;

    // (3) verify that the provided array was updated correctly
    if (empty[0] == null)
      return false;
    if (!empty[0][0].equalsIgnoreCase("green crop top") || !empty[0][1].equalsIgnoreCase("H&M")
        || !empty[0][2].equals("never"))
      return false;

    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i = 1; i < empty.length; i++) {
      if (empty[i] != null)
        return false;
    }

    // (5) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }


  /**
   * tests method for adding a new clothing item to an non-empty oversize array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testAddNonEmpty() {

    String[][] nonEmpty = new String[][] {{"black t-shirt", "Hanes", "2012-09-05"},
        {"Dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;

    int expected = 3;
    int actual = WardrobeManager.addClothing("blue skirt", "AND", nonEmpty, size);

    if (expected != actual) {
      return false;
    }


    if (nonEmpty[size] == null) {
      return false;
    }


    if (!(nonEmpty[size][0].equals("blue skirt") && (nonEmpty[size][1].equals("AND"))
        && (nonEmpty[size][2].equals("never")))) {

      return false;
    }

    for (int i = size + 1; i < nonEmpty.length; i++) {

      if (nonEmpty[i] != null) {
        return false;
      }
    }

    return true;
  }


  /**
   * 
   * tests the method for adding a new clothing item to an non-empty oversize array in which the
   * clothing item is already present.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   * 
   */
  public static boolean testAddDuplicate() {

    String[][] duplicateArray = new String[][] {{"black t-shirt", "Hanes", "2012-09-05"},
        {"Dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;

    int expected = 2;
    int actual = WardrobeManager.addClothing("black t-shirt", "Hanes", duplicateArray, size);

    if (expected != actual) {
      return false;
    }


    for (int i = size; i < duplicateArray.length; i++) {

      if (duplicateArray[i] != null) {
        return false;
      }
    }

    return true;

  }


  /**
   * 
   * tests the method for adding a new clothing item to an non-empty(full) array aka a full
   * wardrobe.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   * 
   */
  public static boolean testAddToFull() {


    String[][] fullArray = new String[][] {{"Black jeans", "LEVIS", "2019-03-29"},
        {"Dark blue jeans", "Levi", "never"}, {"White pant", "Free Authority", "2023-08-05"}};
    String[][] fullArrayCopy = Arrays.copyOf(fullArray, fullArray.length);

    int size = 3;

    int expected = 3;
    int actual =
        WardrobeManager.addClothing("long-sleeve t shirt", "LifeStyle", fullArrayCopy, size);


    if (expected != actual) {
      return false;
    }

    if (!(Arrays.deepEquals(fullArray, fullArrayCopy))) {
      return false;
    }


    return true;
  }



  //// INDEX OF

  /**
   * tests the method if it provides the correct indication/return value when the user tries to
   * search for a clothing item in an empty array aka empty cupboard.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testIndexOfEmpty() {

    String[][] testEmpty = {null, null, null, null, null, null};
    String[][] testEmptyCopy = Arrays.copyOf(testEmpty, testEmpty.length);

    int size = 0;

    int expected = -1;
    int actual = WardrobeManager.indexOfClothing("Black T-shirt", "Gas", testEmptyCopy, size);

    if (expected != actual) {
      return false;
    }

    if (!(Arrays.deepEquals(testEmpty, testEmptyCopy))) {
      return false;
    }

    return true;
  }


  /**
   * 
   * tests the method if it provides the correct indication/return value when the user tries to
   * search for a clothing item that is present in the last position of the array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testIndexOfFirstLast() {

    String[][] testFirstLast =
        {{"White pant", "Free Authority", "2023-08-05"}, {"Batman t-shirt", "Jack&Jones", "never"},
            {"Black jeans", "LEVIS", "2019-03-29"}, null, null, null, null};

    String[][] testFirstLastCopy = Arrays.copyOf(testFirstLast, testFirstLast.length);

    int size = 3;

    int expected = 2;
    int actual = WardrobeManager.indexOfClothing("bLack jEans", "LeViS", testFirstLastCopy, size);

    if (expected != actual) {
      return false;
    }


    if (!(Arrays.deepEquals(testFirstLast, testFirstLastCopy))) {
      return false;
    }


    return true;
  }


  /**
   * tests the method if it provides the correct indication/return value when the user tries to
   * search for a clothing item that is present in the middle of the array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testIndexOfMiddle() {

    String[][] testMiddle =
        {{"White pant", "Free Authority", "2023-08-05"}, {"Batman t-shirt", "Jack&Jones", "never"},
            {"Black jeans", "LEVIS", "2019-03-29"}, null, null, null, null};

    String[][] testMiddleCopy = Arrays.copyOf(testMiddle, testMiddle.length);

    int size = 3;

    int expected = 1;
    int actual =
        WardrobeManager.indexOfClothing("BATMAN T-SHIRT", "Jack&Jones", testMiddleCopy, size);

    if (expected != actual) {
      return false;
    }


    if (!(Arrays.deepEquals(testMiddle, testMiddleCopy))) {
      return false;
    }


    return true;

  }


  /**
   * 
   * tests the method if it provides the correct indication/return value when the user tries to
   * search for a clothing item that is not present in the wardrobe.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testIndexOfNoMatch() {

    String[][] testNoMatch =
        {{"White pant", "Free Authority", "2023-08-05"}, {"Batman t-shirt", "Jack&Jones", "never"},
            {"Black jeans", "LEVIS", "2019-03-29"}, null, null, null, null};

    String[][] testNoMatchCopy = Arrays.copyOf(testNoMatch, testNoMatch.length);

    int size = 3;

    int expected = -1;
    int actual = WardrobeManager.indexOfClothing("Orange trousers", "Gas", testNoMatchCopy, size);

    if (expected != actual) {
      return false;
    }


    if (!(Arrays.deepEquals(testNoMatch, testNoMatchCopy))) {
      return false;
    }
    return true;
  }



  //// WEAR

  /**
   * 
   * tests whether the method correctly identifies a particular clothing that the user wishes to
   * wear and updates its date , when the clothing item is actually there in the array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testWearClothingTrue() {

    String[][] wear = new String[][] {{"Black t-shirt", "Hanes", "2012-09-05"},
        {"Dark blue jeans", "Levi", "never"}, {"Maroon trousers", "GAS", "2020-08-06"}, null, null};
    int size = 3;

    boolean expectedValue = true;
    boolean actualValue =
        WardrobeManager.wearClothing("MAROON tRoUsErS", "Gas", "2024-01-29", wear, size);

    if (expectedValue != actualValue) {
      return false;
    }

    return true;
  }


  /**
   * 
   * tests whether the method provides the correct indication when a user tries to search for a
   * clothing item to wear when it's not actually there in the array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testWearClothingNoMatch() {

    String[][] wear = new String[][] {{"Black t-shirt", "Hanes", "2012-09-05"},
        {"Dark blue jeans", "LEVIS", "never"}, {"Maroon trousers", "GAS", "2020-08-06"}, null,
        null};
    int size = 3;

    boolean expectedValue = false;
    boolean actualValue =
        WardrobeManager.wearClothing("MAROOON tRoUsErS", "Levis", "2024-01-29", wear, size);

    if (expectedValue != actualValue) {
      return false;
    }



    return true;


  }



  //// BRAND COUNT

  /**
   * 
   * tests if the method returns the correct value for the no of clothing items of a specific brand
   * when every clothing item in the array aka the wardrobe is of that brand.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testBrandCountAllMatch() {

    String[][] allBrandMatch = new String[][] {{"Black jeans", "LEVIS", "2019-03-29"},
        {"Yellow crop top", "LEVIS", "never"}, {"White pant", "LEVIS", "2023-08-05"}, null, null,
        null};

    String[][] allBrandMatchCopy = Arrays.copyOf(allBrandMatch, allBrandMatch.length);

    int size = 3;

    int expected = 3;
    int actual = WardrobeManager.getBrandCount("LeViS", allBrandMatchCopy, size);


    if (expected != actual) {
      return false;
    }

    if (!(Arrays.deepEquals(allBrandMatch, allBrandMatchCopy))) {
      return false;
    }


    return true;
  }


  /**
   * 
   * tests if the method returns the correct value for the no of clothing items of a specific brand
   * when some clothing items in the array aka the wardrobe is of that brand.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testBrandCountSomeMatch() {

    String[][] someBrandMatch =
        new String[][] {{"Black jeans", "LEVIS", "2019-03-29"}, {"Yellow crop top", "GAS", "never"},
            {"White pant", "LEVIS", "2023-08-05"}, {"Brown t-shirt", "GAS", "never"}, null, null};

    String[][] someBrandMatchCopy = Arrays.copyOf(someBrandMatch, someBrandMatch.length);

    int size = 4;

    int expected = 2;
    int actual = WardrobeManager.getBrandCount("gAs", someBrandMatchCopy, size);


    if (expected != actual) {
      return false;
    }

    if (!(Arrays.deepEquals(someBrandMatch, someBrandMatchCopy))) {
      return false;
    }

    return true;

  }

  /**
   * 
   * tests if the method returns the correct value for the no of clothing items of a specific brand
   * when no clothing items in the array aka the wardrobe is of that brand.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testBrandCountNoMatch() {

    String[][] noMatch =
        new String[][] {{"Black jeans", "LEVIS", "2019-03-29"}, {"Yellow crop top", "GAS", "never"},
            {"White pant", "ShopStop", "2023-08-05"}, null, null, null};

    String[][] noMatchCopy = Arrays.copyOf(noMatch, noMatch.length);

    int size = 3;

    int expected = 0;
    int actual = WardrobeManager.getBrandCount("AND", noMatchCopy, size);


    if (expected != actual) {
      return false;
    }

    if (!(Arrays.deepEquals(noMatch, noMatchCopy))) {
      return false;
    }


    return true;
  }



  //// COUNT UNWORN

  /**
   * 
   * 
   * tests if the method returns the correct value for the no of unworn clothing items when all
   * clothing items in the array aka the wardrobe are unworn.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testUnwornCountAllMatch() {

    String[][] allUnwornMatch =
        new String[][] {{"Black jeans", "LEVIS", "never"}, {"Yellow crop top", "GaS", "never"},
            {"White pant", "ShopStop", "never"}, null, null, null, null};

    String[][] allUnwornMatchCopy = Arrays.copyOf(allUnwornMatch, allUnwornMatch.length);

    int size = 3;

    int expected = 3;
    int actual = WardrobeManager.getNumUnwornClothes(allUnwornMatchCopy, size);

    if (expected != actual) {
      return false;
    }

    if (!(Arrays.deepEquals(allUnwornMatch, allUnwornMatchCopy))) {
      return false;
    }


    return true;
  }


  /**
   * 
   * tests if the method returns the correct value for the no of unworn clothing items when some
   * clothing items in the array aka the wardrobe are unworn.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testUnwornCountSomeMatch() {

    String[][] allUnwornMatch = new String[][] {{"Black jeans", "LEVIS", "never"},
        {"Yellow crop top", "GAS", "2024-01-19"}, {"White pant", "ShopStop", "never"},
        {"Blue crop top", "GAS", "2013-04-08"}, null, null, null, null};
    String[][] allUnwornMatchCopy = Arrays.copyOf(allUnwornMatch, allUnwornMatch.length);

    int size = 4;

    int expected = 2;
    int actual = WardrobeManager.getNumUnwornClothes(allUnwornMatchCopy, size);

    if (expected != actual) {
      return false;
    }

    if (!(Arrays.deepEquals(allUnwornMatch, allUnwornMatchCopy))) {
      return false;
    }


    return true;

  }


  /**
   * 
   * 
   * tests if the method returns the correct value for the no of unworn clothing items when no
   * clothing items in the array aka the wardrobe are unworn.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testUnwornCountNoMatch() {
    String[][] allUnwornMatch = new String[][] {{"Black jeans", "LEVIS", "2023-06-23"},
        {"Yellow crop top", "GAS", "2024-01-19"}, {"White pant", "ShopStop", "2012-07-13"}, null,
        null, null, null};
    String[][] allUnwornMatchCopy = Arrays.copyOf(allUnwornMatch, allUnwornMatch.length);

    int size = 3;

    int expected = 0;
    int actual = WardrobeManager.getNumUnwornClothes(allUnwornMatchCopy, size);

    if (expected != actual) {
      return false;
    }

    if (!(Arrays.deepEquals(allUnwornMatch, allUnwornMatchCopy))) {
      return false;
    }


    return true;
  }



  //// MOST RECENTLY WORN

  /**
   * tests the method for verifying that the most recently worn item is found correctly.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */


  public static boolean testMostRecentlyWorn() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"Three wolf moon shirt", "The Mountain", "2023-01-25"},
        {"Black t-shirt", "Hanes", "2024-12-19"}, {"Grey UW hoodie", "gildan", "2020-03-16"},
        {"a very cool knit sweater", "Hobbes", "never"}, {"Jorts", "Levi", "2021-01-25"}, null,
        null, null};


    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 5;
    int expected = 1;
    int actual = WardrobeManager.getMostRecentlyWorn(wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;


    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }



  //// REMOVE BY INDEX

  /**
   * 
   * 
   * tests that the method returns the correct revised size of the array after removing the last
   * item (clothing) in the array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testRemoveLastItem() {

    String[][] wardrobe =
        {{"Black t-shirt", "Hanes", "2023-12-19"}, {"Grey UW hoodie", "gildan", "2020-03-16"},
            {"Dark blue jeans", "Levis", "2024-01-25"}, null, null, null};

    int size = 3;

    int expected = 2;
    int actual = WardrobeManager.removeClothingAtIndex(size - 1, wardrobe, size);

    if (expected != actual) {
      return false;
    }

    for (int i = 0; i < expected; i++) {
      if (wardrobe[i] == null) {
        return false;
      }
    }

    return true;

  }


  /**
   * 
   * tests that the method returns the correct revised size of the array after removing the first
   * item (clothing) in the array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testRemoveFirstItem() {

    String[][] wardrobe =
        {{"Black t-shirt", "Hanes", "2023-12-19"}, {"Grey UW hoodie", "gildan", "2020-03-16"},
            {"Dark blue jeans", "LEVIS", "2024-01-25"}, null, null, null};

    int size = 3;

    int expected = 2;
    int actual = WardrobeManager.removeClothingAtIndex(0, wardrobe, size);

    if (expected != actual) {
      return false;
    }

    for (int i = 0; i < expected; i++) {
      if (wardrobe[i] == null) {
        return false;
      }
    }

    return true;

  }


  /**
   * 
   * tests that the method returns the correct revised size of the array after the user wishes to
   * remove the clothing at an invalid index.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testRemoveBadIndex() {

    String[][] wardrobe =
        {{"Black t-shirt", "Hanes", "2023-12-19"}, {"Grey UW hoodie", "gildan", "2020-03-16"},
            {"Dark blue jeans", "LEVIS", "2024-01-25"}, null, null, null};

    int size = 3;

    int expected = 3;
    int actual = WardrobeManager.removeClothingAtIndex(size, wardrobe, size);

    if (expected != actual) {
      return false;
    }

    for (int i = 0; i < expected; i++) {
      if (wardrobe[i] == null) {
        return false;
      }
    }

    return true;

  }



  //// REMOVE ALL UNWORN

  /**
   * 
   * tests that the method returns the correct revised size of the array, (which is the earlier size
   * of the array) , after the user wishes to remove all the unworn items in the array, when there
   * aren't any items in the array that are unworn.
   * 
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testRemoveUnwornNoMatch() {

    String[][] wardrobe =
        {{"Black t-shirt", "Hanes", "2023-12-19"}, {"Grey UW hoodie", "gildan", "2020-03-16"},
            {"Dark blue jeans", "Levi", "2024-01-25"}, null, null, null};

    int size = 3;

    int expected = 3;
    int actual = WardrobeManager.removeAllUnworn(wardrobe, size);

    if (expected != actual) {
      return false;
    }


    for (int i = 0; i < expected; i++) {

      if (wardrobe[i] == null) {
        return false;
      }
    }

    return true;
  }


  /**
   * 
   * tests that the method returns the correct revised size of the array after the user wishes to
   * remove all the unworn items in the array, when there are some of the items in the array are
   * unworn.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testRemoveUnwornSomeMatch() {

    String[][] wardrobe = {{"Black t-shirt", "Hanes", "never"},
        {"Grey UW hoodie", "gildan", "2020-03-16"}, {"Dark blue jeans", "Levi", "never"},
        {"Green cabled sweater", "handmade", "2013-05-04"}, null, null, null};

    int size = 4;

    int expected = 2;
    int actual = WardrobeManager.removeAllUnworn(wardrobe, size);

    if (expected != actual) {
      return false;
    }


    for (int i = 0; i < expected; i++) {

      if (wardrobe[i] == null) {
        return false;
      }
    }

    return true;
  }


  /**
   * 
   * tests that the method returns the correct revised size of the array (which is 0) after the user
   * wishes to remove all the unworn items in the array, when all of the items in the array are
   * unworn.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct.
   */
  public static boolean testRemoveUnwornAllMatch() {

    String[][] wardrobe =
        {{"Black t-shirt", "Hanes", "never"}, {"Grey UW hoodie", "gildan", "never"},
            {"Dark blue jeans", "Levi", "never"}, null, null, null};

    int size = 3;

    int expected = 0;
    int actual = WardrobeManager.removeAllUnworn(wardrobe, size);

    if (expected != actual) {
      return false;
    }


    for (int i = 0; i < expected; i++) {

      if (wardrobe[i] == null) {
        return false;
      }
    }

    return true;
  }



  /**
   * Calls all tester methods and displays the results of the tests.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("CONTAINS:\n  " + (testContainsEmpty() ? "pass" : "FAIL") + ", "
        + (testContainsTrue() ? "pass" : "FAIL") + ", " + (testContainsFalse() ? "pass" : "FAIL"));
    System.out.println("ADD:\n  " + (testAddToEmpty() ? "pass" : "FAIL") + ", "
        + (testAddNonEmpty() ? "pass" : "FAIL") + ", " + (testAddDuplicate() ? "pass" : "FAIL")
        + ", " + (testAddToFull() ? "pass" : "FAIL"));
    System.out.println("INDEX OF:\n  " + (testIndexOfEmpty() ? "pass" : "FAIL") + ", "
        + (testIndexOfFirstLast() ? "pass" : "FAIL") + ", "
        + (testIndexOfMiddle() ? "pass" : "FAIL") + ", "
        + (testIndexOfNoMatch() ? "pass" : "FAIL"));
    System.out.println("WEAR:\n  " + (testWearClothingTrue() ? "pass" : "FAIL") + ", "
        + (testWearClothingNoMatch() ? "pass" : "FAIL"));
    System.out.println("BRAND COUNT:\n  " + (testBrandCountAllMatch() ? "pass" : "FAIL") + ", "
        + (testBrandCountSomeMatch() ? "pass" : "FAIL") + ", "
        + (testBrandCountNoMatch() ? "pass" : "FAIL"));
    System.out.println("UNWORN COUNT:\n  " + (testUnwornCountAllMatch() ? "pass" : "FAIL") + ", "
        + (testUnwornCountSomeMatch() ? "pass" : "FAIL") + ", "
        + (testUnwornCountNoMatch() ? "pass" : "FAIL"));
    System.out.println("MOST RECENTLY WORN:\n  " + (testMostRecentlyWorn() ? "pass" : "FAIL"));
    System.out.println("REMOVE BY INDEX:\n  " + (testRemoveLastItem() ? "pass" : "FAIL") + ", "
        + (testRemoveFirstItem() ? "pass" : "FAIL") + ", "
        + (testRemoveBadIndex() ? "pass" : "FAIL"));
    System.out.println("REMOVE UNWORN:\n  " + (testRemoveUnwornNoMatch() ? "pass" : "FAIL") + ", "
        + (testRemoveUnwornSomeMatch() ? "pass" : "FAIL") + ", "
        + (testRemoveUnwornAllMatch() ? "pass" : "FAIL"));
  }

}
