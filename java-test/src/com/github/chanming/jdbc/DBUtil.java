package com.github.chanming.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Description: 数据库操作工具类
 * Create Date:2016年4月6日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class DBUtil
{
    /**
     * 数据库驱动类
     */
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    /**
     * 连接字符串
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?noAccessToProcedureBodies=true";
    /**
     * 用户名
     */
    private static final String DB_USER = "test";
    /**
     * 密码
     */
    private static final String DB_PWD = "admin";

    /**
     * 得到一个数据库连接的方法
     */
    public static Connection getConn()
    {
        Connection conn = null;

        try
        {
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 关闭各个数据库访问对象的方法——用于仅仅做增、删、改的情况
     */
    public static void closeAll(Connection conn, Statement pstmt)
    {
        // 后开先关原则
        if (pstmt != null)
        {
            try
            {
                pstmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭各个数据库访问对象的方法——用于做查询的情况
     */
    public static void closeAll(Connection conn, Statement pstmt, ResultSet rs)
    {
        // 后开先关原则
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        closeAll(conn, pstmt);
    }
}
