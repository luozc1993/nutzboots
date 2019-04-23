package cn.luozc.unit_app.sys.modules.models;

import cn.luozc.unit_framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

@Table("sys_role_user") //映射的表名称
@Comment("角色用户关联表")
public class SysRoleUser extends BaseModel implements Serializable {

    @Name
    private String id;

    @Column("rid")
    @Comment("角色id")
    private String rid;
    @Column("uid")
    @Comment("用户id")
    private String uid;

    public SysRoleUser(String id,String rid,String uid){
        this.rid = rid;
        this.uid = uid;
        this.id = id;
    }

    public SysRoleUser(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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