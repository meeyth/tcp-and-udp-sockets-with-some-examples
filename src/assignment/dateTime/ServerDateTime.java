package assignment.dateTime;

import java.net.*;
import java.io.*;
import java.util.Date;

public class ServerDateTime
{
    public static void main(String[] args)throws IOException
    {
        ServerSocket sersock = new ServerSocket(1342);

        //(Optional)To confirm Server Reserved specified port or not
        System.out.println("The Server has reserved port No.: "+sersock.getLocalPort()+" for this Service");

        Socket clisock = sersock.accept();

        //To confirm Server communicated through the socket or not
        System.out.println("Client with IP Address "+clisock.getInetAddress()+" has communicated via port No.: "+clisock.getPort());

        Date d = new Date();
        String s="Current Date & Time on Server is: "+d;

        //Send String s to client via client socket
        PrintStream toclient = new PrintStream (clisock.getOutputStream());
        toclient.println(s);

        clisock.close();
        sersock.close();
        toclient.close();
    }
}
