<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@taglib prefix="pageBar" tagdir="/WEB-INF/tags" %>
<html>
<head>
	<meta content='text/html; charset=UTF-8' http-equiv='content-type'>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Gossip 微网志</title>
	<link rel='stylesheet' href="${ctxtPath}/css/home.css" type='text/css'>
	<link type="text/css" href="${ctxtPath}/css/jquery.ui.all.css" rel="stylesheet" />
	<!-- Bootstrap -->
    <link href="${ctxtPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
	<!-- 
	<script src="/weibo/js/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/weibo/js/jquery.ui.core.js"></script>
  	<script type="text/javascript" src="/weibo/js/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="/weibo/js/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="/weibo/js/jquery.ui.button.js"></script>
	<script type="text/javascript" src="/weibo/js/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="/weibo/js/jquery.ui.position.js"></script>
	<script type="text/javascript" src="/weibo/js/jquery.ui.dialog.js"></script>
	-->
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="/weibo/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
			setInterval("hasNewMessage()",3000);
			
			//是menu和微博的内容登高,chrome下面需要延迟等待元素加载完毕
			setTimeout(function() {
				var contentheight = jQuery("#content").height();
	        	var menuheight = jQuery("#menu").height();
	        	if(contentheight > menuheight ) {
	            	jQuery("#menu").height(contentheight);
	            	jQuery("#userinfo").height(contentheight);
	        	}
	        	else {
	            	jQuery("#content").height(menuheight);
	            	jQuery("#userinfo").height(menuheight);
	        	}
   		  }, 100);
			 $(".wb_form").on('click','.do_comment',function(){
				  var comment_detail = $(this).closest(".wb_bottom").find(".comments_detail");
				  var statuesid = comment_detail.data('statusid');
				  var comments_area="<div class='comments_area'><textarea class='comment_content'></textarea>";
				  var button = "<button class='comment_button' type='button'>评论</button></div><div class='commment_list'></div>";
				  
				  if(comment_detail.find(".comment_content").length > 0){
					  comment_detail.empty();
				  }
				  else{
					  comment_detail.append(comments_area+button);
					  var comments_list = comment_detail.find(".commment_list");
					  $.ajax({ 
				          "url" : "/weibo/${user.getUid()}/statuses/commment?statuesid="+statuesid, 
				          "type" : "GET", 
				          "dateType" : "json", 
				          "success" : function(data) { 
				        	  $.each(data,function(idx,item){   
				        		  var comment = "<dl style='clear:left'><dt><img src='${ctxtPath}/image/1.jpg'</dt><dd style='font-size: 15px;font-weight: bold;'>"+item.user.username+":"+item.content+"</dd></dl>";
				        		  comments_list.append(comment);
				          	});
				          }
				        	 
					  });
				  }
			  });
			  $(".comments_detail").on('click','.comment_button',function(event){
				  event.preventDefault();
				  event.stopPropagation();
				  var statuesid = $(this).parent().parent().data('statusid');
				  var comment_detail = $(this).closest(".comments_detail");
				  var comment = comment_detail.find(".comment_content");
				  $.post("/weibo/${user.getUid()}/statuses/commment",
						  {
					  			statues_id:statuesid,
					  			comment_content:comment.val()
						  },
						  function(data,status){
							if(status == 'success'){
								comment.val("");
								var comments_list = comment_detail.find(".commment_list");
								comments_list.empty();
								$.ajax({ 
							          "url" : "/weibo/${user.getUid()}/statuses/commment?statuesid="+statuesid, 
							          "type" : "GET", 
							          "dateType" : "json", 
							          "success" : function(data) { 
							        	  $.each(data,function(idx,item){   	   
							        		  var comment = "<dl style='clear:left'><dt><img src='${ctxtPath}/image/1.jpg'</dt><dd style='font-size: 15px;font-weight: bold;'>"+item.user.username+":"+item.content+"</dd></dl>";
							        		  comments_list.append(comment);	  
							          	  });
							        	  var commnetCount = +comment_detail.prev().find('.do_comment').data('commment-count');
						        		  commnetCount = commnetCount + 1
						        		  comment_detail.prev().find('.do_comment').data('commment-count',commnetCount);
						        		  comment_detail.prev().find('.do_comment').text("评论("+commnetCount+")");
							          }
							        	 
								  });
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
			  $( "#dialog-form" ).dialog({
			      autoOpen: false,
			      height: 300,
			      width: 500,
			      modal: true,
			      buttons: {
			    	      "转发": function() {
			    	    	  $.post("/weibo/${user.getUid()}/statuses/publish",
									  {
										message:$(this).find('#message').val(),
										fowardid:$(this).find('#forwardid').val()
									  },
									  function(data,status){
										if(status == 'success'){
											$('#dialog-form').dialog('close');
										}
									   
							  });
			    	      }
			     }
			   });
			  $(".wb_form").on('click','.do_forwrad',function(){
				  var content = $(this).closest('.wb_detail').find('.wb_text').text();
				  var forwardid;
				  if($(this).closest('.wb_detail').find('.forwardstatus').length != 0){
					  forwardid = $(this).closest('.wb_detail').find('.forwardstatus').data('statusid');
				  }
				  else{
					  forwardid = $(this).closest('.wb_bottom').find('.comments_detail').data('statusid');
				  }
				  $("#dialog-form").find('.content').text(content);
				  $("#dialog-form").find('#forwardid').val(forwardid);
				  $("#dialog-form" ).dialog( "open" );
			  });
			  
	    });	
		function hasNewMessage(){
			 $.ajax({ 
		          "url" : "/weibo/${user.getUid()}/message/newMessageCount", 
		          "type" : "GET", 
		          "dateType" : "json", 
		          "success" : function(data) { 
		        	  if(data != '0');{
		        		  $('.messageCount').text("您有"+data+"新消息了");
		        		  $('.dropdown-toggle').click();
		        	  }
		          }
		        	 
			  });
		}
		
	</script>
</head>
<body>
<div id="container">
	<div id="header" class="navbar">
		<div class="navbar-inner">
		<div class="container">
     		<div class="nav-collapse collapse">
        	<!-- .nav, .navbar-search, .navbar-form, etc -->
        		<ul class="nav" style="margin-left:450px;">
 					 <li class="active">
    			 		<a href="/weibo/${user.getUid()}/statuses/timeline?pageNo=1">首页</a>
  				 	</li>
  					<li><a href="#">Link</a></li>
  					<li><a href="#">Link</a></li>
				</ul>
				<form class="navbar-search pull-left" method="get" action="/weibo/users/search">
  					<input type="text" class="search-query" placeholder="Search" id="query" name="query">
				</form>
				<ul class="nav pull-right" style="margin-right:400px;">
					<li class="dropdown">
						<a  id="messageReminder" class="dropdown-toggle" data-toggle="dropdown" data-target="">
      						消息
     					<b class="caret"></b>
    					</a>
    					<ul class="dropdown-menu">
    						<li class="messageCount">您有0条消息</li>
   					 	</ul>
    				</li>
				</ul>
      		</div>	
		</div>
	</div>
		

	</div>
	<div id="menu">
		<ul class="nav nav-list" style="margin-top:20px">
			<li class="active"><a href="/weibo/${user.getUid()}/statuses/timeline?pageNo=1">首页</a></li>
			<li class="divider"></li>
			<li><a href="/weibo/${user.getUid()}/message?pageNo=1">消息</a></li>
			<li class="divider"></li>
			<li>收藏</li>
			<li class="divider"></li>
		</ul>
	</div>
	<div id="content">
			<form method='post' action="/weibo/${user.getUid()}/statuses/publish">
				<h3>分享新鲜事</h3><br>
				<textarea cols='500' rows='5' id='message' style="width:500px"></textarea>
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
						<c:if test="${status.getForward().getUser()!=null}">
							<div class="forwardstatus" data-statusid='${status.getForward().getId()}'>
								<div class="wb_detail" >
									<div class="acoount_name">
										${status.getForward().getUser().getUsername()}
									</div>
									<div class="wb_text">
										${status.getForward().getContent()}
									</div>
								</div>
								<div class="wb_bottom">
									<div class="wb_forward_time">
										${status.getForward().getCreatetime()}
									</div>
									<div class="wb_forward_form">
										<a href="javascript:void(0);">赞(12)</a>
										<i class="S_txt3">|</i>
										<a href="javascript:void(0);" >转发(${status.getForward().getForwardCount()})</a>
										<i class="S_txt3">|</i>
										<a href="javascript:void(0);">收藏(2)</a>
										<i class="S_txt3">|</i>
										<a href="javascript:void(0);" class="do_comment" data-commment-count='${status.getForward().getCommmentCount()}'>评论(${status.getForward().getCommmentCount()})</a>								
									</div>
								</div>
								<br style="clear:both;" /> 
							</div>
						</c:if>
						<div class="wb_bottom">
							<div class="wb_time">
								${status.getCreatetime()}
							</div>
							<div class="wb_form">
								<a href="javascript:void(0);">赞(12)</a>
								<i class="S_txt3">|</i>
								<a href="javascript:void(0);" class="do_forwrad">转发(${status.getForwardCount()})</a>
								<i class="S_txt3">|</i>
								<a href="javascript:void(0);">收藏(2)</a>
								<i class="S_txt3">|</i>
								<a href="javascript:void(0);" class="do_comment" data-commment-count='${status.getCommmentCount()}'>评论(${status.getCommmentCount()})</a>								
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
<div id="dialog-form" title="转发微博" hidden="true">
    <label for="content">原微博：</label>
    <p class="content"></p>
   	<textarea cols='60' rows='4' id='message' style="width:500px"></textarea><br>
   	<input type='hidden' id='forwardid' VALUE="">
	<br>
</div>
</body>
</html>