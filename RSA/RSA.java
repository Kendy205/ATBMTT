package RSA;

import java.math.BigInteger;

public class RSA {
    public static void main(String[] args) {
        // 1. Khởi tạo thông số đề bài
        long p = 43, q = 47, e = 67, M = 59;
        long n = p * q;
        long phiN = (p - 1) * (q - 1);


        System.out.println("================ RSA SYSTEM ================");
        System.out.println("Thông số: p=" + p + ", q=" + q + ", e=" + e + ", M=" + M);
        System.out.println("n = p * q = " + n);
        System.out.println("phi(n) = (p-1)*(q-1) = " + phiN);
        System.out.println("============================================\n");


        // 2. Tìm khóa riêng d bằng bảng Euclid mở rộng
        System.out.println("BƯỚC 1: TÌM KHÓA RIÊNG d (Nghịch đảo của e mod phi(n))");
        long d = tinhEuclidMoRong(e, phiN);
        System.out.println("=> Khóa riêng d tìm được: " + d + "\n");


        // 3. Thực hiện Bài toán 1: Chữ ký số
        System.out.println("BƯỚC 2: BÀI TOÁN 1 - CHỮ KÝ SỐ (AN KÝ)");
        System.out.println("An dùng khóa riêng d=" + d + " để ký:");
        long C1 = tinhLuyThuaShowBuoc(M, d, n);
        System.out.println("=> Bản mã (Chữ ký) C1 = " + C1);
        System.out.println("Người nhận dùng khóa công khai e=" + e + " để kiểm tra:");
        long check1 = tinhLuyThuaShowBuoc(C1, e, n);
        System.out.println("=> Kết quả giải mã: " + check1 + " (Khớp với M gốc)\n");


        // 4. Thực hiện Bài toán 2: Bảo mật
        System.out.println("BƯỚC 3: BÀI TOÁN 2 - BẢO MẬT (BA GỬI CHO AN)");
        System.out.println("Ba dùng khóa công khai e=" + e + " của An để mã hóa:");
        long C2 = tinhLuyThuaShowBuoc(M, e, n);
        System.out.println("=> Bản mã C2 = " + C2);
        System.out.println("An dùng khóa riêng d=" + d + " của mình để giải mã:");
        long check2 = tinhLuyThuaShowBuoc(C2, d, n);
        System.out.println("=> Kết quả giải mã: " + check2 + " (Khớp với M gốc)");
    }


    /**
     * Hàm in bảng Euclid mở rộng 5 cột: Q, A1, A2, B1, B2
     */
    public static long tinhEuclidMoRong(long e, long phi) {
        long a1 = 0, a2 = phi;
        long b1 = 1, b2 = e;


        System.out.println("------------------------------------------------------------");
        System.out.printf("%-5s | %-10s | %-10s | %-10s | %-10s\n", "Q", "A1", "A2", "B1", "B2");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-5s | %-10d | %-10d | %-10d | %-10d\n", "—", a1, a2, b1, b2);


        while (b2 != 1) {
            long q = a2 / b2;
            long t1 = a1 - q * b1;
            long t2 = a2 - q * b2;


            // Cập nhật giá trị cho dòng tiếp theo
            a1 = b1; a2 = b2;
            b1 = t1; b2 = t2;


            System.out.printf("%-5d | %-10d | %-10d | %-10d | %-10d\n", q, a1, a2, b1, b2);
        }
        System.out.println("------------------------------------------------------------");


        // Nếu b1 âm, đưa về số dương trong modulo phi
        long d = (b1 < 0) ? b1 + phi : b1;
        return d;
    }


    /**
     * Hàm tính lũy thừa nhanh (Square and Multiply) in bảng cột n = 1, 2, 4, 8...
     */
    public static long tinhLuyThuaShowBuoc(long coSo, long soMu, long mod) {
        long ketQua = 1;
        long soDuHienTai = coSo % mod;
        long tempSoMu = soMu;
        long n = 1;


        System.out.println("Bảng tính " + coSo + "^" + soMu + " mod " + mod + ":");
        System.out.printf("%-12s | %-12s | %-18s | %-10s\n", "Số mũ (n)", "Giá trị dư", "Hành động", "Tích dồn");


        while (tempSoMu > 0) {
            String hanhDong;
            if (tempSoMu % 2 == 1) {
                ketQua = (ketQua * soDuHienTai) % mod;
                hanhDong = "NHÂN (Bit 1)";
            } else {
                hanhDong = "Bỏ qua (Bit 0)";
            }


            System.out.printf("%-12d | %-12d | %-18s | %-10d\n", n, soDuHienTai, hanhDong, ketQua);


            soDuHienTai = (soDuHienTai * soDuHienTai) % mod;
            tempSoMu /= 2;
            n *= 2;
        }
        return ketQua;
    }
}



