package com.hwgi.plc.trafficlight.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwgi.plc.trafficlight.trafficStatusData.TrafficStatusData;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plcapi/v1")
public class TestPlcReturn {
    private List testList = new ArrayList<>();

    // 초기화 하면서 랜던 값들 넣기
    @PostConstruct
    public void init() {
        double dValue = Math.random();
        int iValue = (int)(dValue * 10)%5 + 5;

        for(int i = 0;i<iValue;i++){
            String tmpUid = RandomStringUtils.random(5, true, false);
            double dValue2 = Math.random();
            int iValue2 = (int)(dValue2 * 10);
            boolean tmpIsWait = iValue2%2 == 0 ? true : false;

            List<TrafficStatusData> tmpList = new ArrayList<>();
            TrafficStatusData tmpData = new TrafficStatusData();
            tmpData.setUniqueId(tmpUid);
            tmpData.setWaitCnt(iValue2);
            tmpData.setWaiting(tmpIsWait);

            testList.add(tmpData);
        }

    }

    @GetMapping("/getData")
    public List<Object> connTestApi() {
        return testList;
    }

}
