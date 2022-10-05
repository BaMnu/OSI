package ru.netology;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static final int PORT = 9090;
    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(PORT); ) {
            System.out.println("Server started");

            while (true) {
                try (Socket clientSocket = server.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
                {
                    System.out.println("Client connection accepted");

                    final String name = in.readLine();
                    out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                }
            }
        } catch (IOException io) {
            throw new IOException(io);
        }
    }

    public static ServerSocket create(int[] ports) throws IOException {
        for (int port : ports) {
            try {
                return new ServerSocket(port);
            } catch (IOException ex) {
                continue;
            }
        }
        throw new IOException("No free port found");
    }

}