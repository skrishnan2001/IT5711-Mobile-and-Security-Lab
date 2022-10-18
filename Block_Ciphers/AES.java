package Block_Ciphers;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;
import java.util.*;

public class AES {
    private SecretKey key;
    private final int KEY_SIZE = 128;
    private final int DATA_LENGTH = 128;
    private Cipher encryptionCipher;

    public void init() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();
    }

    public String encrypt(String data) throws Exception {
        byte[] dataInBytes = data.getBytes();
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        byte[] dataInBytes = decode(encryptedData);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
        return new String(decryptedBytes);
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            AES aes_encryption = new AES();
            aes_encryption.init();
            System.out.print("Enter the string to be encrypted: ");
            String text = in.next();
            String encryptedData = aes_encryption.encrypt(text);
            String decryptedData = aes_encryption.decrypt(encryptedData);

            System.out.println("Encrypted Data : " + encryptedData);
            System.out.println("Decrypted Data : " + decryptedData);
        } catch (Exception ignored) {
        }
        in.close();
    }
}
