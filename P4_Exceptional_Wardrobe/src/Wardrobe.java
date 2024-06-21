
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.LocalDate;
import java.text.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;



public class Wardrobe {



  private Clothing[] wardrobe;
  private int wardrobeSize;


  public Wardrobe(int capacity) throws IllegalArgumentException {

    if (capacity <= 0) {
      throw new IllegalArgumentException(
          "The capacity of the array cannot be less than or equal to 0 ");
    }
    wardrobe = new Clothing[capacity];
    wardrobeSize = 0;
  }



  public void addClothing(Clothing toAdd) throws IllegalArgumentException {

    for (int i = 0; i < wardrobeSize; i++) {
      if ((wardrobe[i].getBrand().equalsIgnoreCase(toAdd.getBrand()))
          && (wardrobe[i].getDescription().equalsIgnoreCase(toAdd.getDescription()))) {
        throw new IllegalArgumentException("The clothing item is already in the wardrobe");
      }
    }



    if (wardrobeSize == wardrobe.length) {

      Clothing[] tempWardrobe = new Clothing[wardrobe.length * 2];

      for (int i = 0; i < wardrobe.length; i++) {
        tempWardrobe[i] = wardrobe[i];
      }

      tempWardrobe[wardrobe.length] = toAdd;
      wardrobe = tempWardrobe;


    }

    else {
      wardrobe[wardrobeSize] = toAdd;
    }

    wardrobeSize++;


  }



  public int capacity() {
    return wardrobe.length;
  }



  public int size() {
    return wardrobeSize;
  }



  public void removeClothing(String description, String brand)
      throws IllegalStateException, NoSuchElementException {

    int index = -1;

    if (wardrobeSize == 0) {
      throw new IllegalStateException("The wardrobe does not have any clothing items");
    }

    for (int i = 0; i < wardrobeSize; i++) {

      if ((wardrobe[i].getBrand().equalsIgnoreCase(brand))
          && (wardrobe[i].getDescription().equalsIgnoreCase(description))) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      removeElement(wardrobe, index, wardrobeSize);
      wardrobeSize--;
    }

    else {
      throw new NoSuchElementException("The clothing piece is not in the wardrobe");
    }

  }



  public void removeAllClothingWornNumTimes(int threshold) {

    int currIndex = 0;

    while (currIndex != wardrobeSize) {

      if (wardrobe[currIndex].getNumOfTimesWorn() < threshold) {
        removeElement(wardrobe, currIndex, wardrobeSize);
        wardrobeSize--;
      }

      else {
        currIndex++;
      }

    }

  }



  private void removeElement(Clothing[] arr, int index, int size) {
    for (int i = index; i < size - 1; i++) {
      arr[i] = arr[i + 1];
    }

    arr[size - 1] = null;

  }



  public Clothing getClothing(String description, String brand) throws NoSuchElementException {


    for (int i = 0; i < wardrobeSize; i++) {
      if ((wardrobe[i].getBrand().equals(brand))
          && (wardrobe[i].getDescription().equals(description))) {
        return wardrobe[i];
      }
    }

    throw new NoSuchElementException("The clothing item does not exist in the wardrobe");

  }


  protected Clothing[] getArray() {
    return wardrobe;
  }

  @Override 
  public String toString() {

    String clothingItems = "";

    for (int i = 0; i < wardrobeSize; i++) {

      clothingItems += "[" + wardrobe[i].toString() + "]";

      if (i != wardrobeSize - 1) {
        clothingItems += "\n";
      }

    }

    return clothingItems;
  }



  public void removeAllClothingWornBefore(int year, int month, int day) {

    LocalDate date = LocalDate.of(year, month, day);

    int index = 0;

    while (index != wardrobeSize) {

      if (wardrobe[index].getLastWornDate() != null) {

        if (wardrobe[index].getLastWornDate().isBefore(date)) {
          removeElement(wardrobe, index, wardrobeSize);
          wardrobeSize--;
        }

        else {
          index++;
        }
      }

      else {
        removeElement(wardrobe, index, wardrobeSize);
        wardrobeSize--;
      }

    }

  }


  public void wearClothing(Clothing toWear, int year, int month, int day)
      throws IllegalArgumentException {

    String exceptionMessages = "";

    if (year < 1) {
      exceptionMessages += "The year given (" + year + ") is invalid.\n";
    }


    if ((month < 1) || (month > 12)) {
      exceptionMessages += "The month given (" + month + ") is invalid.\n";
    }


    if (!(exceptionMessages.equals(""))) {
      throw new IllegalArgumentException(exceptionMessages);
    }

    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].equals(toWear)) {
        wardrobe[i].wearClothing(year, month, day);
        break;
      }
    }
  }



  public static Clothing parseClothing(String str) throws ParseException {

    String description;
    String brand;
    String date;
    int timesWorn;
    LocalDate lastWornDate;



    try {

      String[] factors = str.split(",");

      if (factors.length != 4) {
        throw new Exception("The string does not have the 4 required pieces of information");
      }

      description = factors[0];
      brand = factors[1];
      timesWorn = Integer.parseInt(factors[3]);
      date = factors[2];
    }


    catch (NumberFormatException excpt) {

      throw new ParseException(
          "The string representation for the number of times the clothing times has been worn is incorrect",
          0);
    }


    catch (Exception excpt) {

      throw new ParseException(excpt.getMessage(), 0);
    }



    try {

      if (!(date.equals("null"))) {
        String[] dateSeprated = date.split("\\/");

        int year = Integer.parseInt(dateSeprated[2]);
        int month = Integer.parseInt(dateSeprated[0]);
        int dayOfMonth = Integer.parseInt(dateSeprated[1]);

        lastWornDate = LocalDate.of(year, month, dayOfMonth);
      }

      else {
        lastWornDate = null;
      }


    }



    catch (Exception excpt) {
      throw new ParseException(
          "There was an issue converting pieces of information to an int or Date object", 0);
    }


    return new Clothing(description, brand, timesWorn, lastWornDate);

  }



  public boolean saveToFile(File saveFile) {
    boolean savedSuccessfully = true;

    try (PrintWriter writer = new PrintWriter(saveFile)) {
      for (int i = 0; i < wardrobeSize; i++) {
        writer.println(wardrobe[i]);
      }
    } catch (FileNotFoundException e) {
      savedSuccessfully = false;
    }

    catch (Exception excpt) {
      savedSuccessfully = false;
    }

    return savedSuccessfully;
  }



  public boolean loadFromFile(File saveFile) {

    boolean loadSuccessful = false;

    try (Scanner input = new Scanner(saveFile)) {

      boolean exitLoop = false;

      while (input.hasNextLine()) {

        try {
          String line = input.nextLine();
          this.addClothing(Wardrobe.parseClothing(line));
          loadSuccessful = true;
        }

        catch (ParseException e) {
          System.out.println("Cannot parse line to Clothing object");
        }
      }

    }


    catch (FileNotFoundException e) {
      return false;
    }

    return loadSuccessful;
  }



}
