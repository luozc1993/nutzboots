package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysUser;
import cn.luozc.unit_app.sys.modules.model.SysUserService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysUserService.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    public SysUserServiceImpl(Dao dao) {
        super(dao);
    }

}
