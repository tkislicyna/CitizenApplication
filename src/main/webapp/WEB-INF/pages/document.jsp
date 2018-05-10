<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <legend><h1><fmt:message key="document.view.title"/></h1></legend>


    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong><fmt:message key="${msg}"/></strong>
        </div>
    </c:if>

    <div class="row">
        <div class="col-sm-9">
            <div class="row">
                <label class="col-sm-2"><fmt:message key="document.holder"/></label>
                <c:url var="holderLink"  value="/citizen/${documentForm.holder.id}/view" />
                <div class="col-sm-10"><a href="${holderLink}"><c:out value="${documentForm.holder.fullName}" escapeXml="true"/></a></div>
            </div>

            <div class="row">
                <label class="col-sm-2"><fmt:message key="document.type"/></label>
                <div class="col-sm-10"><fmt:message key="document.type.${documentForm.type}"/></div>
            </div>


            <div class="row">
                <label class="col-sm-2"><fmt:message key="document.issue_date"/></label>
                <div class="col-sm-10"><fmt:formatDate value="${documentForm.issueDate}" type="date" pattern="dd/MM/yyyy"/></div>
            </div>

            <div class="row">
                <label class="col-sm-2"><fmt:message key="document.expiry_date"/></label>
                <div class="col-sm-10"><fmt:formatDate value="${documentForm.expiryDate}" type="date" pattern="dd/MM/yyyy"/></div>
            </div>

            <div class="row">
                <label class="col-sm-2"><fmt:message key="document.authority"/></label>
                <div class="col-sm-10"><c:out value="${documentForm.authority}" escapeXml="true"/></div>
            </div>

            <br/>
            <c:url var="exitLink"  value="/document/${documentForm.id}/update" />
            <c:url var="deleteLink"  value="/document/${documentForm.id}/delete" />
            <c:url var="citizenLink"  value="/citizen/${documentForm.holder.id}/view" />
            <div class="">
                <a role="button" class="btn btn-primary" href="${exitLink}"><fmt:message key="button.update"/></a>
                <a role="button" class="btn btn-danger" href="${deleteLink}"><fmt:message key="button.delete"/></a>
                <a role="button" class="btn btn-secondary" href="${citizenLink}"><fmt:message key="button.back"/></a>
            </div>
        </div>
    </div>
</div>