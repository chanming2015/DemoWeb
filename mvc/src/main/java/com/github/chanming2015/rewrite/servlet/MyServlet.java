package com.github.chanming2015.rewrite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet{

    /**
     * @author XuMaoSen
     */
    private static final long serialVersionUID = 7183077298090033040L;

    //重写doget方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(Thread.currentThread()+"start.....");

        try {
            buyCards();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        resp.getWriter().write("order sucesful....");
        System.out.println(Thread.currentThread()+" end..........");
    }

    public void buyCards() throws InterruptedException{
        System.out.println(Thread.currentThread()+".............");
        Thread.sleep(5000);//模拟业务操作
    }
}
