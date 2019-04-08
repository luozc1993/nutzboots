package cn.luozc.etu_web.bean.sys;

import org.nutz.dao.entity.annotation.*;

import java.util.Date;
import java.util.List;

@Table("sys_menu")
public class SysMenu {

  @Name
  private String id;
  @Column
  private String name;
  @Column
  private String url;
  @Column("parent_id")
  private String parentId;
  @Column
  private String icon;
  @Column("is_menu")
  private long isMenu;
  @Column
  private long sort;
  @Column("create_time")
  private Date createTime;
  @Column("update_time")
  private Date updateTime;

    @Many(field = "mid")
    private List<SysRoleMenu> sysRoleMenus;


    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public List<SysRoleMenu> getSysRoleMenus() {
        return sysRoleMenus;
    }

    public void setSysRoleMenus(List<SysRoleMenu> sysRoleMenus) {
        this.sysRoleMenus = sysRoleMenus;
    }

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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public long getIsMenu() {
    return isMenu;
  }

  public void setIsMenu(long isMenu) {
    this.isMenu = isMenu;
  }

  public long getOrder() {
    return sort;
  }

  public void setOrder(long order) {
    this.sort = order;
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


  @Override
  public String toString() {
    return "SysMenu{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", url='" + url + '\'' +
            ", parentId='" + parentId + '\'' +
            ", icon='" + icon + '\'' +
            ", isMenu=" + isMenu +
            ", order=" + sort +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            '}';
  }
}
