var path=''
$(function(){
	bingEvent("mouseover","block");
	bingEvent("mouseout","none");
	
	$("#login").on("click",function(){
		var username=$("#username").val();
		var password=$("#password").val();
		var rememberMe=$("#rememberMe").val();
		$.ajax({
			url:projectPath+"Sys/login",
			type:"post",
			dataType:"json",
			data:{
				"username":username,
				"password":password,
				"rememberMe":rememberMe
			},
			success:function(res){
				if(!res.login_err_msg){
					window.location.href=projectPath+"Sys/success";
				}else{
					$("#err_message").html(res.login_err_msg);
				}
			}
		});
	});
});

function bingEvent(event,result){
	$(".evil-nav-item").on(event,function(e){
		var childl=$(this).find("dl");
		if(childl.length!=0){
			childl.css("display",result);
		}
		var childSpan=$(this).find("span");
		var len=$($(this).children("a")[0]).text().trim().replace("/","").length;
		if(childSpan.length!=0){
			childSpan.css("display",result);
		}else{
			var bar="<span class='evil-nav-bar' style='width:"+len*23+"px'></span>"
			$(bar).appendTo($(this))
		}
	});
}
