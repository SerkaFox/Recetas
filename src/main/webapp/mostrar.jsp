<%@ page contentType="text/html;charset=UTF-8" language="java"
    import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="com.recetaweb.DatabaseUtil"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="jakarta.servlet.annotation.WebServlet"%>
<%@ page import="jakarta.servlet.http.HttpServlet"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>
<%@ page import="jakarta.servlet.http.HttpServletResponse"%>

<html>
<head>
<title>Mostrar Recetas</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<script>
    function confirmDelete(id) {
        var result = confirm("¿Estás seguro de que deseas borrar esta receta?");
        if (result) {
            window.location = "/RecetaWeb/Delete?id=" + id;
        }
    }

    function toggleSortOrder(column) {
        var currentOrder = getParameterByName('order', window.location.href);
        var newOrder = 'asc';

        if (currentOrder === 'asc') {
            newOrder = 'desc';
        }

        var url = window.location.href;
        url = updateQueryStringParameter(url, 'sort', column);
        url = updateQueryStringParameter(url, 'order', newOrder);
        window.location.href = url;
    }

    function getParameterByName(name, url) {
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }

    function updateQueryStringParameter(uri, key, value) {
        var re = new RegExp("([?&])" + key + "=.*?(&|$)", "i");
        var separator = uri.indexOf('?') !== -1 ? "&" : "?";
        if (uri.match(re)) {
            return uri.replace(re, '$1' + key + "=" + value + '$2');
        }
        else {
            return uri + separator + key + "=" + value;
        }
    }
</script>
</head>
<body>
    <h1 class="display-4">Mis Recetas</h1>

    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th><a href="javascript:void(0);" onclick="toggleSortOrder('id')">ID</a></th>
                <th><a href="javascript:void(0);" onclick="toggleSortOrder('nombre')">Nombre</a></th>
                <th><a href="javascript:void(0);" onclick="toggleSortOrder('dificultad')">Dificultad</a></th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
        <tbody>
            <%
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                connection = DatabaseUtil.getConnection();
                statement = connection.createStatement();

                String sortColumn = request.getParameter("sort");
                String sortOrder = request.getParameter("order");

                if (sortColumn == null || sortColumn.isEmpty()) {
                    sortColumn = "id";
                }

                if (sortOrder == null || (!sortOrder.equalsIgnoreCase("desc") && !sortOrder.equalsIgnoreCase("asc"))) {
                    sortOrder = "ASC";
                }

                String query = "SELECT r.id, r.nombre, d.dificultad FROM Recetas r INNER JOIN Dificultades d ON r.FK_dificultad = d.id ORDER BY "
                + sortColumn + " " + sortOrder;
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
            %>
            <tr>
                <td><%=resultSet.getInt("id")%></td>
                <td><%=resultSet.getString("nombre")%></td>
                <td><%=resultSet.getString("dificultad")%></td>
                <td>
                    <a href="/RecetaWeb/Edit?id=<%=resultSet.getInt("id")%>" class="btn btn-warning btn-sm">Editar</a>
                    <button class="btn btn-danger btn-sm" onclick="confirmDelete(<%=resultSet.getInt("id")%>)">Eliminar</button>
                </td>
            </tr>
            <%
            }
            } catch (SQLException e) {
            e.printStackTrace();
            } finally {
            try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
            %>
        </tbody>
    </table>

    <form action="/RecetaWeb/Insert" method="get">
        <button type="submit" class="btn btn-primary">Añadir Receta</button>
    </form>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
