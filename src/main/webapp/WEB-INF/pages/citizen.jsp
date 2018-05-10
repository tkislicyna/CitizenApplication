<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<div class="container">
    <legend><h1><fmt:message key="citizen.view.title"/></h1></legend>
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
                <label class="col-sm-2"><fmt:message key="citizen.first_name"/></label>
                <div class="col-sm-10"><c:out value="${citizenForm.firstName}" escapeXml="true"/></div>
            </div>

            <div class="row">
                <label class="col-sm-2"><fmt:message key="citizen.middle_name"/></label>
                <div class="col-sm-10"><c:out value="${citizenForm.middleName}" escapeXml="true"/></div>
            </div>


            <div class="row">
                <label class="col-sm-2"><fmt:message key="citizen.last_name"/></label>
                <div class="col-sm-10"><c:out value="${citizenForm.lastName}" escapeXml="true"/></div>
            </div>

            <div class="row">
                <label class="col-sm-2"><fmt:message key="citizen.birthday"/></label>
                 <div class="col-sm-10"><fmt:formatDate value="${citizenForm.birthDay}" type="date" pattern="dd/MM/yyyy"/></div>
            </div>

            <div class="row">
                <label class="col-sm-2"><fmt:message key="citizen.address"/></label>
                <div class="col-sm-10"><c:out value="${citizenForm.address}" escapeXml="true"/></div>
            </div>

            <br/>
            <c:url var="exitLink"  value="/citizen/${citizenForm.id}/update" />
            <c:url var="deleteLink"  value="/citizen/${citizenForm.id}/delete" />
            <c:url var="listLink"  value="/list" />
            <div class="">
                <a role="button" class="btn btn-primary" href="${exitLink}"><fmt:message key="button.update"/></a>
                <a role="button" class="btn btn-danger" href="${deleteLink}"><fmt:message key="button.delete"/></a>
                <a role="button" class="btn btn-secondary" href="${listLink}"><fmt:message key="button.back"/></a>
            </div>
        </div>

        <div class=" col-sm-3">
            <div class="card border-outline-primary mb-3" style="max-width: 20rem;">
                <div class="card-header"><fmt:message key="document.list"/></div>
                <div class="card-body">
                    <c:forEach items="${citizenForm.documents}" var="doc" varStatus="loop">
                        <c:url var="documentViewLink"  value="/document/${doc.id}/view" />
                        <p class="card-text"><a href="${documentViewLink}"><fmt:message key="document.type.${doc.type}"/></a></p>
                    </c:forEach>

                    <c:url var="addLink"  value="/document/${citizenForm.id}/add" />
                    <a role="button" class="btn btn-success btn-sm" href="${addLink}">
                        <fmt:message key="button.add"/>
                    </a>
                </div>
            </div>
        </div>

        <div class="">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <c:url var="deleteLink"  value="/document/${citizenForm.id}/list" />
                    <a class="nav-link" href="#"></a>
                </li>
            </ul>
        </div>
    </div>
 </div>