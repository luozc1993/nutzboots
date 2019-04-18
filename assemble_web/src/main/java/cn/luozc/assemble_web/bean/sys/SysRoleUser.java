package cn.luozc.assemble_web.bean.sys;

import org.nutz.dao.entity.annotation.*;

@Table("sys_role_user") //映射的表名称
@Comment("角色用户关联表")
public class SysRoleUser {

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