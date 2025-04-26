package day5.sliding_window;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9876);

        byte[] receiveData = new byte[1024];
        byte[] sendData;

        System.out.println("Server is running...");

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String receivedFrame = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received Frame: " + receivedFrame);

            // Send ACK back
            String ack = "ACK" + receivedFrame;
            sendData = ack.getBytes();
            Thread.sleep(1000);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                    receivePacket.getAddress(), receivePacket.getPort());

            socket.send(sendPacket);
        }
    }
}

