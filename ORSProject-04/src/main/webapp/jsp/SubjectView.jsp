<%@page import="in.co.rays.proj4.bean.SubjectBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.SubjectCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
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

	<form action="<%=ORSView.SUBJECT_CTL%>" method="post">
	
	 <jsp:useBean id="bean" class="in.co.rays.proj4.bean.SubjectBean" scope="request"></jsp:useBean>
	 
	  <%
            List<SubjectBean> courseList = (List<SubjectBean>) request.getAttribute("courseList");
        %>
        
		<div align="center">
		
		 <h1 align="center" style="margin-bottom: -15; color: navy">
                <%
                    if (bean != null && bean.getId() > 0) {
                %>
                    Update
                <%
                    } else {
                %>
                    Add
                <%
                    }
                %>
                Subject
            </h1>
            
             <h3 align="center">
                    <font color="green">
                        <%=ServletUtility.getSuccessMessage(request)%>
                    </font>
                </h3>
                
                <h3 align="center">
                    <font color="red">
                        <%=ServletUtility.getErrorMessage(request)%>
                    </font>
                </h3>
                
                  <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
                
		
			<table>
				<tr>
					<th>Subject <span style="color: red">*</span></th>
					<td><input type="text" name="name" placeholder="Enter Subject Name" value="<%=DataUtility.getStringData(bean.getName())%>">
					 <td style="position: fixed;">
                        <font color="red">
                            <%=ServletUtility.getErrorMessage("name", request)%>
                        </font>
                    </td>
				</tr>
				
				<tr>
					<th>Course <span style="color: red">*</span></th>
					<td>  <%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), courseList)%>
                            <td style="position: fixed;">
                        <font color="red">
                            <%=ServletUtility.getErrorMessage("courseId", request)%>
                        </font>
                    </td>

				</tr>
				
				<tr>
					<th>Description <span style="color: red">*</span></th>
					<td><textarea style="width: 170px; resize: none;" name="description" rows="3"
                            placeholder="Enter Short description"><%=DataUtility.getStringData(bean.getDescription()).trim()%></textarea></td>
                            
                            <td style="position: fixed;">
                        <font color="red">
                            <%=ServletUtility.getErrorMessage("description", request)%>
                        </font>
                    </td>
				</tr>
				
				 <tr>
                    <th></th>
                    <%
                        if (bean != null && bean.getId() > 0) {
                    %>
                        <td align="left" colspan="2">
                            <input type="submit" name="operation" value="<%=SubjectCtl.OP_UPDATE%>">
                            <input type="submit" name="operation" value="<%=SubjectCtl.OP_CANCEL%>">
                        </td>
                    <%
                        } else {
                    %>
                        <td align="left" colspan="2">
                            <input type="submit" name="operation" value="<%=SubjectCtl.OP_SAVE%>">
                            <input type="submit" name="operation" value="<%=SubjectCtl.OP_RESET%>">
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