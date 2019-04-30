package cn.luozc.unit_app;

import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * Created by wizzer on 2018/3/17.
 */
@IocBean(create = "init", depose = "depose")
public class SysMainLauncher {
    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    public static void main(String[] args) throws Exception {
        new NbApp().run();
    }

    public void init() {

    }

    public void depose() {

    }
}
