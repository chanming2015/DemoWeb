package com.github.chanming.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Description: 
 * Create Date:2016年4月20日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestIOFiles
{

    public static boolean writeString(File file, String content, boolean append)
    {
        Path path = file.toPath();
        byte[] bs = content.getBytes(Charset.forName("UTF-8"));

        try
        {
            OpenOption option = null;

            if (append && file.exists())
            {
                option = StandardOpenOption.APPEND;
            }
            else
            {
                option = StandardOpenOption.CREATE;
            }
            Files.write(path, bs, option);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static String readString(File file)
    {
        Path path = file.toPath();
        try
        {
            // byte[] bs = Files.readAllBytes(path);
            // ByteBuffer buffer = ByteBuffer.allocate(bs.length);
            // buffer.put(bs);
            // buffer.flip();
            // CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
            // return decoder.decode(buffer).toString();

            List<String> list = Files.readAllLines(path, Charset.forName("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (String str : list)
            {
                sb.append(str);
            }
            return sb.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
