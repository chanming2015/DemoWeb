package com.github.chanming.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Description: 自定义类加载器
 * Create Date:2016年5月24日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class ComplieClassLoader extends ClassLoader
{

    private byte[] getBytes(String fileName) throws IOException
    {
        File file = new File(fileName);
        return Files.readAllBytes(Paths.get(file.toURI()));
    }

    private boolean complie(String javaFile) throws IOException
    {
        System.out.println("ComplieClassLoader: 正在编译 " + javaFile + " ...");
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        try
        {
            p.waitFor();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        int status = p.exitValue();

        return status == 0;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        Class<?> clazz = null;
        String fileStub = name.replaceAll(".", "/");
        String javaFileName = fileStub + ".java";
        String classFileName = fileStub + ".class";
        File javaFile = new File(javaFileName);
        File classFile = new File(classFileName);
        if (javaFile.exists() && (!classFile.exists())
                || javaFile.lastModified() > classFile.lastModified())
        {
            try
            {
                if (!complie(javaFileName) || !classFile.exists())
                {
                    throw new ClassNotFoundException("ClassNotFoundException: " + javaFileName);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (classFile.exists())
        {
            try
            {
                byte[] raw = getBytes(classFileName);
                clazz = defineClass(name, raw, 0, raw.length);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (clazz == null)
        {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length < 1)
        {
            System.out.println("缺失目标类");
        }

        String progClass = args[0];
        String[] progArgs = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);
        ComplieClassLoader classLoader = new ComplieClassLoader();
        Class<?> clazz = classLoader.loadClass(progClass);
        Method main = clazz.getMethod("main", (new String[0]).getClass());

        Object[] argsArray = progArgs;
        main.invoke(null, argsArray);
    }
}
