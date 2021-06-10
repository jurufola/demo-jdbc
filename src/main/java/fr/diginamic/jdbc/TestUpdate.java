package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.List;

public class TestUpdate {
    public static void main(String[] args) {

        FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();
        //Mise à jour fournisseur maison de la Peinture;
        fournisseurDaoJdbc.update("La maison de la Peinture", "La maison des Peintures");
        //Affichage list fournisseurs
        List<Fournisseur> listeFournisseurs = fournisseurDaoJdbc.extraire();
        for (Fournisseur fournisseur : listeFournisseurs) {
            System.out.println(fournisseur);
        }

    }
}
