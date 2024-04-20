package com.hwgi.plc.trafficlight.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrafficStatusData {
    boolean isWaiting;
    int     waitCnt;

}
