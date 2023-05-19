<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
    <title>員工資料新增</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/table.css'/>">
</head>
<body>
    <h3>員工資料</h3>
    <form:form method="POST" action="${pageContext.request.contextPath}/emp/insert" modelAttribute="empVO">
        <table>
            <tr>
                <td>員工姓名:</td>
                <td>
                    <form:input path="ename" size="45" />
                    <form:errors path="ename" cssClass="errormsg" />
                </td>
            </tr>
            <tr>
                <td>職位:</td>
                <td>
                    <form:input path="job" size="45" />
                </td>
            </tr>
            <tr>
                <td>雇用日期:</td>
                <td>
					<form:input path="hiredate" type="date"/>
					<form:errors path="hiredate" cssClass="errormsg"/>
                </td>
            </tr>
            <tr>
                <td>薪水:</td>
                <td>
					<form:input path="sal" size="45"/>
					<form:errors path="sal" cssClass="errormsg"/>
                </td>
            </tr>
            <tr>
                <td>獎金:</td>
                <td>
                	<form:input path="comm" size="45" />
                	<form:errors path ="comm" cssClass="errormsg" />
            	</td>
            </tr>
            <tr>
                <td>部門:<font color=red><b>*</b></font></td>
                <td>
                    <select size="1" name="deptno">
                        <c:forEach var="deptVO" items="${deptVOs}">
                            <option value="${deptVO.deptno}" ${(empVO.deptVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <br />
        <a href="${pageContext.request.contextPath}/">回首頁</a>
        <input type="submit" value="送出新增">
    </form:form>
</body>
</html>
