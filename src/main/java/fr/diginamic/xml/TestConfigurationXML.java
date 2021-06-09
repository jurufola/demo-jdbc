package fr.diginamic.xml;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.json.XML;

import java.util.Iterator;

public class TestConfigurationXML {

    public static void main(String[] args) {
        Configurations configs = new Configurations();
        try {
            XMLConfiguration config = configs.xml("configuration.xml");
            System.out.println("Premier param fichier config " + config.getString("database(0).host"));
            Iterator<String> it = config.getKeys();

            while (it.hasNext()){
                String key = it.next();
                System.out.println(key + " -> " + config.getString(key));

            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}