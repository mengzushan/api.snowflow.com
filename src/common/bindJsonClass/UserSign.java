package common.bindJsonClass;

import com.google.gson.annotations.SerializedName;

public class UserSign {

    @SerializedName("user_name")
    private String userName;

    @SerializedName("user_password")
    private String userPassword;

    @SerializedName("user_vc")
    private String userVc;

    @SerializedName("save_sign")
    private boolean saveSign;

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

    public boolean getSaveSign() {
        return saveSign;
    }

    public void setSaveSign(boolean saveSign) {
        this.saveSign = saveSign;
    }
}
