public class StringRecursion {
    public static void main(String[] args) {
        System.out.println(trim("terriers    "));
        
    }

    public static void printReverse(String str) {
        if (str == null || str.equals("")) {
            return;
        } 
        printReverse(str.substring(1));
        System.out.print(str.charAt(0));
    }

    public static String trim(String str) {
        if (str == null) {
            return null;
        } else if (str.equals("")) {
            return "";
        } 
        String rest = trim(str.substring(1));
        if (str.matches(".*[a-z].*")) {
            rest = str.charAt(0) + rest;
        }
        return rest;
    }

    public static int find(char ch, String str) {
        if (str == null || str.equals("")) {
            return -1;
        }
        int rest = find(ch, str.substring(1));
        if (str.charAt(0) == ch) {
            rest = str.length() - 1;
        }
        return rest;
    }
}
