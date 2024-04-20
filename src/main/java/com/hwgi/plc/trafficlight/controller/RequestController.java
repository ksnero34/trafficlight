package com.hwgi.plc.trafficlight.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RequestController {
    @GetMapping("/conntest")
    public ResponseEntity<Object> connTestApi() {
        String result = "서비스 상황 모니터링 API 통신에 성공하였습니다.";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
