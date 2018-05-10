<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <legend><h1><fmt:message key="about.contacts"/></h1></legend>

    <div class="row">
        <div class="col-sm-9">
            <div class="row">
                <label class="col-sm-3"><fmt:message key="about.developer"/></label>
                <div class="col-sm-9">
                    <fmt:message key="about.developer.value"/>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-3"><fmt:message key="about.email"/></label>
                <div class="col-sm-9">
                    <fmt:message key="about.email.value" var="emailLink"/>
                    <a href="mailto:${emailLink}">${emailLink}</a>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-3"><fmt:message key="about.github"/></label>
                <div class="col-sm-9">
                    <fmt:message key="about.github.value" var="githubLink"/>
                    <a href="${githubLink}">${githubLink}</a>
                </div>
            </div>
        </div>
    </div>
</div>