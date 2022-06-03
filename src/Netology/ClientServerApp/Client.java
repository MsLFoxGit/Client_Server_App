package Netology.ClientServerApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String host = "netology.homework";
        try (Socket clientSocket = new Socket(host, 8080);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            out.println("Client");
            System.out.println(String.format("Client terminal message: %s ",in.readLine()));
            while(in.read() > -1) {
                String incomeMessage = in.readLine();
                System.out.print(String.format("Client terminal message: %s ",incomeMessage));
                out.println(scanner.nextLine());
                Thread.sleep(500);
            }
            in.close();
            out.close();
            clientSocket.close();
            scanner.close();
        } catch (IOException e) {
            e.getMessage();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
