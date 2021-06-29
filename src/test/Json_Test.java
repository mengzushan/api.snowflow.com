package test;


import org.junit.Test;
import common.Json;
import common.bindJsonClass.*;
import common.errors.ErrorDefine;
import utils.Utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Json_Test {

    // 覆盖JsonReturn方法
    @Test
    public void JsonSerialize() {
        Json json = new Json();
        BillReturn bill = new BillReturn();
        bill.setCode(200);
        bill.setMessage("hello");
        Map<String,BillElement> map = new HashMap<String, BillElement>();
        BillElement be = new BillElement();
        be.setDestId(201);
        be.setState(1);
        be.setMountMoney(3000);
        be.setSourceId(202);
        be.setCreateTime("2009 05-24 UTC+8 12-36-36:25");
        map.put("oxffoo4",be);
        bill.setData(map);
        System.out.println(json.readBillReturn(bill));
        // base
        BaseReturn base = new BaseReturn();
        base.setCode(ErrorDefine.Ok.getCode());
        base.setMessage(ErrorDefine.Ok.getMessage());
        System.out.println(json.readBaseReturn(base));
    }

    // 覆盖JsonWrite方法
    @Test
    public void JsonWrite() {
        Json json = new Json();
        CreateBill cb = json.writeCreateBill(JsonTestData.Create_Bill);
        System.out.println("-----create_bill-----");
        try {
            String str = Utils.reflectErgodicValues(cb);
            System.out.println(str);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("-----user_login-----");
        UserLogin ul = json.writeUserLogin(JsonTestData.User_Login);
        try {
            System.out.println(Utils.reflectErgodicValues(ul));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("-----user_sign-----");
        UserSign us = json.writeUserSign(JsonTestData.User_Sign);
        try {
            System.out.println(Utils.reflectErgodicValues(us));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("-----user_set-----");
        UserSet ust = json.writeUserSet(JsonTestData.User_Set);
        try {
            System.out.println(Utils.reflectErgodicValues(ust));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

// 存放Json序列化用到的测试数据常量
class JsonTestData {
    // 创建转账记录
    public static final String Create_Bill = "{\n" +
            "\t\"dest_user\": \"402\",\n" +
            "\t\"mount_money\": 100,\n" +
            "\t\"user_vc\": \"744965\",\n" +
            "\t\"note\": \"工资\"\n" +
            "}";
    // 用户注册绑定的Json
    public static final String User_Login = "{\n" +
            "\t\"user_name\": \"xiaomi\",\n" +
            "\t\"user_password\": \"cok774..\",\n" +
            "\t\"user_vc\": \"7456\",\n" +
            "\t\"user_phone\": 13025800995,\n" +
            "\t\"user_email\": \"565584326@qq.com\"\n" +
            "}";
    public static final String User_Sign = "{\n" +
            "\t\"user_name\": \"xiaohui\",\n" +
            "\t\"user_password\": \"cok774..\",\n" +
            "\t\"user_vc\": \"745695\"\n" +
            "}";
    public static final String User_Set = "{\n" +
            "\t\"user_password\": \"123456\",\n" +
            "\t\"user_vc\": \"fxgh\",\n" +
            "\t\"user_phone\": 13025800995,\n" +
            "\t\"user_email\": \"565584326@qq.com\",\n" +
            "\t\"user_age\":18,\n" +
            "\t\"user_sex\":\"男\"\n" +
            "}";
}