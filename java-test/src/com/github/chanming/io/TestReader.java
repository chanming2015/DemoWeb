package com.github.chanming.io;

import java.io.File;

/**
 * Description: 
 * Create Date:2016年4月20日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestReader
{

    public static void main(String[] args)
    {
        System.out.println(TestIOStream.readString(new File("D:/test/aaa.txt")));

        System.out.println(TestIOChannel.readString(new File("D:/test/bbb.txt")));

        System.out.println(TestIOFiles.readString(new File("D:/test/ccc.txt")));
    }
}
