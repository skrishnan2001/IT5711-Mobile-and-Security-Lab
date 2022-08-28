package Brute_Force_Attacks;
import java.util.*;

public class BruteForceAttackCaesarCipher {
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
        System.out.print("Enter cipher text: ");
        String str = in.next();

        for(int keySize = 0; keySize < 26; keySize++)
        {
            String decryptedString = decrypt(str, keySize);
            System.out.println("Key = " + keySize + ", Decrypted String: " + decryptedString);
        }
        in.close();
    }
}