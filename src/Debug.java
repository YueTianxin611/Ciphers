package cipher;

/** Display a byte array. For debugging purposes. */
public class Debug {
    static boolean DEBUG = false;

    static void show(byte[] b) {
        if (!DEBUG) return;
        for (int i = 0; i < b.length; i++) {
            if (i % 16 == 0) System.out.format("\n%5d: ", i);
            System.out.format("%02X ", b[i]);
        }
        System.out.println();
    }
}
