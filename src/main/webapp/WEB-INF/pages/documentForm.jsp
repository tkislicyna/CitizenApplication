<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
    $(document).ready(function(){
        var issueDate=$('input[name="issueDate"]');
        var expiryDate=$('input[name="expiryDate"]');

        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        var options={
            container: container,
            todayHighlight: true,
            autoclose: true,
        };
        issueDate.datepicker(options);
        expiryDate.datepicker(options);
    })
</script>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="container">
    <c:choose>
        <c:when test="${documentForm['new']}">
            <legend><h1><fmt:message key="document.add.title"/></h1></legend>
        </c:when>
        <c:otherwise>
            <legend><h1><fmt:message key="document.update.title"/></h1></legend>
        </c:otherwise>
    </c:choose>

    <spring:url value="/document" var="documentActionUrl" />

    <form:form class="form-horizontal bootstrap-iso form" method="post" modelAttribute="documentForm" action="${documentActionUrl}">

        <form:hidden path="id" />
        <form:hidden path="holder.id" />

        <div class="row">
            <label class="col-sm-2"><fmt:message key="document.holder"/></label>
            <c:url var="holderLink"  value="/citizen/${documentForm.holder.id}/view" />
            <div class="col-sm-10"><a href="${holderLink}"><c:out value="${documentForm.holder.fullName}" escapeXml="true"/></a></div>
        </div>

        <c:choose>
            <c:when test="${documentForm['new']}">
                <spring:bind path="type">
                    <div class="form-group ${status.error ? 'has-danger' : ''}">
                        <label><fmt:message key="document.type"/></label>
                        <form:select path="type" class="form-control ${status.error ? 'is-invalid' : ''}" >
                            <fmt:message key="document.select" var="noneOption"/>
                            <form:option value="" label="${noneOption}" disabled="true" selected="selected"/>
                             <form:options items="${typeList}" />
                        </form:select>
                        <form:errors path="type" class="invalid-feedback" />
                    </div>
                </spring:bind>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="type" value="${documentForm.type}"/>

                <div class="row">
                    <label class="col-sm-2"><fmt:message key="document.type"/></label>
                    <div class="col-sm-10"><fmt:message key="document.type.${documentForm.type}"/></div>
                </div>
            </c:otherwise>
        </c:choose>

        <spring:bind path="issueDate">
            <div class="form-group ${status.error ? 'has-danger' : ''}">
                <label for="issueDate"><fmt:message key="document.issue_date"/></label>
                <fmt:message key="document.issue_date_placeholder" var="issueDatePlaceholder" />

                <div class="input-group date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                    <form:input path="issueDate" type="text" class="form-control ${status.error ? 'is-invalid' : ''}" id="issueDate" placeholder="${issueDatePlaceholder}" />
                    <form:errors path="issueDate" class="invalid-feedback" />
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                    </div>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="expiryDate">
            <div class="form-group ${status.error ? 'has-danger' : ''}">
                <label class="" for="expiryDate"><fmt:message key="document.expiry_date"/></label>
                <fmt:message key="document.expiry_date_placeholder" var="expiryDatePlaceholder" />

                <div class="input-group date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                    <form:input path="expiryDate" type="text" class="form-control ${status.error ? 'is-invalid' : ''}" id="expiryDate" placeholder="${expiryDatePlaceholder}"/>
                    <form:errors path="expiryDate" class="invalid-feedback" />
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                    </div>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="authority">
            <div class="form-group ${status.error ? 'has-danger' : ''}">
                <label class=""><fmt:message key="document.authority"/></label>
                <fmt:message key="document.authority_placeholder" var="authorityPlaceholder" />
                <form:textarea path="authority" rows="5" class="form-control ${status.error ? 'is-invalid' : ''}" id="authority" placeholder="${authorityPlaceholder}"  htmlEscape="true" />
                <form:errors path="authority" class="invalid-feedback" />
            </div>
        </spring:bind>

        <button type="submit" class="btn btn-success"><fmt:message key="button.save"/></button>
        <c:choose>
            <c:when test="${documentForm['new']}">
                <c:url var="listLink"  value="/citizen/${documentForm.holder.id}/view" />
            </c:when>
            <c:otherwise>
                <c:url var="listLink"  value="/document/${id}/view" />
            </c:otherwise>
        </c:choose>

        <a role="button" class="btn btn-secondary" href="${listLink}"><fmt:message key="button.cancel"/></a>
    </form:form>
</div>