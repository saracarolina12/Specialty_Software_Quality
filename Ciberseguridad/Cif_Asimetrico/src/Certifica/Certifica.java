/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Certifica;

import asim.RSA;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author lelguea
 */
public class Certifica {
    
    public static void main(String[] asdadad) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        
        
        String file_public = "LlavesPub//rsaLMEF_bin.pub";
        try (FileOutputStream fos = new FileOutputStream(file_public)) {
            fos.write(publicKey.getEncoded());
        }
        
        
        File publicKeyFile = new File(file_public);
        byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
        
        
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        keyFactory.generatePublic(publicKeySpec);
        System.out.println(keyFactory.getAlgorithm());
        System.out.println(publicKey.toString());
    }
    
}
