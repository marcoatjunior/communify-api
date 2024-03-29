package com.communify.api.service;

import static com.google.api.client.auth.oauth2.BearerToken.authorizationHeaderAccessMethod;
import static com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport;
import static com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance;

import org.springframework.stereotype.Service;

import com.communify.api.contract.IClassroomService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.classroom.Classroom;

@Service
public class ClassroomService implements IClassroomService {

    @Override
    public Classroom instance(String accessToken) throws Exception {
        return instance(instanciateCredential(accessToken), newTrustedTransport());
    }

    protected Credential instanciateCredential(String accessToken) {
        return new Credential(authorizationHeaderAccessMethod()).setAccessToken(accessToken);
    }

    protected Classroom instance(Credential credential, final NetHttpTransport httpTransport) {
        return new Classroom.Builder(httpTransport, getDefaultInstance(), credential)
                .setApplicationName("Communify")
                .build();
    }
}
