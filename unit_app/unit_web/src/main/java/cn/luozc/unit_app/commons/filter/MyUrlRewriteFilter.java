package cn.luozc.unit_app.commons.filter;

import org.nutz.boot.starter.WebFilterFace;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@IocBean
public class MyUrlRewriteFilter extends UrlRewriteFilter implements WebFilterFace   {

    @Inject
    PropertiesProxy conf;



    public String getName() {
        return "UrlRewrite";
    }


    public String getPathSpec() {
        return "/*";
    }


    public EnumSet<DispatcherType> getDispatches() {
        return EnumSet.of(DispatcherType.REQUEST);
    }

    public Filter getFilter() {
        return new MyUrlRewriteFilter();
    }


    public Map<String, String> getInitParameters() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("confPath","/urlrewrite.xml");
        return map;
    }


    public int getOrder() {
        return 0;
    }

}
