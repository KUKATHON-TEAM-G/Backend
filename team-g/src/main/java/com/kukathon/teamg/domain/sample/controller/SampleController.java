package com.kukathon.teamg.domain.sample.controller;

import com.kukathon.teamg.common.response.SuccessCode;
import com.kukathon.teamg.common.response.SuccessResponse;
import com.kukathon.teamg.domain.sample.dto.response.CreateSampleResponseDto;
import com.kukathon.teamg.domain.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/samples")
public class SampleController {

    private final SampleService sampleService;

    @PostMapping
    public ResponseEntity<SuccessResponse<CreateSampleResponseDto>> createSample(@RequestBody CreateSampleResponseDto createSample) {
        CreateSampleResponseDto response = sampleService.createSample(createSample.getText());
        return SuccessResponse.of(SuccessCode.CREATED, response);
    }
}
