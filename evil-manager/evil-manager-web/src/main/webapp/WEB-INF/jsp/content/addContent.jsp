<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/layui/css/layui.css"  media="all"> --%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/content/addContent.css"  media="all">
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/content/addContent.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/wangEdit/release/wangEditor.js"></script>
<title>新增</title>
</head>
<body>
		<div id="navigation" style="display: inline-block;position: absolute;" oncontextmenu="rightClick();">
			<ul >
				<div id="background" style="position: absolute;overflow: hidden;width: 200px;height: 40px;border-radius:0 20px 20px 0;background-color: beige;"></div>
				<li class="evil-li" >
					<div class="evil-div" >
						<a >
							<span class="evil-span">日记</span>
						</a>
					</div>
				</li>
				<li class="evil-li">
					<div class="evil-div" >
						<a >
							<span class="evil-span">随笔</span>
						</a>
					</div>
				</li>
			</ul>
		</div>
		<div>
			<ul id="new_btn" style="position: absolute;">
				<div id="background_1" hidden="true" style="position: absolute;overflow: hidden;width: 200px;height: 40px;border-radius:0 20px 20px 0;background-color: beige;"></div>
				<li class="evil-li">
					<div class="evil-div">
						<a ><span class="evil-span">回收站</span></a>
					</div>
				</li>
				<li  class="evil-li" value="2">
					<div class="evil-div"><a><span style="padding-left: 50px;position: absolute;">新建</span></a></div>
				</li>
			</ul>
		</div>
		<div class="evil-book" oncontextmenu="rightClick();">
			<ul id="evil-content-title">
			</ul>
		</div>
		<div style="display: inline-block;width: 1200px">
			<div style="padding-left: 370px">
				<div id="editor" style="width: 1050px;"></div>
			</div>
		</div>
		<div class="evil-edit-save-btn">
			<ul>
				<li>
					<div id="save-content-btn" class="evil-save-release-btn">保存</div>
				</li>
				<li><div id="release-content-btn" class="evil-save-release-btn">直接发布</div></li>
			</ul>
		</div>
</body>
</html>