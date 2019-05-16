// 以下代码是配置layui扩展模块的目录，每个页面都需要引入
layui.config({
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
    iconPicker: 'iconPicker/iconPicker',
    dynamicForm: 'dynamicForm/dynamicForm'
}).use(['layer', 'admin'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var admin = layui.admin;

    // 移除loading动画
    setTimeout(function () {
        admin.removeLoading();
    }, window == top ? 600 : 100);

    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }

});

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

/**
 * 判断数据是否为空
 * @param data
 * @returns {*}
 */
function isNull(data){
    var gettype=Object.prototype.toString;
    if(gettype.call(data)==="[object Object]"){
        return (isEmptyObject(data)) ? "" : data;
    }
    if(data !== undefined&&data != null&&data.toString()==="0"){
        return 0;
    }
    return (data === "" || data === undefined ||data === 'undefined'|| data == null) ? "" : data;
}

/**
 * 检查一个对象（Object）是否为空
 * @param obj
 * @returns {boolean}
 */

function isEmptyObject(obj) {
    for (var key in obj) {
        return false;
    }
    return true;
}

function closeTips() {
    top.layer.closeAll();
    //关闭当前窗口
    window.close();
    //关闭当前标签
    top.layui.index.closeTab(window.location.pathname+window.location.search);
}

function closeParentTips() {
    parent.layer.closeAll();
    //关闭当前标签
    parent.layui.index.closeTab(window.location.pathname+window.location.search);
}