<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<title>员工管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="css/style.css" />
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
					<%@include file="header.jsp" %>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						添加员工信息:
					</h1>
					<form action="add.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									姓名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="name" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									年龄:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="age" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									工资:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="salary" />
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="确定" />
						</p>
					</form>
				</div>
			</div>
			<%@include file="footer.jsp" %>
		</div>
	</body>
</html>
