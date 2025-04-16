package assignment.chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer
{
    public static void main(String[] args) throws Exception
    {
        String msgToClient, msgFromClient;

        ServerSocket sersock = new ServerSocket(1342);

        //(Optional)To confirm Server Reserved specified port or not
        System.out.println("The Server has reserved port No.: "+sersock.getLocalPort()+" for this Service");

        Socket clisock = sersock.accept();

        //To confirm Server communicated through the socket or not
        System.out.println("Client with IP Address "+clisock.getInetAddress()+" has communicated via port No.: "+clisock.getPort());


        System.out.println("Server  ready for chatting...\n");

        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(clisock.getInputStream());
        PrintStream p = new PrintStream(clisock.getOutputStream());

        while(true)
        {
            msgFromClient=s2.nextLine();	//receive from client
            if(msgFromClient!= null)
            {
                System.out.println(msgFromClient);  // displaying message from client
            }
            msgToClient = s1.nextLine(); 	// keyboard reading
            p.println(msgToClient);   	// sending to client
        }
    }
}