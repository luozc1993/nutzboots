package cn.luozc.unit_app.sys.modules.model;

import cn.luozc.unit_framework.base.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.Date;

@Table("sys_report_btn")
@Getter
@Setter
@ToString
@Comment("报表按钮")
public class SysReportBtn extends BaseModel implements Serializable {

  @Column("report_id")
  @Comment("报表id")
  @ColDefine(type = ColType.VARCHAR, width = 36)
  private String reportId;
  @Column
  @Comment("类型")
  @ColDefine(type = ColType.VARCHAR, width = 255)
  private String type;
  @Column
  @Comment("名称")
  @ColDefine(type = ColType.VARCHAR, width = 255)
  private String title;
  @Column("data_id")
  @Comment("数据id")
  @ColDefine(type = ColType.VARCHAR, width = 255)
  private String dataId;
  @Column
  @Comment("跳转类型")
  @ColDefine(type = ColType.VARCHAR, width = 255)
  private String jump;



}
