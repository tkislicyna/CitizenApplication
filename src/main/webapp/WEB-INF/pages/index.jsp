<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>Citizens Manager</head>>
<body>

<div>
    <h1>sdfsfdfgsdfgdf</h1>
    <c:if test="${!empty citizens}">
        <table>
            <tr>
                <th>Full name</th>
                <th>Birth day</th>
            </tr>
            <c:forEach items="${citizens}" var="citizen">
                <tr>
                    <td>${citizen.firstName}&nbsp${citizen.middleName}&nbsp${citizen.lastName}</td>
                    <td>${citizen.birthDay}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>>
</div>
</body>
</html>