package network;

import model.InputPacket;
import model.OperatorType;
import model.ResultPacket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private static final int MAX_INPUT_SIZE = 10000;

    public Client() {
        try {
            clientSocket = new Socket("127.0.0.1", Server.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPacketToServer(InputPacket inputPacket) {
        try {
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(inputPacket);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        System.out.println("Instructions");
        System.out.println("add [op1] [op2]");
        System.out.println("subtract [op1] [op2]");
        System.out.println("divide [op1] [op2]");
        System.out.println("multiply [op1] [op2]");
        System.out.println("sin [op1]");
        System.out.println("cos [op1]");
        System.out.println("tan [op1]");
        System.out.println("cot [op1]");
        System.out.println("----------------------------------");
        while (true) {
            System.out.print("[Client] -> ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine().toUpperCase();
            String[] userInputArray = userInput.split(" ");
            if (userInputArray.length == 3) {
                InputPacket packet = new InputPacket(OperatorType.valueOf(userInputArray[0]), Double.valueOf(userInputArray[1]), Double.valueOf(userInputArray[2]));
                sendPacketToServer(packet);
            }
            else if (userInputArray.length == 2) {
                InputPacket packet = new InputPacket(OperatorType.valueOf(userInputArray[0]), Double.valueOf(userInputArray[1]));
                sendPacketToServer(packet);
            }
            else {
                System.err.println("Input command not valid please enter command again: ");
                continue;
            }
            try {
                inputStream = new ObjectInputStream(clientSocket.getInputStream());
                ResultPacket resultPacket = (ResultPacket) inputStream.readObject();
                System.out.println(resultPacket);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
