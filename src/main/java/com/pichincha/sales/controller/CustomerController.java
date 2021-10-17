package com.pichincha.sales.controller;

import com.pichincha.sales.entity.CustomerEntity;
import com.pichincha.sales.serviceImpl.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Log4j2
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000/"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    private ResponseEntity<?> getById() {
        Map<String, Object> response = new HashMap<>();
        List<CustomerEntity> customer = new ArrayList<>();
        try {
            log.info("Request to find customer" + new Date());
            customer = customerService.getAll();

        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<CustomerEntity>>(customer, HttpStatus.OK);
    }
}
