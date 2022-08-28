package Encryption_Algorithms;
import java.util.*;

public class SubstitutionCipher {
    static Scanner in = new Scanner(System.in);

    static void constructMap(Map<Character, Character> mp) {
        char value = 'Z';
        for (char key = 'a'; key <= 'z'; key++)
            mp.put(key, value--);
    }

    static void constructReverseMap(Map<Character, Character> mp) {
        char value = 'a';
        for (char key = 'Z'; key >= 'A'; key--)
            mp.put(key, value++);
    }

    static String encrypt(String text, Map<Character, Character> mp) {
        String encryptedText = "";
        for (char ch : text.toCharArray())
            encryptedText += mp.get(ch);
        return encryptedText;
    }

    static String decrypt(String encryptedText, Map<Character, Character> reverseMp) {
        String decryptedText = "";
        for (char ch : encryptedText.toCharArray())
            decryptedText += reverseMp.get(ch);
        return decryptedText;
    }

    public static void main(String args[]) {
        System.out.print("Enter the plain text: ");
        String text = in.next().toLowerCase();
        Map<Character, Character> mp = new HashMap<>();
        constructMap(mp);

        Map<Character, Character> reverseMp = new HashMap<>();
        constructReverseMap(reverseMp);

        String encryptedText = encrypt(text, mp);
        System.out.println("The encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, reverseMp);
        System.out.println("The decrypted text: " + decryptedText);
    }
}
