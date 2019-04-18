package cn.luozc.assemble_web.bean.sys;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("sys_table")
@Comment("表格表")
public class SysTable {

  @Name
  private String id;
  @Column
  @Comment("名称")
  private String name;
  @Column
  @Comment("备注")
  private String remarks;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

}
