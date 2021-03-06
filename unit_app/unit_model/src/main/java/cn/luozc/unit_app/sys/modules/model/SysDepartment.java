package cn.luozc.unit_app.sys.modules.model;

import cn.luozc.unit_framework.base.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table
@Comment("公司信息")
@Getter
@Setter
@ToString
public class SysDepartment extends BaseModel implements Serializable {

    @Column("name")
    @Comment("部门名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;

    @Column("parent_id")
    @Comment("上级id")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String parentId;

    @Column("company_id")
    @Comment("公司id")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String companyId;

    @Column("leader")
    @Comment("负责人")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String leader;

    @Column("introduce")
    @Comment("部门介绍")
    @ColDefine(type = ColType.TEXT, width = 2000)
    private String introduce;

    @Column("sort")
    @Comment("排序")
    @ColDefine(type = ColType.INT)
    private int sort;

    @One(field = "parentId")
    private SysDepartment parent;
}
