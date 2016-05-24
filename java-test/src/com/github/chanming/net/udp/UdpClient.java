package com.github.chanming.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Description: 
 * Create Date:2016年5月23日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class UdpClient implements Runnable
{

    private static final int DSET_PORT = 30000;
    private static final String DEST_IP = "127.0.0.1";
    private static final int DATA_LEN = 4096;
    private DatagramSocket socket = null;
    byte[] inBuff = new byte[DATA_LEN];
    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
    private DatagramPacket outPacket = null;

    private void init()
    {
        System.out.println("client init...");
        try
        {
            socket = new DatagramSocket();
            outPacket = new DatagramPacket(new byte[0], 0, InetAddress.getByName(DEST_IP),
                    DSET_PORT);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext())
            {
                byte[] buff = scanner.nextLine().getBytes();
                outPacket.setData(buff);
                socket.send(outPacket);
                socket.receive(inPacket);
                System.out.println(new String(inBuff, 0, inPacket.getLength()));
                if ("exit".equalsIgnoreCase(new String(buff)))
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
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        catch (UnknownHostException e)
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
            System.out.println("client destory");
        }

    }

    @Override
    public void run()
    {
        init();
    }
}
