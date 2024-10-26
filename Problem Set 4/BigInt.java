/* 
* File: BigInt.java
* Name: Benjamin Kim
* Date: 10/5/24
* Purpose: A class for objects that represent non-negative integers of 
* up to 20 digits.
*/

import java.util.Arrays;

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int MAX_SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;
    private boolean overflow;

    //Helper function to validate the digits within an array.
    public boolean validateDigit(int[] arr) {

        //Loop through the array.
        for (int i = 0; i < arr.length; i++) {

            //If an element is not an int, or if it's greater than 9 or less than 0,
            if (Integer.class.isInstance(arr[i]) == false || arr[i] > 9 || arr[i] < 0) {

                //return false.
                return false;
            }
        }

        //If everything checks out, return true.
        return true;
    }

    /*
    * Default, no-argument constructor -- creates a BigInt that 
    * represents the number 0.
    */
    public BigInt() {
        digits = new int[MAX_SIZE];
        numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
        overflow = false;
    }

    //BigInt constructor that takes an int.
    public BigInt(int n) {

        //Throw an illegal argument exception if the input is not an int or if's is negative.
        if (Integer.class.isInstance(n) == false || n < 0) {
            throw new IllegalArgumentException("Invalid input.");
        }

        //Initialize the digits field.
        digits = new int[MAX_SIZE];

        //If the input is 0, set numSigDigits to 1, overflow to false, and leave the digits field as is.
        if (n == 0) {
            numSigDigits = 1;
            overflow= false;
        
        //If not, 
        } else {

            //Initialize a temporary array.
            int[] tempArr = new int[MAX_SIZE];

            //Set numSigDigits to 0. It will also serve as a counter in the following while loop.
            numSigDigits = 0;

            //While n > 0,
            while (n > 0) {

                //the element corresponding to the length of the array minus 1 minus the counter (numSigDigits) will become the last digit of n (n % 10).
                tempArr[tempArr.length - 1 - numSigDigits] = n % 10;

                //Increment numSigDigits to get to the next spot in tempArr and to keep track of the significant digits.
                numSigDigits++;

                //Divide n by 10 to get to the next digit.
                n = n / 10; 
            }

            //For loop to fill in the digits field with the elements from tempArr.
            for (int i = 0; i < tempArr.length; i++) {
                digits[MAX_SIZE - 1 - i] = tempArr[tempArr.length - 1 - i];
            }

            //Set overflow to false after all is said and done.
            overflow = false;
        }

    }

    //BigInt constructor that takes an array.
    public BigInt(int[] arr) {

        //If the input is null, its length is outside the bounds, or any of its elements is not a digit as checked by the validateDigit helper function, 
        if (arr == null || arr.length < 0 || arr.length > MAX_SIZE || validateDigit(arr) == false) {

            //Throw a new illegal argument exception.
            throw new IllegalArgumentException("Either the array is null, its size is not between 0 and max_size, or an element within the array is not a digit.");

        //If the length of the array is greater than 20,
        } else if (arr.length > 20) {
            
            //Set overflow to true, and throw a new arithmetic exception.
            overflow = true;
            throw new ArithmeticException();
        }

        //Initialize the digits field.
        digits = new int[MAX_SIZE];

        //Set numSigDigits to 1 as opposed to 0 in case the array that is passed in is all 0s.
        numSigDigits = 1;

        //Loop through the input array.
        for (int i = 0; i < arr.length; i++) {
            
            //The element corresponding to digits.length - 1 - i in digits will become the element corresponding to arr.length - 1 - i in the input array.
            digits[digits.length - 1 - i] = arr[arr.length - 1 - i];
        }

        //Loop through the digits array, now, starting from the last element. Note that the loop will never point to the very first element within digits because if it wasn't 0, Java would
        //throw an error due to the following if statement.
        for (int i = digits.length - 1; i > 0; i--) {

            //If the element the loop is on is not equal to 0, or if it is and the element to the immediate left of it is not equal to 0, numSigDigits will become MAX_SIZE - i.
            if (digits[i] != 0 || digits[i - 1] != 0) {
                numSigDigits = MAX_SIZE - i;
            }
        }

        //If the very first element within digits is not equal to 0, numSigDigits will become MAX_SIZE.
        if (digits[0] != 0) {
            numSigDigits = MAX_SIZE;
        }
    }
    
    //The toString function.
    public String toString() {

        //Create a result variable that will be returned.
        String result = "";

        //Starting from the last element in digits and iterating for as many times as there are sig digits, 
        for (int i = digits.length - 1; i > digits.length - 1 - numSigDigits; i--) {

            //the loop will add the digit it's on to the front of result.
            result = digits[i] + result;
        } 

        //Return result after the for loop.
        return result;
        
    }

    //The compareTo function.
    public int compareTo(BigInt other) {
        
        //If the input is null, 
        if (other == null) {
            
            //throw an exception.
            throw new IllegalArgumentException("Invalid input.");
        }

        //Create a variable for the number of sig digits in the called object and another for the number of 
        //sig digits in the input object.
        int thisNumSigDigits = this.getNumSigDigits();
        int otherNumSigDigits = other.getNumSigDigits();

        //If there are more sig digits in the input object,
        if (thisNumSigDigits < otherNumSigDigits) {
            
            //Return -1.
            return -1;
        
        //If there are more sig digits in the called object,
        } else if (thisNumSigDigits > otherNumSigDigits) {

            //Return 1.
            return 1;
        
        //If they have the same number of sig digits,
        } else {

            //Create variables for their arrays.
            int[] thisDigits = this.getDigits();
            int[] otherDigits = other.getDigits();

            //Starting from where the most significant digit would be (the length of the array minus the number of sig digits) and going until the end
            //of the array, 
            for (int i = thisDigits.length - thisNumSigDigits; i < thisDigits.length; i++) {
                
                //the loop will return -1 the moment an element in the input object's array is greater than the corresponding element in the called object's array,
                if (thisDigits[i] < otherDigits[i]) {
                    return -1;
                
                //and 1 if it's the other way around.
                } else if (thisDigits[i] > otherDigits[i]) {
                    return 1;
                }
            }
            
            //If the loop never comes across a greater element, it will return 0.
            return 0;
        }
    }

    //Sig Digits and array accessor methods.
    public int getNumSigDigits() {
        return numSigDigits;
    }

    public int[] getDigits() {
        return digits;
    }

    //The add method.
    public BigInt add(BigInt other) {

        //If the input is null,
        if (other == null) {

            //Throw an exception.
            throw new IllegalArgumentException("Invalid input.");
        }
        
        //Create two variables for each array.
        int[] thisDigits = this.getDigits();
        int[] otherDigits = other.getDigits();

        //Create an array for the sum.
        int[] sum = new int[MAX_SIZE];

        //Create a variable for the carry value.
        int carry = 0;

        //Starting from the last element in sum and iterating to the first,
        for (int i = sum.length - 1; i >= 0; i--) {

            //if i is the first element and there will be an overflow,
            if (i == 0 && (thisDigits[i] + otherDigits[i] + carry) > 10) {

                //set overflow to true and throw an exception.
                overflow = true;
                throw new ArithmeticException();
            }

            //Otherwise, the element the loop is on will become (thisDigits[i] + otherDigits[i] + carry) % 10,
            sum[i] = (thisDigits[i] + otherDigits[i] + carry) % 10;

            //and carry will become (thisDigits[i] + otherDigits[i] + carry) / 10.
            carry = (thisDigits[i] + otherDigits[i] + carry) / 10;
        }

        //Create a new BigInt object with the sum array.
        BigInt result = new BigInt(sum);

        //Retun that object.
        return result;
    }

    //The mul method.
    public BigInt mul(BigInt other) { 

        //If the input is null,
        if (other == null) {

            //Throw an exception
            throw new IllegalArgumentException("Invalid input.");
        }

        //Create 2 variables for each array and 2 variables for each numSigDigits.
        int[] thisDigits = this.getDigits();
        int thisNumSigDigits = this.getNumSigDigits();
        int[] otherDigits = other.getDigits();
        int otherNumSigDigits = other.getNumSigDigits();

        //Create an array that will house the BigInt objects created from each step of multiplication.
        BigInt[] objArray;

        //The size of objArray must correspond to whichever number has fewer sigDigits.
        if (thisNumSigDigits > otherNumSigDigits) {
            objArray = new BigInt[otherNumSigDigits];
        } else {
            objArray = new BigInt[thisNumSigDigits];
        }

        //This counter is for ensuring there are a number of 0s at the end of a number corresponding to which line product is it.
        int counter = 0; 

        //Create a variable for the carry.
        int carry = 0;

        //This if/else statement is to make sure the program is iterating through the array with fewer sigDigits.
        if (thisNumSigDigits > otherNumSigDigits) {
            for (int i = otherDigits.length - 1; i > otherDigits.length - 1 - otherNumSigDigits; i--) {
                
                //Everytime one line product is finished/i iterates, create a new array for the upcoming line product.
                int[] lineProduct = new int[MAX_SIZE];

                //Repeat the following steps as many times as there are digits in the value with the greater number of sig digits:
                for (int j = thisDigits.length - 1; j > thisDigits.length - 1 - thisNumSigDigits; j--) {

                    //first, check to make sure there isn't an overflow by checking to see if j - counter is less than 0.
                    if (j - counter < 0) {
                        overflow = true;
                        throw new ArithmeticException();
                    }

                    //If there is no overflow, lineProduct[j - counter will become the last digit of the two-digit product.
                    System.out.println("thisDigits[j]: " + thisDigits[j] + " otherDigits[i]: " + otherDigits[i]);
                    lineProduct[j - counter] = ((thisDigits[j] * otherDigits[i] + carry)) % 10;
                    System.out.println("lineProduct: " + lineProduct[j - counter] + " Product: " + (thisDigits[j] * otherDigits[i]));

                    //Divide that product by 10 to get the carry.
                    System.out.println("Before carry: " + carry + " Calculation: " + (thisDigits[i] * otherDigits[j]) + " thisDigits[j]: " + thisDigits[j] + " otherDigits[i]: " + otherDigits[i]);
                    carry = (thisDigits[i] * otherDigits[j] + carry) / 10;
                    System.out.println("After carry: " + carry);

                    if (j == thisDigits.length - 1 - thisNumSigDigits && carry != 0) {
                        lineProduct[j - 1] = carry;
                        carry = 0;
                    }
                    
                }
                //Use counter to input into objArray a BigInt object that uses the lineProduct array.
                objArray[counter] = new BigInt(lineProduct);

                //Increment counter after all is said and done.
                counter++;
            } 
        
        //The bellow steps are identical to what's above except this gets swapped with other.
        } else {
            for (int i = thisDigits.length - 1; i > thisDigits.length - 1 - thisNumSigDigits; i--) {
                
                //Everytime one line product is finished/i iterates, create a new array for the upcoming line product.
                int[] lineProduct = new int[MAX_SIZE];

                //Repeat the following steps as many times as there are digits in the value with the greater number of sig digits:
                for (int j = otherDigits.length - 1; j > otherDigits.length - 1 - otherNumSigDigits; j--) {

                    //first, check to make sure there isn't an overflow by checking to see if j - counter is less than 0.
                    if (j - counter < 0) {
                        overflow = true;
                        throw new ArithmeticException();
                    }

                    //If there is no overflow, lineProduct[j - counter will become the last digit of the two-digit product.
                    
                    System.out.println("thisDigits[i]: " + thisDigits[i] + " otherDigits[k] " + otherDigits[j]);
                    lineProduct[j - counter] = (thisDigits[i] * otherDigits[j] + carry) % 10;
                    System.out.println("Digit: " + lineProduct[j - counter]);

                    //Divide that product by 10 to get the carry.
                    
                    carry = (thisDigits[i] * otherDigits[j] + carry) / 10;
                    System.out.println("Carry: " + carry);

                    if (j == thisDigits.length - 1 - thisNumSigDigits && carry != 0) {
                        lineProduct[j - 1] = carry;
                        carry = 0;
                    }
                }
                //Use counter to input into objArray a BigInt object that uses the lineProduct array.
                objArray[counter] = new BigInt(lineProduct);

                //Increment counter after all is said and done.
                counter++;
            } 
        } 

        //Create a variable for the BigInt object that will be returned. Initialize it to be a BigInt object that represents the number 0.
        BigInt result = new BigInt();
        
        //For each BigInt object in objArray,
        for (int i = 0; i < objArray.length; i++) {
            System.out.println(Arrays.toString(objArray[i].getDigits()));

            //add that object to result.
            result = result.add(objArray[i]);
        }

        //Return result.
        return result;
    }

    public static void main(String [] args) {


        System.out.println("Test 16: result should be 151782");
        BigInt b1 = new BigInt(23);
        BigInt b2 = new BigInt(11111);
        System.out.println(b1.mul(b2));
        System.out.println();

    }
}
