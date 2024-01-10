<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <div class="container mt-5">
        <h1>Error</h1>
        <p>${requestScope.errorMessage}</p>
        <%
            // Вывести стек трейс исключения
            Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
            if (exception != null) {
                out.print("<pre>");
                exception.printStackTrace(new java.io.PrintWriter(out));
                out.print("</pre>");
            }
        %>
    </div>
</body>
</html>
