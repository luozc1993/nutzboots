var chain={
  "default" : {
    "ps" : [
      "cn.luozc.unit_app.commons.processor.LogTimeProcessor",
      "org.nutz.mvc.impl.processor.UpdateRequestAttributesProcessor",
      "org.nutz.mvc.impl.processor.EncodingProcessor",
      "org.nutz.mvc.impl.processor.ModuleProcessor",
      "cn.luozc.unit_app.commons.processor.XssSqlFilterProcessor",
      "org.nutz.mvc.impl.processor.ActionFiltersProcessor",
      "org.nutz.mvc.impl.processor.AdaptorProcessor",
      "org.nutz.mvc.impl.processor.MethodInvokeProcessor",
      "org.nutz.mvc.impl.processor.ViewProcessor"
    ],
    "error" : 'cn.luozc.unit_app.commons.processor.WkFailProcessor'
  }
};