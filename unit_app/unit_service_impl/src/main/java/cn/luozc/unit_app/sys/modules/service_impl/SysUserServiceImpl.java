package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysUser;
import cn.luozc.unit_app.sys.modules.model.SysUserService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.springframework.stereotype.Service;

@IocBean(args = {"refer:dao"})
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    public SysUserServiceImpl(Dao dao) {
        super(dao);
    }

}
