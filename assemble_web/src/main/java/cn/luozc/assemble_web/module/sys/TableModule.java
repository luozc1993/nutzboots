package cn.luozc.assemble_web.module.sys;

import cn.luozc.assemble_web.module.BaseModule;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys/table")
@Ok("json:full")
@IocBean
public class TableModule extends BaseModule {




}
