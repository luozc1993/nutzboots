package cn.luozc.unit_app.sys.modules.model;

import cn.luozc.unit_framework.base.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.Date;

@Table("sys_function")
@Comment("函数管理")
@Getter
@Setter
@ToString
public class SysFunction extends BaseModel implements Serializable {

  @Column
  @Comment("函数名称")
  @ColDefine(type = ColType.VARCHAR, width = 255)
  private String name;
  @Column("function_name")
  @Comment("函数名")
  @ColDefine(type = ColType.VARCHAR, width = 255)
  private String functionName;
  @Column("function_key")
  @Comment("需要传入的字段")
  @ColDefine(type = ColType.VARCHAR, width = 255)
  private String functionKey;

}
