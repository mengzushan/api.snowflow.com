package app.controller;

import app.model.dao.UserDao;
import app.model.mapping.Custom;
import com.google.gson.JsonSyntaxException;
import common.Json;
import common.bindJsonClass.*;
import common.config.AppInside;
import common.errors.*;
import utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class User extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        AppInside appinside = new AppInside();
        response.setContentType(appinside.getServletCodingType() + ";" + appinside.getServletEncodingType());

        PrintWriter out = response.getWriter();
        // 获取Cookie将指定用户的信息返回
    }

    // 处理 POST 方法请求的方法
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        Utils.setArgsAndReturnCoding(request, response);

        // 获取Json参数
        String jsonStr = Utils.toServletStreamGetString(request);
        Json json = new Json();
        PrintWriter out = response.getWriter();
        // 处理用户注册逻辑
        try {
            UserLogin ul = json.writeUserLogin(jsonStr);
            // 验证验证码的正确性
            Cookie[] cookies = request.getCookies();
            if (!Verification.CheckVerification(ul.getUserVc(),cookies)) {
                // 验证码不正确
                response.setStatus(ErrorDefine.ErrVerification.getHttpCode());
                Utils.printBaseInfoForServlet(out,ErrorDefine.ErrVerification);
                return;
            }
            UserDao dao = new UserDao();
            Custom c = new Custom();
            // 填充注册用户的信息
            c.setcPassword(ul.getUserPassword());
            c.setCid("");
            c.setPhoneAuthState(0);
            c.setEmailAuthState(0);
            c.setcName(ul.getUserName());
            Errors info = dao.UserLogin(c);
            // 打印信息
            Utils.printBaseInfoForServlet(out,info);
        } catch (JsonSyntaxException jse) {
            // 自定义错误返回指定的Json信息
            response.setStatus(ErrorDefine.ErrJsonSyntaxError.getHttpCode());
            Utils.printBaseInfoForServlet(out,ErrorDefine.ErrJsonSyntaxError);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    // 删除账户的接口
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        Utils.setArgsAndReturnCoding(request, response);

        PrintWriter out = response.getWriter();
        // 获取Json参数
        String jsonStr = Utils.toServletStreamGetString(request);
        Json json = new Json();
        // 处理删除逻辑
        try {
            UserSign us = json.writeUserSign(jsonStr);
            // TODO:数据库操作...
            UserDao dao = new UserDao();
            dao.UserDelete(us.getUserName());
            //
            BaseReturn b = new BaseReturn();
            Errors err = ErrorDefine.Ok;
            b.setCode(err.getCode());
            b.setMessage(err.getMessage());
            response.setStatus(err.getHttpCode());
            out.println(json.readBaseReturn(b));
        } catch (JsonSyntaxException jse) {
            // 自定义错误返回指定的Json信息
            response.setStatus(ErrorDefine.ErrJsonSyntaxError.getHttpCode());
            BaseReturn b = new BaseReturn();
            b.setCode(ErrorDefine.ErrJsonSyntaxError.getCode());
            b.setMessage(ErrorDefine.ErrJsonSyntaxError.getMessage());
            out.println(json.readBaseReturn(b));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应类型
        Utils.setArgsAndReturnCoding(req, resp);
        PrintWriter out = resp.getWriter();
        // 获取Json参数
        String jsonStr = Utils.toServletStreamGetString(req);
        Json json = new Json();
        // 处理修改用户账户逻辑
        try {
            UserSet ust = json.writeUserSet(jsonStr);
            // TODO:数据库操作
            //
            BaseReturn b = new BaseReturn();
            Errors err = ErrorDefine.Ok;
            b.setCode(err.getCode());
            b.setMessage(err.getMessage());
            out.println(json.readBaseReturn(b));
        } catch (JsonSyntaxException jse) {
            // 自定义错误返回指定的Json信息
            resp.setStatus(ErrorDefine.ErrJsonSyntaxError.getHttpCode());
            BaseReturn b = new BaseReturn();
            b.setCode(ErrorDefine.ErrJsonSyntaxError.getCode());
            b.setMessage(ErrorDefine.ErrJsonSyntaxError.getMessage());
            out.println(json.readBaseReturn(b));
        }
    }
}