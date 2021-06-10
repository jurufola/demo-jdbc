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
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("SELECT * FROM fournisseur");
            // Suppression fournisseur La maison des Peintures
            //resultSet = statement.executeQuery("SELECT * FROM fournisseur");
            resultSet = preparedStatement.executeQuery();

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
                if (preparedStatement != null) {
                    preparedStatement.close();
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
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            //statement = connection.createStatement();
            // Creation nouveau fournisseur
            int id = fournisseur.getId();
            String nom = fournisseur.getNom();
            preparedStatement = connection.prepareStatement("INSERT INTO fournisseur Values(?,?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nom);
            int nbLignes = preparedStatement.executeUpdate();
           // int nbLignes = statement.executeUpdate("INSERT INTO fournisseur Values(" + id + ", '" + nom + "')");
            //System.out.println("Nombre de ligne insérées : " + nbLignes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
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
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        int nbLignes = 0;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            //statement = connection.createStatement();
            //Preparation requete
            preparedStatement = connection.prepareStatement("UPDATE fournisseur SET nom = ?   WHERE nom = ?");
            preparedStatement.setString(1, nouveauNom);
            preparedStatement.setString(2, ancienNom);
            preparedStatement.executeUpdate();

            // Mise à jour fournisseur
            //nbLignes = statement.executeUpdate("UPDATE fournisseur SET nom = '" + nouveauNom + "' WHERE nom = '" + ancienNom + "'");
            //System.out.println("Nombre de ligne mise à jour : " + nbLignes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
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
        PreparedStatement preparedStatement = null;
        //Statement statement = null;
        int nbLignes = 0;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("DELETE FROM fournisseur WHERE id = ?");

            // Suppression fournisseur
            int id = fournisseur.getId();
            String nom = fournisseur.getNom();
           // nbLignes = statement.executeUpdate("DELETE FROM fournisseur WHERE id = " + id);
            preparedStatement.setInt(1, id);
            nbLignes = preparedStatement.executeUpdate();
            System.out.println("Nombre de ligne supprimées : " + nbLignes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
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
