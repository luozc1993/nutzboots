package cn.luozc.etu_web.service.sys;

import cn.luozc.etu_web.bean.sys.SysUser;
import cn.luozc.etu_web.dao.sys.UserDao;
import cn.luozc.etu_web.util.JsonData;
import cn.luozc.etu_web.util.LayuiTableResult;
import cn.luozc.etu_web.util.MD5Util;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        List<String> list = new ArrayList<>();
        list.add("uname");
        list.add("phone");
        list.add("email");
        list.add("nickname");
        return LayuiTableResult.result(0,"",userDao.size(value,list),userDao.getList(pageNumber,pageSize,value,list));
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
        if(userDao.getSysUserByPhoneNotId(sysUser.getPhone(),s.getId())!=null){
            return JsonData.fail("手机号已存在");
        }
        s.setEmail(sysUser.getEmail());
        s.setNickname(sysUser.getNickname());
        s.setPhone(sysUser.getPhone());
        return JsonData.success(userDao.update(s),"修改成功");
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public JsonData del(String id){
        return JsonData.success(userDao.delete(id),"删除成功");
    }

    public JsonData enable(int enable,String id){
        SysUser sysUser = userDao.getDataById(id);
        sysUser.setEnable(enable);
        return JsonData.success(userDao.update(sysUser),"修改成功");
    }
}
