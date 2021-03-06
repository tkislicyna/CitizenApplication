<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
    $(document).ready(function(){
        var birthDay=$('input[name="birthDay"]');

        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        var options={
            container: container,
            todayHighlight: false,
            autoclose: true,
        };
        $(birthDay).datepicker(options);
    })
</script>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="container">
    <c:choose>
        <c:when test="${citizenForm['new']}">
            <legend><h1><fmt:message key="citizen.add.title"/></h1></legend>
        </c:when>
        <c:otherwise>
            <legend><h1><fmt:message key="citizen.update.title"/></h1></legend>
        </c:otherwise>
    </c:choose>

    <spring:url value="/citizen" var="citizenActionUrl" />

    <form:form class="form-horizontal bootstrap-iso form" method="post" modelAttribute="citizenForm" action="${citizenActionUrl}">

        <form:hidden path="id" />

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-danger' : ''}">
                <label class="" for="firstName"><fmt:message key="citizen.first_name"/></label>
                <fmt:message key="citizen.first_name_placeholder" var="firstNamePlaceholder" />
                <form:input path="firstName" type="text" class="form-control ${status.error ? 'is-invalid' : ''}" id="firstName" placeholder="${firstNamePlaceholder}" htmlEscape="true" />
                <form:errors path="firstName" class="invalid-feedback" />
            </div>
        </spring:bind>


        <spring:bind path="middleName">
            <div class="form-group ${status.error ? 'has-danger' : ''}">
                <label class="" for="middleName"><fmt:message key="citizen.middle_name"/></label>
                <fmt:message key="citizen.middle_name_placeholder" var="middleNamePlaceholder" />
                <form:input path="middleName" type="text" class="form-control ${status.error ? 'is-invalid' : ''}" id="middleName" placeholder="${middleNamePlaceholder}" htmlEscape="true"/>
                <form:errors path="middleName" class="invalid-feedback" />
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-danger' : ''}">
                <label class="" for="lastName"><fmt:message key="citizen.last_name"/></label>
                <fmt:message key="citizen.last_name_placeholder" var="lastNamePlaceholder" />
                <form:input path="lastName" type="text" class="form-control ${status.error ? 'is-invalid' : ''}" id="lastName" placeholder="${lastNamePlaceholder}" htmlEscape="true"/>
                <form:errors path="lastName" class="invalid-feedback" />
            </div>
        </spring:bind>

        <spring:bind path="birthDay">
            <div class="form-group ${status.error ? 'has-danger' : ''}">
                <label class="" for="birthDay"><fmt:message key="citizen.birthday"/></label>
                <fmt:message key="citizen.birthday_placeholder" var="birthDayPlaceholder" />

                <div class="input-group date datepicker" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                    <form:input path="birthDay" type="text" class="form-control ${status.error ? 'is-invalid' : ''}" id="birthDay" placeholder="${birthDayPlaceholder}"/>
                    <form:errors path="birthDay" class="invalid-feedback" />
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                    </div>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="address">
            <div class="form-group ${status.error ? 'has-danger' : ''}">
                <label class=""><fmt:message key="citizen.address"/></label>
                <fmt:message key="citizen.address_placeholder" var="addressPlaceholder" />
                <form:textarea path="address" rows="5" class="form-control ${status.error ? 'is-invalid' : ''}" id="address" placeholder="${addressPlaceholder}" htmlEscape="true"/>
                <form:errors path="address" class="invalid-feedback" />
            </div>
        </spring:bind>

        <button type="submit" class="btn btn-success"><fmt:message key="button.save"/></button>
        <c:choose>
            <c:when test="${citizenForm['new']}">
                <c:url var="listLink"  value="/list" />
            </c:when>
            <c:otherwise>
                <c:url var="listLink"  value="/citizen/${citizenForm.id}/view" />
            </c:otherwise>
        </c:choose>

        <a role="button" class="btn btn-secondary" href="${listLink}"><fmt:message key="button.cancel"/></a>

    </form:form>
</div>

