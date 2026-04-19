

public class FermatPower {

   
    static boolean isPrime(long n) {
        if (n < 2) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Tính gcd
    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Lũy thừa nhanh
    static long modPow(long a, long b, long n) {
        long result = 1;
        a %= n;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % n;
            }
            a = (a * a) % n;
            b >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        long a = 7;
        long m = 222;
        long n = 13;

        if (!isPrime(n)) {
            System.out.println("n không phải số nguyên tố → không dùng Fermat");
            return;
        }

        if (gcd(a, n) != 1) {
            System.out.println("a không nguyên tố cùng nhau với n");
            return;
        }

        // Áp dụng Fermat
        long reducedExp = m % (n - 1);

        long result = modPow(a, reducedExp, n);

        System.out.println("b: " + result);
    }
}