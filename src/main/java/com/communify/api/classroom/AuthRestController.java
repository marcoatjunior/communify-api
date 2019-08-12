package com.communify.api.classroom;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.classroom.ClassroomScopes;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/classroom/auth")
@org.springframework.web.bind.annotation.RestController
public class AuthRestController {
	/**
	 * Defines a default instance to JSON
	 */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	/**
	 * List all Classroom Scopes in a array of strings
	 */
	private static final List<String> SCOPES = new ArrayList<>(ClassroomScopes.all());
	/**
	 * Defines the file for Google API credentials
	 */
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
	/**
	 * Defines the file for Access Token
	 */
	private static final String ACCESS_TOKEN_PATH = "/token.txt";

	/**
	 * Get the access token
	 * @return An authorized access token string.
	 */
	@CrossOrigin
	@GetMapping("")
	public boolean allowAccess() {
		try {
			NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			InputStream in = new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream();
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
					HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
					.build();
			LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
			BufferedWriter token = new BufferedWriter(new FileWriter((new ClassPathResource(ACCESS_TOKEN_PATH).getFile().getAbsolutePath())));
			token.write(new AuthorizationCodeInstalledApp(flow, receiver).authorize("user").getAccessToken());
			token.close();
			return true;
		} catch (Exception exception) {
			System.out.println(exception);
			return false;
		}
	}
}