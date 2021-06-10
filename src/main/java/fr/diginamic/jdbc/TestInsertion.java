package fr.diginamic.jdbc;

import org.mariadb.jdbc.Driver;

import java.sql.*;

public class TestInsertion {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            statement = connection.createStatement();
            // Creation nouveau fournisseur
            int nbLignes = statement.executeUpdate("INSERT INTO fournisseur(nom) Values('La Maison de la Peinture')");
            System.out.println("Nombre de ligne insérées : " + nbLignes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Problème de fermeture des ressources :" + e.getMessage());
            }
        }

    }

}
