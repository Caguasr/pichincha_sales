package com.pichincha.sales.controller;


import com.pichincha.sales.entity.OrderEntity;
import com.pichincha.sales.entity.ProductEntity;
import com.pichincha.sales.entity.SupplierEntity;
import com.pichincha.sales.serviceImpl.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000/"})
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/order")
    private ResponseEntity<?> create(@Valid @RequestBody OrderEntity order, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        log.info("Request to create order" + new Date());
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> "The field " + err.getField() + " "+err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            orderService.create(order);

        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Order created successfully");
        response.put("data",order);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/order/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Request to delete the order" + id + ": " + new Date());
            orderService.deleteById(id);
            response.put("message", "Order has been delete");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
