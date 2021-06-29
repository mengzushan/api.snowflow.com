package app.controller;

import common.Json;
import common.bindJsonClass.UserSign;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Sign extends HttpServlet {
    // 保存登录状态的cookie时间
    public static final int SaveSignCookieTime = 60 * 60 * 24 * 15;
    // 不保存登录状态的Cookie时间
    public static final int NoSaveSignCookieTime = 0;
    // 登录接口
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求和响应类型
        Utils.setArgsAndReturnCoding(req, resp);
        // 获取Json参数
        Json json = new Json();
        String jsonStr = Utils.toServletStreamGetString(req);
        UserSign us = json.writeUserSign(jsonStr);
        // TODO:Dao接口查询用户
        // 登录成功返回Cookie
    }

    // 注销接口
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
