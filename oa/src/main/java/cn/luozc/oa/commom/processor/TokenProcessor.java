package cn.luozc.oa.commom.processor;

import cn.luozc.oa.commom.utils.JsonData;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.impl.processor.ViewProcessor;

import javax.servlet.http.HttpServletRequest;

public class TokenProcessor extends ViewProcessor {

    @Override
    public void init(NutConfig config, ActionInfo ai) throws Throwable {
        super.init(config, ai);
    }

    @Override
    public void process(ActionContext ac) throws Throwable {
        HttpServletRequest request = ac.getRequest();
        String token = request.getParameter("token");
        if(token==null){
            ac.setMethodReturn(JsonData.fail(Mvcs.getMessage(ac.getRequest(), "systemServiceDisabled")));
        }
        doNext(ac);
    }
}
