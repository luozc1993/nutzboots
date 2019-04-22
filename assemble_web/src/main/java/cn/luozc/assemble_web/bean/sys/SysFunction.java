package cn.luozc.assemble_web.bean.sys;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("sys_function")
public class SysFunction {

  @Name
  private String id;
  @Column
  private String name;
  @Column("function_name")
  private String functionName;
  @Column("function_key")
  private String functionKey;
  @Column("create_time")
  private Date createTime;
  @Column("update_name")
  private Date updateTime;


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


  public String getFunctionName() {
    return functionName;
  }

  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }


  public String getFunctionKey() {
    return functionKey;
  }

  public void setFunctionKey(String functionKey) {
    this.functionKey = functionKey;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}
