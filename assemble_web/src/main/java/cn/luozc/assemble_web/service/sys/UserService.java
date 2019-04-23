package cn.luozc.assemble_web.service.sys;

import cn.luozc.assemble_web.bean.sys.SysRole;
import cn.luozc.assemble_web.bean.sys.SysRoleUser;
import cn.luozc.assemble_web.bean.sys.SysUser;
import cn.luozc.assemble_web.dao.sys.SysUserDao;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import cn.luozc.assemble_web.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@IocBean
public class UserService {

    @Inject
    private SysUserDao sysUserDao;

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
        List<SysUser> sysUsers = sysUserDao.getList(pageNumber, pageSize, value, list);
        for (int i = 0; i < sysUsers.size(); i++) {
            sysUsers.get(i).setRoles(sysUserDao.getRolesLinks(sysUsers.get(i)).getRoles());
        }
        return LayuiTableResult.result(0,"",sysUserDao.size(value,list),sysUsers);
    }

    /**
     * 添加用户信息
     * @param sysUser   用户信息
     * @return          插入的数据
     */
    public JsonData add(SysUser sysUser,String roleId){
        if(sysUserDao.getSysUserByUname(sysUser.getUname())!=null){
            return JsonData.fail("账号已被注册");
        }
        if(sysUserDao.getSysUserByPhone(sysUser.getPhone())!=null){
            return JsonData.fail("手机号已被注册");
        }
        sysUser.setCreateTime(new Date());
        sysUser.setPassword(MD5Util.getMD5Str("123456"));
        sysUser.setId(UUID.randomUUID().toString());
        SysUser s = sysUserDao.add(sysUser);

        if(s==null){
            return JsonData.fail("添加失败");
        }
        //添加用户角色
        addUserRole(roleId, s);
        return JsonData.success(s,"添加成功");
    }

    private void addUserRole(String roleIds, SysUser sysUser) {
        if(StringUtils.isNotEmpty(roleIds)){
            String[] roleIdArr = roleIds.split(",");
            List<SysRoleUser> sysRoleUsers = new ArrayList<>();
            for (String rid:roleIdArr) {
                sysRoleUsers.add(new SysRoleUser(UUID.randomUUID().toString(),rid,sysUser.getId()));
            }
            sysUser = sysUserDao.getUserRoleLinks(sysUser);
            sysUserDao.delUserRoleBySysUser(sysUser);
            sysUser.setSysRoleUsers(sysRoleUsers);
            sysUserDao.addUserRole(sysUser);

        }
    }

    /**
     * 修改用户信息
     * @param sysUser   用户信息
     * @return          插入的数据
     */
    public JsonData edit(SysUser sysUser,String roleId){
        SysUser s = sysUserDao.getDataById(sysUser.getId());
        if(s==null){
            return JsonData.fail("改用户不存在");
        }
        if(sysUserDao.getSysUserByPhoneNotId(sysUser.getPhone(),s.getId())!=null){
            return JsonData.fail("手机号已存在");
        }
        s.setEmail(sysUser.getEmail());
        s.setNickname(sysUser.getNickname());
        s.setPhone(sysUser.getPhone());
        int update = sysUserDao.update(s);
        if(update==0){
            return JsonData.fail("修改失败");
        }
        //添加用户角色
        addUserRole(roleId, s);
        return JsonData.success(s,"修改成功");
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public JsonData del(String id){
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser = sysUserDao.getUserRoleLinks(sysUser);
        return JsonData.success(sysUserDao.delete(sysUser),"删除成功");
    }

    public JsonData enable(int enable,String id){
        SysUser sysUser = sysUserDao.getDataById(id);
        sysUser.setEnable(enable);
        return JsonData.success(sysUserDao.update(sysUser),"修改成功");
    }

    public JsonData addUserRole(SysRoleUser sysRoleUser){
        return JsonData.success();
    }


    public JsonData login(String uname, String password, HttpSession session){
        SysUser sysUser = sysUserDao.getSysUserByUname(uname);
        if(sysUser==null||!sysUser.getPassword().equals(MD5Util.getMD5Str(password))){
            return JsonData.fail("账号或密码错误");
        }
        session.setAttribute("user",sysUser);
        session.setAttribute("userId",sysUser.getId());
        sysUser = sysUserDao.getRolesLinks(sysUser);
        List<SysRole> roles = sysUser.getRoles();
        List<String> rids = new ArrayList<>();
        for (SysRole sysRole:roles) {
            rids.add(sysRole.getId());
        }
        session.setAttribute("rids", StringUtils.join(rids,","));
        return JsonData.success();
    }
}
