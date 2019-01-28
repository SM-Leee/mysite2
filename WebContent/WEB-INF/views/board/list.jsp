<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${count-status.index }</td>
							<td style="padding-left:${30 *vo.depth } px">
							<c:if test="${vo.depth !=0 }">
									<img
										src="${pageContext.servletContext.contextPath }/assets/images/reply.png">
								</c:if> <a
								href="${pageContext.servletContext.contextPath }/board?a=view&group_no=${vo.group_no }&order_no=${vo.order_no }&depth=${vo.depth }&user_no=${vo.user_no }">${vo.title }</a></td>

							<td>${vo.user_name }</td>
							<td>${vo.hit }</td>
							<td>${vo.write_date }</td>
							<td><c:if test="${authuser.no == vo.user_no}">
									<a
										href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no }"
										class="del"> 삭제 </a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<!-- pager 추가 -->
				<c:if test="${authuser != null }">
					<div class="bottom">
						<a
							href="${pageContext.servletContext.contextPath }/board?a=writeAdd&user_no=${authuser.no }"
							id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>