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
			<div id="guestbook" class="delete-form">
				<form method="post" action="/guestbook">
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="no" value="">
					<label>ë¹ë°ë²í¸</label>
					<input type="password" name="password">
					<input type="submit" value="íì¸">
				</form>
				<a href="">ë°©ëªë¡ ë¦¬ì¤í¸</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>