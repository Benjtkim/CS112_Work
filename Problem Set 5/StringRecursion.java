/**
 * File: StringRecursion.java
 * 
 * Description: Functions that act on strings via recursion.
 *
 * Class: Computer Science 112, Boston University
 *
 * Name: Benjamin Kim
 * 
 * Date: 10/28/24
 *
 */

public class StringRecursion {
    public static void main(String[] args) {
        System.out.println(weave("hello", "apple"));;
        
    }

    public static void printReverse(String str) {

        //If the string is null or we reach the base case, return.
        if (str == null || str.equals("")) {
            return;
        } 

        //If not, keep dividing the string into substrings until we reach the base case.
        printReverse(str.substring(1));

        //Starting from the base case, print the character at index 0.
        System.out.print(str.charAt(0));
    }

    public static String trim(String str) {
        
        //If the input string is null or empty, return null or an empty string.
        if (str == null) {
            return null;
        } else if (str.equals("")) {
            return "";
        } 

        //Depending on whether there is a space at the beginning of the string, or at the end,
        //or both, get the appropriate substring.
        if (str.charAt(0) == ' ' && str.charAt(str.length() - 1) == ' ') {
            return trim(str.substring(1, str.length() - 1));
        } else if (str.charAt(0) == ' ' && str.charAt(str.length() - 1) != ' ') {
            return trim(str.substring(1));
        } else if (str.charAt(0) != ' ' && str.charAt(str.length() - 1) == ' ') {
            return trim(str.substring(0, str.length() - 1));

        //Once there are no more spaces either at the front or the end, return the string.
        } else {
            return str;
        }
    }

    public static int find(char ch, String str) {
        
        //If the input string is null or empty, return -1.
        if (str == null || str.equals("")) {
            return -1;
        
        //If we reach the base case, return 0. The base case is if the first character 
        //of the string is what we're looking for.
        } else if (ch == str.charAt(0)) {
          return 0;
        }

        //This line will add 1 for each time the function was ran recursively. This works since
        //the number of stack frames created will equal the index of the character we're looking for
        //unless it's not in the string.
        int rest = 1 + find(ch, str.substring(1));
        return rest;
    }

    public static String weave(String str1, String str2) {
        
        //If either input string is null,
        if (str1 == null || str2 == null) {

            //throw an exception.
            throw new IllegalArgumentException();
        
        //After running the method however many times, if either string is empty,
        //add to rest the remaining contents of the non-empty string.
        } else if (str1.equals("") && !str2.equals("")) {
            return "" + str2;
        } else if (str2.equals("") && !str1.equals("")) {
            return "" + str1;

        //If both strings are the same length,
        } else if (str1.equals("") && str2.equals("")) {

            //return an empty string.
            return "";
        }

        //The recursion will keep slicing off the first character of both strings.
        String rest = weave(str1.substring(1), str2.substring(1));

        //Once a base case has been achieved, keep adding the appropriate characters to rest.
        return str1.charAt(0) + "" + str2.charAt(0) + rest;
    }

    //Same exact logic as find.
    public static int indexOf(char ch, String str) {
        if (str == null || str.equals("")) {
            return -1;
        } else if (ch == str.charAt(0)) {
          return 0;
        }
        int rest = 1 + find(ch, str.substring(1));
        return rest;
    }
}
