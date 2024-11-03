/*
 * Name: Benjamin Kim
 * 
 * Date: 11/3/24
 * 
 * File: MergeApproach.java
 * 
 * Description: Two methods that each take two arrays of integers and use an approach
 * based on merging to find and return the union and intersection of the two arrays, respectively.
 * 
 * Class: Computer Science 112, Boston University
 */

import java.util.*;

public class MergeApproach {

    public static void main(String[] args) {
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result = intersect(a1, a2);
        System.out.println( Arrays.toString(result) );
        
    }

    public static int[] union(int[] a1, int[] a2) {
        
        //If either array is null, throw an exception.
        if (a1 == null || a2 == null) {
            throw new IllegalArgumentException();
        }

        //Create a unionArray whose length is equal to the sum of the lengths of a1 and 2.
        int[] unionArray = new int[a1.length + a2.length];

        //Sort a1 and a2 with mergesort.
        Sort.mergeSort(a1);
        Sort.mergeSort(a2);

        //Create the indicies that will "walk down" the two original arrays as well as unionArray.
        int i = 0;
        int j = 0;
        int k = 0;

        //This loop will continue for as long as i is less than a1.length and j is less than a2.length.
        while (i < a1.length && j < a2.length) {

            //If the element at i in a1 is less than the element at j in a2,
            if (a1[i] < a2[j]) {

                //check first to see if k is not 0 and the element at k - 1 in unionArray
                //the same as the element at i in a1.
                if (k != 0 && unionArray[k - 1] == a1[i]) {

                    //If it is, only increment i.
                    i++; 

                //If k is 0 or if k is not 0 and the element at k - 1 in unionArray is not the same 
                //as the element at i in a1, copy a1[i] into union array and increment i and k.
                } else {
                    unionArray[k] = a1[i];
                    i++;
                    k++;
                }

            //If the element at j in a2 is less than the element at i in a1, 
            } else {

                //Repeat the same steps as above but with a2 and j instead of a1 and i.
                if (k != 0 && unionArray[k - 1] == a2[j]) {
                    j++; 
                } else {
                    unionArray[k] = a2[j];
                    j++;
                    k++;
                }
            }
        }

        //The following two loops will run if there are any unaccounted elements in a1 or a2 after the above
        //loop is finished.
        while (i < a1.length) {
            if (k != 0 && unionArray[k - 1] == a1[i]) {
                i++; 
            } else {
                unionArray[k] = a1[i];
                i++;
                k++;
            }
        }

        while (j < a2.length) {
            if (k != 0 && unionArray[k - 1] == a2[j]) {
                j++; 
            } else {
                unionArray[k] = a2[j];
                j++;
                k++;
            }
        }

        return unionArray;
    }

    public static int[] intersect(int[] a1, int[] a2) {
        //If either array is null, throw an exception.
        if (a1 == null || a2 == null) {
            throw new IllegalArgumentException();
        }
    
        //Variable for the length of the intersection array.
        int intersectArrayLength;

        //Set intersectArrayLength equal to the size of the smaller input array.
        if (a1.length < a2.length) {
            intersectArrayLength = a1.length;
        } else {
            intersectArrayLength = a2.length;
        }

        //Create intersectArray with intersectArrayLength.
        int[] intersectArray = new int[intersectArrayLength];

        //Sort a1 and a2 with mergesort.
        Sort.mergeSort(a1);
        Sort.mergeSort(a2);
        System.out.println(Arrays.toString(a1) + " " + Arrays.toString(a2));

        //Create the indicies that will "walk down" the two original arrays as well as intersectArray.
        int i = 0;
        int j = 0;
        int k = 0;

        //This loop will continue for as long as i is less than a1.length and j is less than a2.length.
        while (i < a1.length && j < a2.length) { 

            //If the element at i in a1 is equal to the element at j in a2,
            if (a1[i] == a2[j]) {

                //Copy that value over to intersectArray and increment i, j, and k.
                intersectArray[k] = a1[i];
                i++;
                j++;
                k++;

            //If the element at i in a1 is less than the element at j in a2, 
            } else if (a1[i] < a2[j]) {

                //only increment i.
                i++;
            
            //If the element at j in a2 is less than the element at i in a1,
            } else {

                //only increment j.
                j++;
            }
        }
        return intersectArray;
    }
}
