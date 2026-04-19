package AES;

public class AES {
    String input;
    String key;

    public AES(String input, String key) {
        this.input = input;
        this.key = key;
    }

    public String getInput() {
        return input;
    }

    public String getKey() {
        return key;
    }

    private static final int[] S_BOX = {
            0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
            0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
            0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
            0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
            0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
            0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
            0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
            0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
            0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
            0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
            0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
            0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
            0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
            0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
            0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
            0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16
    };
    public static int[][] subByte(int[][] state) {
        int[][] result = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // Lấy giá trị byte tại vị trí (i, j) làm chỉ số để tra S-box
                // Chú ý: đảm bảo giá trị trong khoảng 0-255 (0xFF)
                int index = state[i][j] & 0xFF;
                result[i][j] = S_BOX[index];
            }
        }
        return result;
    }
    public static void printState(int[][] state) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%02x ", state[i][j]);
            }
            System.out.println();
        }
    }
    public static byte[][] shiftRows(byte[][] state) {
        byte[][] y = new byte[4][4];
        // Hàng 0: Không dịch
        y[0][0] = state[0][0];
        y[0][1] = state[0][1];
        y[0][2] = state[0][2];
        y[0][3] = state[0][3];
        // Hàng 1: Dịch trái 1 byte
        y[1][0] = state[1][1];
        y[1][1] = state[1][2];
        y[1][2] = state[1][3];
        y[1][3] = state[1][0];
        // Hàng 2: Dịch trái 2 byte
        y[2][0] = state[2][2];
        y[2][1] = state[2][3];
        y[2][2] = state[2][0];
        y[2][3] = state[2][1];
        // Hàng 3: Dịch trái 3 byte (tương đương dịch phải 1 byte)
        y[3][0] = state[3][3];
        y[3][1] = state[3][0];
        y[3][2] = state[3][1];
        y[3][3] = state[3][2];
        return y;
    }
    // Hàm hỗ trợ in ma trận để kiểm tra
    public static void printState(byte[][] state) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%02X ", state[i][j]);
            }
            System.out.println();
        }
    }
    private static int mul2(int b) {
        // Dịch trái 1 bit [cite: 808]
        int result = (b << 1) & 0xFF;


        // Nếu bit ngoài cùng bên trái của b là 1 (tương đương b & 0x80 khác 0) [cite: 809]
        if ((b & 0x80) != 0) {
            // Thực hiện XOR với 0x1B (0001 1011) [cite: 809]
            result ^= 0x1B;
        }
        return result;
    }
    private static int mul3(int b) {
        // Nhân với {03} bằng nhân với {02} XOR với chính nó
        return mul2(b) ^ b;
    }
    public static int[][] MIXCOLUMN(int[][] state) {
        if (state == null || state.length != 4 || state[0].length != 4) {
            throw new IllegalArgumentException("State phải là ma trận 4x4.");
        }


        int[][] y = new int[4][4];


        // Duyệt qua từng cột của ma trận state
        for (int c = 0; c < 4; c++) {
            // Áp dụng công thức nhân ma trận MixColumns trong GF(2^8) [cite: 753, 758, 767, 768]
            y[0][c] = mul2(state[0][c]) ^ mul3(state[1][c]) ^ state[2][c] ^ state[3][c];
            y[1][c] = state[0][c] ^ mul2(state[1][c]) ^ mul3(state[2][c]) ^ state[3][c];
            y[2][c] = state[0][c] ^ state[1][c] ^ mul2(state[2][c]) ^ mul3(state[3][c]);
            y[3][c] = mul3(state[0][c]) ^ state[1][c] ^ state[2][c] ^ mul2(state[3][c]);
        }


        return y;
    }

    public static int[][] addRoundKey(int[][] state, int[][] K) {
        int[][] y = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                y[i][j] = state[i][j] ^ K[i][j];
            }
        }
        return y;
    }

    // Hàm tiện ích để in ma trận dạng HEX
    public static void printMatrix(int[][] matrix) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                System.out.printf("%02X ", matrix[r][c]);
            }
            System.out.println();
        }
    }





    public static void main(String[] args) {
        // Ví dụ ma trận đầu vào
        byte[][] state = {
                {(byte)0x32, (byte)0x88, (byte)0x31, (byte)0xe0},
                {(byte)0x43, (byte)0x5a, (byte)0x31, (byte)0x37},
                {(byte)0xf6, (byte)0x30, (byte)0x98, (byte)0x07},
                {(byte)0xa8, (byte)0x8d, (byte)0xa2, (byte)0x34}
        };
        System.out.println("Ma trận trước khi ShiftRows:");
        printState(state);
        byte[][] result = shiftRows(state);
        System.out.println("\nMa trận sau khi ShiftRows:");
        printState(result);
    }

}
