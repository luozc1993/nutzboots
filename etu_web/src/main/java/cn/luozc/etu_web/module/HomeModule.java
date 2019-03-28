package cn.luozc.etu_web.module;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At
@IocBean(create="init", depose="depose")
public class HomeModule {

    @Inject
    protected PropertiesProxy conf;

    @At({"/","/index"})
    @Ok("->:/index.html")
    public String now() {

        return null;
    }

    public void init() {}
    public void depose() {}
}
