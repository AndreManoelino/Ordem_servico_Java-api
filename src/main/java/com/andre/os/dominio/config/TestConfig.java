package com.andre.os.dominio.config;

import com.andre.os.services.DBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBServices dbServices;

    // Chama o método diretamente, não é necessário ser um @Bean
    public void instanciaDB() {
        this.dbServices.instanciaDB();
    }
}
