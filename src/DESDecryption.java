
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESDecryption {
    //creating an instance of the Cipher class for encryption
    private static Cipher encrypt;
    //creating an instance of the Cipher class for decryption
    private static Cipher decrypt;
    //initializing vector
    private static final byte[] initialization_vector = {22, 33, 11, 44, 55, 99, 66, 77};

    //main() method
    public static void fnDESDecryption(File file) {

        String encryptedData=String.valueOf(file);
        //path of the decrypted file that we get as output
        String decryptedData = "C:\\Users\\Kenya Aliens IT\\Desktop\\client\\decrypted\\dec.txt";

        try {
            /*
           generating keys by using the KeyGenerator class
           SecretKey scrtkey = KeyGenerator.getInstance("DES").generateKey();
            System.out.println(scrtkey);
             */
            //generating secret key from a constant String Password
            String password = "3nuyeb9thd6";
            byte[] bytes = password.getBytes();
            DESKeySpec keySpec = new DESKeySpec(bytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey scrtkey = keyFactory.generateSecret(keySpec);


            AlgorithmParameterSpec aps = new IvParameterSpec(initialization_vector);

            //setting decryption mode
            decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decrypt.init(Cipher.DECRYPT_MODE, scrtkey, aps);

            //calling decrypt() method to decrypt the file
            decryption(new FileInputStream(encryptedData), new FileOutputStream(decryptedData));
            //prints the stetment if the program runs successfully
            System.out.println("The file has been  successfully Decrypted.");
        }
        //catching multiple exceptions by using the | (or) operator in a single catch block
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IOException | InvalidKeySpecException e) {
            //prints the message (if any) related to exceptions
            e.printStackTrace();
        }
    }

    //method for decryption
    private static void decryption(InputStream input, OutputStream output)
            throws IOException {
        input = new CipherInputStream(input, decrypt);
        //calling the writeBytes() method to write the decrypted bytes to the file
        writeBytes(input, output);
    }

    //method for writting bytes to the files
    private static void writeBytes(InputStream input, OutputStream output)
            throws IOException {
        byte[] writeBuffer = new byte[512];
        int readBytes = 0;
        while ((readBytes = input.read(writeBuffer)) >= 0) {
            output.write(writeBuffer, 0, readBytes);
        }
        //closing the output stream
        output.close();
        //closing the input stream
        input.close();
    }
}
