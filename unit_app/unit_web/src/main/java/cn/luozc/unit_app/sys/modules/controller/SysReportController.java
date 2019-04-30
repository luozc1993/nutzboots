package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.*;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@IocBean
@At("/sys/report")
@Ok("json:full")
public class SysReportController {

    @Inject
    @Reference
    private SysReportService sysReportService;
    @Inject
    @Reference
    private SysReportBtnService sysReportBtnService;
    @Inject
    @Reference
    private SysReportFormService sysReportFormService;


    @At
    @Ok("beetl:/report/${obj.fid}.html")
    public JSONObject page(String fid){
        JSONObject json = new JSONObject();
        json.put("fid",fid);
        return json;
    }

    @At
    public JsonData createReport(String id){


        String html = getReportHtml().toString();

        SysReport sysReport = sysReportService.fetch(id);
        html = html.replace("${title}",sysReport.getReportName());
        List<SysReportBtn> reportBtns = sysReportBtnService.query(Cnd.where("report_id","=", id));
        String topBtnHtml = "";
        for (SysReportBtn btn:reportBtns) {
            topBtnHtml += "<button class='layui-btn layui-btn-sm toolbarBtn' data-type=\""+btn.getType()+"\" data-jump=\""+btn.getJump()+"\" data-title=\""+btn.getTitle()+"\" data-id=\""+btn.getDataId()+"\">"+btn.getTitle()+"</button>";
        }
        html = html.replace("${topBtn}",topBtnHtml);

        List<SysReportForm> reportForms = sysReportFormService.query(Cnd.where("report_id", "=", id).orderBy("order_by","asc"));
        JSONArray array = JSONArray.fromObject(reportForms);
        String col = array.toString();
        html = html.replace("${tableCol}",col);

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
            Chain create = Chain.make("create_html", 1);
            int update = sysReportService.update(create,Cnd.where("id","=",sysReport.getId()));
            System.err.println(update);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return JsonData.success("生成成功");
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


    @At("/report_list")
    public LayuiTableResult report_list(int page,int limit,String fid,String value){
        SysReport sysReport = sysReportService.fetch(fid);
        if(sysReport==null){
            return LayuiTableResult.result(1,"数据错误",0,new ArrayList<>());
        }
        Criteria criteria = sysReportService.getVagueCriteria(value, "report_name");
        int count = sysReportService.count(sysReport.getTableName(), criteria);
        Pagination pagination = sysReportService.listPage(page, limit, sysReport.getTableName(), criteria);
        return LayuiTableResult.result(0,"",count,pagination.getList());
    }




    /**
     * 删除数据
     * @param id    id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        int delete = sysReportService.delete(id);
        if(delete>0){
            return JsonData.success("删除成功");
        }
        return JsonData.fail("删除失败");
    }

    /**
     * 编辑
     * @param sysReport       信息
     * @return              JsonData
     */
    @At
    public JsonData edit(SysReport sysReport){
        sysReport.setCreateHtml(false);
        int update = sysReportService.update(sysReport);
        if(update>0){
            return JsonData.success("修改成功");
        }
        return JsonData.fail("修改失败");
    }
    /**
     * 添加数据
     * @param sysReport       信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysReport sysReport){
        SysReport insert = sysReportService.insert(sysReport);
        if(insert!=null){
            return JsonData.success("添加成功");
        }
        return JsonData.fail("添加失败");
    }

    /**
     * 获取列表
     * @param page      页数
     * @param limit     每页显示数量
     * @param value     搜索框值
     * @return          LayuiTableResult
     */
    @At
    public LayuiTableResult list(int page, int limit, String value){
        Criteria criteria = sysReportService.getVagueCriteria(value, "reportName");
        Pagination listPage = sysReportService.listPageLinks(page, limit,criteria,"sysMenu");
        return LayuiTableResult.result(0,"",sysReportService.count(criteria),listPage.getList());
    }

    /**
     * 获取已创建页面列表列表
     * @param page      页数
     * @param limit     每页显示数量
     * @param value     搜索框值
     * @return          LayuiTableResult
     */
    @At
    public LayuiTableResult listCreate(int page, int limit, String value){
        Criteria criteria = sysReportService.getVagueCriteria(value, "reportName");
        criteria.where().and("create_html","=",1);
        Pagination listPage = sysReportService.listPageLinks(page, limit,criteria,"sysMenu");
        return LayuiTableResult.result(0,"",sysReportService.count(criteria),listPage.getList());
    }






}
