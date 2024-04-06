package com.kukathon.teamg.auth.dto;

import lombok.Builder;

@Builder
public record TokenResponse(String accessToken, String refreshToken) {

}
