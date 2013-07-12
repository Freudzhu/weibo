<%@page pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<html>
<head>
	<meta content='text/html; charset=UTF-8' http-equiv='content-type'>
	<title>用户查找</title>
</head>
<body>
<h1>用户列表</h1>
<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>
	<thead>
		<tr><th></th></tr>
	</thead>
	<tbody>
		<c:forEach var="u" items="${resultUser}">
		    	<tr>
			    	
			    	<td style='vertical-align:top;'>
                		${u.username}<br>
                		${u.email}<br>
                		<c:choose> 
                			<c:when test="${followRelation.get(u)}">
                			    <a href="/weibo//${user.uid}/attentions/unfollow?uuid=${u.uid}&query=${query}">取消关注</a>  
                			</c:when>
                			<c:otherwise>
                				<a href="/weibo//${user.uid}/attentions/follow?uuid=${u.uid}&query=${query}">加关注</a> 
       						</c:otherwise>
                		</c:choose>
                		<hr>
                	</td>
                		
		    	</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>