/*
 * Palindrome.java
 * 
 * Description: A class that determines if a string is a palindrome or not using a stack and a queue.
 * Name: Benjamin Kim
 * email: benjt@bu.edu
 * Date: 11/29/24
 */
   
public class Palindrome {
    // Add your definition of isPal here.
    public static boolean isPal(String str) {

        //If the length of the string is 1 or if its the empty string,
        if (str.length() == 1 || str.equals("")) {

            //return true.
            return true;
        
        //If the string is null, throw an exception.
        } else if (str.equals(null)) {
            throw new IllegalArgumentException();
        }

        //Variables for a new stack and queue are specified to hold characters.
        LLStack<Character> stack = new LLStack<Character>();
        LLQueue<Character> queue = new LLQueue<Character>();

        //For each character in the string,
        for (int i = 0; i < str.length(); i++) {
            //attribute it to a variable called currentCharacter and set it to lowercase.
            char currentCharacter = str.charAt(i);
            currentCharacter = Character.toLowerCase(currentCharacter);

            //The int values of the 'a' and 'z' characters are 97 and 122, respectively. That means
            //if the int value of a character is less than 97 or greater than 122, it is not a 
            //lowercase letter and we should not add it to our stack and queue.
            if ((int)currentCharacter < 97 || (int)currentCharacter > 122) {
                continue;

            //As long as the int value is within bounds, we can add it to our stack and queue.
            } else {
                stack.push(currentCharacter);
                queue.insert(currentCharacter);
            }
        }

        //Create a boolean variable that will be returned later.
        boolean result = true;

        //While the queue (or stack) is not empty, check to see if stack.pop equals queue.remove.
        //As long as the characters are the same, we can keep checking. As soon as we find two 
        //that aren't the same, we know that the string is not a palindrome. This works because
        //stack.pop() is the last character to check each time, and queue.remove() is the first
        //character to check everytime.
        while (!queue.isEmpty()) {
            if (stack.pop().equals(queue.remove())){
                continue;
            } else {
                result = false;
                break;
            }
        }
        return result;
    } 


    
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */
         System.out.println("(1) Testing on \"Apple!\"");
         try {
             boolean results = isPal("Apple!");
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println("false");
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == false);
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
         
         System.out.println();    // include a blank line between tests
 
         System.out.println("(2) Testing on \"empty string as in an emptry string not the words empty string\"");
         try {
             boolean results = isPal("");
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println("true");
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == true);
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
         
         System.out.println();    // include a blank line between tests

         System.out.println("(3) Testing on \"B\"");
         try {
             boolean results = isPal("B");
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println("true");
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == true);
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
         
         System.out.println();    // include a blank line between tests

         System.out.println("(4) Testing on \"Hello\"");
         try {
             boolean results = isPal("Hello");
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println("false");
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == false);
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
         
         System.out.println();    // include a blank line between tests

         System.out.println("--- Testing method isPal ---");
         System.out.println();
 
         System.out.println("(5) Testing on \"RaceCAR!!??\"");
         try {
             boolean results = isPal("RaceCAR!!??");
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println("true");
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == true);
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
         
    }
}