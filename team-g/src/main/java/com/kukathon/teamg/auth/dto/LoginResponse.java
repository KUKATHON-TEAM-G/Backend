package com.kukathon.teamg.auth.dto;

public record LoginResponse(String accessToken, String refreshToken, boolean isRegistered) {

}
