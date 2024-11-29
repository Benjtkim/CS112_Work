/*
 * Palindrome.java
 */
   
public class Palindrome {
    // Add your definition of isPal here.

    public static boolean isPal(String str) {
        if (str.length() == 1 || str.equals("")) {
            return true;
        } else if (str.equals(null)) {
            throw new IllegalArgumentException();
        }

        LLStack<Character> stack = new LLStack<Character>();
        LLQueue<Character> queue = new LLQueue<Character>();

        for (int i = 0; i < str.length(); i++) {
            char currentCharacter = str.charAt(i);
            currentCharacter = Character.toLowerCase(currentCharacter);
            if ((int)currentCharacter < 97 || (int)currentCharacter > 122) {
                continue;
            } else {
                stack.push(currentCharacter);
                queue.insert(currentCharacter);
            }
        }

        boolean result = true;
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