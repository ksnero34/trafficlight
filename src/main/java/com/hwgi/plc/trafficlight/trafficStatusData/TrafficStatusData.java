package com.hwgi.plc.trafficlight.trafficStatusData;


import jakarta.annotation.PostConstruct;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrafficStatusData {
    boolean isWaiting;
    int     waitCnt;

    @PostConstruct
    public void init() {
      //초기화 코드 진행
      this.isWaiting = false;
      this.waitCnt = 0;
    }

}
