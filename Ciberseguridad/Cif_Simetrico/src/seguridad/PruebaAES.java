/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

/**
 *
 * @author lelguea
 */
public class PruebaAES {
    
    public static void main(String[] args) 
{
    final String secretKey = "JamesB007";
     
    String originalString = "El misil debe de ser destruido";
    String encryptedString = AES.encrypt(originalString, secretKey) ;
    String decryptedString = AES.decrypt(encryptedString, secretKey) ;
     
    System.out.println(originalString);
    System.out.println(encryptedString);
    System.out.println(decryptedString);
    
    String OtraLlave="lelguea007";
    String otroCodigo="W8cs+8JuK82ipRwxlHaatPjDmcOfn66zQ4/N5XLZqxGNpz0Ueud8NqrKcvg/P6GAwINH+b5v54j15JADKo59Q6ubK2gk3ST8mPNEhuvsXQg=";
    System.out.println(AES.decrypt(otroCodigo, OtraLlave));
}
    
}
