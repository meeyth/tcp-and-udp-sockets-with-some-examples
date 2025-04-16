package assignment.clientServer;

import java.net.Socket;
import java.util.Scanner;
import java.io.PrintStream;

public class Client
{
    public static void main(String args[]) throws Exception
    {
        int number, altered;
        Socket clisock = new Socket("127.0.0.1", 1342);
        //(Optional) To confirm Client is communicating through the port
        System.out.println("Client "+clisock.getInetAddress()+" is communicating with port No.: "+clisock.getPort());
        Scanner s1 = new Scanner(System.in);
        System.out.println("Enter a number to the client...\n");
        number=s1.nextInt();

        PrintStream p = new PrintStream(clisock.getOutputStream());
        p.println(number);

        Scanner s2 = new Scanner(clisock.getInputStream());
        altered=s2.nextInt();

        System.out.println("Number altered by the server is...\n");
        System.out.println(altered);

        clisock.close();
        s1.close();
        s2.close();
        p.close();
    }
}