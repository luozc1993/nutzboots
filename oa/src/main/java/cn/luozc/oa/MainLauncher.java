package cn.luozc.oa;

import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.*;
import org.nutz.mvc.annotation.*;

@IocBean(create="init", depose="depose")
@Modules(packages = "cn.luozc.oa.module")
@Encoding(input = "UTF-8", output = "UTF-8")
@ChainBy(args = "chain/nutzwk-mvc-chain.js")
@Localization(value = "locales/", defaultLocalizationKey = "zh_CN")
@IocBy(args={
        "*slog" // 自定义aop注解加载器
})
public class MainLauncher {
    @Inject
    private Dao dao;
    
    @Inject
    protected PropertiesProxy conf;
    @At("/")
    @Ok("->:/index.html")
    public void index() {}
    
    public void init() {
        Daos.createTablesInPackage(dao, "cn.luozc.oa.module", false);
        Daos.migration(dao, "cn.luozc.oa.module",  true, false, false);
        // NB自身初始化完成后会调用这个方法
    }
    public void depose() {}

    public static void main(String[] args) throws Exception {
        new NbApp().setArgs(args).setPrintProcDoc(true).run();
    }

}
