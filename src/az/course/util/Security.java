package az.course.util;


import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 4/5/16
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Security {

    public static String  encodeMd5(String password) {
        String result = null;
        try {
          //  String password = "fuad";

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            result =  sb.toString();

         //   System.out.println("Digest(in hex format):: " + sb.toString());

            //convert the byte to hex format method 2
            /*StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);
            }
            System.out.println("Digest(in hex format):: " + hexString.toString());*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    public static String encodeBase64(String pwd) {
        byte[] result = Base64.encodeBase64(pwd.getBytes());
        return result.toString();
    }
}
