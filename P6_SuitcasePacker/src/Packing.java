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
 * Class used for packing a 2D suitcase with items using various strategies.
 */
public class Packing {
  /**
   * Tries to pack each item in the items list in-order. If an item can fit, it must be packed.
   * Otherwise, it should be skipped. Must be a recursive method.
   *
   * @param suitcase current Suitcase object
   * @return a Suitcase representing the outcome of a strategy in which the items were attempted to
   *         be packed in-order.
   */


  public static Suitcase rushedPacking(Suitcase suitcase) {

    // storing the unpacked Items array in the original suitcase to an arrayList.
    ArrayList<Item> unpackedItems = suitcase.getUnpackedItems();


    // We are iterating through every item in the unpackedItems list in order of occurence in the
    // unpackedItem list to see which one we can pack in the current configuration of the Suitcase
    // instance.

    // Once we find an item that we can pack we recursively call the method on the
    // modified suitcase (packed with the item that we found that we could pack(lets call that item
    // X)). If all the items in the unpackedItems list cannot be packed, it means the suitcase
    // cannot pack any more items in the list and is packed to its fullest extent. This instance of
    // suitcase is then returned.

    // It is packing the items in the itemList in order of occurence until no more unpacked item can
    // be packed.

    for (int i = 0; i < unpackedItems.size(); i++) {

      // using a try- catch block to catch the IllegalArgumentException (which is thrown when the
      // item cannot be packed).After catching, using the for loop, it proceeds to see if the
      // next item can be packed or not.


      try {

        Item item = unpackedItems.get(i);

        if (suitcase.canPackItem(item)) {
          Suitcase modifiedSuit = suitcase.packItem(item);
          // packing the item that can be packed,storing the reference of the new Suitcase object
          // into modifiedSuit

          return rushedPacking(modifiedSuit);
          // recursively calling the method rushedPacking modified suitcase (packed with the item
          // that we found that we could pack.
        }
      }


      // catch block to catch the IllegalArgumenTexception when the item cannot be packed.

      catch (IllegalArgumentException e) {
        continue;
        // continues to the next iteration to see if the next item in the unpackedItems
        // list can be packed.
      }
    }

    // base case is reached when all the items in the numItemsUnpacked cannot be packed or there are
    // no items left to pack.

    // This method has an implicit base case.
    return suitcase;
  }

  /**
   * Packs items by greedily packing the largest item which can currently be packed. Must be a
   * recursive method.
   *
   * @param suitcase current Suitcase object
   * @return a Suitcase representing the outcome of a greedy strategy in which at each point the
   *         largest item that can fit is packed.
   */
  public static Suitcase greedyPacking(Suitcase suitcase) {

    // storing the unpacked Items array in the original suitcase to an arrayList.
    ArrayList<Item> unpackedItems = suitcase.getUnpackedItems();


    // We are iterating through every item in the unpackedItems list (in order of how much area they
    // occupy) to see which one we can pack in the current configuration of the Suitcase
    // instance.

    // Once we find the biggest item that we can pack , we recursively call the method on the
    // modified suitcase (packed with the biggest item that we found that we could pack).

    // If all the items in the unpackedItems list cannot be packed or there are
    // no items left to pack, it means the suitcase cannot pack any more items
    // in the list and is packed to its fullest extent.

    // This instance of suitcase is then returned.

    // This while loop finds the biggest item that we can pack until there are no items left to
    // be checked if they can be packed.

    while (unpackedItems.size() != 0) {


      int index = findBiggest(unpackedItems); // calls the findBiggest method to find the index of
                                              // the item(in the unpackedItemsl list) with the
                                              // largest area.
      Item bigItem = unpackedItems.get(index); // storing the reference of the item(with largest
                                               // area) into variable bigItem.

      // using a try-catch block to see if the biggest item can be packed or not. If it can be
      // packed, then the method is called recursively on the modified suitcase. If it cannot , we
      // find the next biggest item and then see if we can pack that as we did so with the earlier
      // item.

      try {
        if (suitcase.canPackItem(bigItem)) {
          Suitcase modifiedSuit = suitcase.packItem(bigItem);
          return greedyPacking(modifiedSuit); //
        }

        else {
          unpackedItems.remove(index);
          // We have to remove this item from the unpackedItems arrayList in order to find the next
          // biggest(occupying max area) item that could be packed.
          // (note: this arrayList is just a copy. It is not the original arrayList
          // (The original unpackedItems arrayList cannot be modified directly anyways))


        }
      }


      catch (IllegalArgumentException e) {
        unpackedItems.remove(index);
      }


    }

    // base case is reached when all the items in the unpackedItems cannot be packed.
    // This method has an implicit base case.
    return suitcase;
  }

