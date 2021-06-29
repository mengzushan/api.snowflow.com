package app.model.mapping;

public class Bank {

    public static final String DB_NAME = "bank";

    private String bid;
    private String bName;
    private double bMoney;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public double getbMoney() {
        return bMoney;
    }

    public void setbMoney(double bMoney) {
        this.bMoney = bMoney;
    }
}
