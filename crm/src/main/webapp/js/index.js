/*获取当前登录的用户信息，显示在页面右上角*/
var user;
var row = null;//被选中的行
$(function(){
	$.ajax({
		type:"get",
		url:"getUser.do",
		success:function(obj){
			//判断是否有用户信息
			if(typeof obj=="string"){
				window.location.href="login.html";
			}
//			debugger;
			//将用户信息显示到页面右上角
			$("#uName").text(obj.uName);
			user = obj;
			//侧栏菜单设置--销售主管登录时隐藏销售计划的菜单选项
			if(obj.rId==1){
				data.splice(1,1);//如果是销售主管，从数组中删除‘销售计划’这个元素
				$('#sm').sidemenu({
	                data: data
	            });
			}
		}
	})
})

/*提交表单*/
function submitForm(btn){
	if($(btn).parent().prev().form('validate')){//验证表单有效字段
		var parameter = $(btn).parent().prev().serialize();
		$.ajax({
			type:"get",
			url:$(btn).parent().prev().attr("data-url")+"?"+parameter,
			success:function(data){
				var msgstr = "操作失败";
				if(data!=null){
					msgstr="操作成功";
					//刷新表格数据
					
				}
				//关闭弹出层
				$(btn).parent().parent().window("close");
				
				$.messager.show({
					title:'is Message',
					msg:msgstr,
					showType:'show',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
			}
		})
	}
}

/*关闭弹出层*/
function clearForm(btn){
	/*清除表单内容*/
	$(btn).parent().prev().form("clear");
	$(btn).parent().parent().window("close");
}

/*form表单加载数据*/
function loadFormData(obj,data){
	obj.form("load",data);
	
}