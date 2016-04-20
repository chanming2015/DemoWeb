package com.github.chanming.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description: 
 * Create Date:2016年4月18日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class RtnThread implements Callable<Integer>
{

    @Override
    public Integer call() throws Exception
    {

        int i = 0;
        while (i < 15)
        {
            System.out.println(i);
            i++;
        }

        return i;
    }

    public static void main(String[] args)
    {
        RtnThread rt = new RtnThread();
        FutureTask<Integer> task = new FutureTask<Integer>(rt);

        for (int i = 0; i < 20; i++)
        {
            if (5 == i)
            {
                new Thread(task).start();
            }
        }

        try
        {
            System.out.println(task.get());
        }
        catch (InterruptedException e)
        {

            e.printStackTrace();
        }
        catch (ExecutionException e)
        {

            e.printStackTrace();
        }
    }
}
