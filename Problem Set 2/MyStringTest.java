
/* 
 * Problem Set 2
 *
 * File: MyStringTest.java
 * Date: 9/24/24
 * Author: Benjamin Kim
 * Course: CS112, Boston University
 *
 * Purpose: Class that tells you whether a certain word or phrase is a palindrome or not.
 */

import java.util.*;

public class MyStringTest {

  //main() method.
  public static void main(String[] args) {
    System.out.println(Arrays.toString(getString()));
  } 

  //Static method that will decide if a word or phrase is a palindrome.
  public static boolean isApalindrome(String s) {
    //First, set the variable isApal to false. It will be set to true if the word or phrase passes the required tests.
    boolean isApal = false;

    //Convert the input to lowercase.
    s = s.toLowerCase();

    //Loop through every character in the input.  
    for (int i = 0; i < s.length(); i++) {

      //If it is not a letter or digit,
      if (Character.isLetterOrDigit(s.charAt(i)) == false ) {

        //replace it with a blank character.
        s = s.replace(s.charAt(i) + "", "");
      }
    }

    //Loop through each character until you get to the halfway point of the input.
    for (int i = 0; i < s.length() / 2 - 1; i++) {

      //For each character in the beginning half of the input, if it is the same as the character on the opposite side via s.length() - 1 - i,
      if (s.charAt(i) == s.charAt(s.length() - 1 - i)) { 

        //keep setting isApal to true.
        isApal = true;
      } else {

        //As soon as a character that fails the test is found, set isApal to false and break.
        isApal = false;
        break;
      }
    }

    //Return the bolean at the end of the method.
    return isApal;
  }

  //The static method that we will call in the main method to get the string we want to test.
  public static int[] getString() {
    //Initialize counters for how many times isApalindrome() is called and for how many strings end up being palindromes.
    int isApalCalled = 0;
    int isApalCounter = 0;

    //Prompt the user to input the string and tell them how to quit the program.
    System.out.print("Please input a string. To stop inputting strings, type quit.");

    //Create a new scan variable to collect the inputs.
    Scanner scan = new Scanner(System.in);
    
    //Use the example code that was given to us (scan.hasNextLine()).
    while (scan.hasNextLine()) {

      //Initialize the variable input for the user's input.
      String input = scan.nextLine();

      //If the string is a palindrome,
      if (isApalindrome(input) == true) {

        //Print out that it is and increase both counters.
        System.out.println("The inputted string is a palindrome.");
        isApalCounter++;
        isApalCalled++;

        //If the word is quit, 
      } else if (input.equals("quit")) {

        //break out of the loop.
        break;

        //If the string is not a palindrome,
      } else {
        //Say that it isn't and only increase 1 counter.
        System.out.println("The inputted string is not a palindrome.");
        isApalCalled++;
      }
  }

  //When quit is typed it will lead us here, at which point the program will print the goodbye message and return the array for the counters.
  System.out.println("Bye! Thanks for using the program!");
  int[] result = {isApalCalled, isApalCounter};
  return result;
}
} // class
