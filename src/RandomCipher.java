package cipher;

/**
 * RandomCipher is a class extends MonoalphCipher class
 */
public class RandomCipher extends MonoalphCipher{

    /**
     * A constructor of RandomCipher that allows user to construct a random cipher
     * @param key The shift key should be specified by the user
     */
    public RandomCipher(int key) {
        this.shift_key = key;
    }

}
