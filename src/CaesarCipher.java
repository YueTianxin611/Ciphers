package cipher;

/**
 * CaesarCipher is a class extends MonoalphCipher class
 */
public class CaesarCipher extends MonoalphCipher {
    /**
     * A constructor of CaesarCipher that allows user to construct a caesar cipher
     * @param shift The shift key should be specified by the user
     */
    public CaesarCipher(int shift) {
        this.shift_key = shift;
    }
}
