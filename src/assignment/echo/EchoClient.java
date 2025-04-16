package assignment.echo;

import java.net.Socket;
import java.util.Scanner;
import java.io.PrintStream;

public class EchoClient
{
    public static void main(String[] args) throws Exception
    {
        String msgToServer, msgFromServer;
        Socket clisock = new Socket("127.0.0.1", 1342);

        //(Optional) To confirm Client is communicating through the port
        System.out.println("Client "+clisock.getInetAddress()+" is communicating with port No.: "+clisock.getPort());

        Scanner s1 = new Scanner(System.in);
        System.out.println("Greet the Server...\n");
        msgToServer=s1.nextLine();

        PrintStream p = new PrintStream(clisock.getOutputStream());
        p.println(msgToServer);

        Scanner s2 = new Scanner(clisock.getInputStream());
        msgFromServer=s2.nextLine();

        System.out.println(msgFromServer);

        clisock.close();
        s1.close();
        s2.close();
        p.close();
    }
}
