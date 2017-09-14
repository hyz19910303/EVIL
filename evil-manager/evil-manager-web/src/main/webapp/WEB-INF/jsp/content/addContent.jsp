<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/layui/css/layui.css"  media="all">
<title>新增</title>
</head>
<body>
	<div style="display: inline-block;position: absolute;">
		<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="margin-right: 10px;">
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">默认展开</a>
			    <dl class="layui-nav-child">
			      <dd><a href="javascript:;">选项一</a></dd>
			      <dd><a href="javascript:;">选项二</a></dd>
			      <dd><a href="javascript:;">选项三</a></dd>
			      <dd><a href="">跳转项</a></dd>
			    </dl>
			  </li>
			  <li class="layui-nav-item">
			    <a href="javascript:;">解决方案</a>
			    <dl class="layui-nav-child">
			      <dd><a href="">移动模块</a></dd>
			      <dd><a href="">后台模版</a></dd>
			      <dd><a href="">电商平台</a></dd>
			    </dl>
			  </li>
			  <li class="layui-nav-item"><a href="">云市场</a></li>
			  <li class="layui-nav-item"><a href="">社区</a></li>
			</ul>
</div>
<div style="display: inline-block;position: absolute;width: 1200px">
	<div style="padding-left: 210px">
		<textarea class="layui-textarea" id="LAY_demo1" style="display: none">  
		</textarea>
	</div>
</div>
<script type="text/javascript">
	layui.use(['element','layedit'], function(){
	  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
	  var layedit = layui.layedit;
	  //监听导航点击
	  element.on('nav(demo)', function(elem){
	    layer.msg(elem.text());
	  });

	 //富文本编辑器
		 var index = layedit.build('LAY_demo1');
		  
		  //编辑器外部操作
		  var active = {
		    content: function(){
		      alert(layedit.getContent(index)); //获取编辑器内容
		    }
		    ,text: function(){
		      alert(layedit.getText(index)); //获取编辑器纯文本内容
		    }
		    ,selection: function(){
		      alert(layedit.getSelection(index));
		    }
		  };
		  
		  $('.site-demo-layedit').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
	});
</script>
</body>
</html>