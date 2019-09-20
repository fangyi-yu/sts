package com.sts.stock.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fangyi on 2019/9/20
 */
@Slf4j
@Component
@EnableScheduling
public class ScheduledTasks {

    //    @Scheduled(cron = "0 5 15 ? * 1-5") //周一到周五，下午三点零五分触发 或 "0 5 15 ? * MON-FRI"
    public void pullCompany() throws Exception {
        //1.为了避免节假日，所以要验证数据日期是否为当前日期
        log.info("Scheduling Tasks pullCompany(): The time is now " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}
