public class PlayFair {
    String banRo = "", banMa = "", khoa = "";
    char[] bangTuKhoa = new char[26 + 1];
    int[] viTri = new int[26 + 1];
    public PlayFair() {}

    public String getBanRo() {

        return banRo;
    }

    public String getBanMa() {

        return banMa;
    }

    public String getKhoa() {

        return khoa;
    }

    public void setBanRo(String banRo) {

        this.banRo = banRo.toUpperCase();
    }

    public void setBanMa(String banMa) {

        this.banMa = banMa.toUpperCase();
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa.toUpperCase();
    }

    private void sinhKhoa() {
        boolean[] kiemTra = new boolean[26 + 1];
        int j = 0;

        for (int i = 0; i < khoa.length(); i++) {
            char c = khoa.charAt(i);
            if(!kiemTra[c - 'A']){
                bangTuKhoa[j] = c;
                kiemTra[c - 'A'] = true;
                viTri[c - 'A'] = j;
                j++;
            }
        }

        for (char i = 'A'; i <= 'Z'; i++) {
            if(i == 'J') continue;

            if(!kiemTra[i - 'A']) {
                bangTuKhoa[j] = i;
                kiemTra[i - 'A'] = true;
                viTri[i - 'A'] = j;
                j++;
            }
        }
    }
    public StringBuilder duyetBanRo () {
        int j = 0;
        StringBuilder duyet = new StringBuilder();
//        while (j < banRo.length()) {
//
//            char a = banRo.charAt(j);
//
//            if (j == banRo.length() - 1) {
//                duyet.append(a).append('X');
//                break;
//            }
//
//            char b = banRo.charAt(j + 1);
//
//            if (a == b) {
//                duyet.append(a).append('X');
//                j++;
//            } else {
//                duyet.append(a).append(b);
//                j += 2;
//            }
//        }
        for (int i = 0; i < banRo.length(); i++) {

            char a = banRo.charAt(i);

            if(i == banRo.length() - 1) {
                duyet.append(a).append('X');
                break;
            }

            char b = banRo.charAt(i+1);

            if(a == b) {
                duyet.append(a).append('X');
            } else {
                duyet.append(a).append(b);
                i++;
            }
        }

        return duyet;
    }
    public String maHoa() {
        sinhKhoa();
        StringBuilder ketQua = new StringBuilder(), duyetBanRo = duyetBanRo();

        for (int i = 0; i < duyetBanRo.length(); i+=2) {
            char tu1 = duyetBanRo.charAt(i);
            char tu2 = duyetBanRo.charAt(i+1);

            int cot1 = viTri[tu1 - 'A'] % 5, hang1 = viTri[tu1 - 'A'] / 5;
            int cot2 = viTri[tu2 - 'A'] % 5, hang2 = viTri[tu2 - 'A'] / 5;

            if (cot1 == cot2) {
                hang1 = (hang1 + 1) % 5;
                hang2 = (hang2 + 1) % 5;
            }
            else if (hang1 == hang2) {
                cot1 = (cot1 + 1) % 5;
                cot2 = (cot2 + 1) % 5;
            }
            else {
                int temp = cot1;
                cot1 = cot2;
                cot2 = temp;
            }

            ketQua.append(bangTuKhoa[hang1 * 5 + cot1]);
            ketQua.append(bangTuKhoa[hang2 * 5 + cot2]);
        }
        banMa = ketQua.toString();
        return banMa;
    }
    public String giaiMa() {
        sinhKhoa();
        StringBuilder ketQua = new StringBuilder();

        for (int i = 0; i < banMa.length(); i += 2) {
            char tu1 = banMa.charAt(i);
            char tu2 = banMa.charAt(i + 1);

            int cot1 = viTri[tu1 - 'A'] % 5, hang1 = viTri[tu1 - 'A'] / 5;
            int cot2 = viTri[tu2 - 'A'] % 5, hang2 = viTri[tu2 - 'A'] / 5;

            if (cot1 == cot2) {
                hang1 = (hang1 + 4) % 5;
                hang2 = (hang2 + 4) % 5;
            }
            else if (hang1 == hang2) {
                cot1 = (cot1 + 4) % 5;
                cot2 = (cot2 + 4) % 5;
            }
            else {
                int temp = cot1;
                cot1 = cot2;
                cot2 = temp;
            }

            ketQua.append(bangTuKhoa[hang1 * 5 + cot1]);
            ketQua.append(bangTuKhoa[hang2 * 5 + cot2]);
        }

        banRo = ketQua.toString();

        return banRo;
    }
}
