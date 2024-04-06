package com.kukathon.teamg.auth.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.kukathon.teamg.auth.dto.TokenResponse;
import com.kukathon.teamg.auth.dto.KakaoOAuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TokenUtils {

    @Value("${kakao.client-id}")
    private String clientId;
    @Value("${kakao.redirect-uri}")
    private String redirectUri;
    @Value("${kakao.token-uri}")
    private String tokenUri;
    @Value("${kakao.userinfo-uri}")
    private String userInfoUri;

    public TokenResponse getAccessToken(String authCode) {
        WebClient webClient = WebClient.create();

        Mono<TokenResponse> mono = webClient.post()
            .uri(tokenUri)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("code", authCode)
	.with("client_id", clientId)
	.with("redirect_uri", redirectUri)
	.with("response_type", "authorization_code"))
            .retrieve()
            .bodyToMono(JsonNode.class)
            .map(jsonNode -> new TokenResponse(jsonNode.get("access_token").asText(),
	jsonNode.get("refresh_token").asText(),
	jsonNode.get("id_token").asText()));

        return mono.block();
    }

    public KakaoOAuthResponse extractMemberInfoByAuthCode(String authCode) {
        TokenResponse accessTokenResponse = getAccessToken(authCode);
        WebClient webClient = WebClient.builder()
            .baseUrl(userInfoUri)
            .build();

        return webClient.get()
            .header("Authorization", "Bearer " + accessTokenResponse.accessToken())
            .retrieve()
            .bodyToMono(KakaoOAuthResponse.class)
            .block();
    }
}
