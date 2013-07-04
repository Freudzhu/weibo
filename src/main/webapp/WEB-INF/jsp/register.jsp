<%@page pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<html>
    <head>
        <title>Gossip 微网志</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/register.css" type="text/css">
        <script src="../js/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="../js/jquery.validate.min.js" type="text/javascript"></script>
        <script type="text/javascript">
	        jQuery(function($){
	        	$('#registerForm').validate({
    				debug: false,
    	            onkeyup: false,
    	            onblur: false,
    	            errorElement: "label",
    	            errorPlacement: function(error, element) {
    	                error.appendTo(element.nextAll('span.errMsg'));
    	            },
    	            rules : {
    	            	email:{required : true , email : true},
    	            	username:{required : true , maxlength:16},
    	            	initPassword:{required : true , minlength: 6,maxlength:16},
    	            	confirmedPasswd:{required : true , minlength: 6,maxlength:16,equalTo: "#initPassword"}
    	            },
    	            messages: {
    	            	email:{required: "请输入Email地址",email: "请输入正确的email地址"},
    	            	username:{required : "请输入用户名" , maxlength: jQuery.validator.format("名称不能大于{0}个字 符")},
    	            	initPassword: {required: "请输入密码",minlength: jQuery.validator.format("密码不能小于{0}个字 符"),maxlength: jQuery.validator.format("密码不能大于{0}个字 符")},
    	            	confirmedPasswd: {required: "请输入确认密码",minlength:  jQuery.validator.format("密码不能小于{0}个字 符"),maxlength: jQuery.validator.format("密码不能大于{0}个字 符"),equalTo: "两次输入密码不一致"}
    	            }
    			});
					$('#registerButton').click(function(){
	        		
	        		$('#registerForm').submit();
	        	});	
	        });
        	

        </script>
    </head>
    <body>
    <c:if test="${requestScope.errors != null}">
        <h1>新增会员失败</h1>
        <ul style='color: rgb(255, 0, 0);'>
            <c:forEach var="error" items="${requestScope.errors}">
                <li>${error}</li>
            </c:forEach>
        </ul><br>
    </c:if>
        <h1>会员注册</h1>
        <form id='registerForm' method='post' action='/weibo/register.do'>
            <table bgcolor=#cccccc>
                <tr>
                    <td>邮件位址：</td>
                    <td><input type='text' name='email' value='${param.email}' size='25' ><span class="errMsg"></span>
                    
                    </td>
                </tr>
                <tr>
                    <td>名称（最大16字符）：</td>
                    <td><input type='text' name='username' value='${param.username}' size='25' ><span class="errMsg"></span></td>
                    
                </tr>
                <tr>
                    <td>密码（6到16字符）：</td>
                    <td><input type='password' id='initPassword' name='initPassword' size='25' ><span class="errMsg"></span>
                    
                    </td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input type='password' name='confirmedPasswd' size='25' maxlength='16'><span class="errMsg"></span></td>
                    
                </tr>
                <tr>
                    <td colspan='2' align='center'><input id='registerButton' type='button' value='注册'></td>
                </tr>
            </table>
        </form>
    </body>
</html>