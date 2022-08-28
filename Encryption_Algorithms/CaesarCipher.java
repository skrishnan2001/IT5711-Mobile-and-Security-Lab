package Encryption_Algorithms;
import java.util.*;

public class CaesarCipher {
    static String encrypt(String str, int keySize) {
        keySize %= 26;
        char[] strArr = str.toCharArray();
        for (int i = 0; i < strArr.length; i++) {
            if (!Character.isLetter(strArr[i]))
                continue;

            char encryptedCharacter = (char) ((int) strArr[i] + keySize);

            if (Character.isLetter(encryptedCharacter))
                strArr[i] = encryptedCharacter;
            else
                strArr[i] = (char) ((int) strArr[i] + keySize - 26);
        }
        return String.valueOf(strArr);
    }

    static String decrypt(String str, int keySize) {
        keySize %= 26;
        char[] strArr = str.toCharArray();
        for (int i = 0; i < strArr.length; i++) {
            if (!Character.isLetter(strArr[i]))
                continue;

            char encryptedCharacter = (char) ((int) strArr[i] - keySize);

            if (Character.isLetter(encryptedCharacter))
                strArr[i] = encryptedCharacter;
            else
                strArr[i] = (char) ((int) strArr[i] - keySize + 26);
        }
        return String.valueOf(strArr);
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the text to be encoded: ");
        String str = in.next();
        System.out.print("Enter the key size: ");
        int keySize = in.nextInt();

        String encryptedStr = encrypt(str, keySize);
        System.out.println("Encrypted String: " + encryptedStr);

        String decryptedString = decrypt(encryptedStr, keySize);
        System.out.println("Decrypted String: " + decryptedString);
        in.close();
    }
}