$(function(){
	ScrentAutoSize(window);
	carousel();
	bingcarouselImage();
	bindTabPageEvent();
	formSubmit();
	verifyCode();
});
/**
 * 
 * <p>MethodName: ScrentAutoSize</p>
 * <p>Description: 设置页面的高度自适应</p>
 * @param window
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月10日
 * @version 1.0
 * Create At 2017年9月10日 上午12:00:25
 */
function ScrentAutoSize(window){
	var height=screen.availHeight;
	var width=screen.availWidth;
	$("#register_main").css("height",height-150);
	//针对14寸电脑修改显示大小
	if(width<=1366){
		$("#main_content").css("margin","0 100px");
		$("#register_main").css("padding-top","40px");
	}
}
/**
 * 
 * <p>MethodName: bingcarouselImage</p>
 * <p>Description:轮播图绑定图片 </p>
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月10日
 * @version 1.0
 * Create At 2017年9月10日 下午12:03:46
 */
function bingcarouselImage(){
	var imagediv="";
	var height=$("#border_div").css("height");
	var width=$("#border_div").css("width");
	for (var i=1;i<=7;i++) {
		imagediv+="<div><img height='"+height+"' width='"+width+"' src='"+projectName+"/image/"+i+".png'></img></div>";
	}
	$(imagediv).appendTo($("#carousel_item"));
}
/**
 * 
 * <p>MethodName: carousel</p>
 * <p>Description: 轮播图参数设置</p>
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月10日
 * @version 1.0
 * Create At 2017年9月10日 下午3:03:09
 */
function carousel(){
	var height=$("#border_div").css("height");
	var width=$("#border_div").css("width");
	layui.use(['carousel', 'form','layer'], function(){
		 var carousel = layui.carousel;
		  var form = layui.form;
		  var layer = layui.layer;
		  carousel.render({
			  elem: '#test10',
//			  arrow: 'always',//
			  arrow: 'none',//箭头是否显示
			 // anim: 'fade',//设置动画类型左右还是上下轮播 fade[渐隐渐显] updown[上下切换]  default[左右切换]
			  indicator:'none',// 指示器位置 none[不显示] outside[容器外部] inside[容器内部]
			  width: width,
			  height: height,
			  interval: 3000
		  });
	});
}
/**
 * 
 * <p>MethodName: bindTabPageEvent</p>
 * <p>Description: 绑定标签页事件</p>
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月10日
 * @version 1.0
 * Create At 2017年9月10日 下午3:04:12
 */
function bindTabPageEvent(){
	var li=$(".layui-tab-title").children('li');
	var div=$(".layui-tab-title").next('div').children("div");;
	if(li && li.length==div.length && li.length>0){
		li.each(function(index,item){
			$(item).on('click',function(e){
				li.each(function(i,it){
					$(it).removeClass("layui-this")
				});
				div.each(function(i,it){
					$(it).removeClass("layui-show")
				});
				$(item).addClass("layui-this");
				$(div[index]).addClass('layui-show');
			});
		});
	}
}
function formSubmit(){
	layui.use(['form', 'layedit', 'laydate'], function(){
		var form= layui.form,layer = layui.layer,layedit = layui.layedit,laydate = layui.laydate;
		form.on('submit(login)', function(obj){
			var datejson=JSON.stringify(obj.field);
			$.ajax({
				url:projectName+"/Sys/login",
				data:obj.field,
				dataType:"json",
				type:"post",
				beforeSend:function(xhr){
					$("#warn_msg_login").html("登入中...");
					$("#login_btn").addClass('layui-btn-disabled').unbind();
				},
				success:function(data){
					if(!data.login_err_msg){
						window.location.href=localhostPath+projectName;
					}else{
						$("#login_btn").removeClass('layui-btn-disabled').bind('submit');
						$("#warn_msg_login").html(data.login_err_msg);
					}
				},
				error:function(e){
					$("#login_btn").show();
				}
			});
			return false;
		});
		form.on('submit(register)', function(obj){
			var datejson=JSON.stringify(obj.field);
			$.ajax({
				url:projectName+"/Sys/register",
				data:obj.field,
				dataType:"json",
				type:"post",
				beforeSend:function(xhr){
					$("#register_btn").hide();
				},
				success:function(data){
					if(!data.registerInfo){
						window.location.href=localhostPath+projectName;
					}else{
						$("#warn_msg_register").html(data.registerInfo);
					}
				},
				error:function(e){
					alert(e);
				}
			});
			return false;
		});
	});
}
function GetItems(fieldArr){
	var o={};
	$(fieldArr).each(function(index,item){
		if(!o[item.name] && o[item.name]!=""){
			o[item.name]=item.value;
		}else{
			if(!o[item.name].push){
				o[item.name]=[o[item.name]];
			}
			o[item.name].push(item.value);
		}
	});
	return o;
}
function bindLoginAndRejister(){
	$("login_btn").on('click',function(){
		submit_login("loginForm","/Sys/login");
	});
	$("register_btn").on('click',function(){
		submit_login("registerForm","/Sys/register");
	});
}
function  submit_login(id,uri){
	var fieldElem = $("#"+id).find('input,select,checkbox') 
	var datajson=GetItems(fieldElem);
	$.ajax({
		url:projectName+uri,
		data:datajson,
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.login_err_msg){
				alert(11);
//				window.href=""
			}
		},
		error:function(e){
			alert(e);
		}
	});
}
/**
 * 
 * <p>MethodName: verifyCode</p>
 * <p>Description: 验证码</p>
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月11日
 * @version 1.0
 * Create At 2017年9月11日 下午2:51:58
 */
function verifyCode(){
	$("#verifyCode").on('click',function(){
		$("#verifyCode").attr({'src':projectName+"/verifyCode?_="+new Date().getTime()});
	});
}