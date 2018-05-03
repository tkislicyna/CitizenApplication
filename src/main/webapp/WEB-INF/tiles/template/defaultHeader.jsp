<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="bs-component">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#"><fmt:message key="citizen.brand"/></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarColor01">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><fmt:message key="menu.home"/><span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><fmt:message key="menu.citizen_list"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><fmt:message key="menu.about"/></a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <fmt:message key="citizen.III_plaseholder" var="search_placeholder" />
                    <input type="text" name="keyword" placeholder="${search_placeholder}" class="form-control mr-sm-2"/>
                    <button type="submit" class="btn btn-secondary my-2 my-sm-0 btn-success"><fmt:message key="button.search"/></button>
                </form>
            </div>
        </div>
    </nav>
</div>