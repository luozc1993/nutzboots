package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysMenu;
import cn.luozc.unit_app.sys.modules.model.SysMenuService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import com.alibaba.dubbo.config.annotation.Service;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysMenuService.class)
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {
    public SysMenuServiceImpl(Dao dao) {
        super(dao);
    }
}
