<%@ tag pageEncoding="UTF-8" %> 
<!--① 声明JSTL标签，以便在本标签中使用之--> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!--② 定义了两个标签属性--> 
<%@ attribute name="pageUrl" required="true" rtexprvalue="true"   
description="分页页面对应的URl" %> 
<%@ attribute name="pageAttrKey" required="true" rtexprvalue="true"   
description="Page对象在Request域中的键名称" %> 
<c:set var="pageUrl" value="${pageUrl}" /> 
<!--③ 将一些标签中需要引用的对象放置到PageContext属性列表中，  
以便后面可以直接通过EL表达式引用之--> 
<%  
   String separator = pageUrl.indexOf("?") > -1?"&":"?";  
   jspContext.setAttribute("pageResult", request.getAttribute(pageAttrKey));  
   jspContext.setAttribute("pageUrl", pageUrl);  
   jspContext.setAttribute("separator", separator);  
%> 
<!--④ 构造分页导航栏--> 
<div style="font:12px;background-color:#DDDDDD"> 
  	  共${pageResult.totalPage}页，第${pageResult.pageNo}页  
    <c:if test="${pageResult.pageNo <=1}"> 首页&nbsp;&nbsp;  
    </c:if> 
    <c:if test="${pageResult.pageNo >1 }"> 
       <a href="<c:url value="${pageUrl}"/>${separator}pageNo=1">首页  </a>&nbsp;&nbsp;  
    </c:if> 
    <c:if test="${pageResult.hasPreviousPage()}"> 
      <a href="<c:url value="${pageUrl}"/>${separator}pageNo=${pageResult.pageNo -1 }">上一页  </a>&nbsp;&nbsp;  
    </c:if> 
    <c:if test="${!pageResult.hasPreviousPage()}"> 上一页&nbsp;&nbsp;  
    </c:if> 
    <c:if test="${pageResult.hasNextPage()}"> 
    	<a href="<c:url value="${pageUrl}"/>${separator}pageNo=${pageResult.pageNo +1 }">下一页  </a>&nbsp;&nbsp;  
    </c:if> 
    <c:if test="${!pageResult.hasNextPage()}"> 
      	下一页&nbsp;&nbsp;  
    </c:if> 
    <c:if test="${pageResult.pageNo >= pageResult.totalPage}"> 
       	末页&nbsp;&nbsp;  
    </c:if> 
    <c:if test="${pageResult.pageNo < pageResult.totalPage}"> 
       <a href="<c:url value="${pageUrl}"/>${separator}pageNo=${pageResult.totalPage }">末页  </a>&nbsp;&nbsp;  
    </c:if> 
</div> 