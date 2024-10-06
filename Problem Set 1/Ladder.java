/* Name: Benjamin Kim
 * Email: benjt@bu.edu
 * Description: Program that computes the required length of ladders.
 */

//This import will allow us to use Java's Scanner class.
import java.util.*;

public class Ladder {
    public static void main(String[] args) {
        //Create a new Scanner object named console.
        Scanner console = new Scanner(System.in);

        //Ask the user for the height input.
        System.out.print("Enter the desired height in feet you'd like your ladder to reach: ");
        //Read the input and save the int to height.
        int height = console.nextInt();

        //Ask the user for the angle input.
        System.out.print("Enter the angle at which the ladder will be positioned: ");
        //Read the next input and save it to angle.
        int angle = console.nextInt();

        //Convert the angle from degress to radians and save that double to angleToRadians.
        double angleToRadians = (angle * Math.PI) / 180;

        //Calculate the ladder length and save that double to ladderLength.
        double ladderLength = height / Math.sin(angleToRadians);

        //Report the length of the ladder in three forms:
        System.out.println("The required length of your ladder is: ");
        System.out.println(ladderLength + " feet");
        System.out.println((ladderLength / 3) + " yards");
        System.out.println(((int)ladderLength /3) + " yards and " + (((ladderLength / 3) - (int)ladderLength /3) * 3) + " feet");
    }
}