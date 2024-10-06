/* 
 * Problem Set 2
 *
 * File: StringProcessing.java
 * Date: 9/24/24
 * Author: Benjamin Kim
 * Course: CS112, Boston University
 *
 * Purpose: Class for various static methods that process a string.
 */

public class StringProcessing {

    //main() method
    public static void main(String[] args) {
        printSubstrings("method");
        System.out.println(combineChars("Boston"));
        System.out.println(combineChars("University"));
        System.out.println(combineChars("a"));
        System.out.println(lastOccurrence("Boston", 'o'));
        System.out.println(lastOccurrence("banana", 'a'));
        System.out.println(lastOccurrence("banana", 'b'));
        System.out.println(lastOccurrence("banana", 'x'));
        System.out.println(addRepetitions("Java", 3));
        System.out.println(addRepetitions("oh!", 7));
    }

    //Static method to print the substrings of the inputted string in reverse order.
    public static void printSubstrings(String str) {

        //Utilize the indices of the string in reverse order.
        for (int i = str.length(); i >= 0; i--) {
            
            //Using those indices, print the substring of the inputted string from 0 to i.
            System.out.println(str.substring(0, i));
        }
    }

    //Static method to combine the first and last characters in the inputted string.
    public static String combineChars(String str) {

        //If the length of the string is 1,
        if (str.length() == 1) {

            //Return the string.
            return str;
        }

        //If not, take the character at index 0 and the character at index str.length() - 1 and combine them into a string.
        return str.charAt(0) + "" + str.charAt(str.length() - 1);
    }

    //Static method to tell you the last occurrence of a character in a string.
    public static int lastOccurrence(String str, char ch) {
        
        //Initialize the index to -1 in case the character is not in the string.
        int index = -1;

        //Loop through each character in the string.
        for (int i = 0; i < str.length(); i++) {

            //If the loop finds the specified character, index becomes the index at which the character was found.
            if (str.charAt(i) == ch) {
                index = i;
            }
        }
        return index;
    }

    //Static method to have the string repeat n times.
    public static String addRepetitions(String str, int n) {

        //Initialize the variable strCopy to be a copy of str.
        String strCopy = str;

        //For n-1 times, add strCopy to str.
        for (int i = 0; i < n - 1; i++) {
            str = str + strCopy;
        }
        return str;
    }
}
