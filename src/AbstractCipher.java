package cipher;

import java.io.*;


/** Build an abstract class AbstractCipher to implement the interface Cipher
 * Methods implemented: encrypt(InputStream in, OutputStream out), decrypt(InputStream in, OutputStream out)
 * Two other methods inherited from Cipher: encrypt(String plaintext), decrypt(String ciphertext)
 */
public abstract class AbstractCipher implements Cipher{
    /**
     * @param in The InputStream the plaintext is on
     * @param out The OutputStream to send the ciphertext to
     * @throws IOException
     */
    public void encrypt(InputStream in, OutputStream out) throws IOException {
        Reader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        String line = br.readLine();
        br.close();

        String output = encrypt(line);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(output);
        bw.close();

        //byte[] bytes = output.getBytes();
        //out.write (bytes);
        //out.close ();
    }

    /**
     *
     * @param in The InputStream the ciphertext is on
     * @param out The OutputStream to send the plaintext to
     * @throws IOException
     */
    public void decrypt(InputStream in, OutputStream out) throws IOException {
        Reader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        String line = br.readLine();
        br.close();

        String output = decrypt(line);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(output);
        bw.close();

        //byte[] bytes = output.getBytes();
        //out.write (bytes);
        //out.close ();
    }


}
