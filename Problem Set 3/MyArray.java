/* 
* Problem Set 3
*
* File: MyArray.java
* Date: 9/26/24
* Author:  Benjamin Kim 
*
* Purpose: To create a class that allows you to manipulate an array of integers. 
*/

import java.util.*;                    

public class MyArray  {
  
    // the sentinel value used to indicate end of input, initialized to -999
    // the default size of the array if one is not specified, initialized to 20
    // the lower bound of the range of integer elements, initialized to 10
    // the upper bound of the range of integer elements, initialized to 100
    // a data member to reference an array of integers
    // a data member to represent the number of elements entered into the array
  
    private static final int sentinel = -999, DEFAULT_SIZE = 20, LOWER = 1, UPPER = 100;

    //The rest of the private attributes we were told to add.
    private int[] arr;
    private int numElements, sum, min, max;
    private double avg;

    //Method that computes the expected statistics of the array, specifically the minimum, maximum, sum, and average of all the elements contained in the array.
    public void computeStatistics() {

        //Set sum, min, max, and avg equal to 0 to prevent any old values from messing up the end results.
        sum = min = max = 0;
        avg = 0;

        //Calculate the sum by adding up all the elements within the array.
        for (int i = 0; i < numElements; i++) {
            sum += arr[i];
        }

        //Calculate the average by diving the sum by the number of elements.
        avg = (double) sum / numElements;

        //Since every element will be less than or equal to UPPER, and greater than or equal to LOWER, we set min to UPPER and max to LOWER so there's no chance the method ends up reporting that
        //the values originally passed into min and max are the min and max.
        min = UPPER;
        max = LOWER;

        //Calculate min and max by comparing each element in arr to min and max. For each element, if it's less than or equal to min or greater than or equal to max, that value will be passed into
        //min or max for that iteration of the loop.
        for (int i = 0; i < numElements; i++) {
            if (arr[i] <= min) {
                min = arr[i];
            }

            if (arr[i] >= max) {
                max = arr[i];
            } 
        }

    }
  
    // CONSTRUCTORS
    // Initializes a MyArray object using default members
    public MyArray() {
        arr = new int[DEFAULT_SIZE];
        
        //Initialize the data members.
        sum = min = max = 0;
        avg = 0;
    }
  
    public MyArray(int n) {
        
        //Creates an array of size n.
        this.arr = new int[n]; 

        //Initialize the data members.
        sum = min = max = 0;
        avg = 0;
    }
  
    public MyArray(int[] arr) {

        //Initialize the data members.
        sum = min = max = 0;
        avg = 0;

        //Set variable pos to 0. This will be used in case any elements from the array we pass into the constructor are outside the bounds.
        int pos = 0;

        //Create a new array that's the same length as the one we passed into the constructor.
        int[] arrCopy = new int[arr.length];

        //Loop through each element in arrCopy.
        for (int i = 0; i <= arrCopy.length - 1; i++) {

            //If the element from the array we passed into the constructor is within the bounds,
            if (arr[i] >= LOWER && arr[i] <= UPPER) {

                //arrCopy at index pos will be set to arr[i], and pos and numElements will be incremented.
                arrCopy[pos] = arr[i];
                pos++;
                numElements++;
            }
        }

        //Make this.arr point to the same array as arrCopy.
        this.arr = arrCopy;

        //Compute the statistics for the new array this.arr has become.
        computeStatistics();
    }
  
    public void inputElements() {

        //Initialize the scanner.
        Scanner scan = new Scanner(System.in);

        //Tell the user what to do.
        System.out.print("Enter up to " + arr.length + " integers between " + LOWER + " and " + UPPER + " inclusive. Enter " + sentinel + " to end user input: ");

        //Have num become the first int entered.
        int num = scan.nextInt();

        int pos = 0;

        //Loop through each element in arr.
        for (int i = 0; i < arr.length; i++) {

            //If num is equal to sentinel, break.
            if (num == this.sentinel) {
                break;
            
            //Else if num is outside the bounds, the value at the current index stays the same and the user input is ignored.
            } else if (num < LOWER || num > UPPER) {
                i--;
            
            //If the user input is legitimate,
            } else {
                
                //and we're replacing a pre-existing value,
                if (arr[i] != 0) {

                    //only change the number in arr.
                    arr[pos] = num;
                
                //If we're not replacing a pre-existing value,
                } else {

                    //Change the value in arr and increment numElements.
                    arr[pos] = num;
                    numElements++;
                    pos++;
                }
            }
            
            //To go the next user input after we're done with the current one.
            num = scan.nextInt();
        }

        //Compute the statistics for the new array arr becomes.
        computeStatistics();
        System.out.println(Arrays.toString(arr) + " " + numElements);
    }
 
