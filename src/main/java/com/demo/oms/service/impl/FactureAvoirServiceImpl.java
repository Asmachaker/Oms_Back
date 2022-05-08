package com.demo.oms.service.impl;

import com.demo.oms.dto.FactureDTO;
import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Client;
import com.demo.oms.entity.FactureAvoir;
import com.demo.oms.repository.ClientRepository;
import com.demo.oms.repository.FactureAvoirRepository;
import com.demo.oms.service.BordereauService;
import com.demo.oms.service.FactureAvoirService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class FactureAvoirServiceImpl implements FactureAvoirService {

    @Autowired
    FactureAvoirRepository factureAvoirRepository;

    @Autowired
    BordereauService bordereauService;

    @Autowired
    ClientRepository clientRepository;


    @Override
    public void addFactureAvoir(FactureAvoir factureAvoir) {
        factureAvoirRepository.save(factureAvoir);
    }

    @Override
    public FactureAvoir getFactureAvoir(Long id) {

        return factureAvoirRepository.findById(id).get();
    }

    @Override
    public List<FactureAvoir> getAllFactureAvoir() {
        return factureAvoirRepository.findAll();
    }

    @Override
    public void EnableFactureAvoir(Long id) {
        factureAvoirRepository.enableFactureAvoir(id);
    }

    @Override
    public Map<Float, Integer> generateFactureAvoir(List<Booking> bookings, Long id) {

        Bordereau bordereau = bordereauService.getBordereau(id);
        FactureAvoir factureAvoir = new FactureAvoir();
        factureAvoir.setClient(bordereau.getClient());
        factureAvoir.setStatut(false);
        LocalDate now = LocalDate.now();
        Date date = Date.valueOf(now);
        factureAvoir.setDate(date);
        factureAvoir.setBordereau(bordereau);
        Map<Float, Integer> tarifMap = new HashMap<>();

        for (Booking booking : bookings) {
            float tarif = booking.getTarif().getPrice();
            if (tarifMap.containsKey(tarif)) {
                int value = tarifMap.get(tarif);
                tarifMap.put(tarif, value + 1);
            } else {
                tarifMap.putIfAbsent(tarif, 1);
            }
        }
        return tarifMap;
}

    @Override
    public List<FactureDTO> generateList(Map<Float, Integer> tarifMap){

        List<FactureDTO> list = new ArrayList<FactureDTO>();
        for(Map.Entry<Float, Integer> entry : tarifMap.entrySet()) {
            Float prix = entry.getKey();
            Integer quantity = entry.getValue();
            Float total =prix * quantity;
            String name = prix.toString();
            
            FactureDTO facture = new FactureDTO(name,quantity,prix,total);
            list.add(facture);
        }
        return list;
      }

      public Double calculTotal(List<FactureDTO> list)
      {Double totalHT= 0.00;
          for (FactureDTO element : list) {
              totalHT=totalHT+element.getTotal();
          }

         return totalHT;
      }
      @Override
    public String exportReport(List<Booking> bookings, Long id) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Asma\\Desktop\\Facture";
        Map<Float, Integer> tarifList = generateFactureAvoir(bookings,id);
        Bordereau bordereau = bordereauService.getBordereau(id);
        Client client = clientRepository.findById(bordereau.getClient().getId()).get();
        List<FactureDTO> list = generateList(tarifList);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:JasperReport/Facture.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> parameters = new HashMap<>();

        Double totalHT = calculTotal(list);
          Double totalTva =(totalHT*19)/100;
          Double total = totalTva+totalHT;


        Date date = Date.valueOf(LocalDate.now());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String Date= formatter.format(date);
        parameters.put("date", Date );
        parameters.put("clientAdress",client.getWorning());
        parameters.put("clientNumber",client.getPhoneNumber());
        parameters.put("clientMatricule",client.getTaxNumber());
        parameters.put("clientName",client.getWorning());
        parameters.put("totalHT",totalHT);
        parameters.put("Remise",0.0);
        parameters.put("totalHtRemise",totalHT);
        parameters.put("TVA",totalTva);
        parameters.put("total",total);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
          String DateClient= formatter.format(bordereau.getDate());

            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\"+client.getWorning()+"_"+DateClient+".pdf");


        return "report generated in path : " + path;
    }

}