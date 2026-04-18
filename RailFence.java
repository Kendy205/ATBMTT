public class RailFence {
    String banRo = "", banMa = "";
    int khoa = 0;
    public RailFence() {}

    public String getBanRo() {

        return banRo;
    }

    public String getBanMa() {

        return banMa;
    }

    public int getKhoa() {

        return khoa;
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
        if (khoa <= 1) return banRo;

        StringBuilder ketQua = new StringBuilder();
        StringBuilder duyetBanRo = new StringBuilder();

        for(int i = 0 ; i < banRo.length() ; i++) {
            if(banRo.charAt(i) >='a' && banRo.charAt(i) <= 'z' || banRo.charAt(i) >='A' && banRo.charAt(i) <= 'Z'){
                duyetBanRo.append(banRo.charAt(i));
            }
        }

        for (int i = 0; i < khoa; i++) {
            int j = i;
            while (j < duyetBanRo.length()) {
                ketQua.append(duyetBanRo.charAt(j));
                j += khoa;
            }
        }
        banMa = ketQua.toString();
        return banMa;
    }
    public String giaiMa() {
        if (khoa <= 1) return banMa;

        int n = banMa.length();
        char[] ketQua = new char[n];

        int index = 0;

        for (int i = 0; i < khoa; i++) {
            int j = i;
            while (j < n) {
                ketQua[j] = banMa.charAt(index++);
                j += khoa;
            }
        }

        banRo = new String(ketQua);
        return banRo;
    }
}
