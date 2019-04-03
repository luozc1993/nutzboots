package cn.luozc.etu_web.util;

public class LayuiTableResult {

    private int code;
    private String msg;
    private long count;
    private Object data;

    public LayuiTableResult(int code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
    public static LayuiTableResult result(int code, String msg, long count, Object data) {
        LayuiTableResult LayuiTableResult = new LayuiTableResult(code,msg,count,data);
        return LayuiTableResult;
    }

    public LayuiTableResult(){}

    @Override
    public String toString() {
        return "LayuiTableResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
