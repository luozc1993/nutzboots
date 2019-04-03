package cn.luozc.etu_web.util;


import java.util.HashMap;
import java.util.Map;

/**
 * 结果集
 */
public class JsonData {

    private boolean status;

    private String msg;

    private Object data;

    public JsonData(boolean status) {
        this.status = status;
    }

    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = "";
        return jsonData;
    }

    public static JsonData success() {
        return new JsonData(true);
    }

    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }
    public static JsonData fail() {
        return new JsonData(false);
    }
    
    public static JsonData fail(Object object) {
        JsonData jsonData = new JsonData(false);
        jsonData.data = object;
        jsonData.msg = "";
        return jsonData;
    }
    public static JsonData fail(Object object, String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("status", status);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonData [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
    
    
}
