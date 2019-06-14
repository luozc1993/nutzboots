package cn.luozc.oa.commom.filter;

import cn.luozc.oa.commom.utils.TokenUtil;
import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@IocBean
public class LoginFilter implements Filter {
    private Set<String> excludesPattern;
    protected String contextPath;
    protected PatternMatcher pathMatcher = new ServletPathMatcher();


    public void init(FilterConfig filterConfig) throws ServletException {
        String loginFilter = filterConfig.getInitParameter("loginFilter");
        if (loginFilter != null && loginFilter.trim().length() != 0) {
            this.excludesPattern = new HashSet(Arrays.asList(loginFilter.split("\\s*,\\s*")));
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();

        //判断是否为需要验证的链接
        if(!isExclusion(requestURI)){
            String token = req.getParameter("token");
            if(token==null|| !TokenUtil.verify(token)){
                res.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
                res.sendError(403,"token错误");
            }
        }





        chain.doFilter(request,response);
    }

    public void destroy() {

    }




    public boolean isExclusion(String requestURI) {
        if (this.excludesPattern == null) {
            return false;
        } else {
            if (this.contextPath != null && requestURI.startsWith(this.contextPath)) {
                requestURI = requestURI.substring(this.contextPath.length());
                if (!requestURI.startsWith("/")) {
                    requestURI = "/" + requestURI;
                }
            }

            Iterator var2 = this.excludesPattern.iterator();

            String pattern;
            do {
                if (!var2.hasNext()) {
                    return false;
                }

                pattern = (String)var2.next();
            } while(!this.pathMatcher.matches(pattern, requestURI));

            return true;
        }
    }
}
