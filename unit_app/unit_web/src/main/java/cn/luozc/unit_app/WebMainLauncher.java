package cn.luozc.unit_app;

import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

@IocBean(create="init", depose="depose")
@Modules(packages = "cn.luozc.unit_app.modules")
@Encoding(input = "UTF-8", output = "UTF-8")
@ChainBy(args = "chain/nutzwk-mvc-chain.js")
@Localization(value = "locales/", defaultLocalizationKey = "zh_CN")
public class WebMainLauncher {
    @Inject("refer:$ioc")
    private Ioc ioc;
    @Inject
    protected PropertiesProxy conf;

    @Inject
    protected Dao dao;

    @At("/")
    @Ok(">>:/index.html")
    public void index(){}
    
    public void init() {


    }

    public void depose() {}

    public static void main(String[] args) throws Exception {
        NbApp nb = new NbApp().setArgs(args).setPrintProcDoc(true);
        nb.run();
    }

}
