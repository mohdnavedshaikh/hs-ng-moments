package in.hopscotch.moments.api;

import in.hopscotch.moments.api.cookie.CookieConstants;
import in.hopscotch.moments.api.cookie.CookieContext;
import in.hopscotch.moments.entity.InstagramAccessTokenResponse;
import in.hopscotch.moments.service.InstagramService;
import in.hopscotch.moments.util.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/hsmoments")
public class InstagramController {
    
    @Inject
    CookieContext cookieContext;
    @Inject
    InstagramService instagramService;
    
    private static final String INSTAGRAM_URL = "https://api.instagram.com/oauth/authorize";
    private static final String CLIENT_ID = "66e5f1dcec744e1aa5acc8418e5a7904";
    private static final String REDIRECT_URI = "https://www.uat.hopscotch.in";
    private static final String RESPONSE_TYPE = "code";
    private static final String CLIENT_SECRET = " b1adac84c3f44dadacf26210219cd7ec";
    
    @RequestMapping(value = "/instagramAutheticationUrl", method = RequestMethod.GET)
    @ResponseBody
    public String getInstagramAutheticationUrl() {
        StringBuilder sb = new StringBuilder(INSTAGRAM_URL).append("/?client_id=").append(CLIENT_ID)
            .append("&redirect_uri=").append(REDIRECT_URI).append("&response_type=").append(RESPONSE_TYPE);
        return sb.toString();
    }
    
    @RequestMapping(value = "/generateAccessToken", method = RequestMethod.POST)
    public void getInstagramAutheticationUrl(@RequestParam String code) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://api.instagram.com/oauth/access_token").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + "UTF-8");
            connection.setRequestProperty("client_id", CLIENT_ID);
            connection.setRequestProperty("client_secret", CLIENT_SECRET);
            connection.setRequestProperty("grant_type", "authorization_code");
            connection.setRequestProperty("redirect_uri", REDIRECT_URI);
            connection.setRequestProperty("code", code);
            connection.connect();
            if (connection.getResponseCode() != 200) {
                InputStream errorStream = connection.getErrorStream();
                String error = new BufferedReader(new InputStreamReader(errorStream))
                .lines().collect(Collectors.joining("\n"));
                System.out.println("Error occured:" + error);
            } else {
                InputStream inputStream = connection.getInputStream();
                String output = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
                InstagramAccessTokenResponse response = JSON.fromJSON(InstagramAccessTokenResponse.class, output);
                String accessToken =  response.getAccessToken();
                String uuId = cookieContext.getCookie(CookieConstants.LOGGED_UUID);
                instagramService.insertOrUpdateAccessToken(uuId, accessToken);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}