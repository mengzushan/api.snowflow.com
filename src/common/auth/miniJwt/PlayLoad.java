package common.auth.miniJwt;

// 载荷部分
public class PlayLoad {
    // jwt的签发者
    private String iss;
    // jwt所面向的用户
    private String sub;
    // 接受jwt的一方
    private String aud;
    // jwt的过期时间
    private String exp;
    // 定义在什么时间之前,该jwt都是不可用的
    private String nbf;
    // jwt的签发时间
    private String iat;
    // 一次性token
    private String jti;

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getNbf() {
        return nbf;
    }

    public void setNbf(String nbf) {
        this.nbf = nbf;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }
}
