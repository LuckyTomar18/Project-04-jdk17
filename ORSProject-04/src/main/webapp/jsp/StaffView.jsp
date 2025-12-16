<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.bean.StaffBean"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.StaffCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.STAFF_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.StaffBean"
			scope="request"></jsp:useBean>

		<%
		List<StaffBean> staffList = (List<StaffBean>) request.getAttribute("staffList");
		%>

		<div align="center">
			<h1 style="margin-bottom: -15; color: navy">
				<%
				if (bean != null && bean.getId() > 0) {
				%>Update<%
				} else {
				%>Add<%
				}
				%>
				Staff
			</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<h3 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</h3>
				<h3 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</h3>
			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			<table>
				<tr>
					<th>Full Name:<span style="color: red">*</span></th>
					<td><input type="text" name="fullName"
						placeholder="Enter Full Name"
						value="<%=DataUtility.getStringData(bean.getFullName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("fullName", request)%>
					</font></td>
				</tr>
				<tr>
					<th>Joining Date:</th>
					<td><input type="text" id="udate" name="joiningDate" placeholde="Enter Date"
						value="<%=DataUtility.getDateString(bean.getJoiningDate())%>"
						style="width: 98%"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("joiningDate", request)%></font></td>
				</tr>

				<tr>
					<th>Division:<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("division", bean.getDivision(), staffList)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("division", request)%></font></td>
				</tr>
				
				<tr>
					<th>Previous Employer<span style="color: red">*</span></th>
					<td><input type="text" name="previousEmployer"
						placeholder="Enter Previous Employer"
						value="<%=DataUtility.getStringData(bean.getFullName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("previousEmployer", request)%>
					</font></td>
				</tr>
				
				<tr>

					<th></th>
					<%
					if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=StaffCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=StaffCtl.OP_CANCEL%>">
					</td>
					<%
					} else {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=StaffCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=StaffCtl.OP_RESET%>">
					</td>
					<%
					}
					%>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>