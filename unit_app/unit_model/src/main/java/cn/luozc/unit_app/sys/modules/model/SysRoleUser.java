package cn.luozc.unit_app.sys.modules.model;

import cn.luozc.unit_framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table("sys_role_user") //映射的表名称
@Comment("角色用户关联表")
public class SysRoleUser extends BaseModel implements Serializable {


    @Column("rid")
    @Comment("角色id")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String rid;
    @Column("uid")
    @Comment("用户id")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String uid;

    public SysRoleUser(String rid,String uid){
        this.rid = rid;
        this.uid = uid;
    }

    public SysRoleUser(){

    }


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}