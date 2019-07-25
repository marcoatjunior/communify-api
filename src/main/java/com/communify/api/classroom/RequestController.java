package com.communify.api.classroom;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestController {
    /**
     * Defines the file for Access Token
     */
    private static final String ACCESS_TOKEN_PATH = "/token.txt";

    /**
     * Executes request and retrieves the result
     * @param urlPath
     * @return Request
     * @throws Exception If an connection error occurs
     */
    protected StringBuffer create(String urlPath) throws Exception {

        HttpURLConnection con = (HttpURLConnection) (new URL(urlPath)).openConnection();

        String accessToken = Files.asCharSource(new ClassPathResource(ACCESS_TOKEN_PATH).getFile(), Charsets.UTF_8).read();

        con.setRequestProperty("Authorization", "Bearer " + accessToken);
        con.setDoOutput(true);

        Reader streamReader = new InputStreamReader(
            con.getResponseCode() > 299 ? con.getErrorStream() : con.getInputStream()
        );

        BufferedReader in = new BufferedReader(streamReader);

        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();
        return content;
    }
}
