package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;

public class TestSelect {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            statement = connection.createStatement();
            // Suppression fournisseur La maison des Peintures
            resultSet = statement.executeQuery("SELECT * FROM fournisseur");

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Fournisseur fournisseur = new Fournisseur(id, nom);
                listeFournisseurs.add(fournisseur);
            }


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

            for (Fournisseur fournisseur : listeFournisseurs) {
                System.out.println(fournisseur);
            }
        }
    }
}
