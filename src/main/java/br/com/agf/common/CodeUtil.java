package br.com.agf.common;


public class CodeUtil {

    public static String getStringCoded(String stringToCode){
        String encoded = null;
        try {            
            encoded = Crypto.Encrypt(stringToCode, "56193824");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encoded;
    }
    
    public static String getStringDecoded(String stringToCode){
        String encoded = null;
        try {            
            encoded = Crypto.Decrypt(stringToCode, "56193824");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encoded;
    }
}
