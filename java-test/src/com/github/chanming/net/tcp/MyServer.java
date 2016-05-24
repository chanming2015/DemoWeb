package com.github.chanming.net.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 
 * Create Date:2016年5月24日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class MyServer
{

    private static final List<Socket> SOCKETS = new ArrayList<Socket>();

    public static List<Socket> getSockets()
    {
        return SOCKETS;
    }

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = null;
        try
        {
            serverSocket = new ServerSocket(30000);
            while (true)
            {
                Socket s = serverSocket.accept();
                SOCKETS.add(s);
                new Thread(new ServerThread(s)).start();
            }
        }
        finally
        {
            if (serverSocket != null)
            {
                try
                {
                    serverSocket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
