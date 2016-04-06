package com.github.chanming.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * Description: 
 * Create Date:2016年4月6日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestDao
{
    public int insert(String name)
    {
        int rows = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = DBUtil.getConn();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("insert into test(name) values(?)");
            pstmt.setString(1, name);

            for (int i = 0; i < 3; i++)
            {
                rows = pstmt.executeUpdate();
            }

            conn.commit();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.closeAll(conn, pstmt);
        }

        return rows;
    }

    public Test selectByName(String name)
    {
        Test user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            conn = DBUtil.getConn();
            pstmt = conn.prepareStatement("select * from test where name=?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                user = new Test();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.closeAll(conn, pstmt, rs);
        }

        return user;
    }

    public int add(int a, int b)
    {
        int rows = -1;
        Connection conn = null;
        CallableStatement cstmt = null;

        try
        {
            conn = DBUtil.getConn();
            cstmt = conn.prepareCall("call addtest(?,?,?)");
            cstmt.setInt(1, a);
            cstmt.setInt(2, b);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.execute();
            rows = cstmt.getInt(3);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.closeAll(conn, cstmt);
        }

        return rows;
    }

    public int update()
    {
        int rows = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            conn = DBUtil.getConn();
            pstmt = conn.prepareStatement("select * from test",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
            rs.last();

            int rowCount = rs.getRow();
            for (int i = rowCount; i > 0; i--)
            {
                rs.absolute(i);
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
                rs.updateString(2, "www");
                rs.updateRow();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.closeAll(conn, pstmt, rs);
        }

        return rows;
    }

    public static void main(String[] args)
    {
        TestDao dao = new TestDao();
        System.out.println(dao.insert("root"));
        //
        // System.out.println(dao.selectByName("root").getId());
        // System.out.println(dao.add(6, 2));
        // System.out.println(dao.update());
    }

}
