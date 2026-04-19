package MaCoDien;

public class MaDon {
    String khoa = "",banRo = "", banMa = "";

    public MaDon() {}

    public String getKhoa() {
        return khoa;
    }

    public String getBanRo() {
        return banRo;
    }

    public String getBanMa() {
        return banMa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public void setBanRo(String banRo) {
        this.banRo = banRo;
    }

    public void setBanMa(String banMa) {
        this.banMa = banMa;
    }
    public String maHoa() {
        StringBuilder ketQua = new StringBuilder();
        for(int i = 0 ; i < banRo.length() ; i++) {

            if(banRo.charAt(i) >= 'a' && banRo.charAt(i) <= 'z') {
                int viTriTuBanRo = banRo.charAt(i) - 'a';
                char tuBanMa = khoa.charAt(viTriTuBanRo);
                ketQua.append(tuBanMa);

            } else if (banRo.charAt(i) >= 'A' && banRo.charAt(i) <= 'Z') {
                int viTriTuBanRo = banRo.charAt(i) - 'A';
                char tuBanMa = khoa.charAt(viTriTuBanRo);
                ketQua.append(tuBanMa);

            }  else {
                ketQua.append(banRo.charAt(i));
            }

        banMa = ketQua.toString();
        }
        return banMa;
    }
    public String giaiMa() {
        StringBuilder ketQua = new StringBuilder();
        for(int i = 0 ; i < banMa.length() ; i++) {

            if(banMa.charAt(i) >= 'a' && banMa.charAt(i) <= 'z') {
                int viTriTuBanMa = khoa.indexOf(banMa.charAt(i));
                char tuBanRo = (char) (viTriTuBanMa + 'a');
                ketQua.append(tuBanRo);

            } else if (banMa.charAt(i) >= 'A' && banMa.charAt(i) <= 'Z') {
                int viTriTuBanMa = khoa.indexOf(banMa.charAt(i));
                char tuBanRo = (char) (viTriTuBanMa + 'A');
                ketQua.append(tuBanRo);

            }  else {
                ketQua.append(banMa.charAt(i));
            }
            banRo = ketQua.toString();
        }
        return banRo;
    }

    public static void main(String[] args) {
            MaDon md = new MaDon();
            md.setKhoa("THLEYNPSXADWKFUBOGMQVJRCIZ");
            md.setBanRo("AWOMANGIVESANDFO");
            System.out.println( "Plain : abcdefghijklmnopqrstuvwxyz");
            System.out.println( "Ban ro hoa chu don : AWOMANGIVESANDFO" );
            System.out.println( "Ban ma hoa chu don : " + md.maHoa());

    }
}
