package kios.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kios.db.DBconnection;

public class QuantityLimit {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String query = null;

    int productCount;

    public QuantityLimit() {

    }

    public QuantityLimit(String text) {
        try {
            connection = DBconnection.getConnection();

            query = "select product_count from product where product_name = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, text);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                productCount = resultSet.getInt("product_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}