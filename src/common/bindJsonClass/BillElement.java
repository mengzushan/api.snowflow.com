package common.bindJsonClass;


public class BillElement {
    private int sourceId;
    private int destId;
    private int mountMoney;
    private String createTime;
    private int state;
    // 转账备注
    private String note;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getDestId() {
        return destId;
    }

    public void setDestId(int destId) {
        this.destId = destId;
    }

    public int getMountMoney() {
        return mountMoney;
    }

    public void setMountMoney(int mountMoney) {
        this.mountMoney = mountMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
