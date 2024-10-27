public class StringRecursion {
    public static void main(String[] args) {
        System.out.println(trim("   Hello, Rabbit      "));
        
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

        if (str.charAt(0) == ' ' && str.charAt(str.length() - 1) == ' ') {
            return trim(str.substring(1, str.length() - 1));
        } else if (str.charAt(0) == ' ' && str.charAt(str.length() - 1) != ' ') {
            return trim(str.substring(1));
        } else if (str.charAt(0) != ' ' && str.charAt(str.length() - 1) == ' ') {
            return trim(str.substring(0, str.length() - 1));
        } else {
            return str;
        }
    }

    public static int find(char ch, String str) {
        if (str == null || str.equals("")) {
            return -1;
        } else if (ch == str.charAt(0)) {
          return 0;
        }
        int rest = 1 + find(ch, str.substring(1));
        return rest;
    }

    public static String weave(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException();
        } else if (str1.equals("") && !str2.equals("")) {
            return "" + str2;
        } else if (str2.equals("") && !str1.equals("")) {
            return "" + str1;
        } else if (str1.equals("") && str2.equals("")) {
            return "";
        }
        String rest = weave(str1.substring(1), str2.substring(1));
        return str1.charAt(0) + "" + str2.charAt(0) + rest;
    }

    public static int indexOf(char ch, String str) {
        if (str == null || str.equals("")) {
            return -1;
        } else if (ch == str.charAt(0)) {
          return 0;
        }
        int rest = 1 + find(ch, str.substring(1));
        return rest;
    }
}
