package test;

import common.auth.miniJwt.MiniJwt;
import common.auth.TokenElement;
import utils.Utils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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

    @Test
    public void MiniJwtTest() {
        MiniJwt jwt = new MiniJwt();
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 60 * 60);
        try {
            String[] time = jwt.getTime(MiniJwt.HOURS * 24);
            String str = jwt.createMiniJwt("1","2","3",time[0],time[1],"ll",TokenElement.SignStateSaveCookieToken);
            System.out.println(str);
            System.out.printf("全部日期和时间信息：%tc%n\n",date);
            System.out.println(jwt.matchMiniJwt(str,TokenElement.SignStateSaveCookieToken));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
