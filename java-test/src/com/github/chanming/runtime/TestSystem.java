package com.github.chanming.runtime;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author XuMaoSen
 * Create Date:2016年3月9日 下午11:20:53
 * Description:
 * Version:1.0.0
 */
public class TestSystem
{
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException,
            InterruptedException
    {
        Scanner sc = new Scanner(new File("d://InstallConfig.ini"));
        while (sc.hasNextLine())
        {
            System.out.println(sc.nextLine());
        }

        Runtime runtime = Runtime.getRuntime();
        runtime.exec("notepad.exe");

    }
}
