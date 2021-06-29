package app.model.mapping;


import java.sql.Time;


// 数据库表bank_card_pool的映射
public class BankCardPool {

    public static final String DB_NAME = "bank_card_pool";

    private String cardId;
    private String cardNum;
    private Time addTime;
    private String masterId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Time getAddTime() {
        return addTime;
    }

    public void setAddTime(Time addTime) {
        this.addTime = addTime;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
}
