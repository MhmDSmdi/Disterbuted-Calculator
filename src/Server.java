import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    public static int SERVER_PORT = 8080;
    private ServerSocket serverSocket;
    private byte[] userByte;
    private static final int USER_MAX_INPUT_SIZE = 10000;
    private Calculator calculator;

    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            userByte = new byte[USER_MAX_INPUT_SIZE];
            calculator = new Calculator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("server start listening on port : " + SERVER_PORT);
        Socket connection;
        while (true) {
            try {
                connection = serverSocket.accept();
                System.out.println("Client accepted");
                ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
                try {
                    Packet packet = (Packet) inputStream.readObject();
                    System.out.println(packet.getOperator().name());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
