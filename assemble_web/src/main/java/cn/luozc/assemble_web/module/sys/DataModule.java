package cn.luozc.assemble_web.module.sys;

import cn.luozc.assemble_web.module.BaseModule;
import net.sf.json.JSONObject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys/data")
@Ok("json:full")
@IocBean
public class DataModule extends BaseModule {

    @At
    @Ok("beetl:/data/${obj.did}.html")
    public JSONObject page(String mid,String did){
        JSONObject json = new JSONObject();
        json.put("did",did);
        return json;
    }
    

}
