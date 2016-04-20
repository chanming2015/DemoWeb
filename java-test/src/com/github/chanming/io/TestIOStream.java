package com.github.chanming.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Description: 
 * Create Date:2016年4月20日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestIOStream
{

    public static boolean writeString(File file, String content, boolean append)
    {

        FileOutputStream fos = null;

        try
        {
            fos = new FileOutputStream(file, append);
            OutputStreamWriter osw = null;
            try
            {
                osw = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
                BufferedWriter bw = null;
                try
                {
                    bw = new BufferedWriter(osw, content.length());
                    bw.write(content);
                    return true;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    close(bw);
                }
            }
            finally
            {
                close(osw);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(fos);
        }
        return false;
    }

    public static String readString(File file)
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            try
            {
                BufferedReader br = null;
                try
                {
                    br = new BufferedReader(isr);
                    CharBuffer buffer = CharBuffer.allocate((int) file.length());
                    br.read(buffer);
                    buffer.flip();
                    return buffer.toString();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    close(br);
                }
            }
            finally
            {
                close(isr);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(fis);
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
