package utils;

import common.Json;
import common.bindJsonClass.BaseReturn;
import common.config.AppInside;
import common.errors.Errors;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class Utils {

    // 从servlet原始body流中获得json字符串
    public static String toServletStreamGetString(HttpServletRequest request) throws ServletException, IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = buf.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    // 反射遍历对象中的值
    public static String reflectErgodicValues(Object obj) throws NoSuchFieldException,IllegalAccessException{
        StringBuilder buf = new StringBuilder();
        Class<?> c1 = obj.getClass();
        Field[] field = c1.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            Field f1 = c1.getDeclaredField(field[i].getName());
            f1.setAccessible(true);
            Object val = f1.get(obj);
            buf.append(field[i].getName());
            buf.append(" : ");
            buf.append(val);
            buf.append("\n");
        }
        return buf.toString();
    }

    // 统一请求和响应编码，以免出现乱码
    // AppInside库的常量作为控制
    public static void setArgsAndReturnCoding(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        AppInside inside = new AppInside();
        request.setCharacterEncoding(inside.getServletEncodingType());
        response.setContentType(inside.getArgsType());
        response.setCharacterEncoding(inside.getServletEncodingType());
    }

    // 生成验证码数字签名
    // 生成摘要后的结果是一个base64字符串
    public static String createDigitalSignature(String uuid,String time,String code,String token) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest;

        messageDigest = MessageDigest.getInstance("SHA-256");
        String sb = uuid + time + code + token;
        byte[] hash = messageDigest.digest(sb.getBytes("UTF-8"));
        // 转为16进制hex字符串
        return Base64.getEncoder().encodeToString(hash);
    }

    // 匹配数字签名
    public static boolean matchDigitalSignature(String uuid,String time,String code,String token,String base64Str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String ds = createDigitalSignature(uuid, time, code,token);
        return ds.equals(base64Str);
    }

    // 打印基本错误/成功信息
    public static void printBaseInfoForServlet(PrintWriter print, Errors err) {
        Json json = new Json();
        BaseReturn b = new BaseReturn();
        b.setCode(err.getCode());
        b.setMessage(err.getMessage());
        print.println(json.readBaseReturn(b));
    }

    // 生成验证码图片
    // [0]为验证码本身,[1]为验证码Base64之后的结果
    public static Object[] createVCInstance() throws IOException {
        // 验证码的长宽高
        int height = 100;
        int width = 30;
        int len = 4;
        // 用于绘制图片，设置图片的长宽和图片类型
        BufferedImage bi = new BufferedImage(height,width,BufferedImage.TYPE_INT_RGB);
        // 获取绘图工具
        Graphics graphics = bi.getGraphics();
        graphics.setColor(new Color(100,230,200));
        graphics.fillRect(0,0,100,30);

        // 验证码生所需的字符串
        char[] codeChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        String captcha = ""; // 存放生成的验证码
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(codeChar.length);
            // 随机生成验证码颜色
            graphics.setColor(new Color(random.nextInt(150),random.nextInt(200),random.nextInt(255)));
            // 将一个字符绘制到图片上，并制定位置(设置x,y坐标
            graphics.drawString(codeChar[index] + "",(i * 20) + 15,20);
            captcha += codeChar[index];
        }
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageIO.write(bi,"JPG", bs);
        Object [] rt = new Object[2];
        rt[0] = captcha;
        rt[1] = Base64.getEncoder().encodeToString(bs.toByteArray());
        return rt;
    }

    // TODO:获得一个随机的用户token
    public static String getUserToken() {
        return "";
    }
}
