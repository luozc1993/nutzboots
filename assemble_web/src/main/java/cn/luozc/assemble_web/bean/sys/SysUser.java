package cn.luozc.assemble_web.bean.sys;


import org.nutz.dao.entity.annotation.*;

import java.util.Date;
import java.util.List;

@Table("sys_user") //映射的表名称
@Comment("用户表")
public class SysUser {

    @Name
    private String id;

    @Column
    @Comment("账号")
    private String uname;

    @Column
    @Comment("昵称")
    private String nickname;

    @Column
    @Comment("密码")
    private String password;

    @Column
    @Comment("邮箱")
    private String email;

    @Column
    @Comment("手机号")
    private String phone;

    @Column("create_time")
    @Comment("创建时间")
    private Date createTime;

    @Column
    @Comment("是否启用")
    private int enable;

    @Comment("角色列表")
    @ManyMany(relation = "sys_role_user",
            from = "uid:id",
            to = "rid")
    public List<SysRole> roles;

    @Comment("角色用户关联表")
    @Many(field = "uid")
    private List<SysRoleUser> sysRoleUsers;


    public List<SysRoleUser> getSysRoleUsers() {
        return sysRoleUsers;
    }

    public void setSysRoleUsers(List<SysRoleUser> sysRoleUsers) {
        this.sysRoleUsers = sysRoleUsers;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", enable=" + enable +
                ", roles=" + roles +
                '}';
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

}

