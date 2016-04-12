package com.github.chanming.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * Description: 
 * Create Date:2016年4月11日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class Test1
{
    public static void main(String[] args)
    {
        Reader reader = null;

        try
        {
            reader = new InputStreamReader(System.in, "UTF-8");

            BufferedReader bufferedReader = new BufferedReader(reader);
            char[] buffer = new char[64];

            int num = 0;
            try
            {
                while ((num = bufferedReader.read(buffer)) > 0)
                {
                    System.out.println(new String(buffer, 0, num));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
