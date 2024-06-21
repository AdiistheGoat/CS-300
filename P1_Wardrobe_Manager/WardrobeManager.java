
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
 * This class contains several methods that help update a wardrobe e.g. remove or add a clothing
 * item , and get data from a wardrobe e.g. no of clothing items of a particular brand.
 * 
 */

public class WardrobeManager {


  /**
   * 
   * Appends (adds at the end) a new clothing item to the given wardrobe oversize array defined by
   * the provided two-dimensional array of strings and its size, and returns the new size of the
   * oversize array. If the array is full, OR if the description/brand combination provided is
   * already present within the array, the item is NOT added and the current size is returned
   * unmodified.
   * 
   * 
   * @param description- - a general description of the clothing item.
   * 
   * @param brand        - the brand of the clothing item, or "handmade".
   * 
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never".
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe.
   * 
   * @return the number of items in the wardrobe after trying to add the new item.
   * 
   */
  public static int addClothing(String description, String brand, String[][] wardrobe,
      int wardrobeSize) {


    if (!(containsClothing(description, brand, wardrobe, wardrobeSize))
        && (wardrobe[wardrobe.length - 1] == null)) {

      String[] addition = {description, brand, "never"};
      wardrobe[wardrobeSize] = addition;
      wardrobeSize++;
    }

    return wardrobeSize;
  }

  /**
   * 
   * Checks whether the oversize array defined by the provided two-dimensional array of strings and
   * its size contains an entry with the provided description AND brand. You can use this method in
   * other methods later to enforce our uniqueness requirements, so be sure to test it!
   * 
   * 
   * @param description- a general description of the clothing item
   * 
   * @param brand        - the brand of the clothing item, or "handmade".
   * 
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never".
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe.
   * 
   * @return true if the description/brand combination is present in the wardrobe; false otherwise.
   * 
   */
  public static boolean containsClothing(String description, String brand, String[][] wardrobe,
      int wardrobeSize) {


    for (int i = 0; i < wardrobeSize; i++) {

      if ((wardrobe[i][0].trim().equalsIgnoreCase(description.trim()))
          && (wardrobe[i][1].trim().equalsIgnoreCase(brand.trim()))) {
        return true;
      }
    }

    return false;
  }


  /**
   * 
   * Counts the number of clothing items in the oversize array defined by the provided
   * two-dimensional array of strings and size which have a brand that matches the provided brand.
   * Recall that this match ignores case; for example, "GUCCI" and "Gucci" are considered
   * equivalent.
   * 
   * @param brand        - the brand of the clothing item, or "handmade"
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never".
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe.
   * 
   * @return the number of items in wardrobe which have a brand matching the provided string.
   */
  public static int getBrandCount(String brand, String[][] wardrobe, int wardrobeSize) {

    int count = 0;

    for (int i = 0; i < wardrobeSize; i++) {

      if (wardrobe[i][1].equalsIgnoreCase(brand)) {
        count++;
      }
    }

    return count;
  }


  /**
   * 
   * Finds the most recently worn item of clothing in the oversize array defined by the provided
   * two-dimensional array of strings and size. The most recently worn item of clothing will be the
   * one with the largest date value (as date values increase over time); "never" is considered the
   * smallest possible date, so if all items have a last-worn date of "never", the method should
   * return 0 (the first index at which that date occurs).
   * 
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never".
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe.
   * 
   * @return the smallest index of a clothing item in the wardrobe with the most recent last-worn
   *         date, or -1 if the wardrobe is empty.
   * 
   * 
   */
  public static int getMostRecentlyWorn(String[][] wardrobe, int wardrobeSize) {


    int index = 0;

    int minYear = 0;
    int minMonth = 0;
    int minDay = 0;


    if (wardrobe[0] == null) {
      return -1;
    }


    int currYear = 0;
    int currMonth = 0;
    int currDay = 0;

    for (int i = 0; i < wardrobeSize; i++) {


      String date = wardrobe[i][2];

      if (date.equals("never")) {
        continue;
      }

      else {
        String[] dateInParts = wardrobe[i][2].split("-");

        if ((currYear == 0) && (currMonth == 0) && (currDay == 0)) {



          currYear = Integer.parseInt(dateInParts[0]);
          currMonth = Integer.parseInt(dateInParts[1]);
          currDay = Integer.parseInt(dateInParts[2]);

          minYear = Integer.parseInt(dateInParts[0]);
          minMonth = Integer.parseInt(dateInParts[1]);
          minDay = Integer.parseInt(dateInParts[2]);

        }

        else {
          currYear = Integer.parseInt(dateInParts[0]);
          currMonth = Integer.parseInt(dateInParts[1]);
          currDay = Integer.parseInt(dateInParts[2]);

          if (currYear >= minYear) {

            if (currYear > minYear) {
              index = i;
            }

            else {
              if (currMonth >= minMonth) {


                if (currMonth > minMonth) {
                  index = i;
                }

                else {

                  if (currDay >= minDay) {

                    if (currDay > minDay) {
                      index = i;
                    }
                  }
                }

              }
            }

          }

        }

      }

      if (index == i) {
        minYear = currYear;
        minMonth = currMonth;
        minDay = currDay;
      }

    }

    return index;
  }

