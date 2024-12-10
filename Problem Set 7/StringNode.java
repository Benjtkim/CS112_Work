import java.io.*;
import java.util.*;

public class StringNode {
    private char ch;
    private StringNode next;

    /**
     * Constructor
     */
    public StringNode(char c, StringNode n) {
        this.ch = c;
        this.next = n;
    }

    public static StringNode convert(String s) {
        if (s.equals("")) {
            return null;
        }
        StringNode convRest = convert(s.substring(1));
        StringNode firstNode = new StringNode(s.charAt(0), convRest);
        return firstNode;
    }

    public static void main(String[] args) {
        
        
    }

}