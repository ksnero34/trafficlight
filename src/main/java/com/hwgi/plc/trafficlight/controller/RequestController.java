package com.hwgi.plc.trafficlight.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwgi.plc.trafficlight.service.TrafficStatusGetter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class RequestController {

    private final TrafficStatusGetter getterService;

    @GetMapping("/conntest")
    public ResponseEntity<Object> connTestApi() {
        String result = "서비스 상황 모니터링 API 통신에 성공하였습니다.";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<Object> getTest() throws JsonProcessingException {
        String result = getterService.getAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/get")
    public String postMethodName(@RequestBody String postData) throws JsonProcessingException {
        //TODO: process POST request
        ObjectMapper objectMapper = new ObjectMapper();
        // log.info(postData);
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(postData);
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getterService.get(jsonNode.findValuesAsText("uniqueId").get(0));
    }
    
}
