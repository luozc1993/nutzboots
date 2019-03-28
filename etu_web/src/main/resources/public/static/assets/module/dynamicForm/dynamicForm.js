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
			//添加容器
			$(id).html('<div id="dynamicForm" class="layui-form  layui-row" ></div>');
            dynamicFormBom = $("#dynamicForm");
            //标题
            var title = obj.title;
            if(title){
                dynamicFormBom.append('<h1 style="text-align: center;padding:30px">'+title+'</h1>');
            }

            //循环生成表单
			$.each(datas,function(index,data){
				dynamicForm.createForm(data)
			});
            //是否显示确定按钮
            if(showBtn){
                //确定取消按钮
                dynamicFormBom.append('<div class="layui-form-item layui-row layui-col-xs12" style="    margin: 20px 0px;">'
                    +'	<div class="layui-input-block " style="    margin-left:0px;text-align: center;">'
                    +'		<button class="layui-btn " lay-submit="" lay-filter="yes">'+yesBtnName+'</button>'
                    +'		<button lay-submit="" class="layui-btn layui-btn-primary " lay-filter="cancel">取消</button>'
                    +'	</div>'
                    +'</div>');
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
		createForm:function(obj){
			var formHtml = '';
			//表单占比 栅格模式
			var col = obj.col?obj.col:12;
			//表单偏移量
			var offset =  obj.offset?obj.offset:0;
			//标签
			var label = obj.label;
			//标签样式
			var nolabel = "";
			if(label){
                label = '<label class="layui-form-label">'+label+'</label>';
            }else{
			    //没标签取消左边的margin-left
                nolabel = "nolabel"
            }
			//标签的id
            var key = obj.key;
			//表单值
            var value = obj.value;
            //占位提示文本
            var tips = obj.tips;

            var type = obj.type;
            //0查看 1编辑 2隐藏
            var power = obj.power;
            if(power===1){
                type = "text";
            }if(power===2){
                return "";
            }

			switch (type){
				case 'text-input'://文本输入框
                    formHtml ='<div class="layui-form-item layui-col-md'+col+' layui-col-md-offset'+ offset+'">'
							+label
							+'<div class="layui-input-block '+nolabel+'">'
							+'  <input type="text" name="'+key+'" id="'+key+'" value="'+value+'"  lay-verify="title" autocomplete="off" placeholder="'+tips+'" class="layui-input">'
							+'</div>'
						  +'</div>';
							dynamicFormBom.append(formHtml);
					break;
				case 'text'://文本显示
                    formHtml ='<div class="layui-form-item layui-col-md'+col+' layui-col-md-offset'+ offset+'">'
							+label
							+'<div class="layui-input-block '+nolabel+'" style="line-height: 36px;">'
							+value
							+'</div>'
						+'</div>';
						dynamicFormBom.append(formHtml);
					break;
				case 'select'://下拉
					//判断数据是否为空
					if(value==null||value==="")break;
					//拆分下拉框值和默认数据
					var arr = value.split("$");	
					var defaultValue = "";
					if(arr.length>1){
						defaultValue = arr[1];
					}
					var options = arr[0].split("|");
					var option = "";
					for (var index in options) {
					    var str = options[index];
						if(str===defaultValue){
							option +='<option  selected="" value="'+str.split("&")[0]+'">'+str.split("&")[1]+'</option>'
						}else{
							option +='<option value="'+str.split("&")[0]+'">'+str.split("&")[1]+'</option>'
						}
						
					}
                    formHtml = '<div class="layui-form-item layui-col-md'+col+' layui-col-md-offset'+ offset+'">'
							+label
							+'<div class="layui-input-block '+nolabel+'">'
							+'  <select name="'+key+'" lay-filter="'+key+'">'
							+	option
							+'  </select>'
							+'</div>'
						  +'</div>';
							dynamicFormBom.append(formHtml);
					break;
					case "date":
                        formHtml ='<div class="layui-form-item layui-col-md'+col+' layui-col-md-offset'+ offset+'">'
								+label
								+'<div class="layui-input-block '+nolabel+'">'
								+'  <input type="text" name="'+key+'" id="'+key+'" lay-verify="title" autocomplete="off" placeholder="'+tips+'" class="layui-input">'
								+'</div>'
								+'</div>';
								dynamicFormBom.append(formHtml);
								if(!value){
									value = new Date();
								}
								//日期
								laydate.render({
									elem: '#'+key,
									type: 'date',
									value: value
								});
					break;
					case "time":
                        formHtml ='<div class="layui-form-item layui-col-md'+col+' layui-col-md-offset'+ offset+'">'
								+label
								+'<div class="layui-input-block '+nolabel+'">'
								+'  <input type="text" name="'+key+'" id="'+key+'" lay-verify="title" autocomplete="off" placeholder="'+tips+'" class="layui-input">'
								+'</div>'
								+'</div>';
								dynamicFormBom.append(formHtml);
								if(!value){
									value = new Date();
								}
								//日期时间
								laydate.render({
									elem: '#'+key,
									type:"time",
									value: value
								});
					break;
					case "datetime":
                        formHtml ='<div class="layui-form-item layui-col-md'+col+' layui-col-md-offset'+ offset+'">'
								+label
								+'<div class="layui-input-block '+nolabel+'">'
								+'  <input type="text" name="'+key+'" id="'+key+'" lay-verify="title" autocomplete="off" placeholder="'+tips+'" class="layui-input">'
								+'</div>'
								+'</div>';
								dynamicFormBom.append(formHtml);
								if(!value){
									value = new Date();
								}
								//日期时间
								laydate.render({
									elem: '#'+key,
									type:"datetime",
									value: value
								});
					break;
					case "month":
                        formHtml ='<div class="layui-form-item layui-col-md'+col+' layui-col-md-offset'+ offset+'">'
								+label
								+'<div class="layui-input-block '+nolabel+'">'
								+'  <input type="text" name="'+key+'" id="'+key+'" lay-verify="title" autocomplete="off" placeholder="'+tips+'" class="layui-input">'
								+'</div>'
								+'</div>';
								dynamicFormBom.append(formHtml);
								if(!value){
									value = new Date();
								}
								//日期时间
								laydate.render({
									elem: '#'+key,
									type:"month",
									value: value
								});
					break;
					case "year":
                        formHtml ='<div class="layui-form-item layui-col-md'+col+' layui-col-md-offset'+ offset+'">'
								+label
								+'<div class="layui-input-block '+nolabel+'">'
								+'  <input type="text" name="'+key+'" id="'+key+'" lay-verify="title" autocomplete="off" placeholder="'+tips+'" class="layui-input">'
								+'</div>'
								+'</div>';
								dynamicFormBom.append(formHtml);
								if(!value){
									value = new Date();
								}
								//日期时间
								laydate.render({
									elem: '#'+key,
									type:"year",
									value: value
								});
					break;
					case 'textarea':
						formHtml = '<div class="layui-form-item layui-form-text layui-col-md'+col+' layui-col-md-offset'+ offset+'">'
											+label
											+'	<div class="layui-input-block '+nolabel+'">'
											+'		<textarea name="'+key+'" id="'+key+'" placeholder="'+tips+'" class="layui-textarea">'+value+'</textarea>'
											+'	</div>'
											+'</div>';
											
													dynamicFormBom.append(formHtml);
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