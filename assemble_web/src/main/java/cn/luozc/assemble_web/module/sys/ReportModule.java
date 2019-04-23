package cn.luozc.assemble_web.module.sys;

import cn.luozc.assemble_web.bean.sys.SysMenu;
import cn.luozc.assemble_web.bean.sys.SysReport;
import cn.luozc.assemble_web.bean.sys.SysReportBtn;
import cn.luozc.assemble_web.bean.sys.SysReportForm;
import cn.luozc.assemble_web.module.BaseModule;
import cn.luozc.assemble_web.service.BaseService;
import cn.luozc.assemble_web.service.sys.MenuService;
import cn.luozc.assemble_web.service.sys.ReportService;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@At("/sys/report")
@Ok("json:full")
@IocBean
public class ReportModule extends BaseModule {
    @Inject private BaseService baseService;
    @Inject private MenuService menuService;
    @Inject private ReportService reportService;
    @Inject private Dao dao;
    @At("/list")
    public LayuiTableResult list(HttpServletRequest request){
        int page = Integer.valueOf(request.getParameter("page"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        String fid = request.getParameter("fid");
        String value = request.getParameter("value");
        List<String> searchField = new ArrayList<>();
        searchField.add("report_name");
        SysReport sysReport = reportService.getSysReportById(fid);
        if(sysReport==null){
            return LayuiTableResult.result(1,"数据错误",0,new ArrayList<>());
        }
        return baseService.getList(sysReport.getTableName(),page,limit,value,searchField);
    }

    @At
    public JsonData fields(String mid) {
        SysMenu menu = menuService.getMenuById(mid);
        reportService.getSysReportFormsByPid(menu.getFid());
        return null;
    }



    @At
    @Ok("beetl:/report/${obj.fid}.html")
    public JSONObject page(String fid){
        JSONObject json = new JSONObject();
        json.put("fid",fid);
        return json;
    }

    @At("/form_list")
    public LayuiTableResult form_list(String reportId){
        List<SysReportForm> sysReportForms = reportService.getSysReportFormsByPid(reportId);
        return LayuiTableResult.result(0,"",sysReportForms.size(),sysReportForms);
    }

    @At
    public JsonData createReport(String reportId,HttpServletRequest request){


        String html = getReportHtml().toString();

        SysReport sysReport = reportService.getSysReportById(reportId);
        html = html.replace("${title}",sysReport.getReportName());
        List<SysReportBtn> reportBtns = reportService.getReportBtnListByPid(sysReport.getId());
        String topBtnHtml = "";
        for (SysReportBtn btn:reportBtns) {
            topBtnHtml += "<button class='layui-btn layui-btn-sm toolbarBtn' data-type=\""+btn.getType()+"\" data-jump=\""+btn.getJump()+"\" data-title=\""+btn.getTitle()+"\" data-id=\""+btn.getDataId()+"\">"+btn.getTitle()+"</button>";
        }
        html = html.replace("${topBtn}",topBtnHtml);

        try {
            String path = this.getClass().getResource("/").getPath() + "template/report/" + sysReport.getId() + ".html";
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(html);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return JsonData.success(html);
    }

    /**
     * 获取模板文件内容
     * @return
     */
    private StringBuffer getReportHtml() {
        StringBuffer html = new StringBuffer();
        BufferedReader br = null;
        try {
            String path = this.getClass().getResource("/").getPath() + "report.html";
            File file = new File(path);
            br = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str=br.readLine())!=null){
                html.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return html;
    }

}
