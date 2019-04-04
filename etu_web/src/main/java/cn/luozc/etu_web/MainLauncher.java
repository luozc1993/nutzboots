package cn.luozc.etu_web;

import cn.luozc.etu_web.bean.sys.SysRole;
import cn.luozc.etu_web.bean.sys.SysRoleUser;
import cn.luozc.etu_web.bean.sys.SysUser;
import cn.luozc.etu_web.dao.BaseDao;
import cn.luozc.etu_web.dao.sys.UserDao;
import org.apache.catalina.Globals;
import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.*;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@IocBean(create="init", depose="depose")
public class MainLauncher {
    
    @Inject
    protected PropertiesProxy conf;

    @Inject
    protected Dao dao;

    @Inject
    protected UserDao userDao;

    
    public void init() {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        execSql(path);
//        SysUser food = dao.fetch(SysUser.class, "e2460c47-dd91-478f-a096-087147ef3159");
//        System.err.println(food);
//        SysUser sysUser = dao.fetchLinks(food,"roles");
//        System.err.println(sysUser);
//
//        SysRole sysRole = dao.fetch(SysRole.class);
//        System.err.println(sysRole);
//        SysRole sysRoles = dao.fetchLinks(sysRole,"");
//        System.err.println(sysRoles);


        //SysUser fetch = userDao.getDataById("e2460c47-dd91-478f-a096-087147ef3159");


//        List<SysUser> list = userDao.getList(0,10,"");
//        System.err.println(list);
        // NB自身初始化完成后会调用这个方法
    }

    //执行sql语句
    private void execSql(String path) {
        String dbpath = path+ File.separator+"db/";
        //记录已更新的最新文件
        String execpath = path+File.separator+"db/version.txt";
        String version = readToString(execpath).trim();
        //sql文件所在目录
        File db = new File(dbpath);

        //判断是否为文件夹
        if(db.isDirectory()) {
            File[] listFiles = db.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    String name = pathname.getName();
                    return version.compareTo(name)<0&&!"version.txt".equals(name);
                }
            });

            for (File file : listFiles) {
                FileSqlManager fm = new FileSqlManager("db/"+file.getName());
                List<Sql> sqlList = fm.createCombo(fm.keys());

                try {
                    Sql[] sqls = sqlList.toArray(new Sql[sqlList.size()]);
                    dao.execute(sqlList.toArray(new Sql[sqlList.size()]));
                    File exec = new File(execpath);
                    PrintStream ps = new PrintStream(new FileOutputStream(exec));
                    ps.println(file.getName());// 往文件里写入字符串
                } catch (Exception e) {
                    System.err.println("升级文件["+file.getName()+"]执行失败");
                }

            }
        }

    }




    //读取文件内容
    public String readToString(String fileName) {
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support UTF-8");
            e.printStackTrace();
            return null;
        }
    }





    public void depose() {}

    public static void main(String[] args) throws Exception {
        new NbApp().setArgs(args).setPrintProcDoc(true).run();
    }

}
