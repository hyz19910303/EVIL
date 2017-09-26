//右键点击事件变量
var edit_flag=false;//
//页面加载后初始化
$(function(){
//	TreeInit();
	LeftNavitationInit();
	cursorInit();
	wangEditInit();
});

function rightClick(e){
//	屏蔽右键点击
	window.event.returnValue=false; 
	return false;
}
/**
 * 
 * <p>MethodName: LeftNavitationInit</p>
 * <p>Description: 左侧菜单类别代码</p>
 * @param edit_flag
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月26日
 * @version 1.0
 * Create At 2017年9月26日 上午9:03:28
 */
function LeftNavitationInit(edit_flag){
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
					//$("#background").show().css({'background-color':'beige','top':liIndex*40+'px','border':'1px solid'});
					$("#background").show().animate({'background-color':'beige','top':liIndex*40+'px','border':'1px solid'},"slow");
					$("#background_1").hide();
					//数据库读取 
					// TO-DO
					let li=$(liItem);
					$("#evil-content-title").empty();
					//模拟数据
					let content_title="";
					var arr=[{"title":"书架"},{"title":"实践论"},{"title":"死亡"}];
					for ( var e in arr) {
						content_title+="<li class='evil-book-li'><a ><span class='evil-span'>"+arr[e]['title']+"</span></a></li>"
					}
					$("#evil-content-title").hide().append($(content_title)).slideToggle("normal");
				}
				else if(e.type=='contextmenu'){
					var temp=$(liItem).find('.evil-rightclick-temp');
					if(temp.length!=0){
						if(edit_flag){
							temp.show();
							edit_flag=false;
						}else{
							temp.hide();
							edit_flag=true;
						}
					}else{
						var rightClickTemp="<div class='evil-rightclick-temp'>";
						rightClickTemp+="<div class='evil-rightclick-inner'>";
						rightClickTemp+="<div class='evil-del-btn' onclick='editItem(this);'>修改</div>";		
						rightClickTemp+="<div class='evil-edit-btn' onclick='deleteItem(this);'>删除</div>";		
						rightClickTemp+="</div>";		
						rightClickTemp+="<div/>";
						$(rightClickTemp).appendTo($(liItem));
					}
				}
			});
		});
	});
}
//删除
function deleteItem(item){
	var parent_li=$(item).parents("li").remove();
	
	var var_li=$("#navigation").find("ul").find('li').unbind();
	var li_length=var_li.length;
	LeftNavitationInit(edit_flag);
	$("#new_btn").css({'margin-top':li_length*40+2+'px'});
}
//编辑
function editItem(item){
	var parent_li=$(item).parents("li");
	var span=$(parent_li).find('span');
	var demo="<input class='evil-input-new' value='"+span.html()+"'>"; 
	span.html(demo);
	$(".evil-input-new").focus();
	$(".evil-input-new").blur(function(e){
		var title_val=$(this).val();
		$(parent_li).find('span').html(title_val);
		//解除绑定事件
		$("#navigation").find("ul").find('li').unbind();
		//刷新 重新绑定事件
		LeftNavitationInit();
	});
}
function cursorInit(){
	var btn_obj=$("#new_btn");
	var li_length=$("#navigation").find("ul").find('li').length;
	btn_obj.css({'margin-top':li_length*40+2+'px'});
	var btn_li=btn_obj.find('li');
	$(btn_li).each(function(index,item){
			$(this).on('mouseover mouseout click',function(e){
				if(e.type=='mouseover'){
					$(this).css({'background-color':'#deb887','border':'1px solid'});
				}else if(e.type=='mouseout'){
					$(this).css({'background-color':'#fff','border':'1px solid transparent'});
				}else if(e.type=='click'){
					///新建
					if($(this).val()==2){
							var mar_top=$(this).parent('ul').css('margin-top');
							var num_mar_top=parseInt(mar_top.substring(0,mar_top.length-2))+40;
							$(this).parent('ul').css('margin-top',num_mar_top+"px");
							var module="<li class='evil-li'>" ;
							module+= "<div class='evil-div'>";
							module+=  "<a>";
							module+="	<span class='evil-span'>";
							module+=		"<input class='evil-input-new' value='请修改类别名称'>" ;
							module+="	</span>";
							module+=  "</a>";
							module+= "</div>";
							module+="</li>";
							$(module).appendTo($("#navigation").find("ul"));
							$(".evil-input-new").focus();
							$(".evil-input-new").blur(function(e){
								var title_val=$(this).val();
								$("#navigation").find(".evil-li").last().find('span').html(title_val);
								//解除绑定事件
								$("#navigation").find("ul").find('li').unbind();
								//刷新 重新绑定事件
								LeftNavitationInit();
							});
					}else{
						$("#background_1").show().animate({'background-color':'beige','top':index*40+'px','border':'1px solid'},"slow");
						$("#background").hide();
					}
				}
			});
	});
}
function getContentText(){
	let child_li=$("#evil-content-title").children('li');
	
}
function wangEditInit(){
	 var E = window.wangEditor;
	 var editor = new E('#editor')
	 editor.customConfig.menus = [
		    'head',  // 标题
		    'bold',  // 粗体
		    'italic',  // 斜体
		    'underline',  // 下划线
		    'strikeThrough',  // 删除线
		    'foreColor',  // 文字颜色
		    'backColor',  // 背景颜色
		    'link',  // 插入链接
		    'list',  // 列表
		    'justify',  // 对齐方式
		   // 'quote',  // 引用
		    'emoticon',  // 表情
		    'image',  // 插入图片
		    'table',  // 表格
		    'video',  // 插入视频
		    'code',  // 插入代码
		    'undo',  // 撤销
		    'redo'  // 重复
		];
//	 editor.customConfig.uploadImgShowBase64 = true;
	 editor.customConfig.uploadImgServer = 'fileManager/fileUpload';
	 editor.customConfig.pasteFilterStyle = false;//
	 editor.customConfig.showLinkImg = false
	// editor.txt.html('<p>请从这书写内容</p>')
	 editor.customConfig.uploadImgHooks={
			// customInsert: function (insertLinkImg, result, editor) {
		        //     console.log('customInsert')
		        //     // 图片上传并返回结果，自定义插入图片的事件，而不是编辑器自动插入图片
		        //     const data = result.data1 || []
		        //     data.forEach(link => {
		        //         insertLinkImg(link)
		        //     })
		        // },
		        before: function before(xhr, editor, files) {
		            // 图片上传之前触发

		            // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
		            // return {
		            //     prevent: true,
		            //     msg: '放弃上传'
		            // }
		        },
		        success: function success(xhr, editor, result) {
		            // 图片上传并返回结果，图片插入成功之后触发
		        },
		        fail: function fail(xhr, editor, result) {
		            // 图片上传并返回结果，但图片插入错误时触发
		        	
		        },
		        error: function error(xhr, editor) {
		            // 图片上传出错时触发
		        },
		        timeout: function timeout(xhr, editor) {
		            // 图片上传超时时触发
		        }
		        ,customInsert:function(insertLinkImg,result, editor){
		        	if(result.imagesURI && result.imagesURI.push){
		        		$(result.imagesURI).each(function(index,URItem){
		        			insertLinkImg(projectName+URItem);
		        		});
		        	}
		        }
	 };
	 editor.create();
}
