package Block_Ciphers;
import java.math.*;
import java.util.*;

public class RSA {
    static Scanner in = new Scanner(System.in);

    static int gcd(int e, int z) {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }

    public static void main(String args[]) {
        int p, q, n, eulerTotient, d = 0, e;

        System.out.print("Enter the integer message: ");
        int msg = in.nextInt();
        double c;
        BigInteger msgback;

        System.out.print("Enter the values of p & q: ");
        p = in.nextInt();
        q = in.nextInt();
        // 3 & 11 for testing

        n = p * q;
        System.out.println("The value of n = " + n);

        eulerTotient = (p - 1) * (q - 1);

        System.out.println("the value of Euler Totient function = " + eulerTotient);

        for (e = 2; e < eulerTotient; e++) {

            // e -> public key exponent
            if (gcd(e, eulerTotient) == 1) {
                break;
            }
        }
        System.out.println("The value of e = " + e);
        System.out.println("The public key : {" + e + ", " + n + "}");

        for(d = 1; d <= eulerTotient; d++)
        {
            if((e * d) % eulerTotient == 1)
                break;
        }

        System.out.println("The value of d = " + d);        
        System.out.println("The private key : {" + d + ", " + n + "}");

        c = (Math.pow(msg, e)) % n;
        System.out.println("Encrypted message : " + c);

        BigInteger N = BigInteger.valueOf(n);

        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        msgback = (C.pow(d)).mod(N);
        System.out.println("Decrypted message : " + msgback);
    }
}