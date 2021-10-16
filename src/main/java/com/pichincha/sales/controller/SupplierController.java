package com.pichincha.sales.controller;


import com.pichincha.sales.entity.SupplierEntity;
import com.pichincha.sales.serviceImpl.SupplierServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api")
@CrossOrigin()
public class SupplierController {

    @Autowired
    private SupplierServiceImpl supplierService;


    @GetMapping("/supplier/{id}")
    private ResponseEntity<?> getById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        SupplierEntity supplier = null;
        try {
            log.info("------------------" + id + "-------------------------");
            supplier = supplierService.getById(id);
            if (supplier == null) {
                response.put("message", "Supplier not found");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<SupplierEntity>(supplier, HttpStatus.OK);
    }

    @GetMapping("/supplier")
    private ResponseEntity<?> getById() {
        Map<String, Object> response = new HashMap<>();
        List<SupplierEntity> supplier = new ArrayList<>();
        try {
            supplier = supplierService.getAll();

        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<SupplierEntity>>(supplier, HttpStatus.OK);
    }

    @PostMapping("/supplier")
    private ResponseEntity<?> create(@Valid @RequestBody SupplierEntity supplier, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> "The field " + err.getField() + " "+err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            SupplierEntity supplierExists = supplierService.alreadyUserCreate(supplier.getRuc());
            if (supplierExists != null) {
                supplierExists.setActive(1);
                supplierExists.setName(supplier.getName());
                supplierExists.setUpdateAt(supplier.setDateUpdate());
                supplierService.save(supplierExists);
                supplier = supplierExists;
            }else{
                supplierService.save(supplier);
            }
        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "User created successfully");
        response.put("data", supplier);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/supplier/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody SupplierEntity supplier) {
        Map<String, Object> response = new HashMap<>();
        SupplierEntity oldSupplier = null;
        try {

            oldSupplier = supplierService.getById(id);

            if (oldSupplier == null) {
                response.put("message", "Supplier not found");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            if (supplier.getName() != null) {
                oldSupplier.setName(supplier.getName());
            }
            if (supplier.getRuc() != null) {
                oldSupplier.setRuc(supplier.getRuc());
            }
            oldSupplier.setUpdateAt(supplier.setDateUpdate());
            supplierService.save(oldSupplier);
            response.put("message", "User update successfully");
            response.put("data", oldSupplier);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/supplier/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            SupplierEntity supplier = supplierService.getById(id);
            if (supplier == null) {
                response.put("message", "Supplier not found");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            supplier.setUpdateAt(supplier.setDateUpdate());
            supplier.setActive(0);
            supplierService.save(supplier);
            response.put("message", "Supplier has been delete");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
