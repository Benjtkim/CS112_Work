/*
 * LinkedTree.java
 * Description: LinkedTree class for binary search trees.
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Benjamin Kim
 *     email: benjt@bu.edu
 * 
 * Date: 12/9/24
 */

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        private Node parent;     // reference to the parent. NOT YET MAINTAINED!
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }

    //Takes an int key as its only parameter and uses iteration
    //to determine and return the sum of the keys on the path from the root
    //to the node with the specified key, including the key itself.
    public int sumKeysTo(int key) {

        //Three variables. Sum will become the result the method returns,
        //trav will begin at the room, and keyFound will be used to check
        //whether sum should be set to 0 at the end of the following while loop.
        int sum = 0;
        Node trav = root;
        boolean keyFound = false;

        //While trav is not equal to null,
        while (trav != null) {
            
            //Add the key trav is on to sum.
            sum += trav.key;

            //If the key trav is on is equal to the key we're looking for,
            if (trav.key == key) {

                //Set keyFound to true and break.
                keyFound = true;
                break;

            //If it's greater, go to the left. If it's not, go to the right,
            //making use of the definition of a binary search tree.
            } else if (trav.key > key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }

        //If keyfound is false at the end of the loop, set sum to 0.
        if (keyFound == false) {
            sum = 0;
        }
        return sum;
    }
    
    //Method that uses recursion to count the number of leaf nodes in a tree.
    private static int numLeafNodesInTree(Node root) {

        //If the root is null, return 0.
        if (root == null) {
            return 0;
        
        //If the right and left fields of a node are null, it is a leaf node and should therefore
        //return 1.
        } else if (root.left == null && root.right == null) {
            return 1;
        }

        //Line of code that does the recursion. The method will first traverse to the leftmost leaf 
        //node in the tree. After it has done that, it will return and look for any nodes that have a 
        //right subtree. After travelling to the rightmost leaf node in that subtree, it will return until
        //it gets to the next node that has a right subtree, and so on until it has counted
        //all the leaf nodes.
        return numLeafNodesInTree(root.left) + numLeafNodesInTree(root.right);
          
    }

    //Wrapper method for the method above.
    public int numLeafNodes() {

        return numLeafNodesInTree(root);
    }
    
    public int deleteSmallest() {
        if (root == null) {
            return -1;
        }
        Node parent = null;
        Node trav = root;
        while (trav.left != null) {
            parent = trav;
            trav = trav.left;
        }
        int toDelete = trav.key;
        if (trav.left == null && trav.right == null) {
            parent.left = null;
        } else if (trav.left != null && trav.right == null) {
            if (trav.key < parent.key) {
                parent.left = trav.left;
            } else {
                parent.right = trav.left;
            }
        } else if (trav.left == null && trav.right != null) {
            if (trav.key < parent.key) {
                parent.left = trav.right;
            } else {
                parent.right = trav.right;
            }
        } 
        return toDelete;
    }

    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }
    
    /* Returns a preorder iterator for this tree. */
    // public LinkedTreeIterator preorderIterator() {
    //     return new PreorderIterator();
    // }
    
    // /* 
    //  * inner class for a preorder iterator 
    //  * IMPORTANT: You will not be able to actually use objects from this class
    //  * to perform a preorder iteration until you have modified the LinkedTree
    //  * methods so that they maintain the parent fields in the Nodes.
    //  */
    // private class PreorderIterator implements LinkedTreeIterator {
    //     private Node nextNode;
        
    //     private PreorderIterator() {
    //         // The traversal starts with the root node.
    //         nextNode = root;
    //     }
        
    //     public boolean hasNext() {
    //         return (nextNode != null);
    //     }
        
    //     public int next() {
    //         if (nextNode == null) {
    //             throw new NoSuchElementException();
    //         }
            
    //         // Store a copy of the key to be returned.
    //         int key = nextNode.key;
            
    //         // Advance nextNode.
    //         if (nextNode.left != null) {
    //             nextNode = nextNode.left;
    //         } else if (nextNode.right != null) {
    //             nextNode = nextNode.right;
    //         } else {
    //             // We've just visited a leaf node.
    //             // Go back up the tree until we find a node
    //             // with a right child that we haven't seen yet.
    //             Node parent = nextNode.parent;
    //             Node child = nextNode;
    //             while (parent != null &&
    //                    (parent.right == child || parent.right == null)) {
    //                 child = parent;
    //                 parent = parent.parent;
    //             }
                
    //             if (parent == null) {
    //                 nextNode = null;  // the traversal is complete
    //             } else {
    //                 nextNode = parent.right;
    //             }
    //         }
            
    //         return key;
    //     }
    // }
    
    /*
     * "wrapper method" for the recursive depthInTree() method
     * from PS 7, Problem 4
     */
    public int depth(int key) {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }
        
        return depthInTree(key, root);
    }
    
    /*
     * original version of the recursive depthInTree() method  
     * from PS 7, Problem 4. You will write a more efficient version
     * of this method.
     */
    private static int depthInTree(int key, Node root) {
        if (key == root.key) {
            return 0;
        }
        
        if (root.left != null) {
            int depthInLeft = depthInTree(key, root.left);
            if (depthInLeft != -1) {
                return depthInLeft + 1;
            }
        }
        
        if (root.right != null) {
            int depthInRight = depthInTree(key, root.right);
            if (depthInRight != -1) {
                return depthInRight + 1;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("(0) Testing on tree from Problem 3, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree.insertKeys(keys);
            
            int results = tree.depth(28);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(3);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 3);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
                           
        System.out.println("(1) sumKeysTo method test 1");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            
            int results = tree.sumKeysTo(13);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(76);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 76);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests

        System.out.println("(2) sumKeysTo method test 2");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            
            int results = tree.sumKeysTo(50);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests

        System.out.println("(3) numLeafNodes method test 1");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            
            int results = tree.numLeafNodes();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 4);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests

        System.out.println("(4) numLeafNodes method test 2");
        try {
            LinkedTree tree = new LinkedTree();
            
            int results = tree.numLeafNodes();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests

        System.out.println("(5) deleteSmallest method test 1");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            
            int results = tree.deleteSmallest();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(13);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 13);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("(6) deleteSmallest method test 2");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            
            tree.deleteSmallest();
            int results = tree.deleteSmallest();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(26);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 26);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
    }
}
