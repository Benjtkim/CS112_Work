/* 
 * Problem Set 2
 *
 * File: ArrayProcessing.java
 * Date: 9/24/24
 * Author: Benjamin Kim
 * Course: CS112, Boston University
 *
 * Purpose: Class for various static methods that process an array.
 */

//Import java.util so we can use Scanner and other goodies.
import java.util.*;

public class ArrayProcessing {

    //Class level array that was given to us.
    public static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    //main() method.
    public static void main(String[] args) {
        int[] a2 = {17, 42, 3, 5, 5, 5, 8, 4, 6, 1, 19};
        System.out.println(Arrays.toString(replaceInNew(a2, 5, 0)));
    }

    //Static method that swaps each element in an array with its neighbor.
    public static void swapNeighbors(int[] values) {

        //Loop through each other element in an array starting with the first.
        for (int i = 0; i < values.length - 1; i += 2) {

            //For each other element, for a time, save it's value to the variable replacementValue.
            int replacementValue = values[i];

            //The value at i becomes the value at i + 1.
            values[i] = values[i + 1];

            //While the value at i + 1 becomes the value saved to replacement value for that loop iteration.
            values[i + 1] = replacementValue;
        }
    }

    //Static method that replaces all instances of an old value with a new value.
    public static int[] replaceInNew(int[] values, int oldVal, int newVal) {
        
        //Because the problem specifies to return a new array that is a modified copy of the original array, create a copy of values
        //that is the same length and save it to result.
        int[] result = new int[values.length];

        //For each value in result,
        for (int i = 0; i < result.length; i++) {

            //if values at that index equals oldVal,
            if (values[i] == oldVal) {

                //result at that index equals newVal.
                result[i] = newVal;
            
                //Else, result at that index equals the oringal value at that index.
            } else {
                result[i] = values[i];
            }
        }
        return result;
    }

    //Static method that takes a reference to an array of integers and returns the length of the longest sorted sequence of integers in the array.
    public static int maxSequence(int[] values) {
        
        //If the input is bad, i.e. null, throw an IllegalArgumentException.
        if (values == null) {
           throw new IllegalArgumentException("Invalid input.");
        }

        //If the length of the input is 1,
        if (values.length == 1) {
            
            //return 1.
            return 1;
        }

        //The first variable will count sequences while the second will be the result we return.
        int counter = 1;
        int result = 0;

        //Loop thorugh each element in values except for the last.
        for (int i = 0; i < values.length - 1; i++) {

            //If the current value is less than or equal to the next value,
            if (values[i] <= values[i + 1]) {

                //increment counter.
                counter++;

                //If not,
            } else {

                //make result equal to counter if counter is greater than the current result,
                if (counter > result) {
                    result = counter;
                    counter = 1;

                //or just make counter equal to 1 again.
                } else {
                    counter = 1;
                }
            }
        }
        return result;
    }

    //Static method that takes a reference to a string and returns the index of that string in the class array DAYS.
    public static int getPositionOf(String day) {

        //Initialize the variable that we will return to tell us the index.
        int index = 0;

        //If the input was null, return -1.
        if (day == null) {
            return -1;
        }
        
        //Make sure that the day input will correspond exactly to how it's formatted in the DAYS array by adding an uppercase first character to the rest of the string in lowercase.
        day = day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase();

        //Loop through each element in DAYS,
        for (int i = 0; i < DAYS.length; i++) {

            //If an element is the same as the day input using .equals,
            if (DAYS[i].equals(day)) {
                
                //The index variable will become that index.
                index = i;
                break;
            }

            //If that day doesn't exist, the index will become -1.
            index = -1;
        }
        return index;
    }

    //Static method that copies the elements from the two inputted arrays passed to the array that will be returned, but interchanged from a forward and reverse direction.
    public static int[] swapSwapSwap( int[] arr1, int [] arr2 ) {

        //If either array is null, throw an IllegalArgumentException.
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("One or both of your arrays are invalid.");
        }

        //Set our result, array variable to null for now.
        int[] result = null;

        //Initialize variables that will help us put the right elements from each array into our result array, which we will return.
        //Counter1 will function for elements from arr1 in ascending order while counter2 will function for elements from arr2 in descending order.
        int counter1 = 0;
        int counter2 = arr2.length - 1;

        //If array 1 is longer than array 2,
        if (arr1.length > arr2.length) {

            //result will become an array equal to twice the length of array 2.
            result = new int[arr2.length * 2];

            //Loop through each element in result.
            for (int i = 0; i < result.length; i++) {

                //If the index is even,
                if (i % 2 == 0) {

                    //The element in result at that index will be equal to the element in arr1 at counter1.
                    result[i] = arr1[counter1];

                    //Increment counter after the line above has executed to get to the next element in arr1.
                    counter1++;

                //If the index is odd,
                } else {

                    //The element in result at that index will be equal to the element in arr2 at counter2.
                    result[i] = arr2[counter2];

                    //Decrement counter after the line above has executed to get to the previous element in arr2.
                    counter2--;
                }
            }

        //If array 2 is longer than array 1 or they're the same length,
        } else if (arr2.length > arr1.length || arr1.length == arr2.length) {

            //result will become an array equal to twice the length of array 1.
            result = new int[arr1.length * 2];

            //The rest of the code is identical to the above case.
            for (int i = 0; i < result.length; i++) {
                if (i % 2 == 0) {
                    result[i] = arr1[counter1];
                    counter1++;
                } else {
                    result[i] = arr2[counter2];
                    counter2--;
                }
            }
        } 
        return result;
    }
}
