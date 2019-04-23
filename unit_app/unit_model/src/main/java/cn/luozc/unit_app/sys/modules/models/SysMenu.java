package cn.luozc.unit_app.sys.modules.models;

import cn.luozc.unit_framework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table("sys_menu")
@Comment("菜单表")
@TableIndexes({@Index(name = "INDEX_SYS_MENU_PATH", fields = {"path"}, unique = true)})
public class SysMenu extends BaseModel implements Serializable {


    @Column
    @Comment("父级ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String parentId;

    @Column
    @Comment("树路径")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String path;

    @Column
    @Comment("菜单名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;

    @Column
    @Comment("资源类型")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String type;

    @Column
    @Comment("资源id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String fid;

    @Column
    @Comment("菜单链接")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String url;

    @Column
    @Comment("菜单图标")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String icon;

    @Column
    @Comment("是否显示")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean showit;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    @Column
    @Comment("菜单介绍")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;

    @Column
    @Comment("排序字段")
    @Prev({
            @SQL(db= DB.MYSQL,value = "SELECT IFNULL(MAX(sort),0)+1 FROM sys_menu"),
            @SQL(db= DB.ORACLE,value = "SELECT COALESCE(MAX(sort),0)+1 FROM sys_menu")
    })
  private long sort;



    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isShowit() {
        return showit;
    }

    public void setShowit(boolean show) {
        this.showit = show;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }
}
