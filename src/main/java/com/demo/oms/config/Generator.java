package com.demo.oms.config;

import com.demo.oms.service.BordereauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Generator {
//
//    @Autowired
//    private BordereauService bordereauService;
//
//    @Scheduled(cron = "0 1 2 0 * ?")
//    public void bordereauGenerator() {
//        bordereauService.generateBordereau();
//    }
}
