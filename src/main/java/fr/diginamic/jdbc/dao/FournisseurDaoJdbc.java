package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDaoJdbc implements FournisseurDao{
    @Override
    public List<Fournisseur> extraire() {
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

        }
        return listeFournisseurs;
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            statement = connection.createStatement();
            // Creation nouveau fournisseur
            int id = fournisseur.getId();
            String nom = fournisseur.getNom();
            int nbLignes = statement.executeUpdate("INSERT INTO fournisseur Values(" + id + ", '" + nom + "')");
            System.out.println("Nombre de ligne insérées : " + nbLignes);
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

    @Override
    public int update(String ancienNom, String nouveauNom) {
        Connection connection = null;
        Statement statement = null;
        int nbLignes = 0;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            statement = connection.createStatement();
            // Mise à jour fournisseur
            nbLignes = statement.executeUpdate("UPDATE fournisseur SET nom = '" + nouveauNom + "' WHERE nom = '" + ancienNom + "'");
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
        return nbLignes;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        Connection connection = null;
        Statement statement = null;
        int nbLignes = 0;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            statement = connection.createStatement();
            // Suppression fournisseur
            int id = fournisseur.getId();
            String nom = fournisseur.getNom();
            nbLignes = statement.executeUpdate("DELETE FROM fournisseur WHERE id = " + id);
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
        return nbLignes == 0 ? false : true;
    }
}
