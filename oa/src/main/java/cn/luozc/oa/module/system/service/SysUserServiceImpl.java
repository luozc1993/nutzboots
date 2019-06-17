package cn.luozc.oa.module.system.service;

import cn.luozc.oa.commom.service.BaseServiceImpl;
import cn.luozc.oa.module.system.model.SysUser;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    public SysUserServiceImpl(Dao dao) {
        super(dao);
    }

}
