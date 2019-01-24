<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="/guestbook" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>ì´ë¦</td><td><input type="text" name="name"></td>
							<td>ë¹ë°ë²í¸</td><td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" íì¸ "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
						<table>
							<tr>
								<td>[4]</td>
								<td>ìëí</td>
								<td>2015-11-10 11:22:30</td>
								<td><a href="">ì­ì </a></td>
							</tr>
							<tr>
								<td colspan=4>
								ìëíì¸ì. ^^;<br>
								íííí	
								</td>
							</tr>
						</table>
						<br>
					</li>
				</ul>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>