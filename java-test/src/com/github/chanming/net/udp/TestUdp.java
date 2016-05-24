package com.github.chanming.net.udp;

/**
 * Description: 
 * Create Date:2016年5月23日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestUdp
{

    public static void main(String[] args)
    {
        UdpServer server = new UdpServer();
        UdpClient client = new UdpClient();

        Thread serverThread = new Thread(server);
        Thread clientThread = new Thread(client);

        serverThread.start();
        clientThread.start();

    }
}
