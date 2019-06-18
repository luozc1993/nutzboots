package cn.luozc.oa.module.system.model;

import cn.luozc.oa.commom.bean.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table("sys_user")
public class SysUser extends BaseModel implements Serializable {

    @Column
    @Comment("账号")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String username;

    @Column
    @Comment("密码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String password;


    @Column
    @Comment("密码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String nickName;

    @Column
    @Comment("头像")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String avatar;

    @Column
    @Comment("性别")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String sex;

    @Column
    @Comment("手机号")
    @ColDefine(type = ColType.VARCHAR, width = 11)
    private String phone;

    @Column
    @Comment("邮箱")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String email;

    @Column
    @Comment("邮箱验证")
    @ColDefine(type = ColType.BOOLEAN)
    private Boolean emailVerified;

    @Column
    @Comment("用户状态")
    @ColDefine(type = ColType.CHAR, width = 1)
    @Default("0")
    private int state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
