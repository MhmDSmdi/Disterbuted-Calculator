import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            calculator = new Calculator();
            userByte = new byte[USER_MAX_INPUT_SIZE];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Server start on port : " + SERVER_PORT);
        Socket connection;
        while (true) {
            try {
                System.out.println("Listening ...");
                connection = serverSocket.accept();
                new SClient(connection).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SClient extends Thread {
        private Socket connection;

        public SClient(Socket socket) {
            this.connection = socket;
        }

        public Socket getSocket() {
            return connection;
        }

        @Override
        public void run() {
            System.out.println("Client accepted -> " + connection.toString());
            while (true) {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
                    try {
                        InputPacket inputPacket = (InputPacket) inputStream.readObject();
                        long time = System.currentTimeMillis();
                        double result = calculator.calculate(inputPacket);
                        time = System.currentTimeMillis() - time;
                        ResultPacket resultPacket = new ResultPacket(result, time);
                        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
                        outputStream.writeObject(resultPacket);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
