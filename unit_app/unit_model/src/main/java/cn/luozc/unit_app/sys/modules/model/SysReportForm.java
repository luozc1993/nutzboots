package cn.luozc.unit_app.sys.modules.model;


import cn.luozc.unit_framework.base.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.Date;

@Table("sys_report_form")
@Getter
@Setter
@ToString
@Comment("报表字段")
public class SysReportForm extends BaseModel implements Serializable {

    @Column("report_id")
    @Comment("报表id")
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String reportId;
    @Column
    @Comment("字段key")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String field;
    @Column
    @Comment("字段名称")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String title;
    @Column
    @Comment("字段最小长度")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String minWidth;
    @Column
    @Comment("字段固定")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String fixed;
    @Column
    @Comment("是否隐藏")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String hide;
    @Column("order_by")
    @Comment("排序")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private int orderBy;


}
