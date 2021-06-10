package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.entites.Article;

import java.util.List;

public class TestJdbcArticles {
    public static void main(String[] args) {
        //Test Extraction
        ArticleDaoJdbc articleDaoJdbc = new ArticleDaoJdbc();
        List<Article> articles = articleDaoJdbc.extraire();
        for (Article article : articles) {
            System.out.println(article);
        }
    }
}
