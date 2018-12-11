import java.io.*;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private byte[] input;
    private static final int MAX_INPUT_SIZE = 10000;

    public Client() {
        try {
            clientSocket = new Socket("127.0.0.1", Server.SERVER_PORT);
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new byte[MAX_INPUT_SIZE];
            outputStream.flush();
            sendPacketToServer(new Packet(OperatorType.Add, 2, 2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPacketToServer(Packet packet) {
        try {
            outputStream.writeObject(packet);
            outputStream.flush();
            System.out.println("Packet Send to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
