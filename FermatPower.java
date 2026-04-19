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
            if ((b % 2) == 1) {
                result = (result * a) % n;
            }
            a = (a * a) % n;
            b = b / 2 ;
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

        long result;

       
        if (a % n == 0) {
            System.out.println("Fermat 2: a ≡ 0 mod n → kết quả = 0");
            result = 0;
        }

       
        else if (gcd(a, n) == 1) {
             m = m % (n - 1);
            System.out.println("Fermat 1:  ");
            result = modPow(a, m, n);
        }

        
        else {
            System.out.println("Fermat 2:");

            while (m >= n) {
                long k = m / n;
                long r = m % n;
                m = k + r;
             }

            System.out.println("Biến đổi m → k + r = " + m);

            result = modPow(a, m, n);
        }

        System.out.println("b: " + result);
    }
}