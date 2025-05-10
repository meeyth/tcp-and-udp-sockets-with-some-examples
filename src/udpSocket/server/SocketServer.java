package udpSocket.server;


import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class SocketServer {
    public static void main(String[] args)  {

        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(9876);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        byte[] receiveData = new byte[1024];
        byte[] sendData;

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }


            String sentence = new String(receivePacket.getData());
//            System.out.println(Arrays.toString(receivePacket.getData()));

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();

            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, receiveData.length, IPAddress, port);
            try {
                serverSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
