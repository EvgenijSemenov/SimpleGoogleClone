<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:import url="/WEB-INF/jsp/component/head.jsp"/>
<body>
    <div class="col-md-12 text-center">
         <H1>Simple Google Clone</H1>
    </div>
    <div class="col-md-12 text-center">
        <form id="search-form" action="/search" method = "GET" accept-charset="UTF-8">
            <input id= "search-text" type="text" name="q">
            <input type="submit" value="Search">
        </form>
    </div>
</body>

</html>
