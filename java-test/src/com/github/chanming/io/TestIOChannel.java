package com.github.chanming.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Description: 
 * Create Date:2016年4月20日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestIOChannel
{
    public static boolean writeString(File file, String content, boolean append)
    {
        RandomAccessFile accessFile = null;
        try
        {
            accessFile = new RandomAccessFile(file, "rw");
            FileChannel outChannel = accessFile.getChannel();
            try
            {
                byte[] bs = content.getBytes(Charset.forName("UTF-8"));
                ByteBuffer buffer = ByteBuffer.allocate(bs.length);
                buffer.put(bs);
                buffer.flip();

                try
                {
                    if (append)
                    {
                        outChannel.position(file.length());
                    }
                    outChannel.write(buffer);
                    return true;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            finally
            {
                close(outChannel);
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(accessFile);
        }

        return false;
    }

    public static String readString(File file)
    {
        RandomAccessFile accessFile = null;
        try
        {
            accessFile = new RandomAccessFile(file, "r");
            FileChannel inChannel = accessFile.getChannel();
            try
            {
                ByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
                CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
                return decoder.decode(buffer).toString();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                close(inChannel);
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(accessFile);
        }
        return "";
    }

    /**
     * Description: 关闭流
     * Create Date:2016年4月20日
     * @author XuMaoSen
     */
    private static void close(Closeable closeable)
    {
        if (closeable != null)
        {
            try
            {
                closeable.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
