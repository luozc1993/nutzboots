package cn.luozc.assemble_web.module.sys;

import cn.luozc.assemble_web.module.BaseModule;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys/report")
@Ok("json:full")
@IocBean
public class ReportModule extends BaseModule {


    @At
    @Ok("->:/page/report/report.html?m=${obj}")
    public Object page(String m){
        return m;
    }

}
