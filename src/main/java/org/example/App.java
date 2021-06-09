package org.example;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.naming.ConfigurationException;
import java.util.ResourceBundle;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {

        Configurations configs = new Configurations();
        try {
        Configuration config = configs.properties("configuration.properties");
            System.out.println(config.getString("nom"));
            System.out.println(config.getString("prenom"));
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
}

