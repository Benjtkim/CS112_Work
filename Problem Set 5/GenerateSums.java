public class GenerateSums {
    public static void main(String[] args) {
        System.out.println(generateSums(6));
    }

    public static String generateSums(int n) {
        int intSum = 0;
        String stringSum = "";
        String stringSum2 = "";
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                intSum += i;
                stringSum += i;
                stringSum2 += i + "\n";
            } else if (i == n) {
                intSum += i;
                stringSum += " " + "+ " + i;
                stringSum2 += stringSum + " " + "= " + intSum;
            } else {
                intSum += i;
                stringSum += " " + "+ " + i;
                stringSum2 += stringSum + " " + "= " + intSum + "\n";
            }
        }

        return stringSum2;
    }

}
