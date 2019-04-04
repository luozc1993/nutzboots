package cn.luozc.etu_web.bean.sys;

import org.nutz.dao.entity.annotation.*;

import java.util.List;

@Table("sys_role_user") //映射的表名称
public class SysRoleUser {

    @Column("rid")
    private String rid;
    @Column("uid")
    private String uid;



    @ManyMany(relation = "sys_role_user",
            from = "rid",
            to = "uid")

    public List<SysRole> role;
}