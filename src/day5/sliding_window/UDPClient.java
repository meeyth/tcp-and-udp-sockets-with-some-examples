package day5.sliding_window;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName("localhost");

        int totalFrames = 10;
        int windowSize = 4;
        int base = 0; // oldest unacknowledged frame

        while (base < totalFrames) {
            int end = Math.min(base + windowSize, totalFrames);

            // Send frames within window
            for (int i = base; i < end; i++) {
                String frame = Integer.toString(i);
                byte[] sendData = frame.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9876);
                socket.send(sendPacket);
                System.out.println("Sent Frame: " + frame);
            }

            // Wait for ACKs
            for (int i = base; i < end; i++) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                socket.setSoTimeout(2000); // 2 seconds timeout
                try {
                    socket.receive(receivePacket);
                    String ack = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received: " + ack);
                    base++; // Move base forward
                } catch (Exception e) {
                    System.out.println("Timeout! Resending from frame " + base);
                    break; // break to resend window starting from base
                }
            }
        }

        socket.close();
        System.out.println("All frames sent successfully!");
    }
}
