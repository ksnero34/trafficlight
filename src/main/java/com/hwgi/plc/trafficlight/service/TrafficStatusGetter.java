package com.hwgi.plc.trafficlight.service;

import org.springframework.stereotype.Component;

import com.hwgi.plc.trafficlight.model.TrafficStatusData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrafficStatusGetter {
    private TrafficStatusData Status;

    private void requestToPlc(){
        int current = Status.getWaitCnt();
        Status.setWaitCnt(current + 1);
        if(current > 3)
            Status.setWaiting(true);
        else 
            Status.setWaiting(false);
    }

    public void run() {
        // 유량제어 was와 통신 후 dto 객체에 값 담기
        requestToPlc();
        log.info("통신완료");
    }
}
