package common.bindJsonClass;

import com.google.gson.annotations.SerializedName;

public class UserLogin {

    @SerializedName("user_name")
    private String userName;

    @SerializedName("user_password")
    private String userPassword;

    @SerializedName("user_vc")
    private String userVc;

    @SerializedName("user_phone")
    private long userPhone;

    @SerializedName("user_email")
    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
