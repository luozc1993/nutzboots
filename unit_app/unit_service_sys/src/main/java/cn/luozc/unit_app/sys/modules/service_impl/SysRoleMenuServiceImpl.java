package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysRoleMenu;
import cn.luozc.unit_app.sys.modules.model.SysRoleMenuService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import com.alibaba.dubbo.config.annotation.Service;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysRoleMenuService.class)
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {
    public SysRoleMenuServiceImpl(Dao dao) {
        super(dao);
    }
}
