package cn.luozc.unit_app.sys.modules.model;

import cn.luozc.unit_framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table("sys_role_menu")
@Comment("角色菜单对照表")
public class SysRoleMenu extends BaseModel implements Serializable {
    @Column
    @Comment("角色id")
  private String rid;
    @Column
    @Comment("菜单id")
  private String mid;

    public SysRoleMenu(){

    }
    public SysRoleMenu(String rid,String mid){
        this.rid = rid;
        this.mid = mid;
    }

  public String getRid() {
    return rid;
  }

  public void setRid(String rid) {
    this.rid = rid;
  }


  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

}
