<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@taglib prefix="pageBar" tagdir="/WEB-INF/tags" %>
<html>
<head>
	<meta content='text/html; charset=UTF-8' http-equiv='content-type'>
	<title>Gossip 微网志</title>
	<link rel='stylesheet' href="${ctxtPath}/css/home.css" type='text/css'>
	<script src="/weibo/js/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			  $(".wb_form").on('click','.do_comment',function(){
				  var comments_area="<textarea class='comment_content'></textarea>";
				  var button = "<button class='comment_button' type='button'>评论</button>";
				  var comment_detail = $(this).closest(".wb_bottom").find(".comments_detail");
				  if(comment_detail.find(".comment_content").length > 0){
					  comment_detail.empty();
					  
				  }
				  else{
					  comment_detail.append(comments_area+button);
				  }
			  });
			  $(".comments_detail").on('click','.comment_button',function(){
				  var statuesid = $(this).parent().data('statusid');
				  var comment = $(this).closest(".comments_detail").find(".comment_content");
				  $.post("/weibo/${user.getUid()}/statuses/commment",
						  {
					  			statues_id:statuesid,
					  			comment_content:comment.val()
						  },
						  function(data,status){
							if(status == 'success'){
								comment.val("");
								alert("评论成功");
							}
						   
				});
				  
			  }); 
			  $("#share").click(function(){
					$.post("/weibo/${user.getUid()}/statuses/publish",
							  {
								message:$('#message').val()
							  },
							  function(data,status){
								if(status == 'success'){
									alert("发送成功");
									$('#message').val("");
								}
							   
							  });
				});
			  
	    });	
		
	</script>
</head>
<body>
<div id="container">
	<div id="header">
		<h1>XX微博</h1>
	</div>
	<div id="menu">
		<ul>
			<li>首页</li>
			<li>消息</li>
			<li>收藏</li>
		</ul>
	</div>
	<div id="content">
			<img src="${ctxtPath}/image/caterpillar.jpg" alt='Gossip 微网志' /><br><br>
			<div class='rightPanel'>
			 <form id="search" method="get" action="/weibo/users/search">
            	  <input type="text" id="query" name="query" value=""/>
              	  <button type="submit">查找用户</button>
          	</form>
		</div>
		<form method='post' action="/weibo/${user.getUid()}/statuses/publish">
			分享新鲜事...<br>
			<textarea cols='60' rows='4' id='message'></textarea><br>
			<br>
			<button type='button' id="share">送出</button>
	
		</form>
			<c:forEach var="status" items="${statuses}">
				<div class="wb_feed_listitem">
					<div class="account_image">
						<img src="${ctxtPath}/image/1.jpg"/>
					</div>
					<div class="wb_detail">
				
						<div class="acoount_name">
							${status.getUser().getUsername()}
						</div>
						<div class="wb_text">
							${status.getContent()}
						</div>
						<div class="wb_bottom">
							<div class="wb_time">
								${status.getCreatetime()}
							</div>
							<div class="wb_form">
								<a href="javascript:void(0);">赞(12)</a>
								<i class="S_txt3">|</i>
								<a href="javascript:void(0);">转发(13)</a>
								<i class="S_txt3">|</i>
								<a href="javascript:void(0);">收藏(2)</a>
								<i class="S_txt3">|</i>
								<a href="javascript:void(0);" class="do_comment">评论(10)</a>
								
							</div>
							<div class="comments_detail" data-statusid='${status.getId()}'>
							
							</div>
						</div>
						
					
					</div>
				</div>
			</c:forEach>
			<pageBar:PageBar pageAttrKey="pagedStatus"  pageUrl="/${user.getUid()}/statuses/timeline"/>
			<hr style='width: 100%; height: 1px;'>
			
			
	</div>
	<div id="userinfo">
		<a href='/weibo/logout.do'>登出 ${ sessionScope.login }</a>
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
	</div>
	<div id="footer">CopyRight zhuhiahuan</div>
</div>
</body>
</html>