<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>
<html>
<head>
	<meta content='text/html; charset=UTF-8' http-equiv='content-type'>
	<title>Gossip 微网志</title>
	<link rel='stylesheet' href='css/member.css' type='text/css'>
</head>
<body>
	<div class='leftPanel'>
		<img src='image/caterpillar.jpg' alt='Gossip 微网志' /><br><br>
		<a href='logout.do?username="${ sessionScope.login }'>
                            登出 ${ sessionScope.login }</a>
	</div>
	<form method='post' action='publish'>
	分享新鲜事...<br>
	<textarea cols='60' rows='4' name='message'></textarea><br>
	<br>
	<button type='submit'>送出</button>
	</form>
	<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>
		<thead>
		<tr><th><hr></th></tr>
		</thead>
		<tbody>
	
		    	<tr>
			    	<td style='vertical-align:top;'>
			    	<a href='delete.do?message='>删除</a>
			    	<hr>
			    	</td>
		    	</tr>
		</tbody>
	</table>
	<hr style='width: 100%; height: 1px;'>
	</body>
	</html>