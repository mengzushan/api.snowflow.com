package app.model.mapping;

import org.jfaster.mango.annotation.Column;

import java.util.Date;

public class CustomShow extends Custom{

    public static final String DB_NAME = "custom_show";

    @Column("Cname")
    private String cName;

    @Column("Ctime")
    private Date cTime;

    @Column("Bid")
    private String bid;

    @Column("Cmoney")
    private double cMoney;

    // 用户的手机号码
    @Column("Cphone")
    private String cPhone;

    // 用户的邮箱
    @Column("user_email")
    private String userEmail;

    // 用户的性别
    @Column("user_sex")
    private String userSex;

    // 用户的年龄
    @Column("user_age")
    private int userAge;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public double getcMoney() {
        return cMoney;
    }

    public void setcMoney(double cMoney) {
        this.cMoney = cMoney;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
}
