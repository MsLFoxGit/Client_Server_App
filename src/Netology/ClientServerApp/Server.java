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
            out.println("Write your name:");
            out.println("?");
            final String userName = in.readLine();
            ServerAnswers serverAnswer = new ServerAnswers(userName);
            String wrongAnswer = "";
            while (!clientSocket.isClosed()) {
                out.println(wrongAnswer + "Are you child? (yes/no)");
                out.println("?");
                final String clientAnswer = in.readLine();
                try {
                    out.println(serverAnswer.getServerAnswer(clientAnswer));
                    out.println(serverAnswer.getServerCommand(clientAnswer));
                    clientSocket.close();
                } catch (NullPointerException e) {
                    wrongAnswer = "Wrong answer! Try again! ";
                }
            }
            out.close();
            in.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}

