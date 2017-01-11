<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>2dolgi2boblo</title>
    <link rel="icon" type="image/x-icon" href="<c:url value="/resources/img/give-me-my-money.png"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/stylesheets/lib.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/stylesheets/styles.css"/>"/>
</head>
<body ng-app="tdtb" ng-cloak>
    <md-nav-bar md-selected-nav-item="'main'">
        <md-nav-item md-nav-href="#debts" name="main">Главная</md-nav-item>
        <span flex></span>
        <md-button ng-click="editUserData()">
            <div layout="row">
                <md-icon md-svg-icon="/resources/img/icons/svg/account_circle_black.svg"></md-icon>
                 {{currentUser.firstName}} {{currentUser.lastName}}
            </div>
        </md-button>
        <md-nav-item md-nav-href="<c:url value="/j_spring_security_logout"/>" name="logout">Выход</md-nav-item>
    </md-nav-bar>
    <ng-view></ng-view>
</body>
</html>
<script type="text/javascript" src="<c:url value="/resources/javascripts/lib.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/javascripts/full.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/javascripts/templates.js"/>"></script>
