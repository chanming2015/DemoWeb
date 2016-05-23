package com.github.chanming.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description: 
 * Create Date:2016年4月21日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestIP
{

    public static void main(String[] args)
    {
        try
        {
            InetAddress ip = InetAddress.getByName("www.baidu.com");
            System.out.println(ip.isReachable(2000));
            System.out.println(ip.getHostAddress());

            InetAddress local = InetAddress.getByAddress(new byte[] {127, 0, 0, 1});
            System.out.println(local.isReachable(2000));
            System.out.println(local.getCanonicalHostName());
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
