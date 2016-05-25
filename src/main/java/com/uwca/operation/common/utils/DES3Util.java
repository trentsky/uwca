package com.uwca.operation.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DES3Util {

	private static final String key = "AHWEBSEC";

	public static void main(String[] args) throws Exception {
		String originValue = "63f0e061bc904cefa66abaec68c419a3|18001270896|123456|79add738178c45e1a7028aed61ba84a0";
		System.out.println(encode(originValue));
		System.out.println(decrypt("IoiD7AIeVyi4EAHN5RTOZ0F5CQ4_EKb7C_E-zJ1Tt5oB0_WTrTMtUtJazD4VUMvzndbnBa_fbU3lECb2wxskE77oRtfeRpa6O9eVMjAGakts--25NTKskw||", key));

	}
	
	public static String encode(String originValue) throws Exception{
		return encrypt(originValue,key).replace("+", "-").replace("/", "_").replace("=", "|");
	}
	
	public static String decode(String secretStr) throws Exception{
		secretStr = secretStr.replace("-", "+").replace("_", "/").replace("|", "=");
		return decrypt(secretStr,key);
	}

    public static String encrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
 
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
 
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
 
        return encodeBase64(cipher.doFinal(message.getBytes("UTF-8")));
    }
 
    public static String decrypt(String message, String key) throws Exception {
 
        byte[] bytesrc = decodeBase64(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
 
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
 
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }
 
    public static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
 
        return digest;
    }
 
    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
 
        return hexString.toString();
    }
 
     
    public static String encodeBase64(byte[] b) {
        return Encodes.encodeBase64(b);
    }
     
    public static byte[] decodeBase64(String base64String) {
        return Encodes.decodeBase64(base64String);
    }

}