    //Class level method to check if an input is within the bounds.
    public static boolean validInput(int num) {
        return (num > LOWER && num < UPPER);
    }
 
    //Method to convert arr into a legible string. 
    public String toString() {

        //Initialize result to the first bracket we want to include.
        String result = "[";

        //Loop through arr only as many times as there are elements.
        for (int i = 0; i < numElements; i++) {

            //If we get to the last element, 
            if (i == numElements - 1) {

                //just add that element.
                result += arr[i];
            
            //If not,
            } else {

                //add that element to result with a comma and a space as well.
                result += arr[i] + "," + " ";
            }
        }
        result += "]";

        return result;
    }
 
    //Method to count how many times an int appears in arr.
    public int numOccurrences(int n) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n) {
                counter++;
            }
        }
        return counter;
    }
 
    //Method to insert an element into arr.
    public boolean insertElement(int n, int position) {

        //If the position is invalid, or the array is full, or n is out of bounds,
        if (position > numElements || position < 0 || arr.length == numElements || n > UPPER || n < LOWER) {

            //return false.
            return false;
        
        //Else,
        } else {

            //Make it so that every value after the one at the index position becomes the value directly in front of it,
            for (int i = numElements; i > position - 1; i--) {
                arr[i + 1] = arr[i]; 
            }

            //then make it so that the value at the index position becomes the one we want.
            arr[position] = n;

            //Remember to increment numElements because we added 1.
            numElements++;

            //Compute the statistics for the new array.
            computeStatistics();
            return true;
        }
    }

    //Method to remove an element from arr.
    public int removeElement(int position) {

        //Initialize an int that will become the removed value.
        int removedValue;

        //If the position is invalid, return -1.
        if (position > numElements || position < 0) {
            return -1;
        } else {

            //If not, save the value at the index we want to remove to removedValue.
            removedValue = arr[position];

            //Starting from the index position, have each value become the value in front of it as many times as there are elements.
            for (int i = position; i < numElements - 1; i++) {
                arr[i] = arr[i + 1];
            }

            //Decrement numElements because we removed one.
            numElements--;

            //Compute the new statistics for the array.
            computeStatistics();
            return removedValue;
        }

    }

    //Method that will grow the size of the array the object is called on by n positions.
    public boolean growArray(int n) {

        //Simulate growing the array by creating a copy that has n more elements than arr and making it so that arr points to the same array as the copy down the line.
        int[] arrCopy = new int[arr.length + n];
        for (int i = 0; i < arr.length; i++) {
            arrCopy[i] = arr[i];
        }

        arr = arrCopy;
        return true;
    }

    //Method that creates and returns a string which is a histogram-like asterisk reprenstation of the array.
    public String computeHistogram() { 

        //Initialize the string that we will be returning.
        String result = "";

        //Loop through the elements within arr.
        for (int i = 0; i < arr.length; i++) {

            //For each element, repeat the steps below for as many times as is represented by arr[i].
            for (int j = 0; j < arr[i]; j++) {
                //If we're at the last iteration, add in a newline as well.
                if (j == arr[i] - 1) {
                    result += "*\n";
                }

                //If not, just add the asterisk.
                else {
                    result += "*";
                }
            }
        }
        return result; 
    }

    //Accessor methods for sum, min, max, avg, and arr.
    public int getSum() {
        return sum;
    }
    
    public int getMin() {
        return min;
    }
    
    public int getMax() {
        return max;
    }
    
    public double getAvg() {
        return avg;
    }
    
    public int[] getArr() {
        return arr;
    }

    public static void main(String [] args) {
         
        System.out.println("\nUnit Test for MyArray.\n");
  
    // Fill in your unit tests;
        int[] a = {5, 4, 2, 3, 12, 3};
        MyArray arr = new MyArray(a);
        System.out.println(arr.numOccurrences(3));
        System.out.println(arr.toString());
        System.out.println(arr.computeHistogram());

        MyArray arr2 = new MyArray(5);
        arr2.inputElements();
        System.out.println(arr2.toString());

        MyArray arr3 = new MyArray();
        System.out.println(arr3.toString());
    }
}