<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
 <head>
   
    <title>網威心情留言板</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
<body>
	<h1>網威心情留言板：</h1>
	<br>
		<a href="<%=request.getContextPath()%>/msgpane/submit.jsp">留言</a>
	<br>
	<%
			List<String> msgs = (List<String>) application
					.getAttribute("msgs");
			for (String s : msgs) {
				out.println(s);
				out.println("<br>-------------------------------------<br>");
		}
	%>
</body>
</html>
