package cn.luozc.assemble_web.module.sys;

import cn.luozc.assemble_web.bean.sys.SysMenu;
import cn.luozc.assemble_web.bean.sys.SysReport;
import cn.luozc.assemble_web.module.BaseModule;
import cn.luozc.assemble_web.service.BaseService;
import cn.luozc.assemble_web.service.sys.MenuService;
import cn.luozc.assemble_web.service.sys.ReportService;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
