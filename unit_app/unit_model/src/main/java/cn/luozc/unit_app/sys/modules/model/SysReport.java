package cn.luozc.unit_app.sys.modules.model;


import cn.luozc.unit_framework.base.model.BaseModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.Date;

@Table("sys_report")
@Comment("报表管理")
@Getter
@Setter
@ToString
public class SysReport extends BaseModel implements Serializable {

    @Column("report_name")
    @Comment("报表名称名称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String reportName;

    @Column("table_name")
    @Comment("报表对应表名")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String tableName;

    @One(field = "id", key = "fid")
    private SysMenu sysMenu;

    @Column("create_html")
    @Comment("是否生成过页面")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean createHtml;
}
