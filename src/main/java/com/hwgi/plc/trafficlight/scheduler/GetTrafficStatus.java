package com.hwgi.plc.trafficlight.scheduler;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hwgi.plc.trafficlight.service.TrafficStatusGetter;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetTrafficStatus {
    // 유량제어 서비스 상태 담을 객체
    private final TrafficStatusGetter trafficStatusGetter;

    @Value("${schedule.use}")
    private boolean useSchedule;

    @Scheduled(cron = "${schedule.cron}")
    public void mainJob() {
        try {
            if (useSchedule) {
                log.info("스케줄러 실행");
                trafficStatusGetter.run();
            }
        // } catch (InterruptedException e) {
        //     log.info("* Thread가 강제 종료되었습니다. Message: {}", e.getMessage());
        } catch (Exception e) {
            log.error("스케줄러가 예기치 않게 종료되었습니다. Message: {}", e.getMessage());
        }
    }

    // @Scheduled(fixedDelay = 30000)
    // public void run() {
    //     log.info("30초 단위 스케줄러 실행");
    //     //deviceService.synchronize();
    // }
}


