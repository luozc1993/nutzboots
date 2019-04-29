package cn.luozc.unit_app.sys.modules.model;

import cn.luozc.unit_framework.base.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
@Table
@Comment("公司信息")
@Getter
@Setter
@ToString
public class SysCompany extends BaseModel implements Serializable {

    @Column("company_name")
    @Comment("公司名称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String companyName;

    @Column("abbreviation")
    @Comment("公司简称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String abbreviation;

    @Column("code")
    @Comment("公司编码")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String code;

    @Column("address")
    @Comment("公司地址")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String address;

    @Column("telephone")
    @Comment("联系电话")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String telephone;

    @Column("email")
    @Comment("电子邮箱")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String email;

    @Column("website")
    @Comment("公司网站")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String website;

    @Column("introduce")
    @Comment("公司介绍")
    @ColDefine(type = ColType.TEXT, width = 2000)
    private String introduce;
}
