import java.util.*;

public class PlayFair {

    static Scanner in = new Scanner(System.in);

    static char[][] playfairMat = new char[5][5];
    static Map<Character, int[]> charRowColMap = new HashMap<>();

    static void constructPlayfairMat(String key) {
        Set<Character> set = new HashSet<>();
        List<Character> list = new ArrayList<>();

        for (char ch : key.toCharArray()) {
            if (!set.contains(ch) && ch != 'J') {
                list.add(ch);
                set.add(ch);
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (!set.contains(ch) && ch != 'J') {
                list.add(ch);
                set.add(ch);
            }
        }

        int idx = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                playfairMat[i][j] = list.get(idx++);
        }
    }

    static void constructCharRowColMap() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                charRowColMap.put(playfairMat[i][j], new int[] { i, j });
        }
    }

    static void printPlayfairMat() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(playfairMat[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    static List<char[]> constructDigraphs(String text, int len) {
        List<char[]> digraphs = new ArrayList<>();
        for (int i = 0; i < len - 1; i += 2) {
            char ch1 = text.charAt(i);
            char ch2 = text.charAt(i + 1);
            if (ch1 != ch2)
                digraphs.add(new char[] { ch1, ch2 });
            else {
                digraphs.add(new char[] { ch1, 'X' });
                i--;
            }
        }

        System.out.println("\nDigraphs: ");
        for (int i = 0; i < digraphs.size(); i++) {
            System.out.println(digraphs.get(i)[0] + " " + digraphs.get(i)[1]);
        }
        System.out.println();
        return digraphs;
    }

    static String encryption(String plainText) {
        String encryptedText = "";
        int len = plainText.length();
        if (len % 2 != 0) {
            plainText += 'Z';
            len++;
        }

        List<char[]> digraphs = constructDigraphs(plainText, len);

        constructCharRowColMap();

        for (char[] pair : digraphs) {
            int[] coordinates1 = charRowColMap.get(pair[0]);
            int[] coordinates2 = charRowColMap.get(pair[1]);

            int row1 = coordinates1[0], col1 = coordinates1[1];
            int row2 = coordinates2[0], col2 = coordinates2[1];

            if (row1 == row2) // Same row check
            {
                encryptedText += playfairMat[row1][(col1 + 1) % 5];
                encryptedText += playfairMat[row1][(col2 + 1) % 5];
            }

            else if (col1 == col2) // Same column check
            {
                encryptedText += playfairMat[(row1 + 1) % 5][col1];
                encryptedText += playfairMat[(row2 + 1) % 5][col1];
            }

            else {
                encryptedText += playfairMat[row1][col2];
                encryptedText += playfairMat[row2][col1];
            }
        }
        return encryptedText;
    }

    static String decryption(String encryptedText) {
        String decryptedText = "";
        int len = encryptedText.length();
        List<char[]> digraphs = constructDigraphs(encryptedText, len);

        for (char[] pair : digraphs) {
            int[] coordinates1 = charRowColMap.get(pair[0]);
            int[] coordinates2 = charRowColMap.get(pair[1]);

            int row1 = coordinates1[0], col1 = coordinates1[1];
            int row2 = coordinates2[0], col2 = coordinates2[1];

            if (row1 == row2) // Same row check
            {
                if (col1 - 1 >= 0)
                    decryptedText += playfairMat[row1][col1 - 1];
                else
                    decryptedText += playfairMat[row1][4];

                if (col2 - 1 >= 0)
                    decryptedText += playfairMat[row1][col2 - 1];
                else
                    decryptedText += playfairMat[row1][4];
            }

            else if (col1 == col2) // Same column check
            {
                if (row1 - 1 >= 0)
                    decryptedText += playfairMat[row1 - 1][col1];
                else
                    decryptedText += playfairMat[4][col1];

                if (row2 - 1 >= 0)
                    decryptedText += playfairMat[row2 - 1][col1];
                else
                    decryptedText += playfairMat[4][col1];
            }

            else {
                decryptedText += playfairMat[row1][col2];
                decryptedText += playfairMat[row2][col1];
            }
        }

        String removeX = ""; //Temporaray fix for removing appended 'X' to balance the diagraphs

        for(char ch : decryptedText.toCharArray())
        {
            if(ch != 'X')
                removeX += ch;
        }
        return removeX;
    }

    public static void main(String args[]) {
        System.out.print("Enter the key: ");
        String key = in.next().toUpperCase();

        constructPlayfairMat(key);

        printPlayfairMat();

        System.out.print("Enter the text to be encrypted: ");
        String plainText = in.next().toUpperCase();

        String encryptedText = encryption(plainText);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decryption(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
