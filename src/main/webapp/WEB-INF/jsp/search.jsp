<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:import url="/WEB-INF/jsp/component/head.jsp"/>
<body>
<div class="row top-menu">
    <div class="col-md-10 col-md-offset-1">
        <h1>Simple Google Clone</h1>
	</div>
</div>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <c:choose>
                <c:when test="${not empty searchResultList}">
                    <c:forEach var="webPage" items="${searchResultList}">
                        <a href="${webPage.url}" target="_blank">
                            <b>${webPage.title}</b></br>
                            <span class="url">${webPage.url}</span>
                        </a>
                        </br></br>
                    </c:forEach>

                    <ul class="pagination">
                        <% int resultCount = (Integer) request.getAttribute("searchResultCount");
                        int pageCount = resultCount/10;
                        if (resultCount%10 > 0) {
                            pageCount++;
                        }
                        for(int i = 1; i <= pageCount; i+=1) { %>
                            <li><a href="search?q=${searchText}&start=<%=i*10-9%>"><%=i%></a></li>
                        <% } %>
                    </ul>
                </c:when>
                <c:otherwise>
                    <h2 class="text-center">Your search did not match any documents.</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>

</html>
