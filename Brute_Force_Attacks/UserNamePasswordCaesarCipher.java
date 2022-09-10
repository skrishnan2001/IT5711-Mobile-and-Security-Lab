package Brute_Force_Attacks;
import java.util.*;

public class UserNamePasswordCaesarCipher {
    static int attempts = 0;
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

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the username: ");
        String user = in.next();
        System.out.print("Enter the password: ");
        String password = in.next();
        // System.out.print("Enter the cipher text: ");
        // String cipher = in.next();
        String cipher = encrypt(password, 4);
        System.out.println("Cipher text: " + cipher);
        for(int key = 1; key < 26; key++)
        {
            String decryptedPassword = decrypt(cipher, key);
            attempts++;
            System.out.println("Key = " + key + ", Decrypted String: " + decryptedPassword);
            if(password.equals(decryptedPassword))
            {
                System.out.println("You have logged in successfully!!");
                System.out.println("No. of attempts: " + attempts);
                return;
            }
        }
        in.close();
    }
}
