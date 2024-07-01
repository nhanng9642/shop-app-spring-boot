package com.example.demo.auth;

import com.example.demo.exception.BadRequestException;
import com.example.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtService jwtService;
    private final UserDetailsService userDetail;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    @Value("${application.security.jwt.expiration}")
    private int timeExpiration;

    public AuthenticationResponse oauth2Login(String code, OAuth2AuthenticationToken token) {
        String accessToken = getAccessTokenFromCode(code);

        // Use the access token to get user info from Google
        Map<String, Object> userInfo = getUserInfoFromAccessToken(accessToken);

        if (userInfo != null) {
            // Lấy thông tin từ userInfo (ví dụ: email, name,...)
            String email = (String) userInfo.get("email");

            UserDetails user = userDetail.loadUserByUsername(email);
            String generatedAccessToken = jwtService.generateJwtToken(user);
            String generatedRefreshToken = jwtService.generateRefreshToken(user);

            // Trả về accessToken và refreshToken
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("accessToken", generatedAccessToken);
            responseBody.put("refreshToken", generatedRefreshToken);

            return AuthenticationResponse.builder()
                    .accessToken(generatedAccessToken)
                    .refreshToken(generatedRefreshToken)
                    .timeExpiration(timeExpiration)
                    .build();
        }
        throw new BadRequestException("Cannot get user info from Google");
    }

    private String getAccessTokenFromCode(String code) {
        String url = "https://oauth2.googleapis.com/token";
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectUri);
        params.put("grant_type", "authorization_code");

        ResponseEntity<Map> response = restTemplate.postForEntity(url, params, Map.class);
        Map responseBody = response.getBody();

        return (String) responseBody.get("access_token");
    }

    private Map<String, Object> getUserInfoFromAccessToken(String accessToken) {
        String url = "https://www.googleapis.com/oauth2/v2/userinfo";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }
}
