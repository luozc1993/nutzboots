package cn.luozc.assemble_web.filter;

import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@IocBean
public class LoginFilter implements Filter {
    private Set<String> excludesPattern;
    protected String contextPath;
    protected PatternMatcher pathMatcher = new ServletPathMatcher();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String loginFilter = filterConfig.getInitParameter("loginFilter");
        if (loginFilter != null && loginFilter.trim().length() != 0) {
            this.excludesPattern = new HashSet(Arrays.asList(loginFilter.split("\\s*,\\s*")));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String requestURI = req.getRequestURI();
        if(!(isExclusion(requestURI)||session.getAttribute("userId")!=null)){
            //跳转到登录页面
            //req.getRequestDispatcher("/login.html").forward(request,response);
            res.sendRedirect("/login.html");
        }

        chain.doFilter(request,response);
    }

    @Override
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
