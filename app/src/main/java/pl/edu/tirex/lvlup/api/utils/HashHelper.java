package pl.edu.tirex.lvlup.api.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHelper
{
    public static String md5(String text)
    {
        byte[] bytes = md5(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes)
        {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1)
            {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static byte[] md5(byte[] bytes)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            return md5.digest();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return new byte[32];
    }
}
