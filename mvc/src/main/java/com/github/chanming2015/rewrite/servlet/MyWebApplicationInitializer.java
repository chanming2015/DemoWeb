package com.github.chanming2015.rewrite.servlet;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import com.github.chanming2015.rewrite.annotation.MyAutowrited;
import com.github.chanming2015.rewrite.annotation.MyController;
import com.github.chanming2015.rewrite.annotation.MyRequestMapping;
import com.github.chanming2015.rewrite.annotation.MyService;

/**
 * Description:
 * Create Date:2019年4月11日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer
{
    private final Map<String, Object> beans = new HashMap<String, Object>();
    private final Map<String, Object> handlerMap = new HashMap<String, Object>();
    /**
     * @author XuMaoSen
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        scanPackage("com.github.chanming2015.rewrite");
        di();
        registerDispatcherServlet(servletContext);
    }

    /**
     * Description: Create Date:2019年4月11日
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
                    else if (clazz.isAnnotationPresent(MyService.class))
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
     * Description: Create Date:2019年4月11日
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
                for (Field field : clazz.getFields())
                {
                    if (field.isAnnotationPresent(MyAutowrited.class))
                    {
                        MyAutowrited maw = field.getAnnotation(MyAutowrited.class);
                        String beanName = getBeanName(maw.value(), field.getClass());
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
    private void registerDispatcherServlet(ServletContext servletContext)
    {
        String servletName = "default-dispatcher";
        ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, new MyServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping(getServletMappings());
    }

    /**
     * Description: Create Date:2019年4月11日
     *
     * @author XuMaoSen
     */
    private String[] getServletMappings()
    {
        if (beans.entrySet().size() <= 0)
        {
            System.out.println("没有类的实例化！");
            return new String[0];
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
                    basePath = requestMapping.path();
                }
                for (Method method : clazz.getMethods())
                {
                    if (method.isAnnotationPresent(MyRequestMapping.class))
                    {
                        MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                        String methodPath = requestMapping.path();
                        handlerMap.put(basePath + methodPath, method);
                    }
                }
            }
        }
        return handlerMap.keySet().toArray(new String[0]);
    }
}
