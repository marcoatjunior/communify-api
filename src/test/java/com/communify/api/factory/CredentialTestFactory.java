package com.communify.api.factory;

import static com.google.api.client.auth.oauth2.BearerToken.authorizationHeaderAccessMethod;

import com.google.api.client.auth.oauth2.Credential;

public class CredentialTestFactory {

    public static final String ACCESS_TOKEN = "accessToken";
    
    private CredentialTestFactory() {
    }
    
    public static Credential create() {
        return new Credential(authorizationHeaderAccessMethod())
            .setAccessToken(ACCESS_TOKEN);
    }
}
