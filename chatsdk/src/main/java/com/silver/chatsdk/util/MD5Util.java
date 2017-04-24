package com.silver.chatsdk.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ArthurYis on 2016/12/5.
 */

public class MD5Util {

    public static String  MD5(String data){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            return  Base64.encodeToString(md5.digest(data.getBytes("utf-8")),Base64.DEFAULT);
        }catch (NoSuchAlgorithmException exception ){

        } catch (UnsupportedEncodingException exception){

        }
        return  null;
    }
}
