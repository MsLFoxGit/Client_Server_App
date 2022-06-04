package Netology.ClientServerApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        new Thread(new Client()).start();
        server();
    }

    public static void server() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server terminal: New connection accepted");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            final String name = in.readLine();
            out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            out.println(" Write your name:");
            String userName = in.readLine();
            while (!clientSocket.isClosed()) {
                System.out.println(serverSocket.isClosed());
                out.println(" Are you child? (yes/no)");
                final String answer = in.readLine().trim().toLowerCase();
                switch (answer) {
                    case "yes":
                        System.out.println(String.format("Welcome to the kids area, %s! Let's play!", userName));
                        clientSocket.close();
                        break;
                    case "no":
                        System.out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", userName));
                        clientSocket.close();
                        break;
                    case "quit":
                        clientSocket.close();
                        break;
                    default:
                        System.out.println("Wrong answer! Try again");
                }
            }
            in.close();
            out.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
//                }
//                if (answer.equals("yes")) {
//                    System.out.println(String.format("Welcome to the kids area, %s! Let's play!", userName));
//                    break;
//                } else if (answer.equals("no")) {
//                    System.out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", userName));
//                    break;
//                } else if (answer.equals("quit")) {
//                    System.out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", userName));
//                    break;
//                } else {
//                    System.out.println("Wrong answer! Try again");
