/**
 * File: ArrayRecursion.java
 * 
 * Description: Functions that act on arrays via recursion.
 *
 * Class: Computer Science 112, Boston University
 *
 * Name: Benjamin Kim
 * 
 * Date: 10/28/24
 *
 */

public class ArrayRecursion {

    public static void main(String[] args) {
        String[] arr = {"abc","def","ghi","klm","nop","qrs"};
        System.out.println(reverseArrayToString(arr, 0));
    }

    public static boolean search(Object item, Object[] arr, int start) {

        //The boolean the method will return.
        boolean result;

        //If the array is null, we throw an exception.
        if (arr == null) {
            throw new IllegalArgumentException();
        
        //If the array doesn't have the value, return false.
        } else if (start == arr.length - 1) {
            return false;
        }

        //If the array at index start is what we're looking for,
        if (arr[start].equals(item)) {

            //return true.
            return true;

        //If not, move onto the next index.
        } else {
            result = search(item, arr, start + 1);
        }
        return result;
    }

    public static String reverseArrayToString(Object[] arr, int index) {
        
        //The string the method will return.
        String result;

        //If arr is null, return an empty string.
        if (arr == null) {
            return "";

        //These two lines of code will run when index reaches the last element in arr, meaning it will 
        //ensure that the last element is the first in the string representation of an array that result will 
        //become thanks to recursion.
        } else if (index == arr.length - 1) {

            //What's returned will get saved to result.
            return "[" + arr[index] + "," + " ";
        }

        //Where the recursion happens.
        result = reverseArrayToString(arr, index + 1);

        //If the index is 0, add the closing bracket as well.
        if (index == 0) {
          return result += arr[index] + "]";

        //If it's not, add a comma and a space.
        } else {
          return result += arr[index] + "," + " ";
        } 
    }
}
