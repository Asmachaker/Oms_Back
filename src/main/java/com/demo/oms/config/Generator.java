package com.demo.oms.config;

import com.demo.oms.entity.Bordereau;
import com.demo.oms.repository.BordereauRepository;
import com.demo.oms.service.BordereauService;
import com.demo.oms.service.FactureService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class Generator {

    @Autowired
    private BordereauService bordereauService;

    @Autowired
    private FactureService factureService;

    @Autowired
    private BordereauRepository bordereauRepo;


//    @Scheduled(cron = "0 2 1 * * ?")
//    public void bordereauGenerator() {
//       List<Bordereau> bordereau = bordereauService.generateBordereau();
//    }
//
//    @Scheduled(cron = "0 3 1 * * ?")
//    public void factureGenerator() throws JRException, FileNotFoundException {
// Date now = Date.valueOf(LocalDate.now());
//        List <Bordereau> bordereaus =bordereauRepo.getBordereauBydate(now);
//        factureService.genererAllFacture(bordereaus);

 //   }
}
