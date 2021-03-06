package com.pichincha.sales.controller;


import com.pichincha.sales.entity.OrderEntity;
import com.pichincha.sales.serviceImpl.OrderServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000/"})
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @GetMapping("/order/{since}/{until}")
    private ResponseEntity<?> getOrderByDate(@PathVariable String since, @PathVariable String until) {
        Map<String, Object> response = new HashMap<>();
        List<OrderEntity> orders = new ArrayList<>();
        try {
            log.info("Request to find orders  by Date " + since + "-" + until + new Date());
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
            Date dateSince = formatterDate.parse(since);
            Date dateUntil = formatterDate.parse(until);
            java.sql.Date toSince = new java.sql.Date(dateSince.getTime());
            java.sql.Date toUntil = new java.sql.Date(dateUntil.getTime());

            orders = orderServiceImpl.getByDate(toSince, toUntil);

        } catch (DataAccessException | ParseException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<OrderEntity>>(orders, HttpStatus.OK);
    }

    @PostMapping("/order")
    private ResponseEntity<?> create(@Valid @RequestBody OrderEntity order, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        log.info("Request to create order" + new Date());
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> "The field " + err.getField() + " " + err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            orderServiceImpl.create(order);

        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Order created successfully");
        response.put("data", order);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/order/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Request to delete the order" + id + ": " + new Date());
            orderServiceImpl.deleteById(id);
            response.put("message", "Order has been delete");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
