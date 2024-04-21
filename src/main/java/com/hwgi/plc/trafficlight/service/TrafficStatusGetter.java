package com.hwgi.plc.trafficlight.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwgi.plc.trafficlight.trafficStatusData.TrafficStatusData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrafficStatusGetter {
    // 테스트용
    private TrafficStatusData Status = new TrafficStatusData();
    // 실데이터 담기
    private List<TrafficStatusData> StatusList;

    private void requestToPlc() throws JsonMappingException, JsonProcessingException {
        // int current = this.Status.getWaitCnt();
        // this.Status.setWaitCnt(current + 1);
        // if(current > 3)
        // this.Status.setWaiting(true);
        // else
        // this.Status.setWaiting(false);

        // RestTemplate을 생성
        RestTemplate restTemplate = new RestTemplate();
        String responseJson = restTemplate.getForObject("http://localhost:8080/plcapi/v1/getData", String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        StatusList = objectMapper.readValue(responseJson, new TypeReference<List<TrafficStatusData>>() {
        });

    }

    public void run() throws JsonMappingException, JsonProcessingException {
        // 유량제어 was와 통신 후 데이터 객체에 값 담기
        requestToPlc();
        log.info("통신완료");
    }

    public String get(String uid) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        for (int i = 0; i < this.StatusList.size(); i++) {
            log.info(StatusList.get(i).getUniqueId());
            if (StatusList.get(i).getUniqueId().equals(uid)) {
                result = objectMapper.writeValueAsString(StatusList.get(i));
            }
        }

        return result;
    }

    public String getAll() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";

        result = objectMapper.writeValueAsString(this.StatusList);

        return result;
    }
}
