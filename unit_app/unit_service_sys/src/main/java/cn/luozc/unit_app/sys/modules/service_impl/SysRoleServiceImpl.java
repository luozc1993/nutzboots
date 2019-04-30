package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysRole;
import cn.luozc.unit_app.sys.modules.model.SysRoleService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import com.alibaba.dubbo.config.annotation.Service;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysRoleService.class)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
    public SysRoleServiceImpl(Dao dao) {
        super(dao);
    }
}
