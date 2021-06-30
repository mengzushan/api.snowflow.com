package app.controller;

import common.Json;
import common.auth.TokenElement;
import common.bindJsonClass.BaseReturn;
import common.bindJsonClass.CookieVerification;
import common.bindJsonClass.VerificationCodeReturn;
import common.errors.ErrorDefine;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class Verification extends HttpServlet {

    public static final int VC_MAX = 600;
    public static final int CLIENT_MAX = 604800;

    // 获取验证码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        Utils.setArgsAndReturnCoding(req, resp);
        // 检查该客户端是否第一次获取验证码
        String cookieName = "client_id";
        Cookie[] cookies = req.getCookies();
        String cookie_value = null;
        PrintWriter out = resp.getWriter();
        Json json = new Json();
        // 没有cookie的情况
        if (cookies == null) {
            String uuid = UUID.randomUUID().toString().trim().replace("-","");
            Cookie ck = new Cookie(cookieName,uuid);
            ck.setMaxAge(CLIENT_MAX);
            resp.addCookie(ck);
            return;
        }
        // 找不到/找到指定的情况
        for (int i = 0; i < cookies.length; i++) {
            if (!cookies[i].getName().equals(cookieName) && i == cookies.length - 1) {
                String uuid = UUID.randomUUID().toString().trim().replace("-","");
                Cookie ck = new Cookie(cookieName,uuid);
                ck.setMaxAge(CLIENT_MAX);
                resp.addCookie(ck);
                break;
            } else if (cookies[i].getName().equals(cookieName)) {
                cookie_value = cookies[i].getValue();
                break;
            }
        }
        // 发送验证码Cookie签名
        if (cookie_value != null) {
            CookieVerification cv = new CookieVerification();
            Date date = new Date();
            String dateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            String dateStr = sdf.format(date);
            cv.setTime(dateStr);
            String cipherText = null;
            Object[] obj = null;
            try {
                // 生成验证码及其图片
                obj = Utils.createVCInstance();
                cipherText = Utils.createDigitalSignature(cookie_value,dateStr,(String) obj[0], TokenElement.VerificationCodeToken);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            cv.setCipherText(cipherText);
            // 对Cookie的值进行Base64编码以免出现错误
            Cookie c = new Cookie(cookie_value,Base64.getEncoder().encodeToString(json.readCookieVerification(cv).getBytes(StandardCharsets.UTF_8)));
            c.setMaxAge(VC_MAX);
            resp.addCookie(c);
            // 给客户端返回响应及图片
            VerificationCodeReturn vc = new VerificationCodeReturn();
            vc.setCode(ErrorDefine.Ok.getCode());
            vc.setMessage(ErrorDefine.Ok.getMessage());
            vc.setData((String) obj[1]);
            out.println(json.readVerificationCodeReturn(vc));
        } else {
            BaseReturn b = new BaseReturn();
            resp.setStatus(ErrorDefine.ErrCookieNotFound.getHttpCode());
            resp.getOutputStream();
            b.setCode(ErrorDefine.ErrCookieNotFound.getCode());
            b.setMessage(ErrorDefine.ErrCookieNotFound.getMessage());
            out.println(json.readBaseReturn(b));
        }
    }

    // 验证验证码的正确性
    public static boolean CheckVerification(String code,Cookie[] cookies) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String dg = null;
        String clientId = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("client_id")) {
                clientId = cookie.getValue();
            }
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(clientId)) {
                dg = cookie.getValue();
            }
        }
        // 匹配成功则base64解密
        if (dg != null) {
            byte[] src = Base64.getDecoder().decode(dg);
            String str = new String(src, StandardCharsets.UTF_8);
            Json json = new Json();
            CookieVerification cv = json.writeCookieVerification(str);
            return Utils.matchDigitalSignature(clientId,cv.getTime(),code,TokenElement.VerificationCodeToken,cv.getCipherText());
        } else {
            return false;
        }
    }
}
