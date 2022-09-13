package Encryption_Algorithms;
import java.util.*;

public class RailFenceCipher {
    static Scanner in = new Scanner(System.in);

    static String encrypt(String message, int key) {
        int n = message.length();
        char rails[][] = new char[key][n];

        for (char[] rail : rails)
            Arrays.fill(rail, '.');

        int row = 0;
        boolean down = true;

        for (int i = 0; i < message.length(); i++) {
            if (down) {
                rails[row++][i] = message.charAt(i);
                if (row == key) {
                    row--;
                    down = false;
                }
            } else {
                row--;
                rails[row][i] = message.charAt(i);
                if (row == 0) {
                    row++;
                    down = true;
                }
            }
        }
        System.out.println();
        String encryptedText = "";
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < n; j++) {
                if (rails[i][j] != '.')
                    encryptedText += rails[i][j];
                System.out.print(rails[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        return encryptedText;
    }

    static String decrypt(String encryptedText, int key) {
        return "";
    }

    public static void main(String args[]) {
        System.out.print("Enter the message: ");
        String message = in.next();
        System.out.print("Enter the key: ");
        int key = in.nextInt();
        String encryptedText = encrypt(message, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptText);
    }
}
