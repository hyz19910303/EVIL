<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="e"%>
<%@ include file="../include/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/index.js"></script>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/js/index.css"
	media="all">
<script type="text/javascript">
	layui.use([ 'layer', 'form' ], function() {
		var layer = layui.layer, form = layui.form;
	});
</script>
<title>首页</title>
</head>
<body >
	<div class="evil-header">
		<div class="evil-logo">LOGO</div>
		<ul class="evil-nav evil-layout-left">
			<li class="evil-nav-item" style="font-family: cursive;padding-top: 6px">
				<a href="javascript:void(0);">控制台</a>
			</li>
			<li class="evil-nav-item" style="padding-top: 6px">
				<a href="javascript:void(0);" style="margin-top: 0px">控制台</a>
				<dl class="evil-nav-child evil-anim evil-anim-upbit" >
		          <dd><a href="" style="font-size: 14px">上传</a></dd>
		          <dd><a href="" style="font-size: 14px">下载</a></dd>
		        </dl>
			</li>
			<li class="evil-nav-item" style="padding-top: 6px">
				<a href="javascript:void(0);" >管理</a></li>
		</ul>
		<ul class="evil-nav evil-layout-right">
			<li class="evil-nav-item">
				<a href="javascript:void(0);" style="font-size: 14px;">
					<img alt="" src="image/spack.png" class="evil-nav-img" >
					管理员
				</a>
				 <dl class="evil-nav-child evil-anim evil-anim-upbit" >
		          <dd><a href="" style="font-size: 14px">基本资料</a></dd>
		          <dd><a href="" style="font-size: 14px">安全设置</a></dd>
		          <dd><a href="" style="font-size: 14px">退出</a></dd>
		        </dl>
			</li>
			<li class="evil-nav-item">
				<a href="register"  style="font-size: 14px;padding-top: 15px">
					注册
				</a>
			</li>
			<li class="evil-nav-item">
				<a href="javascript:void(0);" style="font-size: 14px;padding-top: 15px">
					登入
				</a>
			</li>
		</ul>
	</div>
	<div class="evil-body evil-common" style="display: block;">
		<div style="padding-top: 80px;">
			<div class="evil-main">
				<h4 style="z-index: 100;padding-top: 200px;padding-left: 200px;font-size: 40px;color: red;"></h4>
			</div>
		</div>
	</div>
	<footer class="evil-footer evil-common">
		<div style="padding-top: 20px;">
			<div style="background-color:navajowhite;height: 60px">
				<div style="background-color: #fff;margin-left:200px;margin-right:200px;height: 60px">
					
				</div>
			</div>
		</div>
	</footer>
</body>
</html>