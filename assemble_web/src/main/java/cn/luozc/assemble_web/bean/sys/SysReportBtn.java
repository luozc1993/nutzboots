package cn.luozc.assemble_web.bean.sys;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("sys_report_btn")
public class SysReportBtn {

  @Name
  private String id;
  @Column
  private String pid;
  @Column
  private String type;
  @Column
  private String title;
  @Column("data_id")
  private String dataId;
  @Column
  private String jump;
  @Column("create_time")
  @Comment("创建时间")
  private Date createTime;
  @Column("update_time")
  @Comment("修改时间")
  private Date updateTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getJump() {
    return jump;
  }

  public void setJump(String jump) {
    this.jump = jump;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDataId() {
    return dataId;
  }

  public void setDataId(String dataId) {
    this.dataId = dataId;
  }
}
