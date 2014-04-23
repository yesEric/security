<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>LOGIN</title>
</head>

<body>
	<h3>User List:</h3>

	<table>
		<tr><td>USERNAME</td><td>PASSWORD</td><td>ENABLED</td><td>ROLE</td></tr>
		<tr><td>administrator</td><td>111</td><td>true</td><td>ROLE_SUPERMAN</td></tr>
		<tr><td>scott</td><td>222</td><td>true</td><td>ROLE_ADMIN</td></tr>
		<tr><td>peter</td><td>333</td><td>true</td><td>ROLE_USER</td></tr>
		<tr><td>rod</td><td>444</td><td>false</td><td>ROLE_USER</td></tr>
	</table>
	
	<c:if test="${param.sign!=null}">
		<!-- <font color="red">用户名密码错误</font>	 -->
		<p><font color="red">${param.sign}</font></p>
	</c:if>
	<form id="loginForm" name="loginForm" method="post" action="login.htm" >
		<p><label>账 号：</label><input class="input_txt" type="text" name="loginName" id="loginName"  /></p>
		<p><label>密 码：</label><input class="input_txt" type="password" name="password" id="password"  /></p>			
		<p><input value="提交" type="submit"></input></p>
	</form>
</body>
</html>


