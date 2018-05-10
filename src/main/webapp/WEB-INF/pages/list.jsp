<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix = "fn"  uri = "http://java.sun.com/jsp/jstl/functions"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="container">
    <h1> <fmt:message key="menu.citizen_list"/></h1>

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>
                <c:choose>
                    <c:when test="${not empty  msgParam}">
                        <fmt:message key="${msg}"  >
                            <fmt:param value="${fn:escapeXml(msgParam)}"/>
                        </fmt:message>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="${msg}"/>
                    </c:otherwise>
                </c:choose>
            </strong>

        </div>
    </c:if>

    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="citizen.full_name"/></th>
            <th scope="col"><fmt:message key="citizen.birthday"/></th>
            <th scope="col"><fmt:message key="citizen.address"/></th>
            <th scope="col"><fmt:message key="citizen.actions"/></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${citizens}" var="citizen" varStatus="loop">
            <c:set var = "rowClass" value = ""/>
            <c:if test = "${loop.index % 2 == 0}">
                <c:set var = "rowClass" value = "table-secondary"/>
            </c:if>
                <tr class="${rowClass}">
                    <th scope="row">
                        <c:url var="viewLink"  value="/citizen/${citizen.id}/view" />
                        <a href="${viewLink}"><c:out value="${citizen.fullName}" escapeXml="true"/></a>
                    </th>
                    <td>
                        <fmt:formatDate value="${citizen.birthDay}" type="date" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>
                        <c:out value="${citizen.address}" escapeXml="true"/>
                    </td>
                    <td nowrap>
                        <c:url var="exitLink"  value="/citizen/${citizen.id}/update" />
                        <c:url var="deleteLink"  value="/citizen/${citizen.id}/delete" />
                        <a role="button" class="btn btn-primary btn-sm" href="${exitLink}"><fmt:message key="button.update"/></a>
                        <a role="button" class="btn btn-danger btn-sm" href="${deleteLink}"><fmt:message key="button.delete"/></a>
                    </td>
                </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:url var="addLink"  value="/citizen/add" />
    <a role="button" class="btn btn-success btn-sm" href="${addLink}">
        <fmt:message key="button.add"/>
    </a>
</div>
