package org.minbox.learning.jws;

/**
 * Bit工具类
 *
 * @author 恒宇少年
 */
public class Bit {
    public static String bytesToBit(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(byteToBit(b));
        }
        return buffer.toString();
    }

    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }
}
