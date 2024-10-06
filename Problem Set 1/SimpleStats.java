/*
 * Problem Set 1
 * Name: Benjamin Kim
 * Email: benjt@bu.edu
 * A simple interactive program that performs operations 
 * on a set of three integers.
 *
 * CS112
 *
 */

//Allows us to use the Scanner class.
import java.util.*;

/*
 * Program Class Defintion
 */
public class SimpleStats {

    /*
     * printMenu()
     *
     * Method to display user options.
     */
    public static void printMenu() {
        System.out.println("(0) Enter new numbers");
        System.out.println("(1) Find the largest");
        System.out.println("(2) Compute the sum");
        System.out.println("(3) Compute the range (largest - smallest)");
        System.out.println("(4) Compute the average");
        System.out.println("(5) Print the numbers in ascending order");
        System.out.println("(6) Quit");
        System.out.println();
    }
    
    /*** PUT YOUR SEPARATE HELPER METHODS FOR OPTIONS 1-5 HERE ***/
    
    //Find the max of the 3 ints for option 1.
    public static int option1(int n1, int n2, int n3) {
        return Math.max(Math.max(n1, n2), n3);
    }

    //Add the three ints together for option 2.
    public static int option2(int n1, int n2, int n3) {
        return n1 + n2 + n3;
    }
   
    //Use option 1 to find the max and then subtract it by the min.
    public static int option3(int n1, int n2, int n3) {
        return option1(n1, n2, n3) - Math.min(Math.min(n1, n2), n3);
    }
   
    //Overload the result of option 2 so Java uses floating point division and divide by 3 to find the average.
    public static double option4(int n1, int n2, int n3) {
        return ((double) option2(n1, n2, n3)) / 3;
    }

    //Use option 1 to find the max, then find the min, then find the middleNum through a process of elimination. Then, print them in that order using valueOf to convert the ints to strings.
    public static String option5(int n1, int n2, int n3) {
        int maxNum = option1(n1, n2, n3);
        int minNum = Math.min(Math.min(n1, n2), n3);
        int middleNum;
        if (n1 != maxNum && n1 != minNum) {
            middleNum = n1;
        } else if (n2 != maxNum && n2 != minNum) {
            middleNum = n2;
        } else {
            middleNum = n3;
        }
        return String.valueOf(minNum) + " " + String.valueOf(middleNum) + " " + String.valueOf(maxNum);
    }

    
    /*
     * main()
     *
     * Program execution begins with this method.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);        

        // variable declarations 
        //Have to use Integer instead of int here because Integer can be a null value.
        Integer n1 = null;
        Integer n2 = null;
        Integer n3 = null;

        boolean more_input = true;
        
	/*
 	 * Control loop
 	 */
        do {
            //If option 0 has been selected, n1, n2, and n3, won't be null, allowing us to remind the user of their values before the rest of the print menu.
            if (n1 != null && n2 != null && n3 != null) {
                System.out.println("Numbers are: " + String.valueOf(n1) + " " + String.valueOf(n2) + " " + String.valueOf(n3));
            }
            printMenu();
            System.out.print("Enter choice: ");
            int choice = scan.nextInt();
            /*
	     * Write the conditional logic that processes the possible
	     * menu choices.
             */
            
            //Option 0 will ask the user for 3 numbers and then remind them of what they entered.
            if (choice == 0) {
                System.out.print("Enter three numbers: ");
                n1 = scan.nextInt();
                n2 = scan.nextInt();
                n3 = scan.nextInt();
                System.out.println("Numbers entered are: " + String.valueOf(n1) + " " + String.valueOf(n2) + " " + String.valueOf(n3) + "\n");
            } else if (n1 == null || n2 == null || n3 == null) {
                System.out.println("Cannot compute, numbers have not been entered \n");
            } else if (choice == 1) {
                System.out.println("Largest number entered is: " + option1(n1, n2, n3) + "\n");
            } else if (choice == 2) {
                System.out.println("Sum is: " + option2(n1, n2, n3) + "\n");
            } else if (choice == 3) {
                System.out.println("Range is: " + option3(n1, n2, n3) + "\n");
            } else if (choice == 4) {
                System.out.println("Average is: " + option4(n1, n2, n3) + "\n");
            } else if (choice == 5) { 
                System.out.println("The numbers in ascending order are: " + option5(n1, n2, n3) + "\n");
            } else if (choice == 6) {
                break;
            //If the choice is none of the above options, i.e. not a number through 0-6, we return an error message.
            } else { 
                System.out.println("Invalid choice. Please try again. \n");
            }
            
        } while (more_input);
        
        System.out.println("Have a nice day!");
    }
}
