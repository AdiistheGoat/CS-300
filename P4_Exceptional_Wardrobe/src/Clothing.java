import java.time.LocalDate;

public class Clothing {

  // INSTANCE VARIABLES

  private LocalDate lastWornDate;
  private String description;
  private String brand;
  private int timesWorn;

  // CONSTRUCTORS

  public Clothing(String description, String brand) throws IllegalArgumentException {
    if (description.isBlank() || brand.isBlank()) {
      throw new IllegalArgumentException(
          "Cannot add a piece of clothing with no description and/or brand.");
    } else {
      this.description = description;
      this.brand = brand;
      this.timesWorn = 0;
      this.lastWornDate = null;
    }
  }

  public Clothing(String description, String brand, int timesWorn, LocalDate lastWornDate) {
    if (description.isBlank() || brand.isBlank()) {
      throw new IllegalArgumentException(
          "Cannot add a piece of clothing with no description and/or brand.");
    } else {
      this.description = description;
      this.brand = brand;
      this.timesWorn = timesWorn;
      this.lastWornDate = lastWornDate;
    }
  }


  /**
   * Updates the number of times this piece of clothing has been worn and the last worn date
   *
   * @param year  - the year of the new last worn date
   * @param month - the month of the new last worn date
   * @param day   - the day of the new last worn date
   * @throws IllegalArgumentException - with a descriptive message if the year is less than 1, or
   *                                  the month is outside the range [1,12]
   */
  public void wearClothing(int year, int month, int day) throws IllegalArgumentException {
    if (year < 1) {
      throw new IllegalArgumentException("Year cannot be less than 1");
    }
    if ((month < 1) || (month > 12)) {
      throw new IllegalArgumentException("Month cannot be outside range [1,12]");
    }
    this.timesWorn++;
    // of method updates LocalDate object with parameters year, month and day
    this.lastWornDate = LocalDate.of(year, month, day);
  }

  /**
   * Getter for the description of this piece of clothing.
   *
   * @return this clothing's description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Getter for the brand of this piece of clothing.
   *
   * @return this clothing's brand
   */
  public String getBrand() {
    return this.brand;
  }

  /**
   * Getter for the date that this piece of clothing was last worn.
   *
   * @return this clothing's last worn date
   */
  public LocalDate getLastWornDate() {
    return this.lastWornDate;
  }

  /**
   * Getter for the number of times this piece of clothing has been worn.
   *
   * @return this clothing's number of times worn
   */
  public int getNumOfTimesWorn() {
    return this.timesWorn;
  }

  /**
   * Checks if Object o equals this clothing object, that is the current instance of Clothing.
   *
   * @param o - the object to check if it equals to this piece of clothing
   * @return true if and only if 1) o is of type Clothing 2) the brands match ignoring case AND 3)
   * the descriptions match ignoring case, otherwise return false
   */
  @Override
  public boolean equals(Object o) {
    // checking if o is an instance of Clothing class
    if (!(o instanceof Clothing)) {
      return false;
      // checking if description of Clothing object o is same as the description of this Clothing instance
    } else if (!(((Clothing) o).getDescription().equalsIgnoreCase(this.description))) {
      return false;
      // checking if description of Clothing object o is same as the description of this Clothing instance
    } else if (!(((Clothing) o).getBrand().equalsIgnoreCase(this.brand))) {
      return false;
    }
    return true;
  }

  /**
   * Creates and returns a string representation of this Clothing object. The String is to be
   * formatted as follows: description,brand,lastWornDate,timesWorn The date must be formatted
   * MM/DD/YYYY. For example, lets say a piece of clothing had a description of "black t-shirt",
   * brand of "Gildan", a lastWorn date of September 5th, 2023, and has been worn 15 times. Then the
   * returned string is "black t-shirt,Gildan,09/05/2023,15". If the last worn date is null, then
   * the lastWornDate should be the String "null".
   *
   * @return the String representation of this Clothing object
   */
  @Override
  public String toString() {
    String dateString = "";
    if (this.lastWornDate != null) {
      int month = this.lastWornDate.getMonthValue();
      String monthString = (month < 10 ? "0%d".formatted(month) : Integer.toString(month));

      int day = this.lastWornDate.getDayOfMonth();
      String dayString = (day < 10 ? "0%d".formatted(day) : Integer.toString(day));

      int year = this.lastWornDate.getYear();
      String yearString;
      // adding 0's before the year to ensure it is 4 digit
      if (year < 10) {
        yearString = "000%d".formatted(year);
      } else if (year < 100) {
        yearString = "00%d".formatted(year);
      } else if (year < 1000) {
        yearString = "0%d".formatted(year);
      } else {
        yearString = Integer.toString(year);
      }

      dateString = "%s/%s/%s".formatted(monthString, dayString, yearString);
    } else {
      dateString = "null";
    }
    String timesWornString = Integer.toString(this.timesWorn);

    return "%s,%s,%s,%s".formatted(this.description, this.brand, dateString, timesWornString);
  }
}

