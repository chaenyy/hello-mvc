<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Member> memberList = (List<Member>)request.getAttribute("list");
%>
<!-- 관리자용 admin.css link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<section id="memberList-container">
	<h2>회원관리</h2>
	
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>회원권한</th><%-- select태그 --%>
				<th>성별</th>
				<th>생년월일</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>포인트</th> <%-- 세자리콤마 --%>
				<th>취미</th>
				<th>가입일</th> <%-- yyyy-MM-dd --%>
			</tr>
		</thead>
		<tbody>
		<% if(memberList != null) { 
			for(Member member : memberList) { %>
			<tr>
				<td><%= member.getMemberId() %></td>
				<td><%= member.getMemberName() %></td>
				<td>
					<select name="memberRole">
						<option value="<%= member.getMemberRole() %>"><%= member.getMemberRole() %></option>
					</select>
				</td>
				<td><%= member.getGender() %></td>
				<td><%= member.getBirthday() %></td>
				<td><%= member.getEmail() %></td>
				<td><%= member.getPhone() %></td>
				<td>
				<% 
					int point = member.getPoint();
					DecimalFormat df = new DecimalFormat("#,###");
					String formatPoint = df.format(point);
				%>
					<%= formatPoint %>
				</td> 
				<td><%= member.getHobby() %></td>
				<td><%= new Date(member.getEnrollDate().getTime()) %></td>			
			</tr>
			<% }
			} %>
		</tbody>
	</table>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
