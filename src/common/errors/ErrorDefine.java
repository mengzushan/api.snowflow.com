package common.errors;

import java.net.HttpURLConnection;

public class ErrorDefine {
    // 成功信息
    public static final Errors Ok = new Errors(HttpURLConnection.HTTP_OK,0,"success");
    public static final Errors DelSignOk = new Errors(HttpURLConnection.HTTP_OK,1,"DEL USER SIGN OK");
    public static final Errors UserSignOk = new Errors(HttpURLConnection.HTTP_OK,2,"USER SIGN OK");

    // User模块错误
    public static final Errors ErrUserNotFound = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200101,"用户不存在");
    public static final Errors ErrUserNotFoundOrPasswordIncorrect = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200108,"用户名或密码不正确");
    public static final Errors ErrPasswordIncorrect = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200102,"密码错误");
    public static final Errors ErrUserRegisterAgain = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200103,"重复注册");
    public static final Errors ErrUserNameExist = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200104,"用户名已存在");
    public static final Errors ErrUsernameValidation = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200105,"用户名不合法");
    public static final Errors ErrPasswordValidation = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200106,"密码不合法");
    public static final Errors ErrUserSignNotFound = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200107,"该用户未登录");

    // 客户端错误
    public static final Errors ErrCookieNotFound = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200201,"客户端ID不存在");
    public static final Errors ErrVerification = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,200202,"验证码不正确");
    // 运行时库错误
    public static final Errors ErrJsonSyntaxError = new Errors(HttpURLConnection.HTTP_BAD_REQUEST,300101,"Json语法错误");
}

