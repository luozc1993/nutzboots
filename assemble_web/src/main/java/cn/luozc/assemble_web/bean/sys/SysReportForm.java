package cn.luozc.assemble_web.bean.sys;


import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;
@Table("sys_report_form")
public class SysReportForm {

  @Name
  private String id;
  @Column
  private String pid;
  @Column
  private String field;
  @Column
  private String title;
  @Column
  private String minWidth;
  @Column
  private String fixed;
  @Column
  private String hide;
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


  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getMinWidth() {
    return minWidth;
  }

  public void setMinWidth(String minWidth) {
    this.minWidth = minWidth;
  }


  public String getFixed() {
    return fixed;
  }

  public void setFixed(String fixed) {
    this.fixed = fixed;
  }


  public String getHide() {
    return hide;
  }

  public void setHide(String hide) {
    this.hide = hide;
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
