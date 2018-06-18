<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>员工信息管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
			<%@ include file="header.jsp" %>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						员工列表
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								姓名
							</td>
							<td>
								年龄
							</td>
							<td>
								工资
							</td>
							<td>
								操作
							</td>
						</tr>
						<c:forEach items="${employees}" var="emp" varStatus="s">
								<tr class="row${s.index %2 +1}">
									<td>
										${emp.id}
									</td>
									<td>
										${emp.name}
									</td>
									<td>
										${emp.age}
									</td>
									<td>
										${emp.salary}
									</td>
									<td>
										<a href="del.do?id=${emp.id }" 
											onclick="return confirm('确定删除${emp.name}吗？');">删除</a>&nbsp;
										<a href="load.do?id=${emp.id }">修改</a>
									</td>
								</tr>
						</c:forEach>
					</table>
					<p>
						<input type="button" class="button" value="添加员工" onclick="location='toAdd.do'"/>
					</p>
				</div>
			</div>
			<%@ include file="footer.jsp" %>
		</div>
	</body>
</html>
