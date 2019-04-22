package cn.luozc.assemble_web.bean.sys;

import org.nutz.dao.entity.annotation.*;

import java.util.Date;
import java.util.List;

@Table("sys_menu")
@Comment("菜单表")
public class SysMenu {

  @Name
  private String id;
  @Column
  @Comment("菜单名称")
  private String name;
  @Column
  @Comment("菜单地址")
  private String url;
  @Column("parent_id")
  @Comment("上级菜单")
  private String parentId;
  @Column
  @Comment("菜单图标")
  private String icon;
  @Column("is_menu")
  @Comment("菜单类型")
  private String isMenu;
  @Column
  @Comment("排序")
  private long sort;
  @Column("create_time")
  @Comment("创建时间")
  private Date createTime;
  @Column("update_time")
  @Comment("修改时间")
  private Date updateTime;
  @Column
  private String type;
  @Column
  private String fid;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

  public String getFid() {
    return fid;
  }

  public void setFid(String fid) {
    this.fid = fid;
  }

  public SysMenu getParent() {
        return parent;
    }

    public void setParent(SysMenu parent) {
        this.parent = parent;
    }

    @Comment("菜单关联角色")
    @ManyMany(relation = "sys_role_menu",
            from = "mid:id",
            to = "rid")
    public List<SysRole> roles;
    @Comment("菜单角色对着表")
    @Many(field = "mid")
    private List<SysRoleMenu> sysRoleMenus;
    @Comment("菜单上级对着表")
    @One(field = "parentId")
    public SysMenu parent;

    public SysMenu getSysMenu() {
        return parent;
    }

    public void setSysMenu(SysMenu parent) {
        this.parent = parent;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

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

  public String getIsMenu() {
    return isMenu;
  }

  public void setIsMenu(String isMenu) {
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
