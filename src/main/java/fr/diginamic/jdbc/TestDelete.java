package fr.diginamic.jdbc;

import org.mariadb.jdbc.Driver;

import java.sql.*;

public class TestDelete {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            statement = connection.createStatement();
            // Suppression fournisseur La maison des Peintures
            int nbLignes = statement.executeUpdate("DELETE FROM fournisseur WHERE nom = 'La Maison des Peintures' ");
            System.out.println("Nombre de ligne supprimées : " + nbLignes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
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
