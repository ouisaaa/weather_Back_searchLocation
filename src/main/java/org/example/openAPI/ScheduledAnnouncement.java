package org.example.openAPI;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//규칙적으로 최신 일기예보 발표시간을 저장하는 컴포넌트
@Slf4j
@Component
@Getter
public class ScheduledAnnouncement {
    private String announcementTime;
    private String announcenmentDate=LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    public ScheduledAnnouncement(){
        if(LocalTime.now().getMinute()<45){
            if(LocalTime.now().getHour()==0){
                announcenmentDate = (LocalDate.now().minusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE));
            }
            announcementTime = (LocalTime.now().minusHours(1)).getHour() +"00";   //minusHours -> 한시간 뒤
        }else if(LocalTime.now().getHour()==0) {
            announcementTime="0000";
        }else{
            announcementTime=LocalTime.now().getHour()+"00";
        }
        log.info("시간 체크를 시작합니다. 현제 설정된 값: "+announcementTime);
    }
    //주기적으로 매시간 45분에 실행해 최신 발표 시간을 업데이트를 한다.
    @Scheduled(cron="0 40 * * * *")
    public void updateTime(){
        if(LocalTime.now().getHour()==01){
            announcenmentDate=LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        }
        announcementTime=LocalTime.now().getHour()+"00";
        log.info("일기예보 발표시간이 업데이트되었습니다. 업데이트 시간: "+announcementTime);
    }


}
