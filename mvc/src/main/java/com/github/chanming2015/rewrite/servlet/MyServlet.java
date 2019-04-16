package com.github.chanming2015.rewrite.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.chanming2015.rewrite.annotation.MyAutowrited;
import com.github.chanming2015.rewrite.annotation.MyController;
import com.github.chanming2015.rewrite.annotation.MyRequestMapping;
import com.github.chanming2015.rewrite.annotation.MyRequestParam;
import com.github.chanming2015.rewrite.annotation.MyService;

public class MyServlet extends HttpServlet
{

    /**
     * @author XuMaoSen
     */
    private static final long serialVersionUID = 7183077298090033040L;

    private final Map<String, Object> beans = new HashMap<String, Object>();
    private final Map<String, Object> handlerMap = new HashMap<String, Object>();
    private final Map<Method, List<String>> requestParamMap = new HashMap<Method, List<String>>();

    /**
     * Description:
     * Create Date:2019年4月16日
     * @author XuMaoSen
     */
    @Override
    public void init() throws ServletException
    {
        scanPackage("com.github.chanming2015.rewrite");
        di();
        initServletMappings();
    }

    // 重写doget方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println(Thread.currentThread() + "start.....");
        Optional<String> optional = handlerMap.keySet().stream().filter(str -> req.getRequestURL().toString().endsWith(str)).findFirst();
        if (optional.isPresent())
        {
            Method method = (Method) handlerMap.get(optional.get());
            Object[] args = null;
            List<String> requestParams = requestParamMap.get(method);
            if (requestParams != null)
            {
                args = requestParams.stream().map(param -> req.getParameter(param)).collect(Collectors.toList()).toArray();
            }
            String result = "";
            try
            {
                result = method.invoke(beans.get(getBeanName(null, method.getDeclaringClass())), args).toString();
            }
            catch (IllegalAccessException e)
            {

                e.printStackTrace();
            }
            catch (IllegalArgumentException e)
            {

                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {

                e.printStackTrace();
            }
            resp.getWriter().write(result);
        }
        System.out.println(Thread.currentThread() + " end..........");
    }

    /**
     * Description: 扫描包并实例化对象
     * Create Date:2019年4月11日
     *
     * @author XuMaoSen
     */
    private void scanPackage(String basePackage)
    {
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.", "/"));
        String fileStr = url.getFile();
        File file = new File(fileStr);
        for (String path : file.list())
        {
            File filePath = new File(fileStr + path);
            if (filePath.isDirectory())
            {
                scanPackage(basePackage + "." + path);
            }
            else
            {
                // 如果是class文件则加入List集合(待生成bean)
                String className = basePackage + "." + filePath.getName();
                String cn = className.replace(".class", "");
                try
                {
                    Class<?> clazz = Class.forName(cn);
                    if (clazz.isAnnotationPresent(MyController.class))
                    {
                        MyController controller = clazz.getAnnotation(MyController.class);
                        String beanName = getBeanName(controller.value(), clazz);
                        Object instance = clazz.newInstance();
                        beans.put(beanName, instance);
                    }

                    if (clazz.isAnnotationPresent(MyService.class))
                    {
                        MyService service = clazz.getAnnotation(MyService.class);
                        String beanName = getBeanName(service.value(), clazz);
                        Object instance = clazz.newInstance();
                        beans.put(beanName, instance);
                    }
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (InstantiationException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Description: Create Date:2019年4月11日
     *
     * @author XuMaoSen
     */
    private String getBeanName(String value, Class<?> clazz)
    {
        return (value != null) && (value.length() > 0) ? value : clazz.getSimpleName();
    }

    /**
     * Description: 依赖注入对象
     * Create Date:2019年4月11日
     *
     * @author XuMaoSen
     */
    private void di()
    {
        if (beans.entrySet().size() <= 0)
        {
            System.out.println("没有类的实例化！");
            return;
        }
        for (Object instance : beans.values())
        {
            Class<?> clazz = instance.getClass();
            if (clazz.isAnnotationPresent(MyController.class))
            {
                for (Field field : clazz.getDeclaredFields())
                {
                    if (field.isAnnotationPresent(MyAutowrited.class))
                    {
                        MyAutowrited maw = field.getAnnotation(MyAutowrited.class);
                        String beanName = getBeanName(maw.value(), field.getType());
                        field.setAccessible(true);
                        try
                        {
                            field.set(instance, beans.get(beanName));
                        }
                        catch (IllegalArgumentException e)
                        {
                            e.printStackTrace();
                        }
                        catch (IllegalAccessException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * Description: Create Date:2019年4月11日
     *
     * @author XuMaoSen
     */
    private void initServletMappings()
    {
        if (beans.entrySet().size() <= 0)
        {
            System.out.println("没有类的实例化！");
            return;
        }
        for (Object instance : beans.values())
        {
            Class<?> clazz = instance.getClass();
            if (clazz.isAnnotationPresent(MyController.class))
            {
                String basePath = "";
                if (clazz.isAnnotationPresent(MyRequestMapping.class))
                {
                    MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                    basePath = requestMapping.value();
                }
                for (Method method : clazz.getMethods())
                {
                    if (method.isAnnotationPresent(MyRequestMapping.class))
                    {
                        MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                        String methodPath = requestMapping.value();
                        handlerMap.put(basePath + methodPath, method);

                        List<String> parameters = Arrays.asList(method.getParameters()).stream().filter(parameter -> parameter.isAnnotationPresent(MyRequestParam.class)).map(parameter -> parameter.getAnnotation(MyRequestParam.class).value()).collect(Collectors.toList());
                        if (parameters.size() > 0)
                        {
                            requestParamMap.put(method, parameters);
                        }
                    }
                }
            }
        }
    }
}
