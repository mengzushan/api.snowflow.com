package common.bindJsonClass;

/*
* data中存储的是图片经过base64加密之后的字符串表示的结果
* */

public class VerificationCodeReturn extends BaseReturn{
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
