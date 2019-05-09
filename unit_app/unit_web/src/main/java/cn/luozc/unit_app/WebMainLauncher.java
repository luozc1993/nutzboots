package cn.luozc.unit_app;

import cn.luozc.unit_app.utils.ShellUtil;
import cn.luozc.unit_app.utils.cmd.CommandResult;
import cn.luozc.unit_app.utils.cmd.CommandUtils;
import com.sun.javaws.Globals;
import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.integration.jedis.RedisService;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import static org.nutz.integration.jedis.RedisInterceptor.jedis;

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

    @At({"/","index"})
    @Ok("beetl:/index.html")
    public void index(){}




    public void init() {
        //windows启动zookeeper
        if ( System.getProperty("os.name").startsWith("Windows")) {
            CommandUtils.exec("E:\\nutz\\luozc\\assets\\zookeeper-3.4.10\\bin\\zkServer.cmd");
        }



    }

    public void depose() {}

    public static void main(String[] args) throws Exception {
        NbApp nb = new NbApp().setArgs(args).setPrintProcDoc(true);
        nb.run();
    }

}
