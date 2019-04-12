package cn.luozc.assemble_web.filter;

import org.nutz.boot.starter.WebFilterFace;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.resource.NutResource;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
@IocBean
public class MyUrlRewriteFilter extends UrlRewriteFilter implements WebFilterFace   {

    @Inject
    PropertiesProxy conf;


    @Override
    public String getName() {
        return "UrlRewrite";
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
        return new MyUrlRewriteFilter();
    }

    @Override
    public Map<String, String> getInitParameters() {
        HashMap<String, String> map = new HashMap<>();
        map.put("confPath","/urlrewrite.xml");
        return map;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
