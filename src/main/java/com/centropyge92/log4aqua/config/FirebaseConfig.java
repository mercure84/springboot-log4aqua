package com.centropyge92.log4aqua.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.credential.path}")
    private String credentialsPath;

    @PostConstruct
    public void init() throws IOException {
        System.out.println("FirebaseConfig init() " + credentialsPath);
        // Charger le fichier directement depuis le classpath
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream(credentialsPath);

        if (serviceAccount == null) {
            throw new FileNotFoundException("Firebase credentials file not found in classpath: " + credentialsPath);
        }
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }
}