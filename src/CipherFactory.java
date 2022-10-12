package cipher;

import java.math.BigInteger;
import java.util.Random;

/** Factory class for creating cipher objects. */
public class CipherFactory {

    /**
     * Returns: a monoalphabetic substitution cipher with the English alphabet mapped to the
     * provided alphabet.<br>
     * Requires: {@code encrAlph} contains exactly one occurrence of each English letter and nothing
     * more. No requirement is made on case.
     *
     * @param encrAlph the encrypted alphabet
     */
    public static Cipher getMonoCipher(String encrAlph) {
        int key = encrAlph.charAt(0)-97;
        return new MonoalphCipher(key); // TODO implement
    }

    /**
     * Returns a new Caesar cipher with the given shift parameter.
     *
     * @param shift the cipher's shift parameter
     */
    public static Cipher getCaesarCipher(int shift) {

        return new CaesarCipher(shift); // TODO implement
    }

    /**
     * Returns a Vigenere cipher (with multiple shifts).
     *
     * @param key the cipher's shift parameters. Note that a is a shift of 1.
     */
    public static Cipher getVigenereCipher(String key) {
        return new VigenereCipher(key); // TODO implement
    }

    /** Returns a new monoalphabetic substitution cipher with a randomly generated mapping. */
    public static Cipher getRandomSubstitutionCipher() {
        Random rd = new Random();
        int rd_key = rd.nextInt();
        return new RandomCipher(rd_key); // TODO implement
    }

    /** Returns a new RSA cipher with a randomly generated keys. */
    public static Cipher getRSACipher() {
        Random rnd = new Random();

        return new RSACipher(); // TODO implement
    }

    /**
     * Returns a new RSA cipher with given key.
     *
     * @param e encryption key
     * @param n modulus
     * @param d decryption key
     */
    public Cipher getRSACipher(BigInteger e, BigInteger n, BigInteger d) {
        return new RSACipher(e,n,d); // TODO implement
    }
}
