package cn.luozc.oa.module.system.model;

import cn.luozc.oa.commom.bean.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table("sys_user_role")
public class SysUserRole extends BaseModel implements Serializable {

    @Column
    @Comment("用户id")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String uid;

    @Column
    @Comment("角色id")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String rid;

    public SysUserRole(){}

    public SysUserRole(String uid,String rid){
        this.uid = uid;
        this.rid = rid;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
