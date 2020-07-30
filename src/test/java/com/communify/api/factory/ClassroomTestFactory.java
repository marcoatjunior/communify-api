package com.communify.api.factory;

import static com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport;
import static com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance;

import com.google.api.services.classroom.Classroom;

public class ClassroomTestFactory {

    public static final String ACCESS_TOKEN = "accessToken";
    
    private ClassroomTestFactory() {
    }
    
    public static Classroom create() throws Exception {
        return new Classroom
            .Builder(newTrustedTransport(), getDefaultInstance(), CredentialTestFactory.create())
                .setApplicationName("Communify")
                .build();
      }
}
