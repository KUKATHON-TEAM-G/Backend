package com.kukathon.teamg.auth.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.kukathon.teamg.auth.dto.TokenResponse;
import com.kukathon.teamg.auth.dto.KakaoOAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TokenUtils {

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.token-uri}")
    private String tokenUri;

    @Value("${kakao.userinfo-uri}")
    private String userInfoUri;

    private String clientId = "98e0050b4553c0f6dd346cf67dd612fd";

    private String localRedirectUri = "http://localhost:8080/oauth/kakao";

    public TokenResponse getAccessToken(String authCode) {
        WebClient webClient = WebClient.builder()
            .baseUrl("https://kauth.kakao.com")
            .build();

        Mono<TokenResponse> mono = webClient.post()
            .uri("/oauth/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("grant_type", "authorization_code")
	.with("code", authCode)
	.with("client_id", clientId)
	.with("redirect_uri", localRedirectUri))
            .retrieve()
            .bodyToMono(JsonNode.class)
            .doOnNext(jsonNode -> log.info("Received JsonNode: " + jsonNode)) // JsonNode 확인
            .map(jsonNode -> new TokenResponse(jsonNode.get("access_token").asText(),
	jsonNode.get("refresh_token").asText()));

        return mono.block();
    }

    public KakaoOAuthResponse extractMemberInfoByAuthCode(String authCode) {
        TokenResponse accessTokenResponse = getAccessToken(authCode);
        log.info("accessTokenResponse: {}", accessTokenResponse.accessToken());

        WebClient webClient = WebClient.builder()
            .baseUrl("https://kapi.kakao.com/v2/user/me")
            .defaultHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
            .defaultHeader("Authorization", "Bearer " + accessTokenResponse.accessToken())
            .build();

        return webClient.post()
            .uri(uriBuilder -> uriBuilder
	.queryParam("secure_resource", true)
	.queryParam("property_keys", "[\"kakao_account.email\"]")
	.build())
            .retrieve()
            .bodyToMono(KakaoOAuthResponse.class)
            .block();
    }
}
