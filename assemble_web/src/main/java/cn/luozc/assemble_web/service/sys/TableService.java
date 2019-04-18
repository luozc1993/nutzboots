package cn.luozc.assemble_web.service.sys;

import cn.luozc.assemble_web.bean.sys.SysRole;
import cn.luozc.assemble_web.bean.sys.SysTable;
import cn.luozc.assemble_web.dao.sys.SysRoleDao;
import cn.luozc.assemble_web.dao.sys.SysTableDao;
import cn.luozc.assemble_web.service.BaseService;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import net.sf.json.JSONObject;
import org.nutz.dao.Chain;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@IocBean
public class TableService extends BaseService {

    @Inject
    private SysTableDao sysTableDao;











}
