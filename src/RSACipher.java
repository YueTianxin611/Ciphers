package cipher;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.math.BigInteger.ONE;

public class RSACipher extends AbstractCipher{
    Random rnd = new Random();
    BigInteger p = new BigInteger(126, 20, rnd);
    BigInteger q = new BigInteger(126, 20, rnd);

    public RSACipher(){
    }

    public RSACipher(BigInteger a, BigInteger b, BigInteger c){
        this.n = b;
        this.e = a;
        this.d = c;
    }

    BigInteger n = p.multiply(q);
    BigInteger phi = (p.subtract(ONE)).multiply(q.subtract(ONE));
    BigInteger e = BigInteger.valueOf(65537);
    BigInteger d = e.modInverse(phi);


    public void encrypt(BigInteger in, OutputStream out) throws IOException {;
        //System.out.println(in);
        //byte[] result = in.modPow(e, n).toByteArray();
    }

    @Override
    public String encrypt(String plaintext) {
        BigInteger text = new BigInteger(plaintext.getBytes());
        byte[] result = text.modPow(e, n).toByteArray();
        String encrypted = new String(result, StandardCharsets.UTF_8);
        System.out.println("encrypted"+encrypted);
        return encrypted;
    }

    public String decrypt(String ciphertext){
        BigInteger plaintext = (new BigInteger(ciphertext.getBytes())).modPow(d, n);
        String res = new String(plaintext.toByteArray());
        System.out.println("decrypted"+res);
        return res;
    }

    @Override
    public void save(OutputStream out) throws IOException {
        String line1 = "RSA";
        String line2 = String.valueOf(e);
        String line3 = String.valueOf(n);
        String line4 = String.valueOf(d);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

        bw.write(line1);
        bw.newLine();
        bw.write(line2);
        bw.newLine();
        bw.write(line3);
        bw.newLine();
        bw.write(line4);
        bw.close();
    }


}
