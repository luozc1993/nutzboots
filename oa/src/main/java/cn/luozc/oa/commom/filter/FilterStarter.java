package cn.luozc.oa.commom.filter;

import org.nutz.boot.starter.WebFilterFace;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@IocBean
public class FilterStarter implements WebFilterFace {

    @Inject
    PropertiesProxy conf;



    public String getName() {
        return "LOGIN";
    }


    public String getPathSpec() {
        return "/*";
    }


    public EnumSet<DispatcherType> getDispatches() {
        return EnumSet.of(DispatcherType.REQUEST);
    }


    public Filter getFilter() {
        return new LoginFilter();
    }


    public Map<String, String> getInitParameters() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginFilter",conf.get("loginFilter"));
        return map;
    }


    public int getOrder() {
        return 0;
    }
}
