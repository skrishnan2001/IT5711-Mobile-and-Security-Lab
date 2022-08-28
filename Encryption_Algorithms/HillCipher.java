package Encryption_Algorithms;
//Sample input: 
//text -> helloworld
//key -> cbde

import java.util.*;

public class HillCipher {
    static Scanner in = new Scanner(System.in);

    static int[][] constructKeyMatrix(String key) {
        int[][] keyMat = new int[2][2];
        int idx = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                keyMat[i][j] = key.charAt(idx++) - 'A';
        }
        return keyMat;
    }

    static int[][][] constructTextMatrix(String text) {
        int n = text.length() / 2;
        int[][][] textMat = new int[n][2][1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++)
                textMat[i][j][0] = text.charAt(idx++) - 'A';
        }
        return textMat;
    }

    static int[][][] constructCipherMatrix(String text, String key) {
        int[][] keyMat = constructKeyMatrix(key);
        System.out.println("\nKey Matrix: ");
        printKeyMatrix(keyMat);
        int[][][] textMat = constructTextMatrix(text);
        int n = text.length() / 2;
        int[][][] cipherMat = new int[n][2][1];

        for (int mat = 0; mat < n; mat++) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 1; j++) {
                    for (int k = 0; k < 2; k++)
                        cipherMat[mat][i][j] += keyMat[i][k] * textMat[mat][k][j];
                    cipherMat[mat][i][j] %= 26;
                }
            }
        }
        return cipherMat;
    }

    static void printKeyMatrix(int[][] keyMat) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(keyMat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static String encrypt(String text, String key) {
        int[][][] cipherMat = constructCipherMatrix(text, key);

        String encryptedText = "";

        for (int[][] mat : cipherMat) {
            encryptedText += (char) (mat[0][0] + 65);
            encryptedText += (char) (mat[1][0] + 65);
        }
        return encryptedText;
    }

    static int findDeterminant(int[][] key) {
        return key[0][0] * key[1][1] - key[0][1] * key[1][0];
    }

    static void findAdjointMatrix(int[][] key) {
        int temp = key[0][0];
        key[0][0] = key[1][1];
        key[1][1] = temp;

        key[0][1] *= -1;
        key[1][0] *= -1;
    }

    static String decrypt(String encryptedText, String key) {
        int[][][] cipherMat = constructTextMatrix(encryptedText);
        int n = encryptedText.length() / 2;

        int[][] keyMat = constructKeyMatrix(key);
        int D = findDeterminant(keyMat);
        findAdjointMatrix(keyMat);

        // Modifying keyMat to make it's inverse
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int curr = (keyMat[i][j] * (26 - D % 26)) % 26;
                keyMat[i][j] = curr > 0 ? curr : 26 + curr;
            }
        }

        System.out.println("\nInverse Key Matrix: ");
        printKeyMatrix(keyMat);

        int[][][] originalMatrix = new int[n][2][1];

        for (int mat = 0; mat < n; mat++) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 1; j++) {
                    for (int k = 0; k < 2; k++)
                        originalMatrix[mat][i][j] += keyMat[i][k] * cipherMat[mat][k][j];
                    originalMatrix[mat][i][j] %= 26;
                }
            }
        }

        String decryptedText = "";

        for (int[][] mat : originalMatrix) {
            decryptedText += (char) (mat[0][0] + 65);
            decryptedText += (char) (mat[1][0] + 65);
        }
        return decryptedText;
    }

    public static void main(String args[]) {
        System.out.print("Enter the plain text: ");
        String text = in.next().toUpperCase();
        if (text.length() % 2 != 0) {
            System.out.println("The length of the text should be even!!");
            return;
        }

        System.out.print("Key: ");
        String key = in.next().toUpperCase();
        if (key.length() != 4) {
            System.out.println("The key length should be 4!!");
            return;
        }

        String encryptedText = encrypt(text, key);
        System.out.println("The encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("The decrypted text: " + decryptedText);
    }
}
