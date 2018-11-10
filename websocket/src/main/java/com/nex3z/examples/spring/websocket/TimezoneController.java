package com.nex3z.examples.spring.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

@Controller
public class TimezoneController {

    @MessageMapping("/timezone")
    @SendTo("/topic/time")
    public TimeMsg getTime(TimezoneMsg msg) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timezone = msg.getTimezone();
        if (timezone != null) {
            sdf.setTimeZone(TimeZone.getTimeZone(timezone));
        } else {
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        String time = sdf.format(calendar.getTime());
        return new TimeMsg(time);
    }

}
