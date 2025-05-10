package udpSocket.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        Scanner inFromUser = new Scanner(System.in);

        try {
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName("localhost");

            byte []sendData;
            byte []receiveData = new byte[1024];

            String sentence = inFromUser.nextLine();
            sendData = sentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence.substring(0, sendData.length));

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
