package assignment.chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient
{
    public static void main(String[] args) throws Exception
    {
        String msgToServer, msgFromServer;

        Socket clisock = new Socket("127.0.0.1", 1342);

        //(Optional) To confirm Client is communicating through the port
        System.out.println("Client "+clisock.getInetAddress()+" is communicating with port No.: "+clisock.getPort());

        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(clisock.getInputStream());
        PrintStream p = new PrintStream(clisock.getOutputStream());

        System.out.println("Start the chitchat. Type you message and press Enter\n");

        while(true)
        {
            msgToServer = s1.nextLine();  	// keyboard reading
            p.println(msgToServer);    	// sending to server
            msgFromServer = s2.nextLine();  //receive from server
            if(msgFromServer!= null)
                System.out.println(msgFromServer); // displaying message from server
        }
    }
}