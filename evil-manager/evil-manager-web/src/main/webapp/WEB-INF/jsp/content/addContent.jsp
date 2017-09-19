<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/layui/css/layui.css"  media="all">
<%-- <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/ztree/css/zTreeStyle/zTreeStyle.css"  media="all"> --%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/content/addContent.css"  media="all">
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/content/addContent.js"></script>
<title>新增</title>
</head>
<body>
		<div id="navigation" style="display: inline-block;position: absolute;" oncontextmenu="rightClick();">
			<ul >
				<div id="background" style="position: absolute;overflow: hidden;width: 200px;height: 40px;border-radius:0 20px 20px 0;background-color: beige;"></div>
				<li class="evil-li" >
					<a >
						<span class="evil-span">日记</span>
					</a>
				</li>
				<li class="evil-li">
					<a >
						<span class="evil-span">随笔</span>
					</a>
				</li>
			</ul>
		</div>
		<div>
			<ul id="new_btn" style="position: absolute;margin-top: 82px">
				<li class="evil-li">
					<a ><span class="evil-span">回收站</span></a>
				</li>
				<li  class="evil-li" value="2">
					<a><span style="padding-left: 50px;position: absolute;">新建</span></a>
				</li>
			</ul>
		</div>
		<div class="evil-book" oncontextmenu="rightClick();">
			<ul >
				<li class="evil-book-li"><a ><span class="evil-span">书架</span></a></li>
			</ul>
		</div>
		<div style="display: inline-block;width: 1200px">
			<div style="padding-left: 420px">
				<textarea class="layui-textarea" id="LAY_demo1" style="display: none" >  
				</textarea>
			</div>
		</div>
</body>
</html>