//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Packing
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
import java.util.ArrayList;

/**
 * Class used for testing the methods in the Packing class.
 */
public class PackingTester {
  /**
   * Tester method for the Packing.rushedPacking() method base cases. It should test at least the
   * following scenarios: - There are no items left to pack in the suitcase - There are items left
   * to pack, but none of them fit
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingBaseTest() {

    {
      Suitcase case1 = new Suitcase(15, 20, new ArrayList<Item>());

      Suitcase expected = case1;
      Suitcase actual = Packing.rushedPacking(case1);

      if (expected != actual) {
        return false;
      }
    }


    {
      ArrayList<Item> itemList = new ArrayList<Item>();
      ArrayList<Item> expectedItemsAdded = new ArrayList<Item>();
      ArrayList<Item> expectedItemsRemaining = new ArrayList<Item>();

      Item item1 = new Item("Item 1", 50, 30);
      Item item2 = new Item("Item 2", 87, 40);
      Item item3 = new Item("Item 3", 10, 60);

      itemList.add(item1);
      itemList.add(item2);
      itemList.add(item3);

      Suitcase case2 = new Suitcase(15, 40, itemList);

      Suitcase expected = case2;
      Suitcase actual = Packing.rushedPacking(case2);

      if (expected != actual) {
        return false;
      }

    }

    return true;
  }

  /**
   * Tester method for the Packing.rushedPacking() method recursive cases. It should test at least
   * the following scenarios: - All the items remaining can fit in the suitcase - At least one item
   * remaining cannot fit in the suitcase
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingRecursiveTest() {

    Item A = new Item("A", 4, 2);
    Item B = new Item("B", 6, 3);
    Item C = new Item("C", 7, 4);
    Item D = new Item("D", 4, 5);
    Item E = new Item("E", 4, 5);
    Item F = new Item("F", 5, 4);
    Item G = new Item("G", 2, 6);

    {
      ArrayList<Item> itemList = new ArrayList<Item>();
      ArrayList<Item> expectedItemsAdded = new ArrayList<Item>();
      ArrayList<Item> expectedItemsRemaining = new ArrayList<Item>();

      itemList.add(A);
      itemList.add(B);
      itemList.add(C);
      itemList.add(D);

      expectedItemsAdded.add(A);
      expectedItemsAdded.add(B);
      expectedItemsAdded.add(C);
      expectedItemsAdded.add(D);

      Suitcase case1 = new Suitcase(10, 10, itemList);
      Suitcase packedCase1 = Packing.rushedPacking(case1);

      if (!(expectedItemsRemaining.equals(packedCase1.getUnpackedItems()))) {
        return false;
      }

      if (!(expectedItemsAdded.equals(packedCase1.getPackedItems()))) {
        return false;
      }

    }



    {

      ArrayList<Item> itemList = new ArrayList<Item>();
      ArrayList<Item> expectedItemsAdded = new ArrayList<Item>();
      ArrayList<Item> expectedItemsRemaining = new ArrayList<Item>();

      itemList.add(A);
      itemList.add(B);
      itemList.add(C);
      itemList.add(D);
      itemList.add(E);
      itemList.add(F);
      itemList.add(G);

      expectedItemsAdded.add(A);
      expectedItemsAdded.add(B);
      expectedItemsAdded.add(C);
      expectedItemsAdded.add(D);

      expectedItemsRemaining.add(E);
      expectedItemsRemaining.add(F);
      expectedItemsRemaining.add(G);

      Suitcase case2 = new Suitcase(10, 10, itemList);
      Suitcase packedCase2 = Packing.rushedPacking(case2);

      if (!(expectedItemsRemaining.equals(packedCase2.getUnpackedItems()))) {
        return false;
      }

      if (!(expectedItemsAdded.equals(packedCase2.getPackedItems()))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tester method for the Packing.greedyPacking() method base cases. It should test at least the
   * following scenarios: - There are no items left to pack in the suitcase - There are items left
   * to pack, but none of them fit
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingBaseTest() {

    {
      Suitcase case1 = new Suitcase(15, 20, new ArrayList<Item>());

      Suitcase expected = case1;
      Suitcase actual = Packing.greedyPacking(case1);

      if (expected != actual) {
        return false;
      }
    }


    {
      ArrayList<Item> itemList = new ArrayList<Item>();
      ArrayList<Item> expectedItemsAdded = new ArrayList<Item>();
      ArrayList<Item> expectedItemsRemaining = new ArrayList<Item>();

      Item item1 = new Item("Item 1", 50, 30);
      Item item2 = new Item("Item 2", 87, 40);
      Item item3 = new Item("Item 3", 10, 60);

      itemList.add(item1);
      itemList.add(item2);
      itemList.add(item3);

      Suitcase case2 = new Suitcase(15, 40, itemList);

      Suitcase expected = case2;
      Suitcase actual = Packing.greedyPacking(expected);

      if (expected != actual) {
        return false;
      }

    }

    return true;
  }

  /**
   * Tester method for the Packing.greedyPacking() method recursive cases. It should test at least
   * the following scenarios: - At least one item is packed out of order (an item with a higher
   * index is packed before an item with a lower index) - A scenario where the greedy packing method
   * packs more of the suitcase than the rushed packing (you can use the example given in the
   * writeup)
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingRecursiveTest() {

    Item A = new Item("A", 4, 2);
    Item B = new Item("B", 6, 3);
    Item C = new Item("C", 7, 4);
    Item D = new Item("D", 4, 5);
    Item E = new Item("E", 4, 5);
    Item F = new Item("F", 5, 4);
    Item G = new Item("G", 2, 6);

    {

      ArrayList<Item> itemList = new ArrayList<Item>();
      ArrayList<Item> expectedItemsAdded = new ArrayList<Item>();

      itemList.add(A);
      itemList.add(B);
      itemList.add(G);
      itemList.add(E);
      itemList.add(D);
      itemList.add(F);
      itemList.add(C);
      // E comes first since E and D have same area but E has comes before (has a lesser index)
      expectedItemsAdded.add(C);
      expectedItemsAdded.add(E);
      expectedItemsAdded.add(D);
      expectedItemsAdded.add(G);

      Suitcase case2 = new Suitcase(10, 10, itemList);

      Suitcase greedy2 = Packing.greedyPacking(case2);

      if (!(greedy2.getPackedItems().equals(expectedItemsAdded))) {
        return false;
      }

    }


    {

      ArrayList<Item> itemList = new ArrayList<Item>();
      itemList.add(A);
      itemList.add(B);
      itemList.add(C);
      itemList.add(D);
      itemList.add(E);
      itemList.add(F);
      itemList.add(G);


      Suitcase case1 = new Suitcase(10, 10, itemList);

      Suitcase rushed1 = Packing.rushedPacking(case1);
      Suitcase greedy1 = Packing.greedyPacking(case1);

      if (rushed1.areaPacked() >= greedy1.areaPacked()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tester method for the Packing.optimalPacking() method. This tester should test the
   * optimalPacking() method by randomly generating at least TEN (10) different scenarios, and
   * randomly generating at least ONE-HUNDRED (100) different packing solutions for EACH of the
   * scenarios. Each scenario should have at least FIVE (5) random items, and the suitcases should
   * be of size at least 5x5. If any random solution is better than the optimal packing then it is
   * not actually optimal, so the method does not pass the test. You should use the Utilities method
   * to generate random lists of items, and to randomly pack the suitcases.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean optimalPackingRandomTest() {

    for (int i = 0; i < 10; i++) {
      ArrayList<Item> itemList = Utilities.randomItems(5);

      Suitcase case1 = new Suitcase(10, 10, itemList);
      Suitcase optimalCase1 = Packing.optimalPacking(case1);

      Suitcase caseTempPack;
      for (int j = 0; j < 100; j++) {
        caseTempPack = Utilities.randomlyPack(case1);

        if (caseTempPack.areaPacked() > optimalCase1.areaPacked()) {
          return false;
        }

      }
    }


    for (int i = 0; i < 12; i++) {
      ArrayList<Item> itemList = Utilities.randomItems(7);

      Suitcase case1 = new Suitcase(5, 5, itemList);
      Suitcase optimalCase1 = Packing.optimalPacking(case1);

      Suitcase caseTempPack;
      for (int j = 0; j < 150; j++) {
        caseTempPack = Utilities.randomlyPack(case1);

        if (caseTempPack.areaPacked() > optimalCase1.areaPacked()) {
          return false;
        }

      }
    }


    for (int i = 0; i < 15; i++) {
      ArrayList<Item> itemList = Utilities.randomItems(7);

      Suitcase case1 = new Suitcase(6, 6, itemList);
      Suitcase optimalCase1 = Packing.optimalPacking(case1);

      Suitcase caseTempPack;
      for (int j = 0; j < 200; j++) {
        caseTempPack = Utilities.randomlyPack(case1);

        if (caseTempPack.areaPacked() > optimalCase1.areaPacked()) {
          return false;
        }

      }
    }

    return true;
  }

  public static void main(String[] args) {
    boolean allPass = true;
    String printFormat = "%-29s %s\n";

    boolean rushedBase = rushedPackingBaseTest();
    allPass &= rushedBase;
    System.out.printf(printFormat, "rushedPackingBaseTest():", rushedBase);

    boolean rushedRecur = rushedPackingRecursiveTest();
    allPass &= rushedRecur;
    System.out.printf(printFormat, "rushedPackingRecursiveTest():", rushedRecur);

    boolean greedyBase = greedyPackingBaseTest();
    allPass &= greedyBase;
    System.out.printf(printFormat, "greedyPackingBaseTest():", greedyBase);

    boolean greedyRecur = greedyPackingRecursiveTest();
    allPass &= greedyRecur;
    System.out.printf(printFormat, "greedyPackingRecursiveTest():", greedyRecur);

    boolean optimalRandom = optimalPackingRandomTest();
    allPass &= optimalRandom;
    System.out.printf(printFormat, "optimalPackingRandomTest():", optimalRandom);

    System.out.printf(printFormat, "All tests:", allPass);

  }
}
