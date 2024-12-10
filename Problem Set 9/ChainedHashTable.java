/*
 * ChainedHashTable.java
 * Description: Class for a hash table that uses separate chaining.
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name: Benjamin Kim
 *     email: benjt@bu.edu
 * 
 * Date: 12/9/24
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        this.table = new Node[size];
        this.numKeys = 0;
    }
    
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        /** Replace the following line with your implementation. **/
        int index = h1(key);
        if (this.table[index] == null) {
            this.table[index] = new Node(key, value);
        } else {
            boolean alreadyExists = false;
            Node head = this.table[index];
            Node trav = head;
            Node newNode = new Node(key, value);
            while (trav != null) {
                if (trav.key == key) {
                    alreadyExists = true;
                }
                trav = trav.next;
            }
            if (alreadyExists == false) {
                newNode.next = head;
                this.table[index] = newNode;
            } else {
                return false;
            }
        }
        this.numKeys += 1;
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public LLQueue<Object> search(Object key) {
        /** Replace the following line with your implementation. **/
        int index = h1(key);
        if (this.table[index] == null) {
            return null;
        }
        Node trav = this.table[index];
        while (trav != null) {
            if (trav.key.equals(key)) {
                return trav.values;
            }
            trav = trav.next;
        }
        return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public LLQueue<Object> remove(Object key) {
        /** Replace the following line with your implementation. **/
        int index = h1(key);
        if (this.table[index] == null) {
            return null;
        }
        Node trail = null;
        Node trav = this.table[index];
        while (trav.key != key && trav.next != null) {
            trail = trav;
            trav = trav.next;
        }
        if (trail == null) {
            LLQueue<Object> toReturn = this.table[index].values;
            this.table[index] = this.table[index].next;
            this.numKeys -= 1;
            return toReturn;
        } else if (trail != null && trav.next != null) {
            LLQueue<Object> toReturn = trav.values;
            trail.next = trav.next;
            this.numKeys -= 1;
            return toReturn;
        } else if (trail != null && trav.next == null && trav.key == key) {
            LLQueue<Object> toReturn = trav.values;
            trail.next = null;
            this.numKeys -= 1;
            return toReturn;
        } else {
            return null;
        }
    }
    
    
    /*** Add the other required methods here ***/
    public int getNumKeys() {
        return this.numKeys;
    }

    public double load() {
        return (double) getNumKeys() / this.table.length;
    }
    
    public Object[] getAllKeys() {
        Object[] result = new Object[getNumKeys()];
        int currPosition = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] == null) {
                continue;
            }
            Node trav = this.table[i];
            while (trav != null) {
                result[currPosition] = trav.key;
                currPosition++;
                trav = trav.next;
            }
        }
        return result;
    }

    public void resize(int newSize) {
        Object[] allKeys = getAllKeys();
        Object[] allValues = new Object[allKeys.length];
        int currPosition = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] == null) {
                continue;
            }
            Node trav = this.table[i];
            while (trav != null) {
                allValues[currPosition] = trav.values.remove();
                currPosition++;
                trav = trav.next;
            }
        }
        this.table = new Node[newSize];
        this.numKeys = 0;
        for (int i = 0; i < allKeys.length; i++) {
            insert(allKeys[i], allValues[i]);
        }
    }


    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {

        /** Add your unit tests here **/
        System.out.println("(1) insert test 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            
            boolean results = table.insert("howdy", 15);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(true);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();   

        System.out.println("(2) insert test 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);

            boolean results = table.insert("howdy", 25);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(false);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(3) Search test 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);

            LLQueue<Object> results = table.search("howdy");

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            LLQueue<Object> expectedResults = new LLQueue<Object>();
            expectedResults.insert(15);
            System.out.println(expectedResults);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.peek() == expectedResults.peek());
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(4) Search test 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("apple", 5);

            LLQueue<Object> results = table.search("goodbye");

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("null");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == null);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(5) remove test 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("apple", 5);

            LLQueue<Object> results = table.remove("goodbye");

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("null");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == null);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(6) remove test 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("apple", 5);

            LLQueue<Object> results = table.remove("apple");

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            LLQueue<Object> expectedResults = new LLQueue<Object>();
            expectedResults.insert(5);
            System.out.println(expectedResults);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.peek() == expectedResults.peek());
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(7) getNumKeys test 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);

            int results = table.getNumKeys();

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(8) getNumKeys test 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("apple", 5);
            table.insert("howdy", 15);
            table.insert("howdy", 25);
            table.insert("crimson", 10);
            table.insert("table", 60);

            int results = table.getNumKeys();

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 4);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(9) load test 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);

            double results = table.load();

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0.0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0.0);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(10) load test 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);

            double results = table.load();

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0.6);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0.6);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(11) getAllKeys test 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);

            Object[] results = table.getAllKeys();

            System.out.println("actual results:");
            System.out.println(Arrays.toString(results));
            System.out.println("expected results:");
            Object[] expectedResults = new Object[0];
            System.out.println(Arrays.toString(expectedResults));
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(Arrays.toString(results).equals(Arrays.toString(expectedResults)));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(12) getAllKeys test 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 25);    

            Object[] results = table.getAllKeys();

            System.out.println("actual results:");
            System.out.println(Arrays.toString(results));
            System.out.println("expected results:");
            Object[] expectedResults = {"apple", "howdy", "goodbye"};
            System.out.println(Arrays.toString(expectedResults));
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(Arrays.toString(results).equals(Arrays.toString(expectedResults)));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println(); 
        
        System.out.println("(13) resize test 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5); 

            table.resize(7);

            String results = table.toString();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            Object[] expectedResults = new Object[7];
            System.out.println(Arrays.toString(expectedResults));
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(Arrays.toString(expectedResults)));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();  

        System.out.println("(14) resize test 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("goodbye", 20);

            table.resize(7);

            String results = table.toString();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            Object[] expectedResults = new Object[7];
            expectedResults[1] = "{apple}";
            expectedResults[5] = "{howdy}";
            expectedResults[6] = "{goodbye}";
            System.out.println(Arrays.toString(expectedResults));
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(Arrays.toString(expectedResults)));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
    }
}
