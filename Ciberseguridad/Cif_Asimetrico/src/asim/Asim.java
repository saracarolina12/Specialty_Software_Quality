/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package asim;

/**
 *
 * @author lelguea
 */
public class Asim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    //Definimos un texto a cifrar
        String str = "Subame un punto en el ";
        
        System.out.println("\nTexto a cifrar:");
        System.out.println(str);
        String secure;
                
        String file_private = "C:\\Users\\scago\\Documents\\GitHub\\Cibersec_\\rsa.pri";
        String file_public = "C:\\Users\\scago\\Documents\\GitHub\\Cibersec_\\rsaLMEF.pub";
        
        //Instanciamos la clase
        RSA rsa = new RSA();
        
        //Generamos un par de claves
        //Admite claves de 512, 1024, 2048 y 4096 bits
        rsa.genKeyPair(2048);
        
        
        //Las guardamos asi podemos usarlas despues
        //a lo largo del tiempo
        rsa.saveToDiskPrivateKey(file_private);
        rsa.saveToDiskPublicKey(file_public);
        rsa.openFromDiskPrivateKey(file_private);    
        rsa.openFromDiskPublicKey(file_public);
        //Ciframos y e imprimimos, el texto cifrado
        //es devuelto en la variable secure
        secure = rsa.EncryptPriv(str);
        
        System.out.println("\nCifrado:");
        System.out.println(secure);
        
        System.exit(0);
     
        
         //A modo de ejemplo creamos otra clase rsa
        RSA rsa2 = new RSA();
        secure="2hkj98zqstd5fuvel00ddgy6nsibcamu0djnwppev84cj3rlya8u7blmogady9htrzp7ntyixz8okxoar0ee1z9bntinpx7is76ws4lcz8l06bey6my4vje0g7qet8c81mwl1sou3ruu28d53cc0mj1nbrvrmr0ldegbxh3ea6cbvkyqs1lbuvtyzr41rrccgyit7yu7mhjadre48ydnroj73ij4s9judotog6i5elu03q6arrvluvbvtnu325e0e9nttywcih5iqhwe3anwv3dm7eqiiuwb3y1153qj0fgidouu7kayaxl9fkja82cucpzcniaqzqs79cwrjnwyiouzqwkdrrnnakkncg06kxh2ejodx717knqm0o7j2h4dc2ux6ni0mrh1c";
        
        //A diferencia de la anterior aca no creamos
        //un nuevo par de claves, sino que cargamos
        //el juego de claves que habiamos guadado
        file_public = "C:\\Users\\scago\\Documents\\GitHub\\Cibersec_\\rsa0214342.pub";
        rsa2.openFromDiskPrivateKey(file_private);    
        rsa2.openFromDiskPublicKey(file_public);
        
        
        //Le pasamos el texto cifrado (secure) y nos 
        //es devuelto el texto ya descifrado (unsecure) 
        String unsecure = rsa2.DecryptPub(secure);
        
        //Imprimimos
        System.out.println("\nDescifrado:");
        System.out.println(unsecure);   
                
    }
    
    
}
