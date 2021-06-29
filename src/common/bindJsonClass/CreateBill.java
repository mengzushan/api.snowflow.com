package common.bindJsonClass;

import com.google.gson.annotations.SerializedName;

public class CreateBill {

    @SerializedName("dest_user")
    private String destUser;

    @SerializedName("mount_money")
    private int mountMoney;

    @SerializedName("user_vc")
    private String userVc;

    @SerializedName("note")
    private String note;

    public String getDestUser() {
        return destUser;
    }

    public void setDestUser(String destUser) {
        this.destUser = destUser;
    }

    public int getMountMoney() {
        return mountMoney;
    }

    public void setMountMoney(int mountMoney) {
        this.mountMoney = mountMoney;
    }

    public String getUserVc() {
        return userVc;
    }

    public void setUserVc(String userVc) {
        this.userVc = userVc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
