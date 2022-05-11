package com.demo.oms.service.impl;


import com.demo.oms.dto.FactureDTO;
import com.demo.oms.entity.*;
import com.demo.oms.repository.ClientRepository;
import com.demo.oms.repository.FactureAvoirRepository;
import com.demo.oms.repository.FactureRepository;
import com.demo.oms.service.FactureService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FactureServiceImpl implements FactureService {

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    FactureAvoirRepository factureAvoirRepository;

    @Override
    public void addFacture(Facture facture) {
        factureRepository.save(facture);
    }

    @Override
    public List<Facture> getAllFacture() {
        return factureRepository.findAll();
    }

    @Override
    public Facture getFacture(Long id) {

        return factureRepository.findById(id).get();
    }

    @Override
    public void EnableFacture(Long id) {
        factureRepository.enableFacture(id);
    }

    public Map<Float, Integer> generateFacture(Bordereau bordereau) {

        Facture facture = new Facture();
        facture.setClient(bordereau.getClient());
        facture.setStatut(false);
        Date date = Date.valueOf(LocalDate.now());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String Date= formatter.format(date);
        facture.setDate(date);
        facture.setBordereau(bordereau);
        facture.setName("F"+bordereau.getClient().getWorning()+"_"+Date);
        factureRepository.save(facture);
        Map<Float, Integer> tarifMap = new HashMap<>();
        List <Booking> bookings = bordereau.getBooking();
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

    public Double calculTotal(List<FactureDTO> list)
    {Double totalHT= 0.00;
        for (FactureDTO element : list) {
            totalHT=totalHT+element.getTotal();
        }

        return totalHT;
    }


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

    @Override
    public void genererAllFacture(List<Bordereau> borList) throws JRException, FileNotFoundException {
        for( Bordereau e :borList) {
            exportReport(e);
        }

    }

    @Override
    public void exportReport(Bordereau bordereau) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Asma\\Desktop\\PFE\\ngx-admin-master\\src\\assets\\documents\\facture";
        Map<Float, Integer> tarifList = generateFacture(bordereau);
        Client client =bordereau.getClient();
        Facture facture = factureRepository.getFactureByBordereau(bordereau);
        List<FactureDTO> list = generateList(tarifList);

        Double totalHT = calculTotal(list);
        Double totalTva =(totalHT*19)/100;
        Double total = totalTva+totalHT;
        facture.setAmount(totalHT);
        Date date = Date.valueOf(LocalDate.now());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String Date= formatter.format(date);

        File file = ResourceUtils.getFile("classpath:JasperReport/Facture.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("date", Date );
        parameters.put("clientAdress",client.getWorning());
        parameters.put("clientNumber",client.getPhoneNumber());
        parameters.put("clientMatricule",client.getTaxNumber());
        parameters.put("clientName",client.getWorning());
        parameters.put("facture","Facture N°");
        parameters.put("totalHT",totalHT);
        parameters.put("Remise",0.0);
        parameters.put("totalHtRemise",totalHT);
        parameters.put("TVA",totalTva);
        parameters.put("total",total);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String DateClient= formatter.format(bordereau.getDate());
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\"+facture.getName()+".pdf");


    }

}