/**
 * ShaOne.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.llsfw.core.common.Constants;

/**
 * <p>
 * Title: SHA1算法
 * </p>
 *
 */
public final class ShaOne {

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * Constants.NUMBER_2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> Constants.NUMBER_4) & Constants.NUMBER_0X0F]);
            buf.append(HEX_DIGITS[bytes[j] & Constants.NUMBER_0X0F]);
        }
        return buf.toString();
    }

    /**
     * <p>
     * Description: 编码
     * </p>
     * 
     * @param str 字符串
     * @return 编码后的字符串
     */
    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes(Constants.DEF_CHARACTER_SET_ENCODING));
            return getFormattedText(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
