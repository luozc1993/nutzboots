package cn.luozc.oa.commom.processor;

import cn.luozc.oa.commom.utils.JsonData;
import org.nutz.ioc.IocException;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.impl.processor.ViewProcessor;

public class WkFailProcessor extends ViewProcessor {

    private static final Log log = Logs.get();
    private String errorUri = "/error/500.html";

    @Override
    public void init(NutConfig config, ActionInfo ai) throws Throwable {
        super.init(config, ai);
    }

    @Override
    public void process(ActionContext ac) throws Throwable {
        if (log.isWarnEnabled()) {
            String uri = Mvcs.getRequestPath(ac.getRequest());
            log.warn(String.format("Error@%s :", uri), ac.getError());
        }

        if (ac.getError() instanceof IocException) {
            ac.setMethodReturn(JsonData.fail(Mvcs.getMessage(ac.getRequest(), "systemServiceDisabled")));

            log.warn(Mvcs.getMessage(ac.getRequest(), "systemServiceDisabled"));
        }
        super.process(ac);
    }


}
