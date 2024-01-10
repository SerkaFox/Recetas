<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Receta</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Editar Receta</h1>
        <form action="/RecetaWeb/Edit" method="post">
            <div class="form-group">
                <label for="recipeName">Nombre de la receta:</label>
                <input type="text" class="form-control" id="recipeName" name="recipeName" value="${recipe.recipeName}" required>
            </div>
            <div class="form-group">
                <label for="difficulty">Seleccione el nivel de dificultad:</label>
                <select class="form-control" name="difficulty" id="difficulty">
                    <option value="1" ${recipe.difficulty == 1 ? 'selected' : ''}>Fácil</option>
                    <option value="2" ${recipe.difficulty == 2 ? 'selected' : ''}>Normal</option>
                    <option value="3" ${recipe.difficulty == 3 ? 'selected' : ''}>Difícil</option>
                </select>
            </div>
            <input type="hidden" name="id" value="<%= request.getParameter("id") %>" readonly>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </form>
        <a href="/RecetaWeb/Show" class="btn btn-secondary mt-3">Cancelar</a>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
