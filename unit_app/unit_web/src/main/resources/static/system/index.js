layui.use(['index','element','tableSelect'], function () {
    var $ = layui.jquery;
    var index = layui.index;
    var element = layui.element;
    var tableSelect = layui.tableSelect;



    $.ajax({
        url:"/sys/menu/indexMenu",
        success:function(res){
            if(res.status){
                var datas = res.data;
                for (let i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    if(i==0){
                        $("#system").append(`<li class="layui-nav-item layui-hide-xs layui-this" lay-unselect><a nav-bind="${data.id}">${data.name}</a></li>`)
                        $("#menus").append(`<ul class="layui-nav layui-nav-tree" nav-id="${data.id}" id="${data.id}" lay-filter="admin-side-nav" style="margin: 15px 0;"></ul>`);
                    }else{

                        $("#system").append(`<li class="layui-nav-item layui-hide-xs " lay-unselect><a nav-bind="${data.id}">${data.name}</a></li>`)
                        $("#menus").append(`<ul class="layui-nav layui-nav-tree layui-hide" nav-id="${data.id}" id="${data.id}" lay-filter="admin-side-nav" style="margin: 15px 0;"></ul>`);
                    }
                    $("#systemMobile").append( `<dd lay-unselect><a nav-bind="${data.id}">${data.name}</a></dd>`);

                    var childs = data.child;
                    if(childs){
                        for (let j = 0; j < childs.length; j++) {
                            var child = childs[j];
                            if(child.child){
                                $("#"+child.parentId).append(
                                    `<li class="layui-nav-item" id="${child.id}">
                                        <a><i class="layui-icon ${child.icon}"></i>&emsp;<cite>${child.name}</cite></a>
                                     </li>`);
                                addChildMenu(child);
                            }else{
                                $("#"+child.parentId).append(
                                    `<li class="layui-nav-item" id="${child.id}">
                                        <a lay-href="${child.url}" ><i class="layui-icon ${child.icon}"></i>&emsp;<cite>${child.name}</cite></a>
                                     </li>`);
                            }

                        }
                    }
                }
                element.render('nav');

            }
        }
    });

    function addChildMenu(menu){
        var childs = menu.child;
        if(childs){
            var id = menu.id;
            $("#"+id).append(`<dl class="layui-nav-child" id="${id}_child"></dl>`);
            for (let i = 0; i < childs.length; i++) {
                var child = childs[i];
                if(child.child){
                    $("#"+id+"_child").append(`<dd  id="${child.id}"><a><i class="layui-icon ${child.icon}"></i>&emsp;<cite>${child.name}</cite></a></dd>`);
                    addChildMenu(child);
                }else{
                    $("#"+id+"_child").append(`<dd  id="${child.id}"><a lay-href="${child.url}"><i class="layui-icon ${child.icon}"></i>&emsp;<cite>${child.name}</cite></a></dd>`);
                }


            }
        }
    }



    // 默认加载主页
    index.loadHome({
        menuPath: '../../page/console/console.html',
        menuName: '<i class="layui-icon layui-icon-home"></i>',
        loadSetting: false
    });


});