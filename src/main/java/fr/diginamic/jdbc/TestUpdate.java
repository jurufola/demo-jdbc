package fr.diginamic.jdbc;

import org.mariadb.jdbc.Driver;

import java.sql.*;

public class TestUpdate {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            statement = connection.createStatement();
            // Mise à jour fournisseur
            int nbLignes = statement.executeUpdate("UPDATE fournisseur SET nom = 'La Maison des Peintures' WHERE nom = 'La Maison de la Peinture'");
            System.out.println("Nombre de ligne mise à jour : " + nbLignes);
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
