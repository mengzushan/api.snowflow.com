package app.model.mapping;

import org.jfaster.mango.annotation.Column;
import org.jfaster.mango.annotation.ID;

public class Custom {

    public static final String DB_NAME = "custom";

    @ID
    @Column("Cid")
    protected String cid;

    @Column("Cpassword")
    private String cPassword;

    @Column("identity")
    protected String identity;

    @Column("email_auth_state")
    private int emailAuthState;

    @Column("phone_auth_state")
    private int phoneAuthState;

    @Column("Cname")
    private String cName;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getEmailAuthState() {
        return emailAuthState;
    }

    public void setEmailAuthState(int emailAuthState) {
        this.emailAuthState = emailAuthState;
    }

    public int getPhoneAuthState() {
        return phoneAuthState;
    }

    public void setPhoneAuthState(int phoneAuthState) {
        this.phoneAuthState = phoneAuthState;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
