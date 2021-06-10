package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.List;

public class TestDelete {
    public static void main(String[] args) {
        FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();
        //Suppression fournisseur La maison des Peintures;
        System.out.println(fournisseurDaoJdbc.delete(new Fournisseur(9, "La maison des Peintures")));
        //Affichage list fournisseurs
        List<Fournisseur> listeFournisseurs = fournisseurDaoJdbc.extraire();
        for (Fournisseur fournisseur : listeFournisseurs) {
            System.out.println(fournisseur);
        }

    }
}
