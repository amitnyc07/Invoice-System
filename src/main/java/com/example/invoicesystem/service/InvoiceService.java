package com.example.invoicesystem.service;

import com.example.invoicesystem.exception.CustomerNotFoundException;
import com.example.invoicesystem.model.Invoice;
import com.example.invoicesystem.repository.InvoiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ObjectMapper objectMapper;

    public Map<Long, String> getInvoicesByCustomerID(Long customerID) {
        List<Invoice> invoices = invoiceRepository.findByCustomerID(customerID);
        if (!invoices.isEmpty()) {
            Map<Long, String> result = new HashMap<>();
            for (Invoice invoice : invoices) {
                result.put(invoice.getInvoiceID(), extractTypeFromInvoiceData(invoice.getInvoiceData()));
            }
            return result;
        } else {
            throw new CustomerNotFoundException("Customer with ID " + customerID + " not found");
        }
    }

    private String extractTypeFromInvoiceData(String json) {
        try {
            Map<String, Object> data = objectMapper.readValue(json, Map.class);
            Object tenderDetails = data.get("tenderDetails");
            if (tenderDetails instanceof Map) {
                Object type = ((Map<?, ?>) tenderDetails).get("type");
                if (type instanceof String) {
                    return (String) type;
                }
            }
        } catch (IOException e) {
            return "Error parsing invoice data";
        }

        return "Type not found in invoice data";
    }
}