<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <div class="col-sm">
            <fmt:message key="citizen.full_name"/>
        </div>
        <div class="col-sm">
            <fmt:message key="citizen.birthday"/>
        </div>
        <div class="col-sm">
            <fmt:message key="citizen.actions"/>
        </div>
    </div>

    <c:forEach items="${citizens}" var="citizen">
        <div class="row">
            <div class="col-sm">
                    ${citizen.firstName}&nbsp${citizen.middleName}&nbsp${citizen.lastName}
            </div>
            <div class="col-sm">
                    ${citizen.birthDay}
            </div>
            <div class="col-sm">

            </div>
        </div>
    </c:forEach>
</div>
