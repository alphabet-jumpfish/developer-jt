package com.jumpfish.developer.porjects.monitors.utils.des;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.ObjectUtils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @Description: 加解密工具类
 * @Author wangjl
 * @Date: 2020/7/24 15:00
 */
@Slf4j
public class DesCryptUtils {

    private static String KEY = "4d386d78-e565-43ff-a04f-7caedbdd86f0";

    public static byte[] doFinal(String key, Object datasource) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        DESKeySpec desKey = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
        //创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        //现在，获取数据并加密
        return cipher.doFinal(JSONObject.toJSONString(datasource).getBytes());
    }

    /**
     * DES加密
     *
     * @param datasource key
     * @return
     */
    public static String encode(String datasource, String key) {
        try {
            if (ObjectUtils.isEmpty(key)) {
                key = KEY;
            }
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            byte[] temp = Base64.encodeBase64(cipher.doFinal(datasource.getBytes()));
            return new String(temp, StandardCharsets.UTF_8);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static String encode(String datasource) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes(StandardCharsets.UTF_8));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            byte[] temp = Base64.encodeBase64(cipher.doFinal(datasource.getBytes()));
            return new String(temp, StandardCharsets.UTF_8);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * DES解密
     *
     * @return
     */
    public static String decode(String src, String key) throws Exception {
        if (ObjectUtils.isEmpty(key)) {
            key = KEY;
        }
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        try {
            return new String(cipher.doFinal(Base64.decodeBase64(src)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("密码解析错误");
        }
    }

    public static String decode(String src) throws Exception {
        return decode(src, KEY);
    }


    @SneakyThrows
    public static void main(String[] args) {

        String pass = "1234";
        String pass_encode = encode(pass, null);//xHgGpSYHEnQ=
        String pass_decode = decode(pass_encode, null);

        System.out.println("pass_encode:" + pass_encode);
        System.out.println("pass_decode:" + pass_decode);
    }
}
