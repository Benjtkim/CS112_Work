/**
 * Vehicle.java
 *
 * A class that represents a vehicle.  It is a superclass of all of the
 * other classes that represent vehicles (Automobile, Motorcycle, Truck, 
 * and their subclasses).
 *
 * Fields and methods that all vehicles have in common are defined
 * here so that they can be inherited by the subclasses of this class.
 *
 * Computer Science 112
 */

import java.util.*; 
import java.lang.*; 

 public class Vehicle {
    private String make;
    private String model;
    private int year;
    private int numWheels;
    private int mileage;
    private String plateNumber;
     
    /**
     * a constructor that takes the make, model, year, and number of wheels
     * 
     * XXX: We should add some additional error-checking
     */
    public Vehicle(String make, String model, int year, int numWheels) {
        this.make = make;
        this.model = model;
        if (year < 1900) {
            throw new IllegalArgumentException();
        }
        this.year = year;
        this.numWheels = numWheels;
        this.mileage = 0;
        this.plateNumber = "unknown";
    }

    /*** basic accessors ***/
    
    public String getMake() {
        return this.make;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public int getNumWheels() {
        return this.numWheels;
    }
    
    public int getMileage() {
        return this.mileage;
    }
    
    public String getPlateNumber() {
        return this.plateNumber;
    }

 
    /*** mutators ***/ 
       
    /**
     * setMileage - updates the mileage of this vehicle, changing it to
     * the specified value. Note that we do not allow the new mileage to
     * be greater than the current mileage.
     */
    public void setMileage(int newMileage) {
        if (newMileage < this.mileage) {
            throw new IllegalArgumentException();
        }
        this.mileage = newMileage;
    }
 
    /**
     * setPlateNumber - change the plate number of the vehicle to the
     * specified value.
     * 
     * XXX: We should add some error-checking
     */
    public void setPlateNumber(String plate) {
        this.plateNumber = plate;
    }
    
    /*
     * Creates a string that can be used when printing
     * Vehicles.  This method overrides the toString() 
     * method inherited from the Object class.
     */
    public String toString() {
        String str = this.make + " " + this.model;
        return str;
    }

    public static void main(String[] args) {
        Vehicle car = new Vehicle("Cadillac", "XTS-L", 2013, 4);
        System.out.println(car);
        int[] arr = {5, 3, 2};
        System.out.println(Integer.class.isInstance(arr[0]));
    }
}