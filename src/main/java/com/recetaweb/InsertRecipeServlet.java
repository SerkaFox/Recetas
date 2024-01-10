package com.recetaweb;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class InsertRecipeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/insertar.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeName = request.getParameter("recipeName");
        int difficulty = Integer.parseInt(request.getParameter("difficulty"));

        RecipeDAO recipeDAO = new RecipeDAO();
        Recipe recipe = new Recipe(recipeName, difficulty);
        try {
            recipeDAO.insertRecipe(recipe);
            response.sendRedirect("/RecetaWeb/success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/RecetaWeb/error.jsp");
        }
    }
}
