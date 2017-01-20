package in.hopscotch.moments.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import in.hopscotch.moments.api.response.PublingChannelResponse;
import in.hopscotch.moments.api.response.PublingErrorResponse;
import in.hopscotch.moments.api.response.PublingFilterResponse;
import in.hopscotch.moments.api.response.PublingPostResponse;
import in.hopscotch.moments.api.response.PublingSourceResponse;
import in.hopscotch.moments.service.PublingService;
import in.hopscotch.moments.util.JSON;
import in.hopscotch.moments.web.exception.ProcessException;

import org.springframework.stereotype.Service;

@Service
public class PublingServiceImpl implements PublingService {

    @Override
    public PublingChannelResponse getAllChannels() throws Exception {
        String url = "https://api.publing.co/v1/channels/";
        String requestMethod = "GET";
        String output = doUrlConnection(url, requestMethod);
        PublingChannelResponse response = JSON.fromJSON(PublingChannelResponse.class, output);
        return response;
    }
    
    @Override
    public PublingSourceResponse getAllSources() throws Exception {
        String url = "https://api.publing.co/v1/sources/";
        String requestMethod = "GET";
        String output = doUrlConnection(url, requestMethod);
        PublingSourceResponse response = JSON.fromJSON(PublingSourceResponse.class, output);
        return response;
    }
    
    @Override
    public PublingFilterResponse getAllFilters() throws Exception {
        String url = "https://api.publing.co/v1/filters/";
        String requestMethod = "GET";
        String output = doUrlConnection(url, requestMethod);
        PublingFilterResponse response = JSON.fromJSON(PublingFilterResponse.class, output);
        return response;
    }
    
    @Override
    public PublingPostResponse getAllPosts() throws Exception {
        String url = "https://api.publing.co/v1/filters/";
        String requestMethod = "GET";
        String output = doUrlConnection(url, requestMethod);
        PublingPostResponse response = JSON.fromJSON(PublingPostResponse.class, output);
        return response;    }
    
    private String doUrlConnection(String url, String requestMethod) throws Exception {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(requestMethod);
            if ("POST".equals(requestMethod))
                connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + "UTF-8");
            connection.setRequestProperty("access_token", "input a access token here");
            connection.connect();
            if (connection.getResponseCode() != 200)
                throw new ProcessException("Error occured with status code: " + connection.getResponseCode() + " and message: " + handleErrorResponse(connection));
            else {
                InputStream inputStream = connection.getInputStream();
                String output = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
                return output;
            }
        } catch (Exception e) {
            throw new Exception();
        }

    }
    
    private String handleErrorResponse(HttpURLConnection connection) throws Exception {
        InputStream inputStream = connection.getInputStream();
        String output = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        PublingErrorResponse error = JSON.fromJSON(PublingErrorResponse.class, output);
        return error.getDetail().getMessage();
    }
    
}
