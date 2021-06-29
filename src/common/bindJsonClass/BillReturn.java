package common.bindJsonClass;

/*
* 返回数据使用
* */

import java.util.Map;

public class BillReturn extends BaseReturn{
    private Map<String,BillElement> data;

    public Map<String, BillElement> getData() {
        return data;
    }

    public void setData(Map<String, BillElement> data) {
        this.data = data;
    }
}
