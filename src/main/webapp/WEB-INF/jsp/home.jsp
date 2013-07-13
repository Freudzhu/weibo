<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@taglib prefix="pageBar" tagdir="/WEB-INF/tags" %>
<html>
<head>
	<meta content='text/html; charset=UTF-8' http-equiv='content-type'>
	<title>Gossip 微网志</title>
	<link rel='stylesheet' href="${ctxtPath}/css/home.css" type='text/css'>
</head>
<body>
	<div class='leftPanel'>
		<img src="${ctxtPath}/image/caterpillar.jpg" alt='Gossip 微网志' /><br><br>
		<a href='/weibo/logout.do'>
                            登出 ${ sessionScope.login }</a>
	</div>
	<ul class="user_attent">
		<li>
			<span>关注 </span>
			<strong>(${attentionCount})</strong>
			
		</li>
		<li>
		    <span>粉丝 </span>
			<strong>(${followerCount})</strong>
			
		</li>
		<li>
		    <span>微博 </span>
			<strong>(${statusCount})</strong>
			
		</li>
	</ul>
	<br>
	<div class='rightPanel'>
		 <form id="search" method="get" action="/weibo/users/search">
              <input type="text" id="query" name="query" value=""/>
              <button type="submit">查找用户</button>
          </form>
	</div>
	<form method='post' action="/weibo/${user.getUid()}/statuses/publish">
	分享新鲜事...<br>
	<textarea cols='60' rows='4' name='message'></textarea><br>
	<br>
	<button type='submit'>送出</button>
	
	</form>
	
	
	<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>
		<thead>
		<tr><th></th></tr>
		</thead>
		<tbody>
				<c:forEach var="status" items="${statuses}">
		    		<tr>
			    	
			    		<td style='vertical-align:top;'>
                		${status.getUser().getUsername()}<br>
                		${status.getContent()}<br>
                		${status.getCreatetime()}<br>
                		<hr>
                		</td>
                		
		    		</tr>
		    		
		    	</c:forEach>
		</tbody>
	</table>
	<pageBar:PageBar pageAttrKey="pagedStatus"  pageUrl="/${user.getUid()}/statuses/timeline"/>
	<hr style='width: 100%; height: 1px;'>
	</body>
	</html>