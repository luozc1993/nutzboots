package cn.luozc.assemble_web.service.sys;

import cn.luozc.assemble_web.bean.sys.SysReport;
import cn.luozc.assemble_web.bean.sys.SysReportBtn;
import cn.luozc.assemble_web.bean.sys.SysReportForm;
import cn.luozc.assemble_web.dao.sys.SysReportDao;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class ReportService {

    @Inject
    private SysReportDao sysReportDao;

    @Inject private Dao dao;


    public SysReport getSysReportById(String id){
        return sysReportDao.getDataById(id);
    }

    /**
     * 获取表单表列数据
     * @param pid
     * @return
     */
    public List<SysReportForm> getSysReportFormsByPid(String pid){
        return dao.query(SysReportForm.class, Cnd.where("pid","=",pid));
    }

    /**
     * 获取报表头部按钮
     * @param pid
     * @return
     */
    public List<SysReportBtn> getReportBtnListByPid(String pid){
        return dao.query(SysReportBtn.class,Cnd.where("pid","=",pid));
    }

}
