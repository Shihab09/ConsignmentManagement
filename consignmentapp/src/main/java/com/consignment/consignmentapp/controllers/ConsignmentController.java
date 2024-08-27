package com.consignment.consignmentapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.consignment.consignmentapp.dao.Consignment;
import com.consignment.consignmentapp.dao.ConsignmentRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class ConsignmentController {

    @Autowired
    ConsignmentRepository repo;
    
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/consignments")
    public List<Consignment> getAllConsignments(){
        return (List<Consignment>) repo.findAll();
    }

    @PostMapping("/consignment")
    public Consignment saveConsignment(@RequestBody Consignment consignment){
        return repo.save(consignment);

    }

    @PutMapping("consignment/{id}")
    public Consignment updateConsignment(@PathVariable Integer id, @RequestBody Consignment consignment) {
        
        Consignment existing=repo.findById(id).orElseThrow(()-> new RuntimeException("Consignment not Found"+id));

        existing.setConsignmentStatus(consignment.getConsignmentStatus()!=null?consignment.getConsignmentStatus():existing.getConsignmentStatus());
        existing.setConsignmentCategory(consignment.getConsignmentCategory()!=null?consignment.getConsignmentCategory():existing.getConsignmentCategory());
        existing.setDeliveryPartner(consignment.getDeliveryPartner()!=null?consignment.getDeliveryPartner():existing.getDeliveryPartner());

        repo.save(existing);
        return existing;
    }

    @GetMapping("/consignment/{id}")
    public Consignment getConsignmentById(@PathVariable Integer id) {
        return repo.findById(id).orElseThrow(()-> new RuntimeException("Consignment not Found"+id));
    }

    @DeleteMapping("/consignment/{id}")
    public String deleteConsignment(@PathVariable Integer id){
        repo.deleteById(id);
        return "Consignment deleted"+id;
    }
    
}
