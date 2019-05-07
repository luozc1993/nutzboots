package cn.luozc.unit_app.sys.modules.model;

import cn.luozc.unit_framework.base.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table("sys_department_user") //映射的表名称
@Comment("部门用户关联表")
@Setter
@Getter
public class SysDepartmentUser extends BaseModel implements Serializable {


    @Column("did")
    @Comment("角色id")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String did;
    @Column("uid")
    @Comment("用户id")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String uid;

    public SysDepartmentUser(String did, String uid){
        this.did = did;
        this.uid = uid;
    }

    public SysDepartmentUser(){

    }

}