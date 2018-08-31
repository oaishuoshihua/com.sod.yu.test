package com.sodyu.test.serialize;

/**
 * ClassName：
 * @Date 2017/1/6 10:00
 * @since JRE 1.6.0_22  or higher
 */
public class Serialize {
    public static byte[] GetBytesFromInt32(int value) {
        byte[] buffer = new byte[4];
        for (int i = 0; i < 4; i++) {
            buffer[i] = (byte) (value >> (8 * (3-i))&0xFF);
        }
        return buffer;
    }

    public static byte[] writeInt(int value) {
        byte[] buffer = new byte[4];
        buffer[0] = (byte) value;
        buffer[1] = (byte) (value >> 8);
        buffer[2] = (byte) (value >> 16);
        buffer[3] = (byte) (value >> 24);
        return buffer;
    }

    public static byte[] getFixedInt32(int value)
    {
        byte[] buffer = new byte[4];
        for (int i = 0; i < 4; i++) {
            buffer[i] = ((byte)(value >> 8 * i));
        }
        return buffer;
    }

    public static void main(String[] args) {
//        byte[] bytes=GetBytesFromInt32(1000);
        byte[] bytes1=getFixedInt32(10);
//        System.out.println(bytes);
//        System.out.println((byte)1000);
        System.out.println(bytes1);
    }
}
