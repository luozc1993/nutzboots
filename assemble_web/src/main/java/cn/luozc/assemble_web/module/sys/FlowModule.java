package cn.luozc.assemble_web.module.sys;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys/flow")
@Ok("json:full")
@IocBean
public class FlowModule {

    @At
    public void page(String m,String f){
        System.err.println(m);
        System.err.println(f);
    }

}
