package com.hwgi.plc.trafficlight.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwgi.plc.trafficlight.service.TrafficStatusGetter;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RequestController {

    private final TrafficStatusGetter getterService;

    @GetMapping("/conntest")
    public ResponseEntity<Object> connTestApi() {
        String result = "서비스 상황 모니터링 API 통신에 성공하였습니다.";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/gettest")
    public ResponseEntity<Object> getTest() {
        String result = getterService.getAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
