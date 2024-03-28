/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author lelguea
 */
public class PruebaDES {
    
     public static void main(String[] args) throws Exception {

        String text = "/yjVOIietwNbTL5rp0AjDArvzeCmlHwTCHgdvsmZdYJIM78IwrrcLPWLxifkuSkwQoRelNrnaEgLQtq0ye5PgG6Qdsn+0iL89upKacQMNX8=";
        String secret = "ClaveUnica";

        byte[] codedtext = new PruebaDES().encrypt(text,secret );
        String decodedtext = new PruebaDES().decrypt(codedtext,secret);

        String CodetextBase64=Base64.getEncoder().encodeToString(codedtext);
        //System.out.println(new String(codedtext, StandardCharsets.UTF_8)); // this is a byte array, you'll just see a reference to an array
        System.out.println(CodetextBase64); // this is a byte array, you'll just see a reference to an array
        System.out.println(decodedtext); // This correctly shows "kyle boon"
        
        
        String nuevaCadena="/yjVOIietwNbTL5rp0AjDArvzeCmlHwTCHgdvsmZdYJIM78IwrrcLPWLxifkuSkwQoRelNrnaEgLQtq0ye5PgG6Qdsn+0iL89upKacQMNX8=";
        byte[] nuevocodigo=Base64.getDecoder().decode(nuevaCadena);
        String nuevallave="ClaveUnica";
        String decodedtext2 = new PruebaDES().decrypt(nuevocodigo,nuevallave);
        System.out.println(decodedtext2);
    }

    public byte[] encrypt(String message, String skey) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(skey.getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        // final String encodedCipherText = new sun.misc.BASE64Encoder()
        // .encode(cipherText);

        return cipherText;
    }

    public String decrypt(byte[] message, String skey) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(skey.getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, key, iv);

        // final byte[] encData = new
        // sun.misc.BASE64Decoder().decodeBuffer(message);
        final byte[] plainText = decipher.doFinal(message);

        return new String(plainText, "UTF-8");
    }

}
  