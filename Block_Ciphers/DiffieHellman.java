package Block_Ciphers;

import java.util.*;

public class DiffieHellman {
    static Scanner in = new Scanner(System.in);
    private static long moduloPower(long a, long b, long p) {
        if (b == 1)
            return a;
        else
            return (((long) Math.pow(a, b)) % p);
    }

    static long power(long x, long y, long p) {
        long res = 1; 

        x = x % p; 

        while (y > 0) {
            if (y % 2 == 1) 
                res = (res * x) % p;
        
            y = y >> 1; 
            x = (x * x) % p;
        }
        return res;
    }

    static boolean isPrime(long n) {
        if (n <= 1)
            return false;

        if (n <= 3)
            return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (long i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }

    static void findPrimefactors(HashSet<Long> s, long n) {
        while (n % 2 == 0) {
            s.add((long)2);
            n = n / 2;
        }

        for (long i = 3; i <= Math.sqrt(n); i = i + 2) {
            while (n % i == 0) {
                s.add(i);
                n = n / i;
            }
        }

        if (n > 2)
            s.add(n);
    }

    static long findPrimitive(long n) {
        HashSet<Long> s = new HashSet<Long>();

        if (isPrime(n) == false) 
            return -1;

        long phi = n - 1;

        findPrimefactors(s, phi);

        for (long r = 2; r <= phi; r++) {
            boolean flag = false;
            for (Long a : s) {

                if (power(r, phi / (a), n) == 1) {
                    flag = true;
                    break;
                }
            }

            if (flag == false)
                return r;
        }
        return -1;
    }

    public static void main(String[] args) {
        long P, G, x, a, y, b, ka, kb;

        System.out.print("Enter the value of P: ");
        P = in.nextLong();

        G = findPrimitive(P);
        System.out.println("The value of G (Primitive Root of P): " + G);

        System.out.print("Enter the private key for a: ");
        a = in.nextLong();
        x = moduloPower(G, a, P);

        System.out.print("Enter the private key for b: ");
        b = in.nextLong();
        y = moduloPower(G, b, P);

        ka = moduloPower(y, a, P);
        kb = moduloPower(x, b, P);


        System.out.println("Secret key for A: " + ka);
        System.out.println("Secret key for B: " + kb);
    }
}
