package com.github.chanming.net.tcp;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Description: 
 * Create Date:2016年5月24日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class ClientThread implements Runnable
{

    private final Socket socket;

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        try
        {
            in = socket.getInputStream();
            reader = new InputStreamReader(in);
            br = new BufferedReader(reader);

            String content = null;
            while ((content = br.readLine()) != null)
            {
                System.out.println(content);
                if ("exit".equalsIgnoreCase(content))
                {
                    break;
                }
            }
        }
        catch (IOException e)
        {
            MyServer.getSockets().remove(socket);
            e.printStackTrace();
        }
        finally
        {
            close(br, reader, in);
        }
    }

    private void close(Closeable... closeables)
    {
        for (Closeable c : closeables)
        {
            if (c != null)
            {
                try
                {
                    c.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
