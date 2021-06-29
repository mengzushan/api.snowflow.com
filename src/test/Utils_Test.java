package test;

import utils.Utils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Utils_Test {

    @Test
    public void Sha256Test() {
        try {
            String str = Utils.createDigitalSignature("1","2","3","0xf4568");
            System.out.println(str);
            // 验证数字签名
            System.out.println(Utils.matchDigitalSignature("1","2","3","0xf4568",str));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void base64ImageVerification() {
        try {
            Object[] obj = Utils.createVCInstance();
            System.out.println((String)obj[0]);
            System.out.println((String)obj[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
