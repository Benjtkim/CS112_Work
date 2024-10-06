/**
 * Automobile.java
 *
 * A class that represents an automobile.  It is a subclass of Vehicle
 * and a superclass of other classes that represent automobiles
 * (for now, just Taxi).
 *
 * It inherits many of its fields and methods from Vehicle.
 *
 * Fields and methods that all automobiles have in common are defined
 * here so that they can be inherited by the subclasses of this class.
 *
 * Computer Science 112
 */

 public class Automobile extends Vehicle {
    private int numSeats;
    private boolean isSUV;
 
    /** make, model, etc. are inherited from Vehicle **/

    /**
     * first constructor - takes values for the make, model, year, and
     * number of seats, and a boolean to indicate whether the
     * Automobile is an SUV.
     * 
     * XXX: We should add error-checking for the number of seats
     */
    public Automobile(String make, String model, int year, int numSeats,
      boolean isSUV) {
        // invoke the Vehicle constructor to initialize
        // the fields inherited from Vehicle
        super(make, model, year, 4);
        
        this.numSeats = numSeats;
        this.isSUV = isSUV;
    }

    /**
     * second constructor - takes values for the make, model, and year,
     * and assumes that there are 5 seats and that it is not an SUV
     */
    public Automobile(String make, String model, int year) {
        // call the first constructor
        this(make, model, year, 5, false);
    }
    
    /*** accessors ***/
    // getMake(), getModel(), etc. are inherited from Vehicle.
 
    public int getNumSeats() {
        return this.numSeats;
    }
 
    public boolean isSUV() {
        return this.isSUV;
    }
 
    /*** mutators are all inherited from Vehicle ***/
    
    /*
     * Creates a string that can be used when printing
     * an Automobile object.  This method overrides the 
     * toString() method inherited from the Vehicle class.
     */
    public String toString() {
        // Call the Vehicle version of this method
        // to get a string containing the make and model.
        // Alternately, we could use getMake() and getModel().
        String str = super.toString();
  
        // Add information about the number of seats.
        str += " (" + this.numSeats + " seats)";
        return str;
    }
}