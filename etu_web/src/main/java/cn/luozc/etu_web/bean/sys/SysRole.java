package cn.luozc.etu_web.bean.sys;


import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

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

    @Override
    public String toString() {
        return "SysRole{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", remarks='" + remarks + '\'' +
                ", users=" + users +
                '}';
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }

}

