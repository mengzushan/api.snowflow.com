package app.controller;

import app.model.dao.UserDao;
import app.model.mapping.Custom;
import common.Json;
import common.auth.miniJwt.MiniJwt;
import common.auth.TokenElement;
import common.auth.miniJwt.PlayLoad;
import common.bindJsonClass.UserSign;
import common.errors.ErrorDefine;
import common.errors.Errors;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class Sign extends HttpServlet {
    // 保存登录状态的cookie时间
    public static final int SaveSignCookieTime = 60 * 60 * 24 * 15;
    // 不保存登录状态的Cookie时间
    public static final int NoSaveSignCookieTime = 0;
    // 保存用户登录状态的Cookie
    public static final String SaveCookieName = "sign_state";

    // Jwt签发者
    public static final String Iss = "9-4021 .Inc";

    // 登录接口
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        // 设置请求和响应类型
        Utils.setArgsAndReturnCoding(req, resp);
        // 获取Json参数
        Json json = new Json();
        String jsonStr = Utils.toServletStreamGetString(req);
        UserSign us = json.writeUserSign(jsonStr);
        // TODO:Dao接口查询用户
        UserDao dao = new UserDao();
        Object[] info = dao.UserSign(us.getUserName(), us.getUserPassword());
        if (info[1] != null) {
            // 登录成功返回Cookie
            // 检查Cookie是否存活选项
            Custom c = (Custom) info[1];
            // 构造miniJwt
            MiniJwt jwt = new MiniJwt();
            // *1000为毫秒时间
            String[] dataStr =jwt.getTime(Sign.SaveSignCookieTime * 1000L);
            String jwtToken = null;
            try {
                jwtToken = jwt.createMiniJwt(Sign.Iss,"custom",c.getCid(),dataStr[0],dataStr[1],c.getcToken(), TokenElement.SignStateSaveCookieToken);
            } catch (NoSuchAlgorithmException e) {
                Utils.printBaseInfoForServlet(out,ErrorDefine.ErrJwtTokenCreate);
                return;
            }
            Cookie cookie = new Cookie(Sign.SaveCookieName,jwtToken);
            if (us.getSaveSign()) {
                cookie.setMaxAge(Sign.SaveSignCookieTime);
            } else {
                cookie.setMaxAge(Sign.NoSaveSignCookieTime);
            }
            resp.addCookie(cookie);
        }
        // 给前端返回信息
        Utils.printBaseInfoForServlet(out,(Errors) info[0]);
    }

    // 注销接口
    /*
    *   @author:mengzushan
    *   @info:注销接口，更新用户的签名Token,即可将之前签发的登录密钥失效
    * */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        // 获取用户的jwt token
        MiniJwt jwt = new MiniJwt();
        Cookie[] cookies = req.getCookies();
        String jwtStr = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(Sign.SaveCookieName)) {
                jwtStr = cookie.getValue();
            }
        }
        if (jwtStr != null) {
            PlayLoad pl = jwt.getJwtPlayloadInfo(jwtStr);
            try {
                if (!jwt.matchMiniJwt(jwtStr,TokenElement.SignStateSaveCookieToken)) {
                    Utils.printBaseInfoForServlet(out,ErrorDefine.ErrUserTokenIsFalse);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            // 用户Token相同则更新
            UserDao dao = new UserDao();
            Object[] info = dao.searchUserToken(pl.getAud());
            if (info != null && ((String) info[1]).equals(pl.getJti())) {
                dao.updateUserToken(pl.getAud(),Utils.getUserToken());
                Utils.printBaseInfoForServlet(out,ErrorDefine.Ok);
            } else {
                Utils.printBaseInfoForServlet(out,ErrorDefine.ErrUserTokenIsFalse);
            }
        } else {
            Utils.printBaseInfoForServlet(out,ErrorDefine.ErrUserTokenNotFound);
        }
    }
}
