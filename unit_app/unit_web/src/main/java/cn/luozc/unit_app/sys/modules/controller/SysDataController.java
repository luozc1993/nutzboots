package cn.luozc.unit_app.sys.modules.controller;

import net.sf.json.JSONObject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys/data")
@Ok("json:full")
@IocBean
public class SysDataController  {

    @At
    @Ok("beetl:/data/${obj.did}.html")
    public JSONObject page(String mid,String did){
        JSONObject json = new JSONObject();
        json.put("did",did);
        return json;
    }
    

}
