/*
 * Name: Benjamin Kim
 * 
 * Date: 11/3/24
 * 
 * File: PairFinder.java
 * 
 * Description: Two methods that each find all the pairs of integers in an array of integers that add up to a value k.
 * 
 * Class: Computer Science 112, Boston University
 */

public class PairFinder {
    public static void main(String[] args) {
        int[] arr = {10, 4, 7, 7, 8, 5, 15};
        int[] arr2 = {4, 5, 7, 7, 8, 10, 15};
        findPairSumsFaster(12, arr);
    }

    public static void findPairSums(int k, int[] arr) {

        //If arr is null, throw an exception.
        if (arr == null) {
            throw new IllegalArgumentException();
        }

        //For each value in arr, this nested for loop will check each value proceeding it to see if any sum is the one
        //we're looking for.
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == k) {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + k);
                }
            }
        }
    }

    public static void findPairSumsFaster(int k, int[] arr) {

        //If arr is null, throw an exception.
        if (arr == null) {
            throw new IllegalArgumentException();
        }

        //Sort arr with mergesort. 
        Sort.mergeSort(arr); 

        //These two values will be used to check each value in arr once.
        int smaller = 0;
        int bigger = arr.length - 1;

        //This while loop will run as long as smaller is in fact smaller than bigger.
        while (smaller < bigger) { 

            //If the sum via smaller and bigger is larger than k,
            if (arr[smaller] + arr[bigger] > k) {

                //decrement bigger.
                bigger--;
            
            //If the sum via smaller and bigger is smaller than k,
            } else if (arr[smaller] + arr[bigger] < k) {

                //increment smaller.
                smaller++;

            //If the sum is what we're looking for,
            } else {

                //print the values and the sum,
                System.out.println(arr[smaller] + " + " + arr[bigger] + " = " + k);
                
                //increment smaller,
                smaller++;

                //and decrement bigger.
                bigger--;
            }
        }
    }    
}
