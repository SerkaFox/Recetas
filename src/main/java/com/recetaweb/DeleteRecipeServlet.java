package com.recetaweb;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DeleteRecipeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recipeIdString = request.getParameter("id");

        if (recipeIdString != null && !recipeIdString.isEmpty()) {
            try {
                int recipeId = Integer.parseInt(recipeIdString);

                RecipeDAO recipeDAO = new RecipeDAO();
                recipeDAO.deleteRecipe(recipeId);

                response.sendRedirect("/RecetaWeb/Show");
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.sendRedirect("/RecetaWeb/error.jsp");
            }
        } else {
            response.sendRedirect("/RecetaWeb/error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Вы можете использовать этот метод для обработки GET-запроса, если необходимо
        String recipeIdString = request.getParameter("id");

        if (recipeIdString != null && !recipeIdString.isEmpty()) {
            try {
                int recipeId = Integer.parseInt(recipeIdString);

                RecipeDAO recipeDAO = new RecipeDAO();
                recipeDAO.deleteRecipe(recipeId);

                response.sendRedirect("/RecetaWeb/Show");
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.sendRedirect("/RecetaWeb/error.jsp");
            }
        } else {
            response.sendRedirect("/RecetaWeb/error.jsp");
        }
    }
}
