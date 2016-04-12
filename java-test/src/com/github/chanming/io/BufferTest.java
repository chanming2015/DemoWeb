package com.github.chanming.io;

import java.nio.CharBuffer;

/**
 * Description: 
 * Create Date:2016年4月11日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class BufferTest
{

    public static void main(String[] args)
    {
        // 创建容量为8个字符的字符缓冲区
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());

        // 放入元素
        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        buffer.put('d');
        System.out.println("position: " + buffer.position());

        buffer.flip();
        System.out.println("limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());

        buffer.get();
        buffer.get();
        System.out.println("position: " + buffer.position());

        buffer.clear();
        System.out.println("limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());

    }
}
