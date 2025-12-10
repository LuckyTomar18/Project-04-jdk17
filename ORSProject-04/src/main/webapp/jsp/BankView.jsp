<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.controller.BankCtl"%>
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
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.BANK_CTL%>" method="post">
		<div align="center">

			<jsp:useBean id="bean" class="in.co.rays.proj4.bean.BankBean"
				scope="request"></jsp:useBean>

			<h3>Add Bank</h3>

			<h3 align="center">
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</h3>
			<h3 align="center">
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</h3>

			<table>
				<tr>
					<th>Account No<span style="color: red">*</span></th>
					<td><input type="text" name="accountNo"
						placeholder="Enter Account No"
						value="<%=DataUtility.getStringData(bean.getAccountNumber())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("accountNo", request)%></font></td>
				</tr>

				<tr>
					<th>Holder Name <span style="color: red">*</span></th>
					<td><input type="text" name="accountHolderName"
						placeholder="Enter Holder Name"
						value="<%=DataUtility.getStringData(bean.getAccountHolderName())%>"></td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("accountHolderName", request)%></font></td>
				</tr>

				<tr>
					<th>Branch <span style="color: red">*</span></th>
					<td><input type="text" name="branch"
						placeholder="Enter Account Type"
						value="<%=DataUtility.getStringData(bean.getBranch())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("branch", request)%></font></td>
				</tr>

				<tr>
					<th>Account Type <span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("Saving", "Saving");
							map.put("Current", "Current");
							String htmlList = HTMLUtility.getList("accountType", bean.getAccountType(), map);
						%><%=htmlList%></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("accountType", request)%></font></td>
				</tr>

				<tr>
					<th>Balance <span style="color: red">*</span></th>
					<td><input type="text" name="balance"
						placeholder="Enter balance"
						value="<%=DataUtility.getDoubleData(bean.getBalance())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("balance", request)%></font></td>
				</tr>

				<tr>
					<th>Phone No <span style="color: red">*</span></th>
					<td><input type="text" name="phoneNo"
						placeholder="Enter Phone No"
						value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td align="center" colspan="2"><input type="submit"
						name="operation" value="<%=BankCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=BankCtl.OP_RESET%>">
					</td>
				</tr>

			</table>
		</div>
	</form>
</body>
</html>