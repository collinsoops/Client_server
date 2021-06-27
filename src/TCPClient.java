import java.io.*;
import java.io.ByteArrayOutputStream;
import java.net.*;

class TCPClient {

    private final static String serverIP = "localhost";
    private final static int serverPort = 3248;

    // public static String fileOutput = "";
    public static void main(String args[]) {
        byte[] aByte = new byte[1];
        int bytesRead;

        Socket clientSocket = null;
        InputStream is = null;

        try {
            clientSocket = new Socket(serverIP, serverPort);
            is = clientSocket.getInputStream();
        } catch (IOException ex) {
            // Do exception handling
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (is != null) {
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            try {
                String fileOutput = "C:\\Users\\Kenya Aliens IT\\Desktop\\client\\encryptedfile.txt";
                File file = new File(fileOutput);
                fos = new FileOutputStream(fileOutput);
                bos = new BufferedOutputStream(fos);
                bytesRead = is.read(aByte, 0, aByte.length);

                do {
                    baos.write(aByte);
                    bytesRead = is.read(aByte);
                } while (bytesRead != -1);
                bos.write(baos.toByteArray());
                bos.flush();
                bos.close();
                System.out.println("*********The File Has Been Successfully Received***********");
                DESDecryption.fnDESDecryption(file);
                clientSocket.close();
            } catch (IOException ex) {
                // Do exception handling
            }
        }

    }
}








