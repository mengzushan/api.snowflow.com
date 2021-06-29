package common.bindJsonClass;

import com.google.gson.annotations.SerializedName;

public class UserSet {

    @SerializedName("user_password")
    private String userPassword;

    @SerializedName("user_vc")
    private String userVc;

    @SerializedName("user_phone")
    private long userPhone;

    @SerializedName("user_email")
    private String userEmail;

    @SerializedName("user_age")
    private int userAge;

    @SerializedName("user_sex")
    private String userSex;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserVc() {
        return userVc;
    }

    public void setUserVc(String userVc) {
        this.userVc = userVc;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
