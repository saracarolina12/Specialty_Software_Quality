/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asim;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author scago
 */
public class Ejercicio5 {
    //Se encripta con la publica del profe
    public static void main(String[] args) {
        String texto = "Mensaje de Sarita c:";
        String file_priv = "C:\\Users\\scago\\Documents\\GitHub\\Cibersec_\\rsa.pri";
        String profe_pub_file = "C:\\Users\\scago\\Documents\\GitHub\\Cibersec_\\rsaLMEF_ver1.pub";

        try{
            //mi priv
            RSA rsa1 = new RSA();
            rsa1.openFromDiskPrivateKey(file_priv);
            String enc = rsa1.EncryptPriv(texto);
            System.out.println("El resultado es: " + enc);
            
            // dividir el mensaje
            String a = enc.substring(0, enc.length()/2);
            String b = enc.substring(enc.length()/2);
            
            //pub profe
            RSA rsa2 = new RSA();
            rsa2.openFromDiskPublicKey(profe_pub_file);
            String parteA = rsa2.EncryptPub(a);
            String parteB = rsa2.EncryptPub(b);
            System.out.println("\nParte A: " + parteA);
            System.out.println("\nParte B: " + parteB);
        } catch (IOException | NoSuchPaddingException | NoSuchProviderException | InvalidKeyException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException ex){
            System.err.println("Error in main \n: " + ex.toString());
        }
    }
}