package com.github.chanming2015.rewrite.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Description:
 * Create Date:2019年4月11日
 * @author XuMaoSen
 * Version:1.0.0
 */
public interface WebApplicationInitializer
{
    void onStartup(ServletContext servletContext) throws ServletException;
}
