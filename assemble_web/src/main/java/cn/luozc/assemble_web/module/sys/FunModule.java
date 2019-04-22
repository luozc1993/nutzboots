package cn.luozc.assemble_web.module.sys;

import cn.luozc.assemble_web.module.BaseModule;
import cn.luozc.assemble_web.service.sys.FunService;
import cn.luozc.assemble_web.util.JsonData;
import net.sf.json.JSONObject;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys/fun")
@Ok("json:full")
@IocBean
public class FunModule extends BaseModule {

    @Inject private FunService funService;

    @At
    public JsonData call(String id, String data){
        Object call = funService.call(id, data);
        return JsonData.success(call);
    }
    

}
