package com.hwgi.plc.trafficlight.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hwgi.plc.trafficlight.trafficStatusData.TrafficStatusData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrafficStatusGetter {
    //테스트용
    private TrafficStatusData Status = new TrafficStatusData();
    //실데이터 담기
    private List<TrafficStatusData> StatusList = new ArrayList<>();

    private void requestToPlc(){
        int current = this.Status.getWaitCnt();
        this.Status.setWaitCnt(current + 1);
        if(current > 3)
            this.Status.setWaiting(true);
        else 
            this.Status.setWaiting(false);
    }

    public void run() {
        // 유량제어 was와 통신 후 데이터 객체에 값 담기
        requestToPlc();
        log.info("통신완료");
        log.info("{}----{}",this.Status.isWaiting(),this.Status.getWaitCnt());
    }

    public String getAll() {
        return this.Status.isWaiting() + "---" + this.Status.getWaitCnt() ; 
    }
}
