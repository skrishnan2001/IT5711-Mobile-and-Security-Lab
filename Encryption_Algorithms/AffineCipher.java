package Encryption_Algorithms;

import java.util.*;

public class AffineCipher
{
    static int a;
    static int b;
    static Scanner in = new Scanner(System.in);
    static String encryptMessage(char[] message)
    {
        String cipher = "";
        for (int i = 0; i < message.length; i++)
            cipher += (char) ((((a * (message[i] - 'A')) + b) % 26) + 'A');
        return cipher;
    }
 
    static String decryptCipher(String cipher)
    {
        String message = "";
        int a_inverse = 0;
        int flag = 0;
 
        for (int i = 0; i < 26; i++)
        {
            flag = (a * i) % 26;
            if (flag == 1)
                a_inverse = i;
        }

        for (int i = 0; i < cipher.length(); i++)
            message += (char) (((a_inverse * ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A');
        return message;
    }
 
    public static void main(String[] args)
    {
        System.out.print("Enter the plain text: ");
        String message = in.next();

        System.out.print("Enter the coefficients for aphine encryption(Note a & b have to be co-prime): ");
        a = in.nextInt();
        b = in.nextInt();

 
        String cipherText = encryptMessage(message.toCharArray());
        System.out.println("Encrypted Message: " + cipherText);
 
        System.out.println("Decrypted Message: " + decryptCipher(cipherText));
    }
}
 
