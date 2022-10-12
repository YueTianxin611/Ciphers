package cipher;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * VigenereCipherr is a class extends MonoalphCipher class which inherits encrypt(InputStream in, OutputStream out)
 * and decrypt(InputStream in, OutputStream out), but overrides encrypt(String plaintext) and decrypt(String plaintext) methods.
 */
public class VigenereCipher extends MonoalphCipher{
    String key;

    /**
     * This is a constructor that allows users to construct a vigenere cipher
     * @param key The shift key should be specified by users
     */
    public VigenereCipher(String key){
        this.key = key;
    }

    /**
     * This method is used to encrypt a given String
     * @param plaintext The plaintext to be encrypted
     * @return The text be encrypted, the text is a String type
     */
    public String encrypt(String plaintext) {
        plaintext = plaintext.toLowerCase();
        plaintext = plaintext.replaceAll("[^a-zA-Z\\s]", "");

        StringBuilder sb = new StringBuilder();

        for(int i=0,j=0;i<plaintext.length();i++,j++){
            if((int)plaintext.charAt(i)==32){
                sb.append(" ");
                j--;
                continue;
            }
            if(j==key.length())
                j=0;
            int shift = (int)key.charAt(j)-96;
            char ch = (char) (((int) plaintext.charAt(i) + shift - 97) % 26 + 97);
            sb.append(ch);
        }
        return String.valueOf(sb);
    }
    /**
     * This method is used to decrypt a given String
     * @param ciphertext The plaintext to be encrypted
     * @return The text be decrypted, the text is a String type
     */
    public String decrypt(String ciphertext) {
        ciphertext = ciphertext.toLowerCase();
        ciphertext = ciphertext.replaceAll("[^a-zA-Z\\s]", "");

        StringBuilder sb = new StringBuilder();

        for(int i=0,j=0;i<ciphertext.length();i++,j++){
            if((int)ciphertext.charAt(i)==32){
                sb.append(" ");
                j--;
                continue;
            }
            if(j==key.length())
                j=0;
            int shift = -((int)key.charAt(j)-96);
            while(shift<0){
                shift=shift+26;
            }
            char ch = (char) (((int) ciphertext.charAt(i) + shift - 97) % 26 + 97);
            sb.append(ch);

        }
        return String.valueOf(sb);

    }

    /**
     * This method is to save the output to a file
     * @param out The OutputStream to write the cipher key to
     * @throws IOException
     */
    public void save(OutputStream out) throws IOException {
        String line1 = "VIGENERE";
        String line2 = key;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

        bw.write(line1);
        bw.newLine();
        bw.write(line2);
        bw.close();
    }
}
