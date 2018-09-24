import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RansomNote {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {

        Map<String, Integer> wordsMagazine = new HashMap<>();

        for (String word : magazine) {
            Integer qnt = wordsMagazine.get(word);
            if (qnt != null) {
                wordsMagazine.put(word, ++qnt);
            } else {
                wordsMagazine.put(word, 1);
            }
        }

        int flag = 0;
        for (String word : note) {
            Integer i = wordsMagazine.get(word);
            if (i != null) {
                i--;
                wordsMagazine.put(word, i);
                if (i < 0) {
                    System.out.println("No");
                    flag++;
                    break;
                }
            } else {
                System.out.println("No");
                flag++;
            }
        }
        if (flag == 0)
            System.out.println("Yes");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
