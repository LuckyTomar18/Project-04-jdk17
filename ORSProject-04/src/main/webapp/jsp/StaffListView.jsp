<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.rays.proj4.controller.StaffListCtl"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.bean.StaffBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.StaffBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Staff
			List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.STAFF_LIST_CTL%>" method="post">
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				HashMap<String, String> map = (HashMap<String, String>) request.getAttribute("map");
				List<StaffBean> list = (List<StaffBean>) ServletUtility.getList(request);
				Iterator<StaffBean> it = list.iterator();

				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Full Name :</b></label> <input
						type="text" name="fullName" placeholder="Enter Full Name"
						value="<%=ServletUtility.getParameter("fullName", request)%>">&emsp;

						<%-- 	<label><b>DOB :</b></label> <input type="date" name="dob"
						placeholder="Enter Date"
						value="<%=ServletUtility.getParameter("dob", request)%>">&emsp; --%>

						<label><b>Previous Employer:</b></label> <input type="text"
						name="previousEmployer" placeholder="Enter Previous Employer"
						value="<%=ServletUtility.getParameter("previousEmployer", request)%>">&emsp;

						<label><b>Division : </b></label> <%=HTMLUtility.getList("division", String.valueOf(bean.getDivision()), map)%>&emsp;

						<input type="submit" name="operation"
						value="<%=StaffListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=StaffListCtl.OP_RESET%>">
					</td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="13%">Full Name</th>
					<th width="13%">Joining Date</th>
					<th width="23%">Division</th>
					<th width="10%">Previous Employer</th>
					<th width="5%">Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = (StaffBean) it.next();

							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							String date = sdf.format(bean.getJoiningDate());
				%>

				<tr>
					<td style="text-align: center;"><input type="checkbox"
						name="ids" value="<%=bean.getId()%>"></td>

					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getFullName()%></td>
					<td style="text-align: center;"><%=date%></td>
					<td style="text-align: center; text-transform: lowercase;"><%=bean.getDivision()%></td>
					<td style="text-align: center;"><%=bean.getPreviousEmployer()%></td>
					<td style="text-align: center;"><a
						href="StaffCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
					}
				%>
			</table>

			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=StaffListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=StaffListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=StaffListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=StaffListCtl.OP_NEXT%>"
						<%=nextListSize != 0 ? "" : "disabled"%>></td>
				</tr>
			</table>

			<%
				} else {
			%>

			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=StaffListCtl.OP_BACK%>"></td>
				</tr>
			</table>

			<%
				}
			%>
		</form>
	</div>
</body>
</html>
