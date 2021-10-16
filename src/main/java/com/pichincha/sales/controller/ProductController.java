package com.pichincha.sales.controller;

import com.pichincha.sales.entity.ProductEntity;
import com.pichincha.sales.entity.SupplierEntity;
import com.pichincha.sales.serviceImpl.ProductServiceImpl;
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
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private  SupplierServiceImpl supplierService;

    @PostMapping("/product")
    private ResponseEntity<?> create(@Valid @RequestBody ProductEntity product, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> "The field " + err.getField() + " "+err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {

                productService.save(product);

        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Product created successfully");
        response.put("data", product);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductEntity product) {
        Map<String, Object> response = new HashMap<>();
        ProductEntity updProduct = null;
        try {

            updProduct = productService.getById(id);

            if (updProduct == null) {
                response.put("message", "Product not found");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            if (product.getName() != null) {
                updProduct.setName(product.getName());
            }
            if (product.getPrice() != null) {
                updProduct.setPrice(product.getPrice());
            }
            if (product.getStock() != null) {
                updProduct.setStock(product.getStock());
            }
            updProduct.setUpdateAt(product.setDateUpdate());
            productService.save(updProduct);
            response.put("message", "Product update successfully");
            response.put("data", updProduct);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
           productService.delete(id);
            response.put("message", "Product has been delete");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", "Error processing transaction");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
