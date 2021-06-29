package test;

import common.Json;
import common.bindJsonClass.Student;

import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloForm extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HelloForm() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "使用 GET 方法读取表单数据";
        // 处理中文
//        String name =new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
//        String docType = "<!DOCTYPE html> \n";
        Json json = new Json();
        Student student = new Student();
        student.setId(10);
        student.setAge(20);
        student.setName("xiaomi");
        out.println(json.readStudent(student));
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}