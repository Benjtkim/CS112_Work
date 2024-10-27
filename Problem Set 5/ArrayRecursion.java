public class ArrayRecursion {

    public static void main(String[] args) {
        String[] arr = {"abc","def","ghi","klm","nop","qrs"};
        System.out.println(search("def", arr, 0));
    }

    public static boolean search(Object item, Object[] arr, int start) {
        boolean result;
        if (arr == null) {
            throw new IllegalArgumentException();
        } else if (start == arr.length - 1) {
            return false;
        }

        if (arr[start].equals(item)) {
            return true;
        } else {
            result = search(item, arr, start + 1);
        }
        return result;
    }

    public static String reverseArrayToString(Object[] arr, int index) {
        String result;
        if (arr == null) {
            return "";
        } else if (index == arr.length - 1) {
            return "[" + arr[index] + "," + " ";
        }
        result = reverseArrayToString(arr, index + 1);
        if (index == 0) {
          return result += arr[index] + "]";
        } else {
          return result += arr[index] + "," + " ";
        } 
    }
}
