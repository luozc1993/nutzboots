package cn.luozc.oa.module.system.model;

import cn.luozc.oa.commom.bean.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table("sys_user")
public class SysRole extends BaseModel implements Serializable {

    @Column("role_name")
    @Comment("介绍名称")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String roleName;

    @Column
    @Comment("备注说明")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String comments;



    @Column
    @Comment("是否删除")
    @ColDefine(type = ColType.BOOLEAN, width = 255)
    private Boolean isDelete;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
