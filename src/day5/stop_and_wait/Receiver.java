package day5.stop_and_wait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5000);

        System.out.println("Server is running...");

        Socket s = ss.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        String msg;
        while((msg = in.readLine()) != null) {
            System.out.println("Received message: " + msg);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            out.println("ACK for " + msg);
        }
        s.close();
        ss.close();
    }
}
