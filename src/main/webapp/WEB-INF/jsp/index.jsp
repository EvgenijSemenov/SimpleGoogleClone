<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">

<head>
    <script>
        function onSubmit() {
            document.getElementById('index-form').action = "/index?q=" + document.getElementById('index-url-text').value;
            document.getElementById("index-form").submit();
            return false;
        }
    </script>
</head>

<body>
    <form id="index-form" action="/index" method="POST" onsubmit="return onSubmit();">
        <input id= "index-url-text" type="text">
        <input type="submit" value="Index">
    </form>
</body>

</html>
