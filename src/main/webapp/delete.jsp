<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrar Receta</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">
        <h1 class="mb-4">Borrar Receta</h1>
        <form action="<%= request.getContextPath() %>/Delete" method="post">
            <input type="hidden" name="recipeId" value="<%= request.getParameter("id") %>">
            <p>¿Estás seguro de que deseas borrar esta receta?</p>
            <button type="submit" class="btn btn-danger">Sí, Borrar</button>
            <a href="<%= request.getContextPath() %>/Show" class="btn btn-secondary ml-2">Cancelar</a>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
