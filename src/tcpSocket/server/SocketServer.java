package tcpSocket.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Locale;

public class SocketServer {
    public static void main(String[] args) {
        System.out.println("waiting for client");
        try {
            ServerSocket welcomeSocket = new ServerSocket(9806);


            while (true) {

                Socket conSocket = welcomeSocket.accept();

                System.out.println("Client connected");

                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(conSocket.getInputStream()));

                String modifiedSent = inFromClient.readLine().toUpperCase(Locale.ROOT) + "\n";

                DataOutputStream outToServer = new DataOutputStream(conSocket.getOutputStream());

                outToServer.writeBytes(modifiedSent);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
