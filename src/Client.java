import java.io.*;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private byte[] input;
    private static final int MAX_INPUT_SIZE = 10000;

    public Client() {
        try {
            clientSocket = new Socket("localhost", Server.SERVER_PORT);
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new byte[MAX_INPUT_SIZE];
            sendPacketToServer(new Packet(OperatorType.Add, 2, 2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPacketToServer(Packet packet) {
        try {
            System.out.println("asdsads");
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