  /**
   * This method contains code to find the item in the arrayList with the maximum area and returns
   * its index.
   * 
   * @param itemList arrayList containing instances of Item class
   * @return the index of the item in the arrayList with the maximum area
   */
  private static int findBiggest(ArrayList<Item> itemList) {

    int maxArea = 0;
    int index = 0;

    for (int i = 0; i < itemList.size(); i++) {
      if (itemList.get(i).width * itemList.get(i).height > maxArea) {
        maxArea = itemList.get(i).width * itemList.get(i).height;
        index = i;
      }
    }
    return index;
  }


  /**
   * Finds the optimal packing of items by trying all packing orders. Must be a recursive method.
   *
   * @param suitcase current Suitcase
   * @return a Suitcase representing the optimal outcome.
   */



  public static Suitcase optimalPacking(Suitcase suitcase) {

    ArrayList<Item> unpackedItems = suitcase.getUnpackedItems();

    int maxArea = 0; // maximum area (found till now) (of the suitcase) that can be occupied by the
                     // objects from the itemList

    Suitcase max = suitcase; // reference of the Suitcase instance whose occupied area is
                             // maximized.

    // using a for loop to compare the area occupied by the optimalPacking
    // method for every combination of situation where the first item to be packed is a different
    // unpackedItem (that can be packed). We are also finding what max area of the suitcase can be
    // occupied and the corresponding Suitcase instance .

    for (int i = 0; i < unpackedItems.size(); i++) {

      Item item = unpackedItems.get(i);


      if (suitcase.canPackItem(item)) {

        // if the item can be packed, it is packed and the new suitcase reference is stored in the
        // variable modifiedSuit.

        try {
          Suitcase modifiedSuit = suitcase.packItem(item);
          Suitcase optimalPacked = optimalPacking(modifiedSuit);
          // packing the modifiedSuit by recursively calling the optimalPacked method. By doing
          // this, it will explore all the remaining unpacked items to be packed and is recursively
          // called again and again until no other configurations of the suitcase instance can be
          // computed. All this is done whilst dynamically updating the variable max and max area.


          // if the modified suitcase object packed using optimalPacking occupies more area
          // and is bigger than the maximum area that we have found till
          // now and its corresponding Suitcase instance, then both the reference maxArea and the
          // max area are updated

          if (optimalPacking(modifiedSuit).areaPacked() > maxArea) {
            maxArea = optimalPacking(modifiedSuit).areaPacked();
            max = optimalPacking(modifiedSuit);
          }



        }

        catch (IllegalArgumentException e) {
          continue;
        }


      }

      // if the item cannot be packed, proceed to the next item to see if that can be packed.
      else {
        continue;
      }
    }

    // The base case is when none of the items in the unpackedItems list can be packed into the
    // suitcase instance.

    // Consequently, no other configuration of Suitcase can be computed to be compared to the
    // current instance of the Suitcase corresponding to maximum area. Variable max
    // then now contains a reference to the suitcase with the greatest optimized area out of all the
    // possibilities and is therefore returned.

    // The method returns the Suitcase instance corresponding to the maximum area.
    // This method has an implicit base case
    return max;

  }

}
