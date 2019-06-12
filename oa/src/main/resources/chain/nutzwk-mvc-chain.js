var chain={
  "default" : {
    "ps" : [
      "cn.luozc.oa.commom.processor.LogTimeProcessor",
      "org.nutz.mvc.impl.processor.UpdateRequestAttributesProcessor",
      "org.nutz.mvc.impl.processor.EncodingProcessor",
      "org.nutz.mvc.impl.processor.ModuleProcessor",
      "cn.luozc.oa.commom.processor.XssSqlFilterProcessor",
      "cn.luozc.oa.commom.processor.TokenProcessor",
      "org.nutz.mvc.impl.processor.ActionFiltersProcessor",
      "org.nutz.mvc.impl.processor.AdaptorProcessor",
      "org.nutz.mvc.impl.processor.MethodInvokeProcessor",
      "org.nutz.mvc.impl.processor.ViewProcessor"
    ],
    "error" : 'cn.luozc.oa.commom.processor.WkFailProcessor'
  }
};