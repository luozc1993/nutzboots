package cn.luozc.oa.commom.bean;

import cn.luozc.oa.commom.utils.JwtTokenUtil;
import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.mvc.Mvcs;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @ColDefine(type = ColType.VARCHAR, width = 36)
    @Prev(els = {@EL("uuid()")})
    @Comment("id")
    private String id;


    @Column
    @Comment("操作人")
    @Prev(els = @EL("$me.uid()"))
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String opUser;

    @Column("create_time")
    @Comment("创建时间")
    @Prev(els = @EL("$me.now()"))
    private Long createTime;

    @Column("update_time")
    @Comment("操作时间")
    @Prev(els = @EL("$me.now()"))
    private Long updateTime;

    @Override
    public String toString() {
        return String.format("/*%s*/%s", super.toString(), Json.toJson(this, JsonFormat.compact()));
    }

    public Boolean flag() {
        return false;
    }

    public Long now() {
        return Times.getTS();
    }

    public String uid() {
        String uid = getOpUser();
        if (Strings.isNotBlank(uid)) {
            return uid;
        }
        try {
            HttpServletRequest request = Mvcs.getReq();
            if (request != null) {
                return Strings.sNull(JwtTokenUtil.getUserId(request.getParameter("token")));
            }
        } catch (Exception e) {
        }
        return "";
    }


    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long opDate) {
        this.createTime = opDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
