/**
 * File: GenerateSums.java
 * 
 * Description: A function with an efficiency of O(n) that returns a string representing the sums of a number.
 *
 * Class: Computer Science 112, Boston University
 *
 * Name: Benjamin Kim
 * 
 * Date: 10/28/24
 *
 */

public class GenerateSums {
    public static void main(String[] args) {
        System.out.println(generateSums(6));
    }

    public static String generateSums(int n) {
        
        //We need three variables for this problem. One to keep track of the sum,
        int intSum = 0;

        //another to keep track of what the current string sum with all of its numbers is,
        String stringSum = "";

        //and a third to serve as what the program will return.
        String stringSum2 = "";

        //From i to n,
        for (int i = 1; i <= n; i++) {

            //If i is 1,
            if (i == 1) {

                //Just add i to intSum and stringSum (since that's the only number that will be there for now and we don't need a plus or equals sign),
                //and add i to stringSum2 with a newline in preparation for the next addition.
                intSum += i;
                stringSum += i;
                stringSum2 += i + "\n";

            //If i is not 1, add i to intSum, i and a plus sign to stringSum so it can represent all the numbers in that particular addition,
            //and the whole of stringSum, an equals sign, and intSum to stringSum2 so it can represent the final result. The difference between the code
            //in this if statement and the one below is that if i == n, we don't add a newline to stringSum2.
            } else if (i == n) {
                intSum += i;
                stringSum += " " + "+ " + i;
                stringSum2 += stringSum + " " + "= " + intSum;
            } else {
                intSum += i;
                stringSum += " " + "+ " + i;
                stringSum2 += stringSum + " " + "= " + intSum + "\n";
            }
        }

        return stringSum2;
    }

}
