package cn.luozc.unit_app.sys.modules.model;


import cn.luozc.unit_framework.base.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table
@Comment("数据管理")
@Getter
@Setter
@ToString
public class SysData extends BaseModel implements Serializable {

    @Column("data_name")
    @Comment("数据名称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String dataName;
    @Column("table_name")
    @Comment("数据表名称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String tableName;
    @Column("parent_id")
    @Comment("上级id")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String parentId;

}
