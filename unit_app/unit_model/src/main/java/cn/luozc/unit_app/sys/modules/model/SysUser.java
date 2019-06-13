package cn.luozc.unit_app.sys.modules.model;


import cn.luozc.unit_framework.base.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.List;

@Table("sys_user") //映射的表名称
@Comment("用户表")
@TableIndexes({@Index(name = "INDEX_SYS_USER_UNAME", fields = {"uname"}, unique = true)})
@Setter
@Getter
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
    @Comment("登陆次数")
    @ColDefine(type = ColType.INT)
    private Integer loginCount;



    @Column
    @Comment("是否启用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean enable;

    @Column
    @Comment("公司ID")
    @Default("0")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String company;


    @Comment("角色列表")
    @ManyMany(relation = "sys_role_user",from = "uid:id",to = "rid")
    public List<SysRole> roles;

    @Comment("部门列表")
    @ManyMany(relation = "sys_department_user",from = "uid:id",to = "did")
    public List<SysDepartment> departments;

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

}

