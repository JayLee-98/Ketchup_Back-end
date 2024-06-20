package com.devsplan.ketchup.stream.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

@Service
public class StreamService {

    @Value("${stream.api.key}")
    private String apiKey;

    @Value("${stream.api.secret}")
    private String secretKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://chat.stream-io-api.com";

    public String generateUserToken(String userId) {
        String url = baseUrl + "/users/generate-token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + secretKey);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", userId);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonResponse = (JSONObject) parser.parse(response.getBody());
            return (String) jsonResponse.get("token");
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse response", e);
        }
    }

    public void syncUser(String userId, String userName) {
        String url = baseUrl + "/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + secretKey);

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> user = new HashMap<>();
        user.put("id", userId);
        user.put("name", userName);
        requestBody.put("users", new HashMap<String, Map<String, String>>() {{
            put(userId, user);
        }});

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public void createChannel(String channelId, String userId) {
        String url = baseUrl + "/channels";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + secretKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", channelId);
        requestBody.put("type", "messaging");
        requestBody.put("created_by", new HashMap<String, String>() {{
            put("id", userId);
        }});

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public void addMember(String channelId, String memberId) {
        String url = baseUrl + "/channels/messaging/" + channelId + "/members";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + secretKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("add_members", new String[]{memberId});

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public void removeMember(String channelId, String memberId) {
        String url = baseUrl + "/channels/messaging/" + channelId + "/members";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + secretKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("remove_members", new String[]{memberId});

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public void sendMessage(String channelId, String userId, String messageText) {
        String url = baseUrl + "/channels/messaging/" + channelId + "/message";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + secretKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("text", messageText);
        requestBody.put("user_id", userId);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}

