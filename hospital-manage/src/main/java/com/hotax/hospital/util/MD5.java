package com.hotax.hospital.util;

import org.apache.commons.codec.BinaryDecoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public final class MD5 {

    public static String encrypt(String strSrc) {
        try {
            char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            byte[] bytes = strSrc.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！！+" + e);
        }
    }

    public static void main(String[] args) {
        int a = 15;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.valueOf("111100", 2));
        int b = 10;
        int c = b >>> 4;
        System.out.println("--------------------------");
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));
        System.out.println("--------------------------");
        System.out.println("b 二进制：" + Integer.toBinaryString(b));
        int d = b & 0xf;
        System.out.println("0xf 二进制：" + Integer.toBinaryString(0xf));
        System.out.println("d 二进制：" + Integer.toBinaryString(d));
        System.out.println(d);
        System.out.println("--------------------------");
        System.out.println(c & 0xf);

        System.out.println("14 = " + Integer.toBinaryString(14));

        //encrypt("123456");
//        int num = 2 << 16;
//        int num2 = num >> 16;
//        System.out.println(num);
//        System.out.println(num2);
//
//        System.out.println("----------------");
//
//        System.out.println(11 >> 2);
//
//        System.out.println("-------->>>--------");
//        System.out.println(60 >>> 1);
//        System.out.println("----------------");
//        int a = 3;//定义一个变量；
//        boolean b = (a<4)&&(a++<10);
//        System.out.println("使用短路逻辑运算符的结果为"+b);
//        System.out.println("a的结果为"+a);
    }
}
