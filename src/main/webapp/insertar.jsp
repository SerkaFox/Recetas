<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insertar Receta</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>

	<div class="container mt-5">
		<form action="/RecetaWeb/Insert" method="post">
			<div class="form-group">
				<label for="recipeName">Nombre de la receta:</label> <input
					type="text" class="form-control" id="recipeName" name="recipeName"
					required>
			</div>
			<div class="form-group">
				<label for="difficulty">Seleccione el nivel de dificultad:</label> <select
					class="form-control" name="difficulty" id="difficulty">
					<option value="1">Fácil</option>
					<option value="2">Normal</option>
					<option value="3">Difícil</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Añadir receta</button>

		</form>
		<a href="/RecetaWeb/Show" class="btn btn-secondary mt-3 float-left">Volver a Mostrar Recetas</a>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
