package cn.luozc.unit_framework.base.model;

import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.mvc.Mvcs;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by wizzer on 2016/6/21.
 */
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @ColDefine(type = ColType.VARCHAR, width = 36)
    @Prev(els = {@EL("uuid()")})
    @Comment("ID")
    private String id;


    @Column
    @Comment("操作人")
    @Prev(els = @EL("$me.uid()"))
    @ColDefine(type = ColType.VARCHAR, width = 36)
    private String opUser;

    @Column
    @Comment("操作时间")
    @Prev(els = @EL("$me.now()"))
    private Long opDate;


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
                return Strings.sNull(request.getSession(true).getAttribute("userId"));
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

    public Long getOpDate() {
        return opDate;
    }

    public void setOpDate(Long opDate) {
        this.opDate = opDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
