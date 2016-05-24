package com.github.chanming.net.tcp;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Description: 
 * Create Date:2016年5月24日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class MyClient
{

    public static void main(String[] args) throws Exception
    {
        Socket s = new Socket("127.0.0.1", 30000);
        Thread t = new Thread(new ClientThread(s));
        t.start();

        PrintStream out = new PrintStream(s.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext())
        {
            String content = scanner.nextLine();
            out.println(content);
            if ("exit".equalsIgnoreCase(content))
            {
                break;
            }
        }

        try
        {
        }
        finally
        {
            scanner.close();
        }
        t.stop();

    }
}
