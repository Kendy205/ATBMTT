public class Vigenere_LapKhoa {
    String banMa = "", banRo = "", khoa = "";


    public Vigenere_LapKhoa() {};

    public String getBanMa() {
        return banMa;
    }

    public String getBanRo() {
        return banRo;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setBanMa(String banMa) {
        this.banMa = banMa;
    }

    public void setBanRo(String banRo) {
        this.banRo = banRo;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }
    public StringBuilder lapKhoa() {
        StringBuilder lapKhoa = new StringBuilder();
        lapKhoa.append(khoa);
        int leng = banRo.length() - khoa.length();
        for (int i = 0 ; i < leng ; i++) {
            char k = khoa.charAt(i % khoa.length());
            lapKhoa.append(k);
        }
        return  lapKhoa;
    }
    public String maHoa() {
        StringBuilder lapKhoa = lapKhoa(), ketQua = new StringBuilder();
        for (int i = 0 ; i < banRo.length() ; i++ ) {
            if(banRo.charAt(i) >= 'a' && banRo.charAt(i) <= 'z') {
                int viTriTuKhoa = lapKhoa.charAt(i) -'a';
                int viTriTuBanRo = banRo.charAt(i) - 'a';
                char tuMaHoa = (char) (((viTriTuBanRo + viTriTuKhoa) % 26) + 'a');
                ketQua.append(tuMaHoa);
            } else if (banRo.charAt(i) >= 'A' && banRo.charAt(i) <= 'Z') {
                int viTriTuKhoa = lapKhoa.charAt(i) - 'A';
                int viTriTuBanRo = banRo.charAt(i) - 'A';
                char tuMaHoa = (char) (((viTriTuBanRo + viTriTuKhoa) % 26) + 'A');
                ketQua.append(tuMaHoa);
            }  else {
                ketQua.append(banRo.charAt(i));
            }
        }
        banMa = ketQua.toString();
        return banMa;
    }

    public String giaiMa() {
        StringBuilder lapKhoa =lapKhoa(), ketQua = new StringBuilder();

        for (int i = 0 ; i < banMa.length() ; i++ ) {
            if(banMa.charAt(i) >= 'a' && banMa.charAt(i) <= 'z') {
                int viTriTuKhoa = lapKhoa.charAt(i) - 'a';
                int viTriTuBanMa = banMa.charAt(i) - 'a';
                char tuGiaiMa = (char) (((viTriTuBanMa - viTriTuKhoa + 26) % 26 ) + 'a');
                ketQua.append(tuGiaiMa);
            } else if (banMa.charAt(i) >= 'A' && banMa.charAt(i) <= 'Z') {
                int viTriTuKhoa = lapKhoa.charAt(i) - 'A';
                int viTriTuBanMa = banMa.charAt(i) - 'A';
                char tuGiaiMa = (char) (((viTriTuBanMa - viTriTuKhoa + 26) % 26) + 'A');
                ketQua.append(tuGiaiMa);
            }  else {
                ketQua.append(banMa.charAt(i));
            }
        }
        banRo = ketQua.toString();
        return banRo;
    }
}
