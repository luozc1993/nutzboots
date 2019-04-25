package cn.luozc.unit_app.sys.modules.service;


import cn.luozc.unit_framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.List;

@Table("sys_user") //映射的表名称
@Comment("用户表")
@TableIndexes({@Index(name = "INDEX_SYS_USER_UNAME", fields = {"uname"}, unique = true)})
public class SysUser extends BaseModel implements Serializable {


    @Column
    @Comment("账号")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    
    private String uname;

    @Column
    @Comment("密码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    
    private String password;

    @Column
    @Comment("昵称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    
    private String nickname;

    @Column
    @Comment("头像")
    
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String avatar;

    @Column
    @Comment("是否在线")
    
    @ColDefine(type = ColType.BOOLEAN)
    private boolean online;


    @Column
    @Comment("电子邮箱")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    
    private String email;

    @Column
    @Comment("手机号码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    
    private String phone;

    @Column
    @Comment("登陆时间")
    
    private Long loginTime;

    @Column
    @Comment("登陆IP")
    
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String loginIp;

    @Column
    @Comment("登陆次数")
    
    @ColDefine(type = ColType.INT)
    private Integer loginCount;

    @Column
    @Comment("登陆SessionId")
    
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String loginSessionId;

    @Column
    @Comment("皮肤样式")
    
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String theme;

    @Column
    @Comment("是否启用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean enable;

    @Comment("角色列表")
    @ManyMany(relation = "sys_role_user",from = "uid:id",to = "rid")
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

    public SysUser(){}
    public SysUser(String uname,String password,String nickname){
        this.uname = uname;
        this.password = password;
        this.nickname = nickname;
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


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getLoginSessionId() {
        return loginSessionId;
    }

    public void setLoginSessionId(String loginSessionId) {
        this.loginSessionId = loginSessionId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}

