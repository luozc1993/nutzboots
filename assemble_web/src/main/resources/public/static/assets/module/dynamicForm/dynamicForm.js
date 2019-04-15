/**
  项目JS主入口
  以依赖layui的layer和form模块为例
**/    
layui.define(['layer', 'form','jquery','laydate'], function(exports){
	$ = layui.$;
	var form = layui.form;
	var laydate = layui.laydate;
	var dynamicFormBom = $("#dynamicForm");
	var dynamicForm = {
		//动态创建表单
		form: function(obj){
			//表单需要插入的id
			var id = obj.id;
			//表单数据
			var datas = obj.data;
			//确定按钮名称
			var yesBtnName = obj.yesBtnName;
			//是否显示按钮
            var showBtn = obj.showBtn;
			yesBtnName = yesBtnName?yesBtnName:"提交";
			var pane = obj.pane;
			var paneAtr = "";
			console.log(pane)
			if(pane){
                paneAtr = 'layui-form-pane';
            }
			//添加容器
			$(id).html(`<div id="dynamicForm" class="layui-form layui-row ${paneAtr}" ></div>`);
            dynamicFormBom = $("#dynamicForm");
            //标题
            var title = obj.title;
            if(title){
                dynamicFormBom.append(`<h1 style="text-align: center;padding:30px">${title}</h1>`);
            }

            //循环生成表单
			$.each(datas,function(index,data){
				dynamicForm.createForm(data,obj)
			});
            //是否显示确定按钮
            if(showBtn){
                //确定取消按钮
                dynamicFormBom.append(`<div class="layui-form-item layui-row layui-col-xs12" style="    margin: 20px 0px;">
                                            <div class="layui-input-block " style="    margin-left:0px;text-align: center;">
                                                <button class="layui-btn " lay-submit="" lay-filter="yes">${yesBtnName}</button>
                                                <button lay-submit="" class="layui-btn layui-btn-primary " lay-filter="cancel">取消</button>
                                            </div>
                                        </div>`);
            }

			//刷新渲染
			form.render(); 
			//执行成功后回调
			var success = obj.success;
			if (success&&typeof success === "function"){
				success(obj);     //调用传入的回调函数
			}
			//监听确认事件
			form.on("submit(yes)",function(data){
				var yes = obj.yes;
				if (yes&&typeof yes === "function"){
					yes(data);     //调用传入的回调函数
				}
			});
			//监听取消事件
			form.on("submit(cancel)",function(data){
				var cancel = obj.cancel;
				if (cancel&&typeof cancel === "function"){
					cancel(data);     //调用传入的回调函数
				}
			});
		},
        dataTime: function (nolabel, formHtml, data,type,label,change,value,key) {
            var html = `<div class="layui-form-item">
                            ${label}
                            <div class="layui-input-block ${nolabel}">
                              <input type="text" name="${key}" id="${key}" lay-verify="${key}" autocomplete="off" class="layui-input">
                            </div>
                        </div>`;
            dynamicFormBom.append(formHtml.replace("formHtml", html));
            if (!value) {
                value = new Date();
            }
            //日期时间
            laydate.render({
                elem: '#' + key,
                type: type,
                value: value,
                done: function (value, date, endDate) {
                    if (change && typeof change === "function") {
                        change(data, value);     //调用传入的回调函数
                    }
                }
            });
            return value;
        },
        createForm:function(data, obj){

			//表单占比 栅格模式
			var col = data.col?data.col:12;
			//表单偏移量
			var offset =  data.offset?"layui-col-md-offset"+data.offset:"";
			//标签
			var label = data.label;
			//标签样式
			var nolabel = "";
			if(label){
                label = '<label class="layui-form-label">'+label+'</label>';
            }else{
			    //没标签取消左边的margin-left
                nolabel = "nolabel"
            }
			//标签的id
            var key = data.key;
			//表单值
            var value = data.value;
            //占位提示文本
            var tips = data.tips;
            //选项
            var options = data.options;
            var type = data.type;
            //0查看 1编辑 2隐藏
            var power = data.power;
            if(power===1){
                type = "text";
            }if(power===2){
                return "";
            }
            var change = obj.change;

            var formHtml = `<div class="layui-col-md${col} ${offset}">formHtml</div>`;
			switch (type){
				case 'text-input'://文本输入框
                    var html = `<div class="layui-form-item ">
                                    ${label}
                                    <div class="layui-input-block ${nolabel}">
                                      <input type="text" name="${key}" id="${key}" value="${value}"  lay-verify="${key}" autocomplete="off" placeholder="${tips}" class="layui-input">
                                    </div>
                                  </div>`;
                    dynamicFormBom.append(formHtml.replace("formHtml",html));
							$("#"+key).change(function (o) {
                                if (change&&typeof change === "function"){
                                    change(data,$(this).val());     //调用传入的回调函数
                                }
                            });
					break;
                case 'hide-input'://文本输入框
                    var html = `<div class="layui-form-item" style="display: none">
                                    ${label}
                                    <div class="layui-input-block ${nolabel}">
                                        <input type="text" name="${key}" id="${key}" value="${value}"  lay-verify="${key}" autocomplete="off" placeholder="${tips}" class="layui-input">
                                    </div>
                                </div>`;
                    dynamicFormBom.append(formHtml.replace("formHtml",html));
                    break;
				case 'text'://文本显示
                    var html =`<div class="layui-form-item">
                                    ${label}
                                    <div class="layui-input-block ${nolabel}" style="line-height: 36px;">${value}</div>
                                </div>`;
                    dynamicFormBom.append(formHtml.replace("formHtml",html));
					break;
				case 'select'://下拉
					if(options==null||options.length===0)break;
					//拆分下拉框值
					var optionHtml = "";
					for (var i in options) {
					    var option = options[i];
						if(option.name===value){
                            optionHtml +='<option  selected="" value="'+option.id+'">'+option.name+'</option>'
						}else{
                            optionHtml +='<option  value="'+option.id+'">'+option.name+'</option>'
						}
					}
                    var html =`<div class="layui-form-item">
                                    ${label}
                                    <div class="layui-input-block ${nolabel}">
                                      <select name="${key}" lay-filter="${key}">
                                       ${optionHtml}
                                      </select>
                                    </div>
                                  </div>`;
                    dynamicFormBom.append(formHtml.replace("formHtml",html));
                    //监听表单点击事件
                    form.on('select('+key+')', function(o){
                        if (change&&typeof change === "function"){
                            change(data,o.value);     //调用传入的回调函数
                        }
                    });
					break;
                case "date":
                case "time":
                case "datetime":
                case "month":
                case "year":
                    this.dataTime(nolabel, formHtml, data,type,label,change,value,key);
                    break;
                case 'textarea':
                    var html = `<div class="layui-form-item layui-form-text">
                                    ${label}
                                    <div class="layui-input-block ${nolabel}">
                                        <textarea name="${key}" id="${key}" placeholder="${tips}" class="layui-textarea">${value}</textarea>
                                    </div>
                                </div>`;

                    dynamicFormBom.append(formHtml.replace("formHtml",html));
                                $("#"+key).change(function (o) {
                                    if (change&&typeof change === "function"){
                                        change(data,$(this).val());     //调用传入的回调函数
                                    }
                                });
                break;
                case 'switch':
                    if(options.length<2)break;

                    var text = options[0].name+"|"+options[1].name;
                    var input = "";
                    if(options[0].name==value){
                        input = `<input type="checkbox" checked="" name="${key}" id="${key}" lay-text="${text}" lay-filter="${key}" value="${value}"  lay-skin="switch" title="开关">`;
                    }else{
                        input = `<input type="checkbox"  name="${key}" id="${key}" lay-text="${text}" lay-filter="${key}" value="${value}"  lay-skin="switch" title="开关">`;
                    }

                    var html = ` <div class="layui-form-item" pane="">
                                    ${label}
                                    <div class="layui-input-block">
                                      ${input}
                                    </div>
                                 </div>`;
                    dynamicFormBom.append(formHtml.replace("formHtml",html));
                    //监听表单点击事件
                    form.on('switch('+key+')', function(o){
                        console.log(o)
                        if (change&&typeof change === "function"){
                            change(data,o.value);     //调用传入的回调函数
                        }
                    });
                    break;
                case 'checkbox':
                    var input = "";
                    for (let i = 0; i < options.length; i++) {
                        var option = options[i];
                        input += `<input type="checkbox" name="${key}[${option.id}]" lay-skin="primary" lay-filter="${key}" title="${option.name}">`;
                    }

                    var html = ` <div class="layui-form-item " pane="">
                                    ${label}
                                    <div class="layui-input-block ${nolabel}">
                                      ${input}
                                    </div>
                                  </div>`;
                    dynamicFormBom.append(formHtml.replace("formHtml",html));
                    form.on('checkbox('+key+')', function(o){
                        if (change&&typeof change === "function"){
                            change(data,o.value);     //调用传入的回调函数
                        }
                    });
                    break;
				default:
					break;
			}
			return formHtml;
		}
	};
  
  //输出test接口
  exports('dynamicForm', dynamicForm); 
}); 