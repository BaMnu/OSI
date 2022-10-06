package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static String massage = "";
    private static final int PORT = 8989;

    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started");

            while (true) {

                try (Socket clientSocket = server.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                ) {

                    if (massage.equals("")) {
                        out.println("???");

                        massage = in.readLine();
                        System.out.println(massage);

                        out.println("OK");

                    } else {
                        out.println("Welcome. Previous city name: " + massage);

                        String nextCity = in.readLine();

                        char firstChar = nextCity.charAt(0);
                        char lastChar = massage.charAt(massage.length() - 1);

                        if (firstChar == lastChar) {
                            out.println("OK");
                            massage = nextCity;
                            System.out.println(nextCity);
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }

        } catch (IOException io) {
            System.out.println("Error. Can't start server.");
            io.printStackTrace();
        }
    }
}