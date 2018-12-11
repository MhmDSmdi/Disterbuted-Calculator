import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private byte[] userByte;
    private static final int USER_MAX_INPUT_SIZE = 10000;

    public Server() {
        try {
            serverSocket = new ServerSocket(8080);
            userByte = new byte[USER_MAX_INPUT_SIZE];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Socket connection = null;
        while (true) {
            try {
                connection = serverSocket.accept();
                BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
                inputStream.read(userByte);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
