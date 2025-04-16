package assignment.clientServer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintStream;

public class Server
{
    public static void main(String args[]) throws Exception
    {
        int number;
        ServerSocket sersock = new ServerSocket(1342);

        //(Optional)To confirm Server Reserved specified port or not
        System.out.println("The Server has reserved port No.: "+sersock.getLocalPort()+" for this Service");

        Socket clisock = sersock.accept();

        //To confirm Server communicated through the socket or not
        System.out.println("Client with IP Address "+clisock.getInetAddress()+" has communicated via port No.: "+clisock.getPort());

        Scanner s1 = new Scanner(clisock.getInputStream());
        number=s1.nextInt();
        number*=10;
        PrintStream p = new PrintStream(clisock.getOutputStream());
        p.println(number);

        sersock.close();
        clisock.close();
        s1.close();
    }
}