<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:import url="/WEB-INF/jsp/component/head.jsp"/>
<body>
    <script>
        function onSubmit() {
            document.getElementById('index-form').action = "/index?q=" + document.getElementById('index-url-text').value;
            document.getElementById("index-form").submit();
            return false;
        }
    </script>
    <div class="row">
        <div class="col-md-12 text-center">
                 <H1>Simple Google Clone</H1>
        </div>
        <div class="col-md-12 text-center">
            <form id="index-form" action="/index" method="POST" onsubmit="return onSubmit();">
                <div class="row">
                    <input id= "index-url-text" class="index-url-text-input" type="text" placeholder="Enter url...">
                    <input type="submit" value="Index">
                </div>
                <div class="row">
                    <input id= "url-search-deep" class="url-search-deep-text-input" type="text" name="searchUrlDeep" placeholder="Max search url deep. Optional, 3 by default.">
                </div>
            </form>
        </div>
    </div>
</body>

</html>
