import java.util.*;

public class LLBag implements Bag {
    private class Node {
        private Object item;
        private Node next;

        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }

    private Node head;   
    private int length;   

    public LLBag() {
        head = new Node(null, null);
        length = 0;
    }

    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node trav = head;
        while (trav.next != null) {
            trav = trav.next;
        }
        trav.next = new Node(item, null);;
        length++;
        return true;
    }

    public boolean contains(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node trav = head;
        while (trav.next != null) {
            trav = trav.next;
            if (trav.item.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public Object[] toArray() {
        Object[] result = new Object[length];
        Node trav = head;
        int count = 0;
        while (trav.next != null) {
            trav = trav.next;
            result[count] = trav.item;
            count++;
        }
        return result;
    }

    public boolean remove(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node trav = head;
        Node trail = null;
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

    public Object grab() {
        if (length == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        int whichOne = (int)(Math.random() * length);
        System.out.println(whichOne);
        Node trav = head.next;
        int count = 0;
        while (count < whichOne) {
            trav = trav.next;
            count++;
        }
        return trav.item;
    }

    public int numItems() {
        return length;
    }

    public String toString() {
        String str = "{";
        Node trav = head;
        while (trav.next != null) {
            trav = trav.next;
            str += trav.item;
            if (trav.next != null) {
                str += ", ";
            }
        }
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
