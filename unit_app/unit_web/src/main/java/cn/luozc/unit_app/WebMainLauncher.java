package cn.luozc.unit_app;

import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.ChainBy;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Modules;

@IocBean(create="init", depose="depose")
@Modules(packages = "cn.luozc")
@Encoding(input = "UTF-8", output = "UTF-8")
@ChainBy(args = "chain/nutzwk-mvc-chain.json")
public class WebMainLauncher {
    
    @Inject
    protected PropertiesProxy conf;

    @Inject
    protected Dao dao;

    
    public void init() {
        //通过POJO类创建表结构
        try {
            Daos.createTablesInPackage(dao, "cn.luozc.unit_app.sys", false);
            //通过POJO类修改表结构
            //Daos.migration(dao, "cn.wizzer.app.cms", true, false);
        } catch (Exception e) {
        }

    }

    public void depose() {}

    public static void main(String[] args) throws Exception {
        new NbApp().setArgs(args).setPrintProcDoc(true).run();
    }

}
