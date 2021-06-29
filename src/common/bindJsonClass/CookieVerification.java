package common.bindJsonClass;


// 客户端签名验证码参数
public class CookieVerification {
    public static final String token = "0xf489531845dddfxx00";

    private String time;
    private String cipherText;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }
}
