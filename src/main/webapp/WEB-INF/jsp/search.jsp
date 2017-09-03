<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:import url="/WEB-INF/jsp/component/head.jsp"/>
<body>
<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <c:if test="${not empty searchResultList}">
            <c:forEach var="listValue" items="${searchResultList}">
                <a href="${listValue.url}" target="_blank">
                    <b>${listValue.title}</b></br>
                    <span class="url">${listValue.url}</span>
                </a>
                </br></br>
            </c:forEach>
        </c:if>
	</div>
</div>
</body>

</html>
