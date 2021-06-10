package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.List;

public interface ArticleDao {

    List<Article> extraire();
    void insert(Article article);
    int update(String acienneRef, String ancienneDesignation, Fournisseur ancienFournisseur, String nouvelleRef, String nouvelleDesignation, Fournisseur nouveauFournisseur );
    boolean delete(Article article);

}
