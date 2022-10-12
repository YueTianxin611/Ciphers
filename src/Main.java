package cipher;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command line interface to allow users to interact with your ciphers.
 */
public class Main {
    static Cipher cipher;
    static String output = "";
    static OutputStream out;
    static InputStream in;

    /**
     * The main function to run the program
     * @param args, sample input : java -jar <your_jar> --caesar 15 --em "ENCrypt␣Me!" --out encr.txt --save ca15
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String[] myargs= {"--monoLoad", "ca15", "--ef", "encr.txt" ,"--print"};
        int pos1 = parseCipherType(myargs, 0);
        int pos2 = parseCipherFunction(myargs, pos1);
        parseOutputOptions(myargs, pos2);
    }

    /**
     * Set up the cipher type based on the options found in args starting at position pos, and
     * return the index into args just past any cipher type options.
     */
    private static int parseCipherType(String[] args, int pos) throws IllegalArgumentException, IOException {
        // check if arguments are exhausted
        if (pos == args.length) return pos;

        String cmdFlag = args[pos++];
        switch (cmdFlag) {
            case "--caesar":
                // Create a new Caesar cipher with the given integer shift parameter.
                int shift = Integer.parseInt(args[pos++]);
                cipher = CipherFactory.getCaesarCipher(shift);
                break;
            case "--random":
                //  Create a new monoalphabetic substitution cipher with a randomly
                //  chosen permutation of the alphabet.
                cipher = CipherFactory.getRandomSubstitutionCipher();
                pos++;
                break;
            case "--monoLoad":
                //Load a monoalphabetic substitution cipher (caesar or random) from the file specified.
                BufferedReader br = new BufferedReader(new FileReader(args[pos++]));
                String type = br.readLine();
                String map = br.readLine();
                cipher = CipherFactory.getMonoCipher(map);
                break;
            case "--vigenere":
                //Create a new Vigene`re cipher with the given keyword. The keyword is given as a
                //string of maximum length 128 characters.
                String key = args[pos++];
                cipher = CipherFactory.getVigenereCipher(key);
                break;
            case "--vigenereLoad":
                // Load a Vigene`re cipher from the file specified.
                File f_vige = new File(args[pos++]);
                in = new FileInputStream(f_vige);
                BufferedReader br_vige = new BufferedReader(new InputStreamReader(in));
                String type_vige = br_vige.readLine();
                String key_vige = br_vige.readLine();
                cipher = CipherFactory.getVigenereCipher(key_vige);
                break;
            case "--rsa":
                // TODO create new RSA cipher
                break;
            case "--rsaLoad":
                // Create an RSA encrypter/decrypter from the public/private key pair stored in the file specified.
                break;
            default:
                System.out.println("Initialize Cipher Failed...");
                break;
        }
        return pos;
    }

    /**
     * Parse the operations to be performed by the program from the command-line arguments in args
     * starting at position pos. Return the index into args just past the parsed arguments.
     */
    private static int parseCipherFunction(String[] args, int pos) throws IllegalArgumentException, IOException {
        // check if arguments are exhausted
        if (pos == args.length) return pos;

        switch (args[pos++]) {
            case "--em":
                // Encrypt the given string using the specified cipher scheme.
                output = cipher.encrypt(args[pos++]);
                break;
            case "--ef":
                // Encrypt the contents of the specified file using the specified cipher scheme.
                File ef = new File(args[pos++]);
                in = new FileInputStream(ef);
                BufferedReader br_ef = new BufferedReader(new InputStreamReader(in));
                String line_ef = br_ef.readLine();
                output=cipher.encrypt(line_ef);
                break;
            case "--dm":
                // Decrypt the given string using the specified cipher scheme.
                output = cipher.decrypt(args[pos++]);
                break;
            case "--df":
                // Decrypt the contents of the specified file using the specified cipher scheme.
                File f = new File(args[pos++]);
                in = new FileInputStream(f);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line = br.readLine();
                output=cipher.decrypt(line);
                break;
            default:
                System.out.println("Initialize Operation failed... The accepted parameters are: --em, --ef, --dm, --df");
                break;
        }
        return pos;
    }

    /**
     * Parse options for output, starting within {@code args} at index {@code argPos}. Return the
     * index in args just past such options.
     */
    private static int parseOutputOptions(String[] args, int pos) throws IllegalArgumentException, IOException {
        // check if arguments are exhausted
        if (pos == args.length) return pos;

        String cmdFlag;
        while (pos < args.length) {
            switch (cmdFlag = args[pos++]) {
                case "--print":
                    // Print the result of applying the cipher (if any) to the console.
                    System.out.println(output);
                    break;
                case "--out":
                    // Print the result of applying the cipher (if any) to the specified file.
                    File f_out = new File(args[pos++]);
                    out = new FileOutputStream(f_out);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
                    bw.write(output);
                    bw.close();
                    break;
                case "--save":
                    // Save the current cipher to the specified file.
                    File f = new File(args[pos++]);
                    out = new FileOutputStream(f);
                    cipher.save(out);
                    break;
                default:
                    System.out.println("Output operation failed..., the accepted commands are: --print, --out, --save");
                    break;
            }
        }
        return pos;
    }
}
