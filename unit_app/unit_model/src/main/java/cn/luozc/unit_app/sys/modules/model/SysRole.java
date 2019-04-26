package cn.luozc.unit_app.sys.modules.model;


import cn.luozc.unit_framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.List;

@Table("sys_role") //映射的表名称
@Comment("角色表")
@TableIndexes({@Index(name = "INDEX_SYS_ROLE_CODE", fields = {"code"}, unique = true)})
public class SysRole extends BaseModel implements Serializable {

    @Column
    @Comment("角色名称")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String name;

    @Column
    @Comment("角色代码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String code;

    @Column
    @Comment("是否启用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    @Column
    @Comment("备注")
    private String remarks;

    @Comment("用户列表")
    @ManyMany(relation = "sys_role_user",from = "rid:id",to = "uid")
    public List<SysUser> users;

    @Comment("菜单列表")
    @ManyMany(relation = "sys_role_menu",
            from = "rid:id",
            to = "mid")
    public List<SysMenu> menus;


    public SysRole(){

    }
    public SysRole(String name){
        this.name = name;
    }



    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}

