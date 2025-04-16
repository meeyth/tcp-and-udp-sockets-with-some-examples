package tcpSocket.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        System.out.println("Client started");

        try {
            Socket s = new Socket("localhost", 9806);

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            DataOutputStream outToServer = new DataOutputStream(s.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));

            System.out.println("Enter text: ");
            String sent = inFromUser.readLine();

            outToServer.writeBytes(sent + "\n");

            String modifiedSent = inFromServer.readLine();

            System.out.println("Modified string sent by server: " + modifiedSent);

            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
