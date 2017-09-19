//页面加载后初始化
$(function(){
//	TreeInit();
	LeftNavitationInit();
	cursorInit();
	Pageinit();
});

function rightClick(e){
//	屏蔽右键点击
	window.event.returnValue=false; 
	return false;
}
function LeftNavitationInit(){
	var ul=$("#navigation").find("ul");
	$(ul).each(function(ulIndex,ulItem){
		var li=$(ulItem).find("li");
		$(li).each(function(liIndex,liItem){
			$(liItem).on("mouseover mouseout click contextmenu",function(e){
				if(e.type=='mouseover'){
					$(this).css({'background-color':'#bab7b7','border':'1px solid'});
				}else if(e.type=='mouseout'){
					$(this).css({'background-color':'white','border':'0px solid'});
				}else if(e.type=='click'){
					$("#background").css({'background-color':'beige','top':liIndex*40+'px','border':'1px solid'});
				}else if(e.type=='contextmenu'){
					var rightClickTemp="<div class='evil-rightclick-temp'>";
					rightClickTemp+="<div class='evil-rightclick-inner'></div>";		
					rightClickTemp+="<div/>";
					$(rightClickTemp).appendTo($(this));
					//html(rightClickTemp);
					//console.log($(liItem).html());
				}
			});
//				var top=$("#background").css("top");
//				var index=top.substring(0,top.length-2);
//				if(index<liIndex*40){
//					for(;index<=liIndex*40;index++){
//						setTimeout(function(){
//							$("#background").css({'background-color':'red','top':index+'px','border':'1px solid'});
//						},200);
//					}
//				}else{
//					for(;index>=liIndex*40;index--){
//						setTimeout(function(){
//							$("#background").css({'background-color':'red','top':index+'px','border':'1px solid'});
//						},200)
//					}
//				}
		});
	});
}
function cursorInit(){
	var btn_li=$("#new_btn").find('li');
	$(btn_li).each(function(index,item){
			$(this).on('mouseover mouseout click',function(e){
				if(e.type=='mouseover'){
					$(this).css({'background-color':'#deb887','border':'1px solid'});
				}else if(e.type=='mouseout'){
					$(this).css({'background-color':'#fff','border':'0px solid'});
				}else if(e.type=='click' && $(this).val()==2){
//					confirm("确定");
//					var name=prompt("请输入类别名称");
					var mar_top=$(this).parent('ul').css('margin-top');
					var num_mar_top=parseInt(mar_top.substring(0,mar_top.length-2))+40;
					$(this).parent('ul').css('margin-top',num_mar_top+"px");
					var module="<li class='evil-li'>";
					module+="<a>";
					module+="<span class='evil-span'>";
					module+="<input class='evil-input-new' value='请修改类别名称'>" ;
					module+="</span>";
					module+="</a>";
					module+="</li>";
					$(module).appendTo($("#navigation").find("ul"));
					$(".evil-input-new").focus();
					$(".evil-input-new").blur(function(e){
						var title_val=$(this).val();
//						LeftNavitationInit();
						$("#navigation").find(".evil-li").last().find('span').html(title_val);
						//刷新
						LeftNavitationInit();
					});
				}
			});
	});
}
/**
 * 
 * <p>MethodName: TreeInit</p>
 * <p>Description: 左侧树导航</p>
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月17日
 * @version 1.0
 * Create At 2017年9月17日 下午6:05:20
 */
function TreeInit(){
	var setting={};
	var zNodes=[
		   {name:"test1", open:true, children:[
			      {name:"test1_1"}, {name:"test1_2"}]},
			   {name:"test2", open:true, children:[
			      {name:"test2_1"}, {name:"test2_2"}]}
			   ];
	var zTreeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);
}
/**
 * 
 * <p>MethodName: Pageinit</p>
 * <p>Description:页面初始化样式 包括富文本编辑器以及导航栏 </p>
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月15日
 * @version 1.0
 * Create At 2017年9月15日 下午2:48:17
 */
function Pageinit(){
		layui.use(['element','layedit','upload'], function(){
			var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
			var layedit = layui.layedit;
			var upload = layui.upload;
			//监听导航点击
//			element.on('nav(demo)', function(elem){
//				layer.msg(elem.text());
//			});
			
			//富文本编辑器
			var index=layedit.build('LAY_demo1', {
			    tool: ['strong','italic','underline','del','|','left','center','right','link','unlink','face','image','code']
			    ,height: 400,
			    uploadImage:{url:projectName+'/fileManager/fileUpload',type:'post'}
			  })
			
			//编辑器外部操作
			var active = {
					content: function(){
						alert(layedit.getContent(index)); //获取编辑器内容
					},
					text: function(){
						alert(layedit.getText(index)); //获取编辑器纯文本内容
					},
					selection: function(){
						alert(layedit.getSelection(index));
					}
			};
			$('.site-demo-layedit').on('click', function(){
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
			
			var uploadInst = upload.render({
			    elem: '#test1'
			    ,url: '/upload/'
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo1').attr('src', result); //图片链接（base64）
			      });
			    }
			    ,done: function(res){
			      //如果上传失败
			      if(res.code > 0){
			        return layer.msg('上传失败');
			      }
			      //上传成功
			    }
			    ,error: function(){
			      //演示失败状态，并实现重传
			      var demoText = $('#demoText');
			      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
			      demoText.find('.demo-reload').on('click', function(){
			        uploadInst.upload();
			      });
			    }
			  });
		});
}