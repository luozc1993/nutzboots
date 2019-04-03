package cn.luozc.etu_web.service.sys;

import cn.luozc.etu_web.bean.sys.SysUser;
import cn.luozc.etu_web.dao.sys.UserDao;
import cn.luozc.etu_web.util.JsonData;
import cn.luozc.etu_web.util.LayuiTableResult;
import cn.luozc.etu_web.util.MD5Util;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Date;
import java.util.UUID;

@IocBean
public class UserService {

    @Inject
    private UserDao userDao;

    /**
     * 获取用户列表数据 模糊分页查询
     * @param pageNumber
     * @param pageSize
     * @param value
     * @return
     */
    public LayuiTableResult getList(int pageNumber,int pageSize,String value){
        return LayuiTableResult.result(0,"",userDao.size(value),userDao.getList(pageNumber,pageSize,value));
    }

    /**
     * 添加用户信息
     * @param sysUser   用户信息
     * @return          插入的数据
     */
    public JsonData add(SysUser sysUser){
        if(userDao.getSysUserByUname(sysUser.getUname())!=null){
            return JsonData.fail("账号已被注册");
        }
        if(userDao.getSysUserByPhone(sysUser.getPhone())!=null){
            return JsonData.fail("手机号已被注册");
        }
        sysUser.setCreateTime(new Date());
        sysUser.setPassword(MD5Util.getMD5Str("123456"));
        sysUser.setId(UUID.randomUUID().toString());
        return JsonData.success(userDao.add(sysUser),"添加成功");
    }

    /**
     * 修改用户信息
     * @param sysUser   用户信息
     * @return          插入的数据
     */
    public JsonData edit(SysUser sysUser){
        SysUser s = userDao.getDataById(sysUser.getId());
        if(s==null){
            return JsonData.fail("改用户不存在");
        }
        s.setEmail(sysUser.getEmail());
        s.setNickname(sysUser.getNickname());
        s.setPhone(sysUser.getPhone());
        return JsonData.success(userDao.update(s),"添加成功");
    }
}
