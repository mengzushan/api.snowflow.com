package common.auth.miniJwt;

/*
*  @author: mengzushan
*  @feather: jwt的mini实现
* */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class MiniJwt {
    public static final String TYPE = "jwt";
    public static final String ALG_TYPE = "HS256";
    public static final Long HOURS = 1000L * 60L * 60L;

    private GsonBuilder builder;

    public MiniJwt() {
        this.builder = new GsonBuilder();
        this.builder.setPrettyPrinting();
    }

    // 快速返回格式化好的时间，用于jwt的签名时间和过期时间
    // 0为签名时间,1为过期时间
    // 参数为Hour * n
    public String[] getTime(Long offSet) {
        String[] dateStr = new String[2];
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        dateStr[0] = sdf.format(date);
        date.setTime(date.getTime() + offSet);
        dateStr[1] = sdf.format(date);
        return dateStr;
    }

    // sub-面向的用户
    // iss-签发者
    // aud-接受jwt的用户id或者name
    // exp-jwt的过期时间，不能小于签发时间
    // iat-签发的时间
    // jti-一次性token
    public String createMiniJwt(String iss, String sub, String aud, String iat,String exp,String jti,String token) throws NoSuchAlgorithmException {
        Gson gson = this.builder.create();
        // 生成Jwt的头部
        Header header = new Header();
        header.setType(MiniJwt.TYPE);
        header.setAlgorithm(MiniJwt.ALG_TYPE);
        String jwtHeaderStr = gson.toJson(header);
        // 生成载荷部分
        PlayLoad pl = new PlayLoad();
        pl.setSub(sub);
        pl.setIss(iss);
        pl.setAud(aud);
        pl.setIat(iat);
        pl.setExp(exp);
        String jwtPlayLoad = gson.toJson(pl);
        // 生成最后的签名部分
        return createSignature(jwtHeaderStr,jwtPlayLoad,token);
    }

    // 生成签证信息的私有函数
    private String createSignature(String header,String playLoad,String token) throws NoSuchAlgorithmException {
        header = Base64.getEncoder().encodeToString(header.getBytes(StandardCharsets.UTF_8));
        playLoad = Base64.getEncoder().encodeToString(playLoad.getBytes(StandardCharsets.UTF_8));
        String signatureText = header + "." + playLoad + "." + token;
        // 生成签证
        MessageDigest messageDigest;
        messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(signatureText.getBytes(StandardCharsets.UTF_8));
        // 拼接为jwt显示的格式
        return header + "." + playLoad + "." + Base64.getEncoder().encodeToString(hash);
    }

    // 验证jwt签证是否正确
    // header.playload.signature
    public boolean matchMiniJwt(String base64Str,String token) throws NoSuchAlgorithmException {
        // 分割字符串
        String[] str = base64Str.split("\\.");
        // base64解码
        byte[] headerByte = Base64.getDecoder().decode(str[0].getBytes(StandardCharsets.UTF_8));
        byte[] playloadByte = Base64.getDecoder().decode(str[1].getBytes(StandardCharsets.UTF_8));
        String header = new String(headerByte,StandardCharsets.UTF_8);
        String playLoad = new String(playloadByte,StandardCharsets.UTF_8);
        return createSignature(header,playLoad,token).equals(base64Str);
    }

    // 获取jwt token的playload部分的信息
    public PlayLoad getJwtPlayloadInfo(String token) throws JsonSyntaxException {
        // 分割字符串
        String[] str = token.split("\\.");
        byte[] playloadByte = Base64.getDecoder().decode(str[1].getBytes(StandardCharsets.UTF_8));
        String playload = new String(playloadByte,StandardCharsets.UTF_8);
        // json序列化
        Gson gson = builder.create();
        return gson.fromJson(playload,PlayLoad.class);
    }
}

// 头部
class Header {
    // 类型信息
    @SerializedName("typ")
    private String type;

    // 使用的签名算法
    @SerializedName("alg")
    private String algorithm;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}