  /**
   * 
   * Counts the number of clothing items in the oversize array defined by the provided
   * two-dimensional array of strings and size which have a last-worn date of "never".
   * 
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never".
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe.
   * 
   * @return the number of clothing items in wardrobe which have a last-worn date of "never".
   * 
   */
  public static int getNumUnwornClothes(String[][] wardrobe, int wardrobeSize) {


    int count = 0;

    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i][2].equals("never")) {
        count++;
      }
    }

    return count;
  }


  /**
   * 
   * Finds the location (index) of a provided clothing item in an oversize array defined by the
   * provided two-dimensional array of strings and its size. If the item is NOT present in the
   * array, returns -1.
   * 
   * @param description  - a general description of the clothing item.
   * 
   * @param brand        - the brand of the clothing item, or "handmade".
   * 
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never".
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe
   * 
   * @return the index of the clothing item if it is present, or -1 if it is not.
   */
  public static int indexOfClothing(String description, String brand, String[][] wardrobe,
      int wardrobeSize) {

    int index = -1;

    for (int i = 0; i < wardrobeSize; i++) {

      if ((wardrobe[i][0].equalsIgnoreCase(description))
          && (wardrobe[i][1].equalsIgnoreCase(brand))) {

        index = i;
        break;
      }
    }


    return index;
  }


  /**
   * 
   * Removes any clothing item from the oversize array defined by the provided two-dimensional array
   * of strings and size where the item's last-worn date is "never", and returns the updated size of
   * the oversize array.
   * 
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never".
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe.
   * 
   * @return the size of the oversize array after all never-worn items have been removed.
   * 
   */
  public static int removeAllUnworn(String[][] wardrobe, int wardrobeSize) {


    int index = 0;

    while (true) {

      if (wardrobe[index] == null) {
        break;
      }

      if (wardrobe[index][2].equals("never")) {

        wardrobe[index] = null;



        for (int i = index; i < wardrobeSize; i++) {
          wardrobe[i] = wardrobe[i + 1];
        }


        wardrobeSize -= 1;
      }

      else {

        index++;
      }



    }

    return wardrobeSize;
  }


  /**
   * 
   * Removes the single clothing item entry at the provided index of the oversize array defined by
   * the provided two-dimensional array of strings and size, updates the array to uphold the
   * oversize array requirements, and returns the new size of the array. If the index is outside of
   * the bounds of the provided oversize array, the array must NOT be modified and the previous size
   * returned.
   * 
   * @param index        - the index of the clothing item to remove from the wardrobe.
   * 
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never".
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe.
   * 
   * @return the size of the oversize array after attempting to remove the item at the provided
   *         index.
   */
  public static int removeClothingAtIndex(int index, String[][] wardrobe, int wardrobeSize) {

    int size = 0;


    if (index >= wardrobeSize) {
      size = index;
    }


    else {

      wardrobe[index] = null;

      for (int i = index; i < wardrobeSize; i++) {
        wardrobe[i] = wardrobe[i + 1];
      }

      size = wardrobeSize - 1;
    }

    return size;
  }


  /**
   * 
   * Locates the clothing item matching the provided description/brand in the oversize array defined
   * by the provided two-dimensional array of strings and size, and updates the last-worn date to
   * the provided date value (assumed to be formatted as "YYYY-MM-DD"). If the wardrobe does not
   * contain a clothing item matching the description/brand combination, this method returns false.
   * 
   * @param description  - a general description of the clothing item.
   * 
   * @param brand        - the brand of the clothing item, or "handmade".
   * 
   * @param date         - the date on which this clothing item was worn, as "YYYY-MM-DD.
   * 
   * @param wardrobe     - a two-dimensional array of Strings, which stores wardrobe entries.
   *                     wardrobe[i][0] contains a description of item i, wardrobe[i][1] contains
   *                     its brand name, and wardrobe[i][2] contains its last-worn date formatted as
   *                     "YYYY-MM-DD", or "never".
   * 
   * @param wardrobeSize - number of items currently stored in the wardrobe.
   * 
   * @return true if the item's last-worn date was successfully updated; false otherwise.
   * 
   */
  public static boolean wearClothing(String description, String brand, String date,
      String[][] wardrobe, int wardrobeSize) {


    for (int i = 0; i < wardrobeSize; i++) {

      if ((wardrobe[i][0].equalsIgnoreCase(description))
          && (wardrobe[i][1].equalsIgnoreCase(brand))) {

        wardrobe[i][2] = date;
        return true;
      }
    }

    return false;
  }



}
