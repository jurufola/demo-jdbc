package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoJdbc implements  ArticleDao{
    @Override
    public List<Article> extraire() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        ArrayList<Article> articles = new ArrayList<>();
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta2", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM article");
            resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                int id = resultSet1.getInt("id");
                String ref = resultSet1.getString("ref");
                String designation = resultSet1.getString("designation");
                double prix = resultSet1.getDouble("prix");
                int idFou = resultSet1.getInt("id_Fou");
                String sousRequete = "SELECT * FROM fournisseur WHERE id=?";
                preparedStatement = connection.prepareStatement(sousRequete);
                preparedStatement.setInt(1, idFou);
                resultSet2 = preparedStatement.executeQuery();
                resultSet2.next();
                Fournisseur fournisseur = new Fournisseur(idFou, resultSet2.getString("nom"));
                Article article = new Article(id, ref, designation, prix, fournisseur);
                articles.add(article);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }if (resultSet2 != null) {
                    resultSet2.close();
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
        return articles;
    }

    @Override
    public void insert(Article article) {

    }

    @Override
    public int update(String acienneRef, String ancienneDesignation, Fournisseur ancienFournisseur, String nouvelleRef, String nouvelleDesignation, Fournisseur nouveauFournisseur) {
        return 0;
    }

    @Override
    public boolean delete(Article article) {
        return false;
    }
}
