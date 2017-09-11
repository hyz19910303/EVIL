<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		
		//var  test=document.getElementById("test");
		var map={};
		var test={};
		//map['name']="zhangsan";
		//map['age']="3";
		test["personinfo"]="张三";
		test["personinfo"]['name']="zhangsan";
		
	//	test.addEventListener("onclick", "test", false);
		function test1(){
			//alert(test);
			console.log(test);
		}
	
	</script>
</head>
<body>

	<form action="Sys/register.do" method="post">
		<p >
			用户名<input name="username" placeholder="请输入用户名" autofocus="autofocus" autocomplete="on">
		</p>
		<p >
			密码<input name="password" placeholder="请输入密码">
		</p>
		<p>
			<input type="submit" name="登入">
		</p>
	</form>
		<p>
			<input type="button" name="测试"  id="test" onclick="test1()">
		</p>
</body>
	
</html>