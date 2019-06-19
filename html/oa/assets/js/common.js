/** EasyWeb iframe v3.1.2 date:2019-06-02 License By http://easyweb.vip */

// 以下代码是配置layui扩展模块的目录，每个页面都需要引入
layui.config({
    version: true,
    base: getProjectUrl() + 'assets/module/'
}).extend({
    formSelects: 'formSelects/formSelects-v4',
    treetable: 'treetable-lay/treetable',
    dropdown: 'dropdown/dropdown',
    notice: 'notice/notice',
    step: 'step-lay/step',
    dtree: 'dtree/dtree',
    citypicker: 'city-picker/city-picker',
    tableSelect: 'tableSelect/tableSelect',
    cropper: 'cropper/cropper',
    zTree: 'zTree/zTree'
}).use(['layer', 'admin'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var admin = layui.admin;

    // 移除loading动画
    setTimeout(function () {
        admin.removeLoading();
    }, window == top ? 600 : 100);
	//设置ajax所有错误回调方法
	$.ajaxSettings.error = function(res){
		if(res.status==403){
			window.location.href = "/login.html"
		}
	};
	
	
	token = admin.getTempData("token");
	console.log(token)
	if(!admin.getTempData("token")&&window.location.href.replace(getProjectUrl(),"")!="login.html"){
		top.window.location.href = getProjectUrl()+"login.html"
	}else if(admin.getTempData("token")&&window.location.href.replace(getProjectUrl(),"")=="login.html"){
		top.window.location.href = getProjectUrl()+"index.html"
	}
});
/////////////////////////////////////////////////////////////
////////////////////////全局变量开始/////////////////////////////
/////////////////////////////////////////////////////////////
var serverUrl = "http://127.0.0.1:20000";
var token = "";
/////////////////////////////////////////////////////////////
////////////////////////全局变量结束/////////////////////////////
/////////////////////////////////////////////////////////////

function sysAjax(url,data,type,success){
	layer.load(2);
	var $ = layui.jquery;
	data['token'] = token;
	$.ajax({
		url:url,
		data:data,
		dataType:'json',//服务器返回json格式数据
		type:type,
		success:success,
		complete:function(){
			layer.closeAll('loading');
		}
	});
}


// 获取当前项目的根路径，通过获取layui.js全路径截取assets之前的地址
function getProjectUrl() {
    var layuiDir = layui.cache.dir;
    if (!layuiDir) {
        var js = document.scripts, last = js.length - 1, src;
        for (var i = last; i > 0; i--) {
            if (js[i].readyState === 'interactive') {
                src = js[i].src;
                break;
            }
        }
        var jsPath = src || js[last].src;
        layuiDir = jsPath.substring(0, jsPath.lastIndexOf('/') + 1);
    }
    return layuiDir.substring(0, layuiDir.indexOf('assets'));
}