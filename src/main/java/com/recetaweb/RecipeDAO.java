package com.recetaweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    public void insertRecipe(Recipe recipe) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseUtil.getConnection();
            String sql = "INSERT INTO recetas (nombre, FK_dificultad) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipe.getRecipeName());
            preparedStatement.setInt(2, recipe.getDifficulty());
            preparedStatement.executeUpdate();
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    public void updateRecipe(Recipe recipe) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseUtil.getConnection();
            String sql = "UPDATE recetas SET nombre = ?, FK_dificultad = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipe.getRecipeName());
            preparedStatement.setInt(2, recipe.getDifficulty());
            preparedStatement.setInt(3, recipe.getId()); 
            preparedStatement.executeUpdate();
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    public void deleteRecipe(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseUtil.getConnection();
            String sql = "DELETE FROM recetas WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    private void closeResources(PreparedStatement preparedStatement, Connection connection) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Recipe> getAllRecipes() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            String sql = "SELECT id, nombre, FK_dificultad FROM recetas";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                int difficulty = resultSet.getInt("FK_dificultad");
                Recipe recipe = new Recipe(id, name, difficulty);
                recipes.add(recipe);
            }
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return recipes;
    }


    public Recipe getRecipeById(int recipeId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            String sql = "SELECT id, nombre, FK_dificultad FROM recetas WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, recipeId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                int difficulty = resultSet.getInt("FK_dificultad");
                return new Recipe(id, name, difficulty);
            }
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return null;
    }

    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
