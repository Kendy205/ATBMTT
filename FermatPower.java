public class FermatPower {

    static boolean isPrime(long n) {
        if (n < 2) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

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
            System.out.println("n không phải số nguyên tố");
            return;
        }

        long reducedExp;

        if (gcd(a, n) == 1) {
            //  Định lý Fermat dạng 1
            reducedExp = m % (n - 1);
            System.out.println("Dùng Fermat 1: m mod (n-1) = " + reducedExp);
        } else {
            //  Định lý Fermat dạng 2
            reducedExp = m % n;
            System.out.println("Dùng Fermat 2: m mod n = " + reducedExp);
        }

        long result = modPow(a, reducedExp, n);

        System.out.println("b: " + result);
    }
}