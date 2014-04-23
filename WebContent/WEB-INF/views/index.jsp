<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="d" uri="/WEB-INF/tag/securityresource.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>INDEX</title>
</head>
<body>
<h3>This is the index page.</h3>
<h4>${welcomewords }</h4>
<p><font color="red"><a href="${pageContext.request.contextPath }/logout.htm">logout</a></font></p>
<table>
	<tr><td>ResourceName</td><td>Role</td><td>Must Login?</td><td>URL</td></tr>
	<tr><td>hello.htm</td><td>ALL</td><td>yes</td><td><d:ss resource="/hello.htm" type="a" request="${pageContext.request }" otherAttribute='href="${pageContext.request.contextPath }/hello.htm"' value="hello" /></td></tr>
	<tr><td>super.htm</td><td>supman</td><td>yes</td><td><d:ss resource="/super.htm" type="a" request="${pageContext.request }" otherAttribute='href="${pageContext.request.contextPath }/super.htm"' value="super" /></td></tr>
	<tr><td>admin.htm</td><td>supman/admin</td><td>yes</td><td><d:ss resource="/admin.htm" type="a" request="${pageContext.request }" otherAttribute='href="${pageContext.request.contextPath }/admin.htm"' value="admin" /></td></tr>
	<tr><td>user.htm</td><td>supman/admin/user</td><td>yes</td><td><d:ss resource="/user.htm" type="a" request="${pageContext.request }" otherAttribute='href="${pageContext.request.contextPath }/user.htm"' value="user" /></td></tr>
</table>
</body>
</html>