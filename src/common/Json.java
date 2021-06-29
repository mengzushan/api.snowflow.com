package common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import common.bindJsonClass.*;
import common.errors.Errors;

public class Json {
    private GsonBuilder builder;

    public Json() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        this.builder = builder;
    }

    public String readStudent(Student student) {
        Gson gson = builder.create();
        return gson.toJson(student);
    }

    // 自定义错误转换字符串格式显示
    public String readCustomErrors(Errors err) {
        Gson gson = builder.create();
        return gson.toJson(err);
    }

    // 转换为字符串
    public String readBaseReturn(BaseReturn base) {
        Gson gson = builder.create();
        return gson.toJson(base);
    }

    public String readBillReturn(BillReturn bill) {
        Gson gson = builder.create();
        return gson.toJson(bill);
    }

    public String readVerificationCodeReturn(VerificationCodeReturn vc) {
        Gson gson = builder.create();
        return gson.toJson(vc);
    }

    public String readCookieVerification(CookieVerification cv) {
        Gson gson = builder.create();
        return gson.toJson(cv);
    }

    // 将json字符串转换为java对象
    public UserSet writeUserSet(String userSet) throws JsonSyntaxException{
        Gson gson = builder.create();
        return gson.fromJson(userSet,UserSet.class);
    }

    public UserLogin writeUserLogin(String userLogin) throws JsonSyntaxException {
        Gson gson = builder.create();
        return gson.fromJson(userLogin,UserLogin.class);
    }

    public UserSign writeUserSign(String userSign) throws JsonSyntaxException{
        Gson gson = builder.create();
        return gson.fromJson(userSign,UserSign.class);
    }

    public CreateBill writeCreateBill(String createBill) throws JsonSyntaxException{
        Gson gson = builder.create();
        return gson.fromJson(createBill,CreateBill.class);
    }

    public CookieVerification writeCookieVerification(String str) throws JsonSyntaxException{
        Gson gson = builder.create();
        return gson.fromJson(str,CookieVerification.class);
    }
}
