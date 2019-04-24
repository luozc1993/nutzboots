package cn.luozc.unit_app.modules.controller.sys;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
@At("/sys/user")
@Ok("json:full")
public class SysUserController {

    @At
    public void page(){}

}
