package com.communify.api.service;

import static com.google.api.client.auth.oauth2.BearerToken.authorizationHeaderAccessMethod;
import static com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport;
import static com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance;

import org.springframework.stereotype.Service;

import com.communify.api.contracts.IClassroomService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.classroom.Classroom;

@Service
public class ClassroomService implements IClassroomService {

    @Override
    public Classroom instance(String accessToken) {
        try {
            Credential credential = new Credential(authorizationHeaderAccessMethod()).setAccessToken(accessToken);
            final NetHttpTransport httpTransport = newTrustedTransport();
            Classroom service = instance(credential, httpTransport);
            return service;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new Classroom(null, null, null);
    }

    private Classroom instance(Credential credential, final NetHttpTransport httpTransport) {
        return new Classroom.Builder(httpTransport, getDefaultInstance(), credential).build();
    }
}
