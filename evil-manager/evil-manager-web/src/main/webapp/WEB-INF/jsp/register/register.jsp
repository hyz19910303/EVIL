<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/layui/css/layui.css"  media="all">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/jsp/register/register.css"  media="all">
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jsp/register/register.js"></script>
</head>
<body>
	<div style="display: block;margin-left: 200px;margin-right: 200px;background-color: #fbf7f7;height: 100%">
		<div id="register_main" style="padding-top: 80px;">
			<div style="margin:40px 50px 40px 50px ;height: 500px;">
				<div  id="border_div" style="display: inline-block;background-color: white;height: 500px;width: 600px">
					<div class="layui-carousel" id="test10" style="display: inline-block;">
						  <div id="carousel_item" carousel-item="" style="border-radius: 5px;">
						  	
						  </div>
					</div>
				</div>
				<div style="display: inline-block;background-color: white;height: 500px;width: 10px"></div>
				<div style="display: inline-block;background-color: #fefefe;height: 500px;width: 400px;box-sizing:content-box;border-radius: 5px;border: 1px solid;position: absolute;">
					<div class="layui-tab layui-tab-brief" >
						  <ul class="layui-tab-title">
						    <li class="layui-this" style="width: 170px" >
							    <span class="evil-regiser-title">注册账号</span>
						    </li>
						    <li style="width: 170px" >
						    	<span class="evil-regiser-title">用户登入</span>
						    </li>
						  </ul>
						  <div class="layui-tab-content" style="height: 100px;">
						    <div class="layui-tab-item layui-show">
						    	<div style="position: inherit;margin: 20px 5px 0 5px;height: 300px">
						    		<div style="margin: 30px 20px 0 20px ;height: 250px">
						    			<form class="layui-form" method="post">
											  <div class="layui-form-item">
											    <label class="layui-form-label" style="padding-left: 0px">用&nbsp;户&nbsp;名<span style="color: red">*</span></label>
											    <div class="layui-input-inline ">
											      <input  type="text" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
											    </div>
											    <span id="username_msg" class="evil-warn-msg"></span>
											  </div>
											  <div class="layui-form-item">
											    <label class="layui-form-label" style="padding-left: 0px">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码<span style="color: red">*</span></label>
											    <div class="layui-input-inline">
											      <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
											    </div>
											    <span id="password_msg" class="evil-warn-msg"></span>
											  </div>
											  <div class="layui-form-item">
											    <label class="layui-form-label" style="padding-left: 0px">确认密码<span style="color: red">*</span></label>
											    <div class="layui-input-inline">
											      <input type="password" name="confirm_password" lay-verify="required" placeholder="请再次输入密码" autocomplete="off" class="layui-input">
											    </div>
											    <span id="confirm_password_msg" class="evil-warn-msg"></span>
											  </div>
											  <div class="layui-form-item">
											    <div class="layui-inline">
											      <label class="layui-form-label" style="padding-left: 0px">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</label>
											      <div class="layui-input-inline">
											        <input type="tel" name="phone" lay-verify="phone" placeholder="请输入手机号"  autocomplete="off" class="layui-input">
											      </div>
											    </div>
											    <div class="layui-inline">
											      <label class="layui-form-label" style="padding-left: 0px">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label>
											      <div class="layui-input-inline">
											        <input type="text" name="email"   placeholder="请输入邮箱" autocomplete="off" class="layui-input">
											      </div>
											    </div>
											  </div>
											  <div class="layui-form-item">
											    <div class="layui-input-block">
											      <button class="layui-btn" lay-submit="" lay-filter="rejister">注册</button>
											      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
											    </div>
											  </div>
										</form>
						    		</div>
						    	</div>
						    </div>
						    <div class="layui-tab-item">
						    	<div style="position: inherit;margin: 20px 5px 0 5px;height: 300px">
						    		<div style="margin: 30px 20px 0 20px ;height: 250px">
								    	<form class="layui-form" method="post">
											  <div class="layui-form-item">
											    <label class="layui-form-label" style="padding-left: 0px">用&nbsp;户&nbsp;名<span style="color: red">*</span></label>
											    <div class="layui-input-inline ">
											      <input  type="text" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
											    </div>
											    <span id="username_msg" class="evil-warn-msg"></span>
											  </div>
											  <div class="layui-form-item">
											    <label class="layui-form-label" style="padding-left: 0px">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码<span style="color: red">*</span></label>
											    <div class="layui-input-inline">
											      <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
											    </div>
											    <span id="password_msg" class="evil-warn-msg"></span>
											  </div>
											  <div class="layui-form-item">
											    <label class="layui-form-label" style="padding-left: 0px">验&nbsp;证&nbsp;码<span style="color: red">*</span></label>
											    <div class="layui-input-inline" style="width: 100px">
											      <input type="text" name="verifyCode" lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
											    </div>
												  <div style="margin: 1px 0 0 0;height: 36px;width: 80px;display: inline-block;cursor: pointer;">
												  	<img id="verifyCode" style="height: 36px;width: 80px" alt="" src="/evil/verifyCode">
												  </div>
											  </div>
											  <div class="layui-form-item">
											    <div class="layui-input-block">
											      <button class="layui-btn" style="margin-left: 40px" lay-submit="" >登&nbsp;&nbsp;入</button>
											    </div>
											  </div>
										</form>
										</div>
									</div>
						     </div>
						  </div>
					</div> 
				</div>
			</div>
		</div>
	</div>
</body>
</html>