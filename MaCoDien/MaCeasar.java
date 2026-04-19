package MaCoDien;

public class MaCeasar {
    private String banRo = "", banMa = "";
    int khoa = 0;

    public MaCeasar() {}

    public int getKhoa() {
        return khoa;
    }

    public String getBanRo() {
        return banRo;
    }

    public String getBanMa() {
        return banMa;
    }

    public void setBanRo(String banRo) {
        this.banRo = banRo;
    }

    public void setBanMa(String banMa) {
        this.banMa = banMa;
    }

    public void setKhoa(int khoa) {
        this.khoa = khoa;
    }

    public String maHoa() {
        StringBuilder ketQua = new StringBuilder();

        for (int i = 0; i < banRo.length(); i++) {
            char c = banRo.charAt(i);

            if (c >= 'a' && c <= 'z') {
                int viTriCu = c - 'a';
                int viTriMoi = (viTriCu + khoa) % 26;
                ketQua.append((char) (viTriMoi + 'a'));

            } else if (c >= 'A' && c <= 'Z') {
                int viTriCu = c - 'A';
                int viTriMoi = (viTriCu + khoa) % 26;
                ketQua.append((char) (viTriMoi + 'A'));

            } else {
                ketQua.append(c);
            }
        }
        banMa = ketQua.toString();
        return banMa;
    }

    public String giaiMa() {
        StringBuilder ketQua = new StringBuilder();

        for (int i = 0; i < banMa.length(); i++) {
            char c = banMa.charAt(i);

            if (c >= 'a' && c <= 'z') {
                int viTriCu = c - 'a';
                int viTriMoi = (viTriCu - khoa + 26) % 26;
                ketQua.append((char) (viTriMoi + 'a'));
            } else if (c >= 'A' && c <= 'Z') {
                int viTriCu = c - 'A';
                int viTriMoi = (viTriCu - khoa + 26) % 26;
                ketQua.append((char) (viTriMoi + 'A'));
            } else {
                ketQua.append(c);
            }
        }

        banRo = ketQua.toString();
        return banRo;
    }

    public static void main(String[] args) {
        MaCeasar ceasar = new MaCeasar();
        ceasar.setBanRo("NOROSEWITHOUTATH");
         int k =8;
        ceasar.setKhoa(k);

        System.out.println("Ban ro Ceaser : NOROSEWITHOUTATH"+ "\nKhóa: "+ k);
        System.out.println("Ban ma Ceaser : " + ceasar.maHoa());

    }
}