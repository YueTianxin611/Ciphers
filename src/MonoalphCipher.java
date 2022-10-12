package cipher;

import java.io.*;

/**
 * Build a MonoalphCipher entends the AbstractCipher
 */
public class MonoalphCipher extends AbstractCipher{

    int shift_key;

    /**
     * @param key: The shift key will be used by the cipher
     */
    public MonoalphCipher(int key){
        this.shift_key = key;
    }

    public MonoalphCipher() {
    }

    /**
     * A method to encrypt a given String
     * @param plaintext The plaintext to be encrypted
     * @return result The ciphertext be encrypted
     */
    public String encrypt(String plaintext) {
        StringBuilder result = new StringBuilder();
        String text = plaintext.toLowerCase();
        text = text.replaceAll("[^a-zA-Z\\s]", "");

        while(shift_key<0){
            shift_key=shift_key+26;
        }

        for(int i=0; i<text.length();i++){
            if((int)text.charAt(i)==32){
                result.append(" ");
                continue;
            }
            char ch = (char) (((int) text.charAt(i) + shift_key - 97) % 26 + 97);
            result.append(ch);
        }

        return String.valueOf(result);
    }

    /**
     * A method to decrypt a given String
     * @param ciphertext The ciphertext to decrypt
     * @return result The plaintext decrypted from the ciphertext
     */
    public String decrypt(String ciphertext) {
        int de_key;
        de_key = -shift_key;

        ciphertext = ciphertext.toLowerCase();
        ciphertext = ciphertext.replaceAll("[^a-zA-Z\\s]", "");
        StringBuffer result = new StringBuffer();


        while(de_key<0){
            de_key=de_key+26;
        }

        for(int i=0; i<ciphertext.length();i++){
            if((int)ciphertext.charAt(i)==32){
                result.append(" ");
                continue;
            }
            char ch = (char) (((int) ciphertext.charAt(i) + de_key - 97) % 26 + 97);
            result.append(ch);
        }

        return String.valueOf(result);
    }

    /**
     * A method to save the output to a file
     * @param out The OutputStream to write the cipher key to
     * @throws IOException
     */
    public void save(OutputStream out) throws IOException {
        String line1 = "MONO";
        String line2;

        String text = "abcdefghijklmnopqrstuvwxyz";
        line2 = encrypt(text);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

        bw.write(line1);
        bw.newLine();
        bw.write(line2);
        bw.close();
    }


}
