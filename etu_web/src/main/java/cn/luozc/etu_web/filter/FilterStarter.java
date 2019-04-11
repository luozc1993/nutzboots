package cn.luozc.etu_web.filter;

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


    @Override
    public String getName() {
        return "LOGIN";
    }

    @Override
    public String getPathSpec() {
        return "/*";
    }

    @Override
    public EnumSet<DispatcherType> getDispatches() {
        return EnumSet.of(DispatcherType.REQUEST);
    }

    @Override
    public Filter getFilter() {
        return new LoginFilter();
    }

    @Override
    public Map<String, String> getInitParameters() {
        HashMap<String, String> map = new HashMap<>();
        map.put("loginFilter",conf.get("loginFilter"));
        return map;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
