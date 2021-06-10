package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestSelect {
    public static void main(String[] args) {
        FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();

        //Affichage list fournisseurs
        List<Fournisseur> listeFournisseurs = fournisseurDaoJdbc.extraire();
        for (Fournisseur fournisseur : listeFournisseurs) {
            System.out.println(fournisseur);
        }
    }
}
