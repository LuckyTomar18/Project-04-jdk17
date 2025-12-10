<%@page import="in.co.rays.proj4.controller.CourseCtl"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
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

	<form action="<%=ORSView.COURSE_CTL%>" method="post">
	
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CourseBean" scope="request"></jsp:useBean>
	
	
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
                Course
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
					<th>Name <span style="color: red">*</span></th>
					<td><input type="text" name="name" placeholder="Enter Course Name" value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					  <td style="position: fixed;">
                        <font color="red">
                            <%=ServletUtility.getErrorMessage("name", request)%>
                            
                        </font>
                    </td>
				</tr>
				
				<tr>
					<th>Duration <span style="color: red">*</span></th>
					<td>
					 <%
                            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                            map.put("1 Year", "1 Year");
                            map.put("2 Year", "2 Year");
                            map.put("3 Year", "3 Year");
                            map.put("4 Year", "4 Year");
                            map.put("5 Year", "5 Year");
                            map.put("6 Year", "6 Year");
                            map.put("7 Year", "7 Year");

                            String htmlList = HTMLUtility.getList("duration", bean.getDuration(), map);
                        %>
                        <%=htmlList%>
                        </td>
                         <td style="position: fixed;">
                        <font color="red">
                            <%=ServletUtility.getErrorMessage("duration", request)%>
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
                            <input type="submit" name="operation" value="<%=CourseCtl.OP_UPDATE%>">
                            <input type="submit" name="operation" value="<%=CourseCtl.OP_CANCEL%>">
                        </td>
                    <%
                        } else {
                    %>
                        <td align="left" colspan="2">
                            <input type="submit" name="operation" value="<%=CourseCtl.OP_SAVE%>">
                            <input type="submit" name="operation" value="<%=CourseCtl.OP_RESET%>">
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