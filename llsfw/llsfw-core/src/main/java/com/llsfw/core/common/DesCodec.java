/**
 * DesCodec.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.llsfw.core.exception.SystemException;

/**
 * <p>
 * ClassName: DesCodec
 * </p>
 * <p>
 * Description: DES加密解密
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年1月4日
 * </p>
 */
public class DesCodec {

    /**
     * <p>
     * Field keyAlgorithm: 算法名称
     * </p>
     */
    private String keyAlgorithm = "DES";

    /**
     * <p>
     * Field cipherAlgorithm: 算法名称/加密模式/填充方式 <br />
     * DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
     * </p>
     */
    private String cipherAlgorithm = "DES/ECB/PKCS5Padding";

    /**
     * <p>
     * Field keySize: 密码长度
     * </p>
     */
    private String keySize = "56";

    /**
     * <p>
     * Field byteSize: 字节数组长度
     * </p>
     */
    private String byteSize = "1024";

    /**
     * <p>
     * Field charSet: 字符集编码
     * </p>
     */
    private String charSet = "UTF-8";

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     */
    public DesCodec() {
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param charSet 字符集编码
     */
    public DesCodec(String charSet) {
        this.charSet = charSet;
    }

    /**
     * <p>
     * Description: 生成密钥
     * </p>
     * 
     * @return 密钥
     * @throws NoSuchAlgorithmException 异常
     */
    public String initkey() throws NoSuchAlgorithmException {
        // 实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(this.keyAlgorithm);

        // 初始化密钥生成器
        kg.init(Integer.parseInt(this.keySize));

        // 生成密钥
        SecretKey secretKey = kg.generateKey();

        // 获取二进制密钥编码形式
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

    /**
     * <p>
     * Description: 加密数据
     * </p>
     * 
     * @param data 待加密数据
     * @param key 密钥
     * @return 加密后的数据
     * @throws SystemException 异常
     */
    public String encrypt(String data, String key) throws SystemException {
        try {
            Key k = this.toKey(Base64.decodeBase64(key)); // 还原密钥
            Cipher cipher = Cipher.getInstance(this.cipherAlgorithm); // 实例化Cipher对象，它用于完成实际的加密操作
            cipher.init(Cipher.ENCRYPT_MODE, k); // 初始化Cipher对象，设置为加密模式
            // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
            return Base64.encodeBase64String(cipher.doFinal(data.getBytes(this.charSet)));
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException(e);
        } catch (NoSuchPaddingException e) {
            throw new SystemException(e);
        } catch (InvalidKeyException e) {
            throw new SystemException(e);
        } catch (IllegalBlockSizeException e) {
            throw new SystemException(e);
        } catch (BadPaddingException e) {
            throw new SystemException(e);
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e);
        }
    }

    /**
     * <p>
     * Description: 解密数据
     * </p>
     * 
     * @param data 待解密数据
     * @param key 密钥
     * @return 解密后的数据
     * @throws SystemException 异常
     */
    public String decrypt(String data, String key) throws SystemException {
        try {
            Key k = this.toKey(Base64.decodeBase64(key)); // 还原密钥
            Cipher cipher = Cipher.getInstance(this.cipherAlgorithm); // 实例化Cipher对象，它用于完成实际的加密操作
            cipher.init(Cipher.DECRYPT_MODE, k); // 初始化Cipher对象，设置为解密模式
            return new String(cipher.doFinal(Base64.decodeBase64(data)), this.charSet); // 执行解密操作
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException(e);
        } catch (NoSuchPaddingException e) {
            throw new SystemException(e);
        } catch (InvalidKeyException e) {
            throw new SystemException(e);
        } catch (IllegalBlockSizeException e) {
            throw new SystemException(e);
        } catch (BadPaddingException e) {
            throw new SystemException(e);
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e);
        }
    }

    /**
     * <p>
     * Description: 文件加密
     * </p>
     * 
     * @param file 需加密的文件
     * @param destFile 加密后存放的文件
     * @param key 密码
     * @throws SystemException 异常
     */
    public void encryptFile(String file, String destFile, String key) throws SystemException {
        BufferedInputStream is = null;
        BufferedOutputStream out = null;
        BufferedInputStream cis = null;
        try {
            // 还原密钥
            Key k = null;
            k = this.toKey(Base64.decodeBase64(key));

            // 实例化Cipher对象，它用于完成实际的加密操作
            Cipher cipher = null;
            cipher = Cipher.getInstance(this.cipherAlgorithm);

            // 初始化Cipher对象，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, k);

            // 创建输入输出流
            is = new BufferedInputStream(new FileInputStream(file), Constants.IO_BUFFERED);
            out = new BufferedOutputStream(new FileOutputStream(destFile), Constants.IO_BUFFERED);
            cis = new BufferedInputStream(new CipherInputStream(is, cipher), Constants.IO_BUFFERED);

            // 开始写文件
            byte[] buffer = new byte[Integer.parseInt(this.byteSize)];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
        } catch (IOException e) {
            throw new SystemException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException(e);
        } catch (NoSuchPaddingException e) {
            throw new SystemException(e);
        } catch (InvalidKeyException e) {
            throw new SystemException(e);
        } finally {
            try {
                if (cis != null) {
                    cis.close();
                }
                if (is != null) {
                    is.close();
                }
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                throw new SystemException(e);
            }
        }
    }

    /**
     * <p>
     * Description: 解密文件
     * </p>
     * 
     * @param file 待解密的文件
     * @param destFile 解密后存放的文件
     * @param key 密码
     * @throws SystemException 异常
     */
    public void decryptFile(String file, String destFile, String key) throws SystemException {
        BufferedInputStream is = null;
        BufferedInputStream cis = null;
        BufferedOutputStream out = null;
        try {
            // 还原密钥
            Key k = null;
            k = this.toKey(Base64.decodeBase64(key));

            // 实例化Cipher对象，它用于完成实际的加密操作
            Cipher cipher = null;
            cipher = Cipher.getInstance(this.cipherAlgorithm);

            // 初始化Cipher对象，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, k);

            // 创建输入输出流
            is = new BufferedInputStream(new FileInputStream(file), Constants.IO_BUFFERED);
            out = new BufferedOutputStream(new FileOutputStream(destFile), Constants.IO_BUFFERED);
            cis = new BufferedInputStream(new CipherInputStream(is, cipher), Constants.IO_BUFFERED);

            // 开始写文件
            byte[] buffer = new byte[Integer.parseInt(this.byteSize)];

            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
        } catch (IOException e) {
            throw new SystemException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException(e);
        } catch (NoSuchPaddingException e) {
            throw new SystemException(e);
        } catch (InvalidKeyException e) {
            throw new SystemException(e);
        } finally {
            try {
                if (cis != null) {
                    cis.close();
                }
                if (is != null) {
                    is.close();
                }
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                throw new SystemException(e);
            }
        }
    }

    /**
     * 
     */
    /**
     * <p>
     * Description: 转换密钥
     * </p>
     * 
     * @param key 密码
     * @return 秘钥
     * @throws SystemException 异常
     */
    private Key toKey(byte[] key) throws SystemException {
        try {
            DESKeySpec dks = new DESKeySpec(key); // 实例化Des密钥
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(this.keyAlgorithm); // 实例化密钥工厂
            return keyFactory.generateSecret(dks); // 生成密钥
        } catch (InvalidKeyException e) {
            throw new SystemException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException(e);
        } catch (InvalidKeySpecException e) {
            throw new SystemException(e);
        }
    }

}
