package cn.luozc.etu_web.service.sys;

import cn.luozc.etu_web.bean.sys.SysMenu;
import cn.luozc.etu_web.dao.sys.SysMenuDao;
import cn.luozc.etu_web.util.JsonData;
import cn.luozc.etu_web.util.LayuiTableResult;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;

@IocBean
public class MenuService {

    @Inject
    private SysMenuDao sysMenuDao;

    /**
     * 获取用户列表数据 模糊分页查询
     * @param pageNumber
     * @param pageSize
     * @param value
     * @return
     */
    public LayuiTableResult getList(int pageNumber,int pageSize,String value){
        List<String> list = new ArrayList<>();
        list.add("name");
        list.add("remarks");
        List<SysMenu> menus = sysMenuDao.getList(pageNumber, pageSize, value, list);
        return LayuiTableResult.result(0,"",sysMenuDao.size(value,list),menus);
    }


    public JsonData add(SysMenu sysMenu){
        if(StringUtils.isEmpty(sysMenu.getParentId())){
            sysMenu.setParentId("0");
        }
        sysMenuDao.add(sysMenu);
        return JsonData.success();
    }


}
