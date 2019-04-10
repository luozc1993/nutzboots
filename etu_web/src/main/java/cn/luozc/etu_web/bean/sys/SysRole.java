package cn.luozc.etu_web.bean.sys;


import org.nutz.dao.entity.annotation.*;

import java.util.Date;
import java.util.List;

@Table("sys_role") //映射的表名称
public class SysRole {

    @Name
    private String id;

    @Column
    private String name;

    @Column
    private String remarks;

    @ManyMany(relation = "sys_role_user",
            from = "rid:id",
            to = "uid")
    public List<SysUser> users;

    @ManyMany(relation = "sys_role_menu",
            from = "rid:id",
            to = "mid")
    public List<SysMenu> menus;

    @Many(field = "rid")
    private List<SysRoleUser> sysRoleUsers;


    public SysRole(){

    }
    public SysRole(String id){
        this.id = id;
    }



    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }

    public List<SysRoleUser> getSysRoleUsers() {
        return sysRoleUsers;
    }

    public void setSysRoleUsers(List<SysRoleUser> sysRoleUsers) {
        this.sysRoleUsers = sysRoleUsers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return "SysRole{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", remarks='" + remarks + '\'' +
                ", users=" + users +
                ", menus=" + menus +
                ", sysRoleUsers=" + sysRoleUsers +
                '}';
    }
}

