package com.github.chanming.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Description: 
 * Create Date:2016年5月23日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class UdpServer implements Runnable
{

    private static final int PORT = 30000;
    private static final int DATA_LEN = 4096;
    private DatagramSocket socket = null;
    byte[] inBuff = new byte[DATA_LEN];
    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
    private DatagramPacket outPacket = null;

    private void init()
    {
        System.out.println("server init...");
        try
        {
            socket = new DatagramSocket(PORT);
            while (true)
            {
                socket.receive(inPacket);
                String message = new String(inBuff, 0, inPacket.getLength());
                byte[] sendData = "Hello".getBytes();
                outPacket = new DatagramPacket(sendData, sendData.length,
                        inPacket.getSocketAddress());
                socket.send(outPacket);
                System.out.println(message);
                if ("exit".equalsIgnoreCase(message))
                {
                    break;
                }
            }
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (socket != null)
            {
                socket.close();
            }
            System.out.println("server destory");
        }

    }

    @Override
    public void run()
    {
        init();
    }
}
