<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
  <body>
    <form action="<%=request.getContextPath()%>/MsgPane" method="post">
    	名稱：<br><input type="text" name="name"><br>
    	
    	留言：<br><textarea rows="50" cols="50" name="message"></textarea><br><br>   	
    	<input type="submit" value="留言"><br>
    </form>
    <form action="<%=request.getContextPath()%>/msgpane/messages.jsp">
    	<input type="submit" value="取消">
    </form>
<%--  </body>--%>
</html>
