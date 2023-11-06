package com.example.invoicesystem.controller;

import com.example.invoicesystem.exception.CustomerNotFoundException;
import com.example.invoicesystem.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/{customerID}")
    public ResponseEntity<Map<Long, String>> getInvoicesByCustomerID(@PathVariable Long customerID) {
        try {
            Map<Long, String> invoices = invoiceService.getInvoicesByCustomerID(customerID);
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
