//右键点击事件变量
var edit_flag=false,category,title;//

//页面加载后初始化
$(function(){
//	TreeInit();
	LeftNavitationInit();
	cursorInit();
	var E = window.wangEditor;
	var editor = new E('#editor')
	wangEditInit(editor);
	pageSizeAutoResize();
	saveOrReleaseContent(editor,"save-content-btn","contentManager/Save","1");
	saveOrReleaseContent(editor,"release-content-btn","contentManager/Release","2");
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
					var li=$(liItem);
					category=li.text().trim();
					$("#evil-content-title").empty();
					//模拟数据
					let content_title="";
					var arr=[{"title":"书架"},{"title":"实践论"},{"title":"死亡"}];
					for ( var e in arr) {
						content_title+="<li class='evil-book-li' onclick='getArticleTitle(this)'><a ><span class='evil-span'>"+arr[e]['title']+"</span></a></li>"
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
/**
 * 
 * <p>MethodName: getArticleTitle</p>
 * <p>Description: 获取文章的标题</p>
 * @param that
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年10月12日
 * @version 1.0
 * Create At 2017年10月12日 下午5:10:12
 */
function getArticleTitle(that){
	title=$(that).text();
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
								//插入数据库  to-do
								commonRequestData('contentManager/saveCategory',{category:title_val},'json',function(res){
									alert(res);
								});
								
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
function wangEditInit(editor){
	 
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
		   // 'video',  // 插入视频
		    'code',  // 插入代码
		    'undo',  // 撤销
		    'redo'  // 重复
		];
//	 editor.customConfig.uploadImgShowBase64 = true;
	 editor.customConfig.uploadImgServer = 'fileManager/fileUpload';
	// editor.customConfig.pasteFilterStyle = true;//
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
};
/**
 * 
 * <p>MethodName: pageSizeAutoResize</p>
 * <p>Description:页面元素大小根据屏幕自适应 未做完 </p>
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月27日
 * @version 1.0
 * Create At 2017年9月27日 上午10:45:35
 */
function pageSizeAutoResize(){
	var availHeight=window.screen.availHeight;
	var availWidth=window.screen.availWidth;
//	var pixelDepth=window.screen.pixelDepth;
//	var deviceXDPI=window.screen.deviceXDPI;
	var child_div=$("#editor").children('div');
	$(child_div).each(function(index,item){
		if(availWidth>1440){
			$(item).css({'width':availWidth-500+'px'});
			if(index!=0){
				$(item).css({'height':availHeight-150+'px'});
			}
		}else{
			$("#navigation").find()
		}
	});
}
/**
 * 
 * <p>MethodName: saveOrReleaseContent</p>
 * <p>Description:保存或者发布文章 </p>
 * @returns
 * 
 * @example
 *
 * @author EVIL
 * @date 2017年9月27日
 * @version 1.0
 * Create At 2017年9月27日 上午10:46:55
 */
function saveOrReleaseContent(editor,id,uri,data){
	$("#"+id).on('click',function(e){
		var text=editor.txt.text();
		if(!text || !text.trim() || !text.replaceAll("&nbsp;","")){
			//TO-DO  提示
			alert("内容不能为空");
			return;
		};
		var html=editor.txt.html();
		if(!category || !title){
			alert("选择标题或者分类");
			return 
		}
		var la_data={
				'category':category,
				'title':title,
				'content':html,
				'flag':data
		};
		debugger
		try{
			$.ajax({
				url:uri,
				"data":la_data,
				dataType:"json",
				type:"post",
				success:function(res){
					if(res.resultInfo==='success'){
						editor.txt.html("");
					}else if(res.resultInfo==='fail'){
						//TO-DO
					}else{
						//TO-DO
						
					}
				},
				error:function(rea){
					console.log(res);
				}
			});
		}catch(e){
			
		}
		
	});
}
