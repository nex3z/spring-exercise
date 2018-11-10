package com.nex3z.examples.spring.websocket;

import lombok.Data;

@Data
public class TimeMsg {

    private String time;

    public TimeMsg(String time) {
        this.time = time;
    }

}
