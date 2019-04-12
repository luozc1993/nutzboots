package cn.luozc.assemble_web.bean.sys;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("sys_role_menu")
public class SysRoleMenu {
    @Name
  private String id;
    @Column
  private String rid;
    @Column
  private String mid;

    public SysRoleMenu(){

    }
    public SysRoleMenu(String id,String rid,String mid){
        this.id = id;
        this.rid = rid;
        this.mid = mid;
    }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
