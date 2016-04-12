<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	 <link rel="stylesheet" type="text/css" href="index.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/index.css" />
	 <link rel="stylesheet" type="text/css" href="/by.issoft.paramCollectorWebApp/src/main/webapp/index.css">
<!-- 	 <link rel="stylesheet" type="text/css" href="/index.css"> -->
<!-- 	 <link rel="stylesheet" type="text/css" href="resources/index.css"> -->
<!-- 	 <link rel="stylesheet" type="text/css" href="/resources/index.css"> -->
<!-- 	 <link rel="stylesheet" type="text/css" href="main/resources/index.css"> -->
<!-- 	 <link rel="stylesheet" type="text/css" href="/main/resources/index.css"> -->
<!-- 	 <link rel="stylesheet" type="text/css" href="src/main/resources/index.css"> -->
<!-- 	 <link rel="stylesheet" type="text/css" href="/src/main/resources/index.css"> -->
	 
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Params</title>
	<c:import url="imports.jsp" charEncoding="UTF-8"></c:import>


</head>

<script>
$(document).ready(function(){

	$(".butt").click(function(event){
        var buttonID = event.target.id;
		 $.get('/reload', {"buttonID": buttonID}, 
			function(resp) {
			
			
		 	$("#result-"+buttonID).text(resp);

		 	
		 }).fail(function() { 
	         alert("Request failed.");
	     });
	}); 
	 
});
</script>

<script>
$(document).ready(function(){

	$(".toggle").click(function(event){
        var buttonID = event.target.id;
		 $.get('/toggle', {"buttonID": buttonID}, 
			function(resp) {
			 $("#"+buttonID).attr("value", resp);					 
		 }).fail(function() { 
	         alert("Request failed.");
	     });
	}); 
	 
});
</script>




<body>

	<p>HELLO </p>
	<%response.setIntHeader("Refresh", 10);%>
	<div class="container">    
	  <table class="table">

	    <thead>
	      <tr>
	        <th>Param</th>
	        <th>Freequency</th>
	        <th>Host</th>
	        <th>Status</th>
	        <th>Current value</th>
	        

	      </tr>
	    </thead>
	    <tbody>
	    	<c:forEach items="${threads}" var="thread">
				<tr class="success">
			        <td><c:out value="${thread.collectingParam.paramName}"></c:out></td>
			        <td><p>1 in <c:out value="${thread.collectingParam.frequency}"></c:out> milis</p></td>
			        <td><c:out value="${thread.collectingParam.host}"></c:out></td>
			        <td>		       
			        	<input type="button" value="${thread.status}" width="32" height="32"  src="resources/images/reload.png" class="toggle" id="${thread.collectingParam.paramName}-${thread.collectingParam.hostWithoutDots}-${thread.collectingParam.frequency}" />	
			       	</td>       	
			        <td >
			      	        <table>
				        	<tr>
								<td><input type="button" value="refresh"  width="32" height="32"  src="resources/images/reload.png" class="butt" id="${thread.collectingParam.paramName}-${thread.collectingParam.hostWithoutDots}-${thread.collectingParam.frequency}" /></td>
								<td>&nbsp;&nbsp;&nbsp;</td>
								<td><div id="result-${thread.collectingParam.paramName}-${thread.collectingParam.hostWithoutDots}-${thread.collectingParam.frequency}">
									<c:out value="${thread.lastValue}"></c:out>
								</div></td>						       		
				       		</tr>
			       			</table>
			        </td>       
		        </tr>	
		    </c:forEach>	     
	    </tbody>
	  </table>
	</div>
	
</body>
</html>