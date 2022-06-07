package Netology.ClientServerApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    public void run() {
        String host = "localhost";
        try (
                Socket clientSocket = new Socket(host, 8080);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Scanner scanner = new Scanner(System.in);
        ) {
            out.println("Client");
            System.out.println(String.format("Client terminal message: %s ", in.readLine()));
            while (in.ready()) {
                final String incomeMessage = in.readLine();
                final String incomeCommand = in.readLine();
                System.out.print(String.format("Client terminal message: %s ", incomeMessage));
                implementServerCommand(incomeCommand, out, scanner);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void implementServerCommand( String incomeCommand, PrintWriter out, Scanner scanner){
        switch (incomeCommand) {
            case "?":
                out.println(scanner.nextLine());
                break;
            case "q":
                break;
        }

    }
}