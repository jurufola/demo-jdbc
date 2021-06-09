package fr.diginamic.props;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.util.Iterator;

public class TestConfigurationProps {
    public static void main(String[] args) {
        Configurations configs = new Configurations();
        try {
            /*Configuration config = configs.properties("configuration.properties");
            System.out.println(config.getString("nom"));
            System.out.println(config.getString("prenom"));*/
            Configuration config = configs.properties("configuration.properties");
            Iterator<String> it = config.getKeys();

            while (it.hasNext()){
                String key = it.next();
                System.out.println(key + " -> " + config.getString(key));

            }


        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
}
