package com.recetaweb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class EditRecipeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Recipe ID (GET): " + id);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseUtil.getConnection();
			String query = "SELECT * FROM Recetas WHERE id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Recipe recipe = new Recipe(resultSet.getString("nombre"), resultSet.getInt("FK_dificultad"));
				request.setAttribute("recipe", recipe);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/editar.jsp");
				dispatcher.forward(request, response);
				System.out.println("Recipe with ID " + id);
			} else {
				System.out.println("Recipe with ID " + id + " not found.");
				response.sendRedirect("/RecetaWeb/error.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception (GET): " + e.getMessage());
			response.sendRedirect("/RecetaWeb/error.jsp");
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String idParam = request.getParameter("id");
	    if (idParam != null && !idParam.isEmpty()) {
	        try {
	            int id = Integer.parseInt(idParam);
	            System.out.println("Recipe ID (POST): " + id);

	            String updatedName = request.getParameter("recipeName");
	            String difficultyParam = request.getParameter("difficulty");

	            int updatedDifficulty = 0;
	            if (difficultyParam != null && !difficultyParam.isEmpty()) {
	                try {
	                    updatedDifficulty = Integer.parseInt(difficultyParam);
	                } catch (NumberFormatException e) {
	                    e.printStackTrace();
	                    System.out.println("Error parsing difficulty: " + e.getMessage());
	                    response.sendRedirect("/RecetaWeb/error.jsp");
	                    return;
	                }
	            }

	            Connection connection = null;
	            PreparedStatement preparedStatement = null;

	            try {
	                connection = DatabaseUtil.getConnection();
	                String query = "UPDATE Recetas SET nombre=?, FK_dificultad=? WHERE id=?";
	                preparedStatement = connection.prepareStatement(query);
	                preparedStatement.setString(1, updatedName);
	                preparedStatement.setInt(2, updatedDifficulty);
	                preparedStatement.setInt(3, id);
	                System.out.println("SQL Query: " + preparedStatement.toString());
	                int updatedRows = preparedStatement.executeUpdate();
	                if (updatedRows > 0) {
	                    response.sendRedirect("/RecetaWeb/Show");
	                } else {
	                    System.out.println("No rows updated in the database.");
	                    response.sendRedirect("/RecetaWeb/error.jsp");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                System.out.println("SQL Exception (POST): " + e.getMessage());
	                request.setAttribute("errorMessage", "SQL Error: " + e.getMessage());
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/RecetaWeb/error.jsp");
	                dispatcher.forward(request, response);
	            } finally {
	                try {
	                    if (preparedStatement != null)
	                        preparedStatement.close();
	                    if (connection != null)
	                        connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }

	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            System.out.println("Error parsing 'id' parameter: " + e.getMessage());
	            response.sendRedirect("/RecetaWeb/error.jsp");
	        }
	    } else {
	        System.out.println("Invalid or missing 'id' parameter.");
	        response.sendRedirect("/RecetaWeb/error.jsp");
	    }
	}


}
