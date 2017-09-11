<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%-- <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/layui/css/layui.css"  media="all"> --%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/js/index.css"  media="all">
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jQuery/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/vue/vue.js"></script>
<script type="text/javascript">
	var path="${pageContext.servletContext.contextPath }";
	//当前的全部路径
	var curWwwPath=window.document.location.href;  
    var pathName=window.document.location.pathname;  
    //获取ip地址
    var host=window.document.location.host;  
    //获取ip地址和端口号，如： http://localhost:8083  
    var pos=curWwwPath.indexOf(pathName);  
    var localhostPath=curWwwPath.substring(0,pos);  
    //获取带"/"的项目名，如：/evil  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
	//项目的绝对路径 http://localhost:8080/evil
	var projectPath=localhostPath+projectName;
	
</script>
</head>
<body>
</body>
</html>