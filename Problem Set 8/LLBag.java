/*
 * File: LLBag.java
 * Description: Linked list class that implements the bag interface.
 * Name: Benjamin Kim 
 * email: benjt@bu.edu
 * Date: 11/28/24
 */

import java.util.*;

//Header specifies that the class implements bag.
public class LLBag implements Bag {

    //Private class that creates the nodes.
    private class Node {
        private Object item;
        private Node next;

        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }

    //Private variables for the head of the linked list and its length.
    private Node head;   
    private int length;   

    //Constructor that creates a dummy head node and sets the length of the LLBag to 0.
    public LLBag() {
        head = new Node(null, null);
        length = 0;
    }

    //Method that adds an object to the linked list.
    public boolean add(Object item) {

        //Error checking to make sure the item we're trying to put in is not null. All methods that work with an object input have this.
        if (item == null) {
            throw new IllegalArgumentException();
        }

        //Set trav equal to the head node.
        Node trav = head;

        //Keep setting trav to the next node until the node's next field is null.
        while (trav.next != null) {
            trav = trav.next;
        }

        //When the node's next field is null, have it point to a new node that stores the item.
        trav.next = new Node(item, null);;

        //Increment length after we're done.
        length++;
        return true;
    }

    //Method that returns true/false depending on if the specified item is in the linked list.
    public boolean contains(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node trav = head;

        //Have a while loop keep going until trav.next is null or trav.item equals the specified item.
        //If we find the item, return true. If not, return false.
        while (trav.next != null) {
            trav = trav.next;
            if (trav.item.equals(item)) {
                return true;
            }
        }
        return false;
    }

    //Method that creates an array that contains the items in the linked list.
    public Object[] toArray() {

        //Have the array we will return be of size length.
        Object[] result = new Object[length];

        Node trav = head;

        //Create a variable called count so that we can iterate through the available spaces in result.
        int count = 0;

        //Until trav.next is null, keep putting the items we encounter inside result.
        while (trav.next != null) {
            trav = trav.next;
            result[count] = trav.item;
            count++;
        }

        return result;
    }

    //Method that removes the first instance of the specified item from our LLBag.
    public boolean remove(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node trav = head;

        //We need a trail this time so that we can have trail.next set to trav.next.
        Node trail = null;

        //Have a while loop keep going until trav.next is null or trav.item equals the specified item.
        //If we find the item, set trail.next to trav.next, decrement length, and return true. If not, return false.
        while (trav.next != null) {
            trail = trav;
            trav = trav.next;
            if (trav.item.equals(item)) {
                trail.next = trav.next;
                length--;
                return true;
            }
        }
        
        return false;
    }

    //Method that picks out a random item from our LLBag.
    public Object grab() {
        if (length == 0) {
            throw new IllegalStateException("the bag is empty");
        }

       //Use math.random * length to determine which item in the LLBag will be selected.
        int whichOne = (int)(Math.random() * length);
        Node trav = head.next;

        //Use a variable set to 0 to iterate through the LLBag until we get to the one we want.
        int count = 0;
        while (count < whichOne) {
            trav = trav.next;
            count++;
        }

        //Then, return it.
        return trav.item;
    }

    //Accessor method for length.
    public int numItems() {
        return length;
    }

    //To string method.
    public String toString() {

        //Set a variable str to the opening brackets.
        String str = "{";

        Node trav = head;

        //Iterate through the LLBag until trav.next is null.
        while (trav.next != null) {
            trav = trav.next;

            //Everytime it's not null, add the item along with a comma and a space to str. 
            str += trav.item;
            if (trav.next != null) {
                str += ", ";
            }
        }

        //Add the final closing brackets after we're done iterating through the linked list.
        str += "}";
        return str;
    }

    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("number of items in bag 1: ");
        int numItems = scan.nextInt();
        Bag bag1 = new LLBag();
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < numItems; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println("bag 1 length = " + bag1.numItems());
        System.out.println();
    }
}
