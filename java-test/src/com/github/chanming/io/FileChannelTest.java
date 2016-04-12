package com.github.chanming.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Description: 
 * Create Date:2016年4月11日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class FileChannelTest
{

    public static void main(String[] args)
    {
        File fa = new File("D:/a.txt");
        File fb = new File("D:/b.txt");
        FileInputStream in = null;
        RandomAccessFile out = null;

        try
        {

            in = new FileInputStream(fa);
            out = new RandomAccessFile(fb, "rw");

            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();

            try
            {

                ByteBuffer buffer = ByteBuffer.allocate(256);

                while (inChannel.read(buffer) > 0)
                {
                    buffer.flip();
                    Charset charset = Charset.forName("UTF-8");
                    CharsetDecoder decoder = charset.newDecoder();
                    CharBuffer charBuffer = decoder.decode(buffer);
                    System.out.println(charBuffer);
                    buffer.clear();
                }

                // SortedMap<String, Charset> map = Charset.availableCharsets();
                // for (Entry<String, Charset> entry : map.entrySet())
                // {
                // System.out.println(entry.getKey() + "-->"
                // + entry.getValue());
                // }

                // MappedByteBuffer buffer =
                // inChannel.map(FileChannel.MapMode.READ_ONLY, 0, fa.length());
                // outChannel.position(fb.length());
                // outChannel.write(buffer);
                // buffer.clear();
                // Charset charset = Charset.forName("UTF-8");
                // CharsetDecoder decoder = charset.newDecoder();
                // CharBuffer charBuffer = decoder.decode(buffer);
                // System.out.println(charBuffer);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {

                try
                {
                    outChannel.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                try
                {
                    inChannel.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {

            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }
}
