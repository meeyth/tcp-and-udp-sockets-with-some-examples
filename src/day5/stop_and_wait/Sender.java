package day5.stop_and_wait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender {
    public static void main(String[] args) throws IOException {
        Socket s  = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        String[] msgs = {"Packet 1", "Packet 2", "Packet 3", "Packet 4", "Packet 5"};


        for (String msg: msgs) {

            System.out.println("Sending " + msg);

            out.println(msg);

            System.out.println("Waiting for ACK");

            System.out.println(in.readLine() + " Received");

        }

        s.close();
    }
}
