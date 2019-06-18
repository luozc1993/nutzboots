package cn.luozc.oa.commom.page;

import java.io.Serializable;

public class LayuiTable implements Serializable {
    private static final long serialVersionUID = 1L;

    //状态码
    private int code;

    //信息
    private String msg;

    //数据量
    private int count;

    //数据
    private Object data;

    public LayuiTable(){}

    public LayuiTable(int code,int count,Object data,String msg){
        this.code = code;
        this.count = count;
        this.data = data;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
