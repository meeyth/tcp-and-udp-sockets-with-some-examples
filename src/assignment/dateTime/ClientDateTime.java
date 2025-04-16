package assignment.dateTime;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientDateTime
{
    public static void main(String[] args) throws UnknownHostException,IOException
    {
        Socket clisock= new Socket("127.0.0.1",1342);

        //(Optional) To confirm Client is communicating through the port
        System.out.println("Client "+clisock.getInetAddress()+" is communicating with port No.: "+clisock.getPort());

        Scanner s1 = new Scanner(clisock.getInputStream());

        System.out.println(s1.nextLine());
        s1.close();
        clisock.close();

    }
}
